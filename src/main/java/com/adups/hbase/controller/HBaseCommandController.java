package com.adups.hbase.controller;

import com.adups.hbase.bean.Car;
import com.adups.hbase.service.impl.HBaseServiceImpl;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author faissal
 * @date 23/11/2017.
 */

@RestController
@RequestMapping("/hbase/table")
public class HBaseCommandController {

	@Autowired
	private HBaseServiceImpl hBaseService;

	//http://localhost:18080/table/create/
	@GetMapping(value = "/")

	public String createTable() throws Exception {
		hBaseService.createTable("car", "info");
		return " table create success!";
	}



	@DeleteMapping("/table/{tableName}")
	public String deleteTable(@PathVariable String tableName) throws Exception {
		hBaseService.deleteTable(tableName);
		return "delete table success!";
	}
}
