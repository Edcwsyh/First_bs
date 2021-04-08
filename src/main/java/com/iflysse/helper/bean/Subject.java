package com.iflysse.helper.bean;

import com.iflysse.helper.tools.Constant;

/**
 * 
 * @author Edcwsyh
 *
 */
public class Subject {
    /**
     * id
     */
    private Integer id;
    
    /**
     * 学期 - 非空外键
     */
    private Integer term;
    
    /**
     * 讲师-非空外键
     */
    private Integer teacher;

    /**
     * 科目名称-非空
     */
    private String name;

    /**
     * 科目类别-非空
     */
    private Byte type;

    /**
     * 助教
     */
    private String ta;

    /**
     * 班级, class为关键字, 故使用klass， 非空
     */
    private String klass;

    /**
     * 总课时-非空
     */
    private Short timeTotal;

    /**
     * 理论课时-非空
     */
    private Short timeTheory;

    /**
     * 实践课时-非空
     */
    private Short timePractice;
    
    /**
     * Default constructor
     */
    public Subject() {
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getTeacher() {
		return teacher;
	}

	public void setTeacher(Integer teacher) {
		this.teacher = teacher;
	}

	public String getTa() {
		return ta;
	}

	public void setTa(String ta) {
		this.ta = ta;
	}

	public String getKlass() {
		return klass;
	}

	public void setKlass(String klass) {
		this.klass = klass;
	}

	public Short getTimeTotal() {
		return timeTotal;
	}

	public void setTimeTotal(Short timeTotal) {
		this.timeTotal = timeTotal;
	}

	public Short getTimeTheory() {
		return timeTheory;
	}

	public void setTimeTheory(Short timeTheory) {
		this.timeTheory = timeTheory;
	}

	public Short getTimePractice() {
		return timePractice;
	}

	public void setTimePractice(Short timePractice) {
		this.timePractice = timePractice;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
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
//		if( (checkField & Constant.CHECK_ID) != 0 && id == null ) {
//			result |= Constant.CHECK_ID;
//		}
//		if( (checkField & Constant.CHECK_USERNAME) != 0 && username == null ) {
//			result |= Constant.CHECK_USERNAME;
//		}
//		if( (checkField & Constant.CHECK_PASSWORD) != 0 && password == null ) {
//			result |= Constant.CHECK_PASSWORD;
//		}
//		if( (checkField & Constant.CHECK_REALNAME) != 0 && realName == null ) {
//			result |= Constant.CHECK_REALNAME;
//		}
//		if( (checkField & Constant.CHECK_PHONE) != 0 && phone == null ) {
//			result |= Constant.CHECK_PHONE;
//		}
//		if( (checkField & Constant.CHECK_MAIL) != 0 && mail == null ) {
//			result |= Constant.CHECK_MAIL;
//		}
		return result;
	}

}