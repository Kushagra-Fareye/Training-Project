package com.fareye.training.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fareye.training.model.Car;

@RestController
public class CarController {

    @GetMapping("/car")
    public String carData(){
        Car car=new Car();
        return car.toString();
    }
    
    @PostMapping("/car")
    public String createCar(@RequestBody @Valid Car car,BindingResult bindingResult){
        bindingResult.hasErrors();
        return car.toString();
    }

    @GetMapping("/exceptionapi")
    public void exceptionDemo() throws Exception{
        throw new Exception("Demo Exception");
    }
}
