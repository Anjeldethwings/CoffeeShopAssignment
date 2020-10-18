package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.respository.CoffeeTypeRepository;
import com.global.coffeeshop.service.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeTypeServiceImpl implements CoffeeTypeService {

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    @Override
    public boolean isExistByCoffeeType(Long id) {
        return coffeeTypeRepository.existsById(id);
    }
}
