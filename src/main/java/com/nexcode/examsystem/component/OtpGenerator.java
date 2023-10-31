package com.nexcode.examsystem.component;

import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.model.exception.AppException;
import com.nexcode.examsystem.model.exception.BadRequestException;
import com.nexcode.examsystem.repository.UserRepository;

@Component
public class OtpGenerator {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public boolean validateOtp(String email,String otp)
	{
		User foundedUser = userRepository.findByEmail(email).orElseThrow(()->new BadRequestException("User Not Found : email->"+email));
		
		if(foundedUser!=null)
		{
			Date otpExpirationTime=foundedUser.getOtpExpirationTime();
			String existingOtp=foundedUser.getOtp();
			System.out.println("Existing OTP "+foundedUser.getOtp());
			if(existingOtp.equals(otp))
			{
				if(!isOtpExpired(otpExpirationTime))
				{
					return true;
				}
				throw new AppException("otp expired");
			}
			throw new AppException("otps are not matched!!");
		}
		return false;
	}
	public String generateOTP() {
		Random random = new Random();
	    
	    String sixDigitOTP = IntStream.range(0, 6)
	        .mapToObj(i -> String.valueOf(random.nextInt(10)))
	        .collect(Collectors.joining());
	    
	    return sixDigitOTP;
    }
	public boolean isOtpExpired(Date otpExpirationTime)
	{
		if(otpExpirationTime!=null)
		{
			Date currentTime=new Date();
			return currentTime.after(otpExpirationTime);
		}
		return false;
	}

}
