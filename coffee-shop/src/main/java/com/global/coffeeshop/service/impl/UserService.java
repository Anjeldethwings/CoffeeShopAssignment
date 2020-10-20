package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.request.UserDto;
import com.global.coffeeshop.entity.Role;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.UserRepository;
import com.global.coffeeshop.service.CommonService;
import com.global.coffeeshop.service.CoffeeUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements CoffeeUserService,UserDetailsService, CommonService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoffeeUserService coffeeUserService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try {
            authorities.add(new SimpleGrantedAuthority(coffeeUserService.getUserByUserName(s).getRole().getName()));
            return new User(coffeeUserService.getUserByUserName(s).getName(), passwordEncoder.encode("admin"), authorities);
        } catch (CoffeeShopCustomException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public boolean isExistByUserId(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        com.global.coffeeshop.entity.User user = new com.global.coffeeshop.entity.User();
        modelMapper.map(userDto, user);

        Role role = new Role();
        role.setId(userDto.getRoleId());
        user.setRole(role);

        com.global.coffeeshop.entity.User userCreated = userRepository.save(user);
        userDto.setId(userCreated.getId());

        return userDto;
    }

    @Override
    public com.global.coffeeshop.entity.User getUserFromContext() throws CoffeeShopCustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return getUserByUserName(username);
    }

    @Override
    public com.global.coffeeshop.entity.User getUserByUserName(String username) throws CoffeeShopCustomException {

        if (username.isEmpty()) {
            logger.error("Invalid username");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Invalid username");
        }
        return userRepository.getUserByName(username);
    }

    @Override
    public boolean validateUser(Long userId) throws CoffeeShopCustomException {
        com.global.coffeeshop.entity.User user = coffeeUserService.getUserFromContext();
        if (!userId.equals(user.getId())) {
            logger.error("Authorization error for user id : {}", userId );
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Authorization error for user id : " + userId);
        }
        return true;
    }
}
