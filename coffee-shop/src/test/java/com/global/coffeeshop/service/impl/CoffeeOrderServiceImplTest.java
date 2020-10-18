package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.entity.*;
import com.global.coffeeshop.respository.*;
import com.global.coffeeshop.service.CoffeeOrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeeOrderServiceImplTest {

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private CoffeeShopRespository coffeeShopRespository;

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderQueueRepository orderQueueRepository;

    @Autowired
    private CoffeeOrderCoffeeTypeRepository coffeeOrderCoffeeTypeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setCoffeeShop(){
        //create coffee shop
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setName("Coffee Shop 1");
        coffeeShop.setPhone(0342257553);

        CoffeeShop coffeeShopcreated = coffeeShopRespository.save(coffeeShop);

        CoffeeShop coffeeShop2 = new CoffeeShop();
        coffeeShop2.setName("Coffee Shop 2");
        coffeeShop2.setPhone(0342257553);

        CoffeeShop coffeeShopcreated2 = coffeeShopRespository.save(coffeeShop2);

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.setCoffeeShop(coffeeShopcreated);
        orderQueueRepository.save(orderQueue);

        OrderQueue orderQueue1 = new OrderQueue();
        orderQueue1.setCoffeeShop(coffeeShopcreated);
        orderQueueRepository.save(orderQueue1);

        OrderQueue orderQueue3 = new OrderQueue();
        orderQueue3.setCoffeeShop(coffeeShopcreated2);
        orderQueueRepository.save(orderQueue3);

        OrderQueue orderQueue4 = new OrderQueue();
        orderQueue4.setCoffeeShop(coffeeShopcreated);
        orderQueueRepository.save(orderQueue4);
    }

    @Test
    void setCoffeeTypes(){
        //save coffee types
        String[] coffeeArr = {"Espresso", "Americano", "Macchiato"};

        for(int i = 0 ; i < 3 ; i++){
            CoffeeType coffeeType = new CoffeeType();
            coffeeType.setName(coffeeArr[i]);
            coffeeTypeRepository.save(coffeeType);
        }
    }

    @Test
    void setMenu(){
        //create menu
        Menu menu = new Menu();
        CoffeeShop coffeeShopCreated = new CoffeeShop();
        coffeeShopCreated.setId((long) 1);
        menu.setCoffeeShop(coffeeShopCreated);
        menu.setName("menu 1");

        CoffeeType type1 = new CoffeeType();
        type1.setId((long) 1);
        CoffeeType type2 = new CoffeeType();
        type2.setId((long) 2);

        List<CoffeeType> coffeeTypeList = new ArrayList<>();
        coffeeTypeList.add(type1);
        coffeeTypeList.add(type2);

        menu.setCoffeeTypeList(coffeeTypeList);

        menuRepository.save(menu);
    }

    @Test
    void setUsers(){
        Role role = new Role();
        role.setName("Admin");
        roleRepository.save(role);

        Role role1 = new Role();
        role1.setName("Customer");
        roleRepository.save(role1);

        User user = new User();
        user.setName("Amila");
        user.setEmail("amila.indrajith.ukwattage@gmail.com");
        user.setMobile("0775455047");

        userRepository.save(user);
    }

    @Test
    void setOrder(){

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.setId((long) 2);

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setOrderQueue(orderQueue);
        coffeeOrderRepository.save(coffeeOrder);


        CoffeeType coffeeType = new CoffeeType();
        coffeeType.setId((long) 1);

        CoffeeType coffeeType1 = new CoffeeType();
        coffeeType1.setId((long) 2);

        CoffeeOrderCoffeeType coffeeOrderCoffeeType1 = new CoffeeOrderCoffeeType();
        coffeeOrderCoffeeType1.setCoffeeType(coffeeType1);
        CoffeeOrder coffeeOrder1 = new CoffeeOrder();
        coffeeOrder1.setId((long) 3);
        coffeeOrderCoffeeType1.setCoffeeOrder(coffeeOrder1);
        coffeeOrderCoffeeTypeRepository.save(coffeeOrderCoffeeType1);

//        CoffeeOrderCoffeeType coffeeOrderCoffeeType2 = new CoffeeOrderCoffeeType();
//        coffeeOrderCoffeeType2.setCoffeeType(coffeeType1);




    }

    @Test
    void dbSetup(){
//

//
//        Menu menu1 = new Menu();
//        menu1.setId((long) 1);
//
//        MenuCoffeeType menuCoffeeType1 = new MenuCoffeeType();
//        menuCoffeeType1.setCoffeeType(type1);
//        menuCoffeeType1.setMenu(menu1);
//        menuCoffeeTypeRepository.save(menuCoffeeType1);
//
//        MenuCoffeeType menuCoffeeType2 = new MenuCoffeeType();
//        menuCoffeeType2.setCoffeeType(type2);
//        menuCoffeeType2.setMenu(menu1);
//        menuCoffeeTypeRepository.save(menuCoffeeType2);



//        CoffeeShop coffeeShop = new CoffeeShop();
//        coffeeShop.setId((long) 1);
//
//        OrderQueue orderQueue = new OrderQueue();
//        orderQueue.setCoffeeShop(coffeeShop);
//
//        orderQueueRepository.save(orderQueue);
        System.out.println();
    }

    @Test
    void createCoffeeOrder() {

    }
}