package com.global.coffeeshop.service.impl.security;

import com.global.coffeeshop.controller.dto.request.LoginDto;
import com.global.coffeeshop.controller.dto.response.AuthenticationResDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.service.AuthService;
import com.global.coffeeshop.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService coffeeUserDetailServiceImpl;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
    public AuthenticationResDto authenticationCheck(LoginDto loginDto) throws CoffeeShopCustomException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for login request");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Invalid credentials for login request");
        }

        final UserDetails userDetails = coffeeUserDetailServiceImpl.loadUserByUsername(loginDto.getUserName());

        final String jwt = jwtUtilService.generateToken(userDetails);

        return new AuthenticationResDto(jwt);

    }


}
