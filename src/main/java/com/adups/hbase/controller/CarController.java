package com.adups.hbase.controller;


import com.adups.hbase.bean.Car;
import com.adups.hbase.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hbase/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/")
    public Car postObject(@RequestBody Car car) {
        carService.insert(car);
        return car;
    }

    @GetMapping("/RowKey/{rowKey}")
    public Car findByRowKey(@PathVariable String rowKey) {
        return carService.findByRowKey(rowKey);

    }

    @DeleteMapping("/RowKey/{rowKey}")
    public void deletByRowKey(@PathVariable String rowKey) {
        carService.deletByRowKey(rowKey);

    }
    @PutMapping("/")
    public String update(@RequestBody Car car){
        return carService.update(car);
    }

}
