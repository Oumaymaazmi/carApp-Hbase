package com.adups.hbase.service;

import com.adups.hbase.bean.Car;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IHBaseService {

    /**
     * @param tableName
     * @param families
     */
    void createTable(String tableName) throws IOException;

    /**
     * @param tableName
     */
    void deleteTable(String tableName);





    /**
     * @param tableName
     * @param regexKey
     * @return
     */
    List<Cell> scanRegexRowKey(String tableName, String regexKey);

    /**
     * @param tableName
     * @param rowKey
     */
    void deleteAllColumn(String tableName, String rowKey);

    /**
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param columnName
     */
    void deleteColumn(String tableName, String rowKey, String familyName, String columnName);


}
