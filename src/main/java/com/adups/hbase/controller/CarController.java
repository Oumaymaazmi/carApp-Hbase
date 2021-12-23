package com.adups.hbase.controller;


import com.adups.hbase.bean.Car;
import com.adups.hbase.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hbase/car")
public class CarController {
    @Autowired
    private CarService carService;


    @PostMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public Car postObject(@RequestBody Car car) {
        carService.insert(car);
        return car;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/RowKey/{rowKey}")
    public Car findByRowKey(@PathVariable String rowKey) {
        return carService.findByRowKey(rowKey);

    }

    @DeleteMapping("/RowKey/{rowKey}")
    public void deletByRowKey(@PathVariable String rowKey) {
        carService.deletByRowKey(rowKey);

    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/")
    public String update(@RequestBody Car car){
        return carService.update(car);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public List<Car> findAll() throws Exception {
        return carService.findAll();

    }
}
