package com.global.coffeeshop.service.impl.security;

import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.service.CommonService;
import com.global.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeUserDetailServiceImpl implements UserDetailsService, CommonService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Admin"));
        try {
            return new User(userService.getUserByUserName(s).getName(), passwordEncoder.encode("admin"), authorities);
        } catch (CoffeeShopCustomException e) {
            //TODO - handle the error
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

}
