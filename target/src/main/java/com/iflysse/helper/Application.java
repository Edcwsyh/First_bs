package com.iflysse.helper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.iflysse.helper.bean.Term;
import com.iflysse.helper.controller.TermController;

@SpringBootApplication
@MapperScan("com.iflysse.helper.dao")
public class Application {
	
    public static void main( String[] args ){
    	SpringApplication.run(Application.class, args);
    }
    
}
