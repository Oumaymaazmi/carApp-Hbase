package com.adups.hbase.service.impl;

import com.adups.hbase.bean.Car;
import com.google.gson.Gson;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;

public class MapingHbase {

    public static Car toObject(Result result) {
        String json = "{";
        int lFamily = result.getNoVersionMap().entrySet().size();
        for (Map.Entry<byte[], NavigableMap<byte[], byte[]>> colFamilyEntry
                : result.getNoVersionMap().entrySet()) {
            lFamily--;
            String columnFamilyName = Bytes.toString(colFamilyEntry.getKey());
            json += "\"" + columnFamilyName + "\":{";
            int lcol = colFamilyEntry.getValue().entrySet().size();
            for (Map.Entry<byte[], byte[]> columnNameAndValueMap
                    : colFamilyEntry.getValue().entrySet()) {
                lcol--;
                if (lcol != 0) {
                    json += "\"" + Bytes.toString(columnNameAndValueMap.getKey()) + "\":" + "\"" + Bytes.toString(columnNameAndValueMap.getValue()) + "\",";

                } else {
                    json += "\"" + Bytes.toString(columnNameAndValueMap.getKey()) + "\":" + "\"" + Bytes.toString(columnNameAndValueMap.getValue()) + "\"";
                }
            }
            if (lFamily != 0) {
                json += "},";
            } else
                json += "}";
        }
        json += "}";
        System.out.println(json);
        Gson gg = new Gson();
        Car car = gg.fromJson(json, Car.class);
        return car;
    }

    public static Put toHbase(Car car) {
        Put put = new Put(Bytes.toBytes(car.getRowKey()));
        Field[] fields = Car.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if (!field.getName().equals("rowKey")) {
                field.setAccessible(true);
                Field[] colm = field.getType().getDeclaredFields();
                Arrays.stream(colm).forEach(col -> {
                    col.setAccessible(true);
                    try {
                        put.addColumn(Bytes.toBytes(field.getName()), Bytes.toBytes(col.getName()), Bytes.toBytes(col.get(field.get(car)).toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

            }
        });
        return put;

    }
}
