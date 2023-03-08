package com.example.threadtest.controller;

import com.example.threadtest.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {


    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test")
    public String test() {
        return testService.check();
    }

    @GetMapping("/testOne")

    public String testOne() {
        return testService.check1();
    }

    @GetMapping("/testTwo")
    public String testTwo() {
        return testService.check2();
    }

    @GetMapping("/testThree")
    public String testThree() {

       for (int th = 0; th < 5; th++) {

        testService.check3();

       }

        return "Ok";
    }
}


