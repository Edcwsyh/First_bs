package com.iflysse.helper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflysse.helper.bean.User;
import com.iflysse.helper.dao.UserDao;
import com.iflysse.helper.service.UserServer;
import com.iflysse.helper.tools.Constant;
import com.iflysse.helper.tools.Result;
import com.iflysse.helper.tools.ResultCode;

@Service
public class UserServerImpl implements UserServer {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User get_user(Byte field, String value) {
		if(value == null) {
			return null;
		}
		//检测要检测的字段类型
		switch(field.intValue()) {
			//用户名
			case Constant.FIELD_TYPE_USERNAME : 
				return userDao.get_user_by_username(value);
			
			//邮箱
			case Constant.FIELD_TYPE_MAIL : 
				return userDao.get_user_by_mail(value);
			
			//电话号码
			case Constant.FIELD_TYPE_PHONE : 
				return userDao.get_user_by_phone(value);
			
			default :
				return null;
		}
	}
	
	@Override
	public Result<User> user_all_info(Integer userId) {
		// TODO 自动生成的方法存根
		User dbUser = userDao.get_user_by_id( userId );
		if ( dbUser == null ) {
			return new Result<User>(ResultCode.ERROR_USER_NOT_FOUND, null);
		}
		return new Result<User>(ResultCode.SUCCESS, dbUser);
	}

	@Override
	public Result<Void> user_update_all_info(User user) {
		//检测传入的参数是否存在空字段
		if( user.check( Constant.CHECK_ALL ) != 0 ) {
			return new Result<Void>(ResultCode.ERROR_PARAM, null);
		}
		User dbUser = userDao.get_user_by_id( user.getId() );
		//检测数据库中是否存在该条数据
		if ( dbUser == null ) {
			return new Result<Void>(ResultCode.ERROR_USER_NOT_FOUND, null);
		}
		userDao.update_user_all( user );
		return new Result<Void>(ResultCode.SUCCESS, null);
	}

	@Override
	public Result<Void> user_add(User user) {
		// TODO 自动生成的方法存根
		//检测传入的数据是否存在空字段
		if( user.check( Constant.CHECK_ALL ) != 0 ) {
			return new Result<Void>(ResultCode.ERROR_PARAM, null);
		}
		//检测数据库中的用户名、邮箱、电话是否重复
		if(userDao.get_user_by_ump(user) != null) {
			return new Result<Void>(ResultCode.ERROR_USER_EXIST, null);
		}
		userDao.insert_user(user);
		return new Result<Void>(ResultCode.SUCCESS, null);
	}

	@Override
	public Result<List<User>> user_list() {
		// TODO 自动生成的方法存根
		return new Result< List<User> >(ResultCode.SUCCESS, userDao.get_user_list());
	}

	@Override
	public Result<Void> user_update_info(User user) {
		if( user.check( Constant.CHECK_ALL ^ Constant.CHECK_ID ^ Constant.CHECK_PASSWORD ) != 0 ) {
			return new Result<Void>(ResultCode.ERROR_PARAM, null);
		}
		userDao.update_user_info(user);
		return new Result<Void>(ResultCode.SUCCESS, null);
	}

	@Override
	public Result<Void> user_update_password(User user) {
		// TODO 自动生成的方法存根
		
		return null;
	}

}
