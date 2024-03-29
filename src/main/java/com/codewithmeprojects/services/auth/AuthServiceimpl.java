package com.codewithmeprojects.services.auth;


import com.codewithmeprojects.dto.SignUpRequest;
import com.codewithmeprojects.dto.userDto;
import com.codewithmeprojects.entity.user;
import com.codewithmeprojects.enums.userProfil;
import com.codewithmeprojects.enums.userRole;
import com.codewithmeprojects.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service

public class AuthServiceimpl implements AuthService{

    private final UserRepository userRepository ;


    public AuthServiceimpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount(){
        user adminAccount = userRepository.findByUserRole(userRole.Admin);
        if(adminAccount== null)
        {
            user newAdminAccount =new user ();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setUserRole(userRole.Admin);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created ");

        }
    }
    @Override
    public userDto createCustomer(SignUpRequest SignUpRequest) {
        user user=new user();
        user.setName(SignUpRequest.getName());
        user.setEmail(SignUpRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(SignUpRequest.getPassword()));
        user.setUserRole(userRole.Customer);
        user createdUser=userRepository.save(user);
        userDto userDto=new userDto();
        userDto.setId(createdUser.getId());
        return userDto;

    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();

    }
}
