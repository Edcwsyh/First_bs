package com.iflysse.helper.controller;

import java.sql.Date;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.tools.Constant;

@Controller
public class CacheController {
	@Autowired
	private TermDao termDao;

	/**
	 * 学期缓存(当前学期)
	 */
	public static Term termBuffer = null;
	
	@PostConstruct
	private void cache_init() {
		if ( ( termBuffer = termDao.get_current_term() ) == null) {
			System.out.println("学期为空");
			termBuffer = new Term("预留学期", new Date(System.currentTimeMillis() ), 
					Constant.TERM_DEFAULT_WEEKS , true );
			termDao.insert_term(termBuffer);
		}
	}
}
