package com.iflysse.helper.tools;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.iflysse.helper.bean.Course;
import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.service.UserServer;

@Controller
public class CacheUtil {
	@Autowired
	private TermDao termDao;
	
	@Autowired
	private UserServer userServer;

	/**
	 * 学期缓存(当前学期)
	 */
	public static Term currTerm = null;
	
	@PostConstruct
	private void cache_init() {
		if ( ( currTerm = termDao.get_current_term() ) == null) {
			System.out.println("学期为空");
			currTerm = new Term("预留学期", new Date(System.currentTimeMillis() ), 
					Constant.TERM_DEFAULT_WEEKS , true );
			termDao.insert_term(currTerm);
		}
		//初始化一个管理员用户
		User admin = userServer.get_user(Constant.FIELD_TYPE_USERNAME, "admin");
		if ( admin == null ) {
			admin = new User("admin", "123456", "null", "null", "null");
			userServer.user_add(admin);
		}
	}
}
