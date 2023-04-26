package com.example.threadtest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestService {
    Logger log = LoggerFactory.getLogger(TestService.class);

    //-----------First Way to create Thread Manually
    public String check() {
        List<Integer> numberList = new ArrayList<>(100);

        Thread th1;


        // ------ for(int i:numberList){
        for (int i = 0; i <= 100; i++) {
            numberList.add(i);

        }
        for (int th = 0; th < 3; th++) {
            th1 = new Thread("Thread:" + th + " " + numberList);
            th1.start();
            //-------diff b/w start and run() method
            //   th1.run();
            log.info(th1.getName());


        }

        return ("Success");


    }

    //-------------------Second Way to create Thread using ExecuterServiceInterface
    //------using Thread pool

    public String check1() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Integer> numberList = new ArrayList<>(100);
        for (int i = 0; i <= 100; i++) {
            numberList.add(i);
        }
        // for(int k:executorService){
        for (int k = 0; k < 10; k++) {
            executorService.execute(() -> {
                log.info(Thread.currentThread().getName() + " " + numberList);
            });

        }
        executorService.shutdown();

        return "Success";

    }

    //////-----3rd way to create thraed using Runnable Interface
    public String check2() {
        ExecutorService ex = Executors.newFixedThreadPool(5);
        List<Integer> numberList = new ArrayList<>(100);
        // for(Integer i:numberList){
        for (int i = 0; i <= 100; i++) {
            numberList.add(i);
        }
        for (int f = 0; f < 5; f++) {
            Runnable runnable = () -> {   //Functional Interface which only have one abstract method
                String nam = Thread.currentThread().getName() + " " + numberList;
                log.info(nam);
            };
            ex.execute(runnable);
        }
        ex.shutdown();
        return ("Success");
    }


    @Async //("CustomThread")
    //   @Async has two limitations:
    //   1. It must be applied to publicmethods only.
    //   2. Self-invocation — calling the async method from within the same class — won't work.

    public void check3() {
        List<Integer> numberList = new ArrayList<>(100);
        for (int i = 0; i <= 100; i++) {
            numberList.add(i);
            // log.info("value is:" + Thread.currentThread().getName() + numberList);
        }

        log.info("value is:" + Thread.currentThread().getName() + numberList);


    }

}


