package com.global.coffeeshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public interface CommonService {
    Logger logger = LoggerFactory.getLogger(CommonService.class);
    boolean isDebugEnable = logger.isDebugEnabled();

    ObjectMapper mapper = new ObjectMapper();
}
