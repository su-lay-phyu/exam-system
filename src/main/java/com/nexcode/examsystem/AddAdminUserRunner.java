package com.nexcode.examsystem;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.nexcode.examsystem.model.entities.Level;
import com.nexcode.examsystem.model.entities.LevelName;
import com.nexcode.examsystem.model.entities.Role;
import com.nexcode.examsystem.model.entities.RoleName;
import com.nexcode.examsystem.model.entities.User;
import com.nexcode.examsystem.repository.LevelRepository;
import com.nexcode.examsystem.repository.RoleRepository;
import com.nexcode.examsystem.repository.UserRepository;

@Component
public class AddAdminUserRunner implements CommandLineRunner {

	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LevelRepository levelRepository;
    private final PasswordEncoder encoder;
    
    public AddAdminUserRunner(UserRepository userRepository, RoleRepository roleRepository,
			LevelRepository levelRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.levelRepository = levelRepository;
		this.encoder = encoder;
	}
    
    @Override
    public void run(String... args) throws Exception {
    	for (RoleName roleEnum : RoleName.values()) {
            String roleName = roleEnum.name();
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }

        for (LevelName levelEnum : LevelName.values()) {
            String levelName = levelEnum.name();
            if (!levelRepository.existsByName(levelName)) {
                Level level = new Level();
                level.setName(levelName);
                levelRepository.save(level);
            }
        }
        Optional<User> existingAdminOptional = userRepository.findByEmail("eduzone2023.mm@gmail.com");
    	 if (existingAdminOptional.isEmpty()) 
    	 {
        	 User adminUser = new User();
             adminUser.setUsername("Admin");
             adminUser.setEmail("eduzone2023.mm@gmail.com");
             adminUser.setPassword(encoder.encode("admin123"));
             adminUser.setPhone("+95971452639");
             adminUser.setPasswordChanged(false);
             Role admin=roleRepository.findByName(RoleName.ADMIN.name()).orElse(null);
             if(admin!=null)
             {
             	 adminUser.setRoles(Arrays.asList(admin));
                  userRepository.save(adminUser);
             }
         }
         
       
    }
}
