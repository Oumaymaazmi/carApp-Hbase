package com.adups.hbase.service.impl;

import com.adups.hbase.bean.Car;
import com.adups.hbase.bean.Caracteristique;
import com.adups.hbase.bean.Model;
import com.adups.hbase.service.IHBaseService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class HBaseServiceImpl implements IHBaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Configuration configuration;

    @Override
    public void createTable(String tableName, String... families) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        HTableDescriptor tableDescriptor = new
                HTableDescriptor(TableName.valueOf(tableName));
        // Adding column families to table descriptor
        tableDescriptor.addFamily(new HColumnDescriptor("caracteristique"));
        tableDescriptor.addFamily(new HColumnDescriptor("model"));
        tableDescriptor.addFamily(new HColumnDescriptor("propietaire"));


        // Execute the table through admin
        admin.createTable(tableDescriptor);
        System.out.println(" Table created ");
    }

    @Override
    public void deleteTable(String tableName) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Admin admin = connection.getAdmin()) {
            TableName table = TableName.valueOf(tableName);
            if (!admin.tableExists(TableName.valueOf(tableName))) {
                logger.info("[" + tableName + "] is not existed. Delete failed!");
                return;
            }
           // admin.disableTable(table);
            admin.deleteTable(table);
            System.out.println("delete table " + tableName + " successfully!");
            logger.info("delete table " + tableName + " successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }




    @Override
    public List<Cell> scanRegexRowKey(String tableName, String regexKey) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Table table = connection.getTable(TableName.valueOf(tableName))) {
            Scan scan = new Scan();
            Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(regexKey));
            scan.setFilter(filter);
            ResultScanner rs = table.getScanner(scan);
            for (Result r : rs) {
                return r.listCells();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAllColumn(String tableName, String rowKey) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Table table = connection.getTable(TableName.valueOf(tableName))) {
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
    public void deleteColumn(String tableName, String rowKey, String familyName, String columnName) {
        try (Connection connection = ConnectionFactory.createConnection(configuration);
             Table table = connection.getTable(TableName.valueOf(tableName))) {
            Delete delColumn = new Delete(Bytes.toBytes(rowKey));
            delColumn.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
            table.delete(delColumn);
            System.out.println("Delete Column Success");
            logger.info("Delete rowKey:" + rowKey + "'s Column:" + columnName + " Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
