package com.adups.hbase.service.impl;

import com.adups.hbase.bean.Car;
import com.adups.hbase.service.CarService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Configuration configuration;

    @Override
    public Car insert(Car car) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Table table = connection.getTable(TableName.valueOf("car"))) {
            Put put = MapingHbase.toHbase(car);
            table.put(put);
            System.out.println("row table success");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return car;
    }

    @Override
    public Car findByRowKey(String rowKey) {
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection connection = null;
            connection = ConnectionFactory.createConnection(conf);
            Table table = connection.getTable(TableName.valueOf("car"));
            Get g = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(g);
            Car car = MapingHbase.toObject(result);
            car.setRowKey(rowKey);
            return car;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletByRowKey(String rowKey) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Table table = connection.getTable(TableName.valueOf("car"))) {
            Delete delAllColumn = new Delete(Bytes.toBytes(rowKey));
            table.delete(delAllColumn);
            System.out.println("Delete AllColumn Success");
            logger.info("Delete rowKey:" + rowKey + "'s all Columns Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Override
    public String update(Car car) {
        Car carup= findByRowKey(car.getRowKey());
       if (carup==null){
            return "car dont exist";
       }
       insert(car);
       return "update done";

    }

    @Override
    public List<Car> findAll() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("car"));

        Scan scan = new Scan(Bytes.toBytes("1"));

      // scan.addColumn(Bytes.toBytes("rowKey"), null);
        PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes("1"));
        scan.setFilter(prefixFilter);
//        scan.setFilter(new RowFilter(CompareOperator.NOT_EQUAL,
//                new BinaryComparator(Bytes.toBytes(1))));
        ResultScanner scanner = table.getScanner(scan);
        Result[] bar = scanner.next(100);
        List<Car> cars = new ArrayList<>();
        for(int i=0;i<bar.length;i++){
            System.out.println(bar[i]);
            Car car = MapingHbase.toObject(bar[i]);
            car.setRowKey(Bytes.toString(bar[i].getRow()));
            cars.add(car);



        }
        return cars;
    }

}
