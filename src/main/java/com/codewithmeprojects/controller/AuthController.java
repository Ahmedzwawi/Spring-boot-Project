package com.codewithmeprojects.controller;


import com.codewithmeprojects.dto.AuthenticationRequest;
import com.codewithmeprojects.dto.AuthenticationResponse;
import com.codewithmeprojects.dto.SignUpRequest;
import com.codewithmeprojects.dto.userDto;
import com.codewithmeprojects.entity.user;
import com.codewithmeprojects.repository.UserRepository;
import com.codewithmeprojects.services.auth.AuthService;
import com.codewithmeprojects.services.auth.jwt.UserService;
import com.codewithmeprojects.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private final AuthService authService;

    @Autowired
    private final AuthenticationManager authenticationManager ;

    @Autowired
    private final UserService userService ;

    @Autowired
    private final JWTUtil jwtUtil;

    @Autowired
    private final UserRepository userRepository ;


    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signupRequest){
       if(authService.hasCustomerWithEmail(signupRequest.getEmail()))
           return new ResponseEntity<>("Customer " +
                   " exests",HttpStatus.NOT_ACCEPTABLE);
      userDto createdCustomerDto= authService.createCustomer(signupRequest);
      if(createdCustomerDto==null)
          return  new ResponseEntity<>("customer not created", HttpStatus.BAD_REQUEST);
      else
          return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException ,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect password or name .");

        }
        final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<user> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse ;
    }
    @RequestMapping(value="/alluser",method=RequestMethod.GET )

    //@PreAuthorize("hasAuthority('Admin')")
    public List<user> getallUsers(){
        return userRepository.findAll();
    }



}



