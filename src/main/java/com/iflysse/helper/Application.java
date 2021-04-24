package com.iflysse.helper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iflysse.helper.bean.Term;
import com.iflysse.helper.controller.TermController;
import com.iflysse.helper.tools.ExcelUtil;

@SpringBootApplication
@MapperScan("com.iflysse.helper.dao")
public class Application {
	
    public static void main( String[] args ) {
//    	try {
//			FileOutputStream fout = new FileOutputStream("G:/草稿/templare.xlsx");
//			SXSSFWorkbook excel = ExcelUtil.get_excel_template();
//			excel.write(fout);
//		} catch (Exception e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//			return;
//		}
    	SpringApplication.run(Application.class, args);
    }
    
}
