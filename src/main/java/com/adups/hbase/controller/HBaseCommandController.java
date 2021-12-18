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

//	//http://localhost:18080/hbase/command/scan/
//	@RequestMapping(value = "/hbase/command/scan", method = RequestMethod.GET)
//	public String scanRegexRowKey() {
//		String regexKey = "^.*\\+15022176018\\+20900$";
//		List<Cell> result = hBaseService.scanRegexRowKey("ota_pre_record", regexKey);
//		if (null==result) {
//			System.out.println("result is null");
//		}
//		for (Cell cell : result) {
//			System.out.println("rowKey:" + Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()));
//			System.out.println("family:" + Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
//			System.out.println("qualifier:" + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
//			System.out.println("value:" + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
//			System.out.println("Timestamp:" + cell.getTimestamp());
//		}
//		return "scan success";
//	}
//
//	@RequestMapping(value = "/hbase/command/delete/allColumn", method = RequestMethod.GET)
//	public String deleteAllColumn(@RequestParam(value = "rowKey") String rowKey) {
//
//		hBaseService.deleteAllColumn("ota_pre_record", rowKey);
//		return "delete all column  success";
//	}




}
