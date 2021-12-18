package com.adups.hbase.service;

import com.adups.hbase.bean.Car;

import java.util.List;

public interface CarService {

    public Car insert(Car car);
    public Car findByRowKey(String rowKey);
    public void deletByRowKey(String rowKey);
    public String update(Car car);
    public List<Car> findAll();


}
