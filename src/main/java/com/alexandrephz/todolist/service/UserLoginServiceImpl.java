package com.alexandrephz.todolist.service;

import com.alexandrephz.todolist.DTO.ApiResponseDto;
import com.alexandrephz.todolist.DTO.ApiResponseStatus;
import com.alexandrephz.todolist.DTO.SigninRequestDto;
import com.alexandrephz.todolist.exceptions.UserAlreadyExistsException;
import com.alexandrephz.todolist.exceptions.UserIncorrectUsernameOrPasswordException;
import com.alexandrephz.todolist.exceptions.UserNotFoundException;
import com.alexandrephz.todolist.exceptions.UserServiceLogicException;
import com.alexandrephz.todolist.repository.RoleRepository;
import com.alexandrephz.todolist.repository.UserRepository;
import com.alexandrephz.todolist.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Ensure password encoder is available

    @Override
    public ResponseEntity<ApiResponseDto<?>> signinUser(SigninRequestDto loginRequest)
            throws UserIncorrectUsernameOrPasswordException, UserNotFoundException, UserServiceLogicException {

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate the JWT token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Get the user details
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // Get the roles
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // Return the response with the JWT token and user details
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                        "User authenticated successfully" + jwt +  "," + roles + userDetails.getUsername()));
    }

    // Helper method to authenticate user using AuthenticationManager
    private Authentication authenticateUser(String username, String password)
            throws UserIncorrectUsernameOrPasswordException, UserNotFoundException {

        try {
            // This is where BCryptPasswordEncoder comes into play.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            // Ensure password matches with encoded password
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new UserIncorrectUsernameOrPasswordException("Invalid password");
            }

            return authentication;
        } catch (BadCredentialsException e) {
            throw new UserIncorrectUsernameOrPasswordException("Invalid username or password");
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
    }
}