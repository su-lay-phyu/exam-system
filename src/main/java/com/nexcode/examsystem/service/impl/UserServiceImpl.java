package com.nexcode.examsystem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nexcode.examsystem.component.EmailComponent;
import com.nexcode.examsystem.component.OtpGenerator;
import com.nexcode.examsystem.mapper.CourseMapper;
import com.nexcode.examsystem.mapper.RoleMapper;
import com.nexcode.examsystem.mapper.UserMapper;
import com.nexcode.examsystem.model.dtos.CourseDto;
import com.nexcode.examsystem.model.dtos.UserDto;
import com.nexcode.examsystem.model.entities.Course;
import com.nexcode.examsystem.model.entities.Role;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.model.exception.NotFoundException;
import com.nexcode.examsystem.model.requests.UserRequest;
import com.nexcode.examsystem.repository.CourseRepository;
import com.nexcode.examsystem.repository.ExamRepository;
import com.nexcode.examsystem.repository.RoleRepository;
import com.nexcode.examsystem.repository.UserRepository;
import com.nexcode.examsystem.service.UserService;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final CourseMapper courseMapper;
	
	
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final CourseRepository courseRepository;
	private final ExamRepository examRepository;
	

	private final PasswordEncoder passwordEncoder;

	private final EmailComponent emailSender;

	private final OtpGenerator otpGenerator;

	
	@Override
	public List<UserDto> getAllUser() {
		return userMapper.toDtoList(userRepository.findAllUserWithCategories());
	}
	@Override
	public List<CourseDto> getAllCategoryByUser(String email) 
	{
		User foundedUser=userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User not found"));
		List<Course>courses=userRepository.findAllCourseWithUserEmail(foundedUser.getId());
		List<CourseDto>dtos=new ArrayList<>();
		for(Course c:courses)
		{
			CourseDto dto=new CourseDto();
			dto.setId(c.getId());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			Long percentage=examRepository.getPercentage(c.getId());
			dto.setPercentage(percentage);
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	@Transactional(rollbackOn = Exception.class)
	public boolean signUpUser(UserRequest request) {
		User user = new User();
			Integer maxRollNo = userRepository.findMaxRollNoForStudents();
	        String nextRollNo;
			if (maxRollNo == null) {
	            nextRollNo = "stu0001"; 
	        } else {
	            nextRollNo = String.format("stu%04d", maxRollNo + 1);
	        }
			user.setRollNo(nextRollNo);
			user.setUsername(request.getUsername());
			user.setEmail(request.getEmail());
			String password = RandomString.make(8);
			String encodedPassword = passwordEncoder.encode(password);			
			user.setPassword(encodedPassword);
			user.setPhone(request.getPhone());
			user.setIsPasswordChanged(false);
			user.setDisable(false);
			List<Role> roles = new ArrayList<>();
			Role userRole = roleRepository.findByName("USER").orElse(null);
			roles.add(userRole);
			user.setRoles(roles);
			List<Long>ids=request.getCourses();
			List<Course>courses=ids.stream()
					.map(id->courseRepository.findById(id)
						.orElseThrow(()->new BadRequestException("Category can't found")))
						.collect(Collectors.toList());
			user.setCourses(courses);
			User savedUser=userRepository.save(user);
			UserDto userInfo=new UserDto(savedUser.getRollNo(),
					savedUser.getUsername(),
					savedUser.getEmail(),
					password,roleMapper.toDtoList(savedUser.getRoles()),
					courseMapper.toDtoList(savedUser.getCourses()));
			if (!sendSignUpVerifiedStudent(userInfo)) {
				throw new AppException("This err is happened in signupUser function");
			}
			return true;
	}
	@Override
	public UserDto findByEmailAddress(String email) 
	{
		return userMapper.toDto(userRepository.findByEmail(email).orElse(null));
	}
	@Override
	public boolean generateOneTimePassword(UserDto userDto) {
		String otp = otpGenerator.generateOTP();
		String email = userDto.getEmail();
		User foundedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User Not Found : email->" + email));
		foundedUser.setOtp(otp);
		Date otpExpirationTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)); 																						// time
		foundedUser.setOtpExpirationTime(otpExpirationTime);
		userRepository.save(foundedUser);
		sendOTPEmail(foundedUser, otp);
		return true;
	}
	@Override
	public boolean changePassword(String email, String requestOldPassword, String requestNewPassword) {
		
		User foundedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User Not Found : email->" + email));
		if(!foundedUser.getIsPasswordChanged())
		{
			foundedUser.setIsPasswordChanged(true);
		}
		String oldDbPassword = foundedUser.getPassword();
		if (passwordEncoder.matches(requestOldPassword, oldDbPassword)) {
			String encodedNewPassword = passwordEncoder.encode(requestNewPassword);
			foundedUser.setPassword(encodedNewPassword);
			userRepository.save(foundedUser);
			return true;
		}
		throw new BadRequestException("The password are incorrect.");
	}

	@Override
	public boolean validateOtp(String email, String otp) {
		return otpGenerator.validateOtp(email, otp);
	}

	@Override
	public boolean setNewResetPassword(String email, String password) {
		User foundedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User Not Foun  d : email->" + email));
		if (foundedUser != null) {
			foundedUser.setPassword(passwordEncoder.encode(password));
			userRepository.save(foundedUser);
			return true;
		}
		return false;
	}
	
	public boolean sendSignUpVerifiedStudent(UserDto userDto) {
	    String to = userDto.getEmail();
	    
	    try {
	        String subject = "Welcome to YourApp - Account Information";
	        StringBuilder content = new StringBuilder("Hello " + userDto.getUsername() + ",<br><br>")
	                .append("Thank you for signing up at Our Language School. Here are your account details:<br>")
	                .append("<ul>")
	                .append("<li>Roll No: ").append(userDto.getRollNo()).append("</li>")
	                .append("<li>Username: ").append(userDto.getUsername()).append("</li>")
	                .append("<li>Email: ").append(userDto.getEmail()).append("</li>")
	                .append("<li>Temporary Password: ").append(userDto.getPassword()).append("</li>")
	                .append("<li>Courses:");

	        for (CourseDto category : userDto.getCategories()) 
	        {
	            content.append("<br>").append(" - ").append(category.getName());
	        }
	        content.append("</li>")
	                .append("</ul><br>")
	                .append("Thank you for being a part of our educational platform<br><br>")
	                .append("Best regards,<br>Eduzone Center");
	        String emailContent=content.toString();
	        emailSender.sendEmail(to, subject, emailContent);
	        return true;
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        throw new AppException("Email sending failed.");
	    }
	}
	@Override
	public boolean updateStudent(Long id, UserRequest request) {
	    try {
	        User foundedUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
	        String oldEmail = foundedUser.getEmail();
	        String password = RandomString.make(8);
			String encodedPassword = passwordEncoder.encode(password);	
	        foundedUser.setUsername(request.getUsername());
	        foundedUser.setEmail(request.getEmail());
	        foundedUser.setPhone(request.getPhone());
	        foundedUser.setPassword(encodedPassword);		
	        List<Long> ids = request.getCourses();
	        List<Course> courses = ids.stream()
	                .map(c -> courseRepository.findById(c).orElseThrow(() -> new NotFoundException("Course not found")))
	                .collect(Collectors.toList());
	        foundedUser.setCourses(courses);
	        
	        User savedUser = userRepository.save(foundedUser);
	  
	        if (!oldEmail.equals(request.getEmail())) {
	        	 UserDto userInfo=new UserDto(savedUser.getRollNo(),savedUser.getUsername(),savedUser.getEmail(),password,roleMapper.toDtoList(savedUser.getRoles()),courseMapper.toDtoList(savedUser.getCourses()));
	        	 return sendSignUpVerifiedStudent(userInfo);
	        }
	        return true;
	    } catch (AppException e) {
	        throw new AppException("An error occurred in the update function");
	    }
	}

	public boolean sendOTPEmail(User user, String Otp) {
		String to = user.getEmail();
		String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
		String content = "<p>Hello " + user.getUsername()+ "</p>"
				+ "<p>For security reason, you're required to use the following " + "One Time Password to login:</p>"
				+ "<p><b>" + Otp + "</b></p>" + "<br>" + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
		try {
			emailSender.sendEmail(to, subject, content);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new AppException("Email sending failed.");
		}
	}

	public boolean clearOTP(String email) {
		User foundedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User Not Found : email->" + email));
		foundedUser.setOtp(null);
		foundedUser.setOtpExpirationTime(null);
		userRepository.save(foundedUser);
		return true;
	}
	@Override
	public CourseDto findUserCourseById(String email, Long id) {
		User foundedUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new BadRequestException("User Not Found : email->" + email));
		Course course=courseRepository.findUserCourseById(foundedUser.getId(),id);
		return courseMapper.toDto(course);
	}


}
