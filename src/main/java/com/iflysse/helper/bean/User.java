package com.iflysse.helper.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.iflysse.helper.tools.Constant;

public class User {
	
	private Integer id;
	/*
	 * 用户名
	 */
	@NotBlank
	@Pattern(regexp="[A-Za-z_][A-Za-z0-9_]{3,16}", message="用户名不符合规则")
	private String username;
	/*
	 * 密码名
	 */
	private String password;
	/*
	 * 真实姓名
	 */
	private String realName;
	/*
	 * 电话号码
	 */
	private String phone;
	/*
	 * 邮箱
	 */
	private String mail;
	/*
	 * 性别
	 */
	private Byte gender = Constant.USER_GENDER_UNKNOW;
	/*
	 * 权限
	 */
	private Byte permission = Constant.USER_PERMISSION_NORMAL;
	/*
	 * 状态
	 */
	private Byte state = Constant.USER_STATE_NORMAL;
	
	User(){
		
	}

	public User(@NotBlank @Pattern(regexp = "[A-Za-z_][A-Za-z0-9_]{3,16}", message = "用户名不符合规则") String username,
			String password, String realName, String phone, String mail) {
		super();
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.phone = phone;
		this.mail = mail;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Byte getPermission() {
		return permission;
	}

	public void setPermission(Byte permission) {
		this.permission = permission;
	}

	public Byte getState() {
		return state;
	}

	public void setState(Byte state) {
		this.state = state;
	}
	
	/**
	 * 用于检测对象中的某些属性是否为空, 检测参数应使用Constant中提供的check常量(CHECK_*)	
	 * 若有多个检测字段则应使用"按位或(异或)运算"来传递参数, 列入检测id和状态是否为空 : Constant.CHECK_ID | Constant.CHECK_STATE	
	 * 若所检测的字段为空则返回的结果对应参数, 例如id和状态都为空, 则返回值为 : Constant.CHECK_ID | Constant.CHECK_STATE		
	 * 该函数可检测属性 : CHECK_ID, CHECK_USERNAME, CHECK_PASSWORD, CHECK_REALNAME, CHECK_PHONE, CHECK_MAIL, CHECK_ALL(所有属性)		
	 * @param checkField
	 * @return result
	 */
	public int check(int checkField) {
		int result = 0;
		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
			System.out.println("id为空");
			result |= Constant.CHECK_ID;
		}
		if( (checkField & Constant.CHECK_USERNAME) != 0 && username == null ) {
			System.out.println("用户名为空");
			result |= Constant.CHECK_USERNAME;
		}
		if( (checkField & Constant.CHECK_PASSWORD) != 0 && password == null ) {
			System.out.println("密码为空");
			result |= Constant.CHECK_PASSWORD;
		}
		if( (checkField & Constant.CHECK_REALNAME) != 0 && realName == null ) {
			System.out.println("姓名为空");
			result |= Constant.CHECK_REALNAME;
		}
		if( (checkField & Constant.CHECK_PHONE) != 0 && phone == null ) {
			System.out.println("电话为空");
			result |= Constant.CHECK_PHONE;
		}
		if( (checkField & Constant.CHECK_MAIL) != 0 && mail == null ) {
			System.out.println("邮箱为空");
			result |= Constant.CHECK_MAIL;
		}
		return result;
	}
}
