package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.entity.CoffeeOrderCoffeeType;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.CoffeeOrderCoffeeTypeRepository;
import com.global.coffeeshop.service.CoffeeOrderCoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeOrderCoffeeTypeServiceImpl implements CoffeeOrderCoffeeTypeService {

    @Autowired
    CoffeeOrderCoffeeTypeRepository coffeeOrderCoffeeTypeRepository;

    @Override
    public List<CoffeeOrderCoffeeType> createBulk(List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList) {
        return coffeeOrderCoffeeTypeRepository.saveAll(coffeeOrderCoffeeTypeList);
    }

    @Override
    public boolean deleteBulk(List<Long> idList) throws CoffeeShopCustomException {
        //TODO - better method for deleteing items.
        for (Long id : idList) delete(id);
        return true;
    }

    @Override
    public boolean delete(Long id) throws CoffeeShopCustomException {

        if (!isExistById(id)) {
            logger.info("Order id not found for get order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for get order operation");
        }

        Optional<CoffeeOrderCoffeeType> coffeeOrderCoffeeType = coffeeOrderCoffeeTypeRepository.findById(id);

        if (coffeeOrderCoffeeType.isPresent()) {
            CoffeeOrderCoffeeType coffeeOrderCoffeeType1 = coffeeOrderCoffeeType.get();
            coffeeOrderCoffeeType1.setDeleted(true);
            coffeeOrderCoffeeTypeRepository.save(coffeeOrderCoffeeType1);
        }
        return true;
    }

    private boolean isExistById(Long id) {
        return coffeeOrderCoffeeTypeRepository.existsById(id);
    }

}
