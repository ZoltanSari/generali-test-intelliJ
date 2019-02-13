package com.generali.test.generalitest.controller;

import com.generali.test.generalitest.GeneraliTestApplication;
import com.generali.test.generalitest.service.CurrentTimeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@CrossOrigin("*")
@RestController
public class CurrentTimeController {

    @Autowired
    private CurrentTimeService service;

    private static final Logger logger = LogManager.getLogger(GeneraliTestApplication.class);

    @GetMapping("/time")
    public ResponseEntity getActualTime() {
        LocalTime currentTime = LocalTime.now();
        logger.info(currentTime);
        service.addCurrentTimeToDB(currentTime.toString());
        return ResponseEntity.status(HttpStatus.OK).body(currentTime);
    }
}
