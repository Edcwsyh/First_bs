package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Subject;

public interface SubjectDao {

	/*
	 * 获取指定用户及指定学期的科目列表
	 */
	@Select("select * from t_subject where teacher=#{userId} and term=#{termId}")
	public List<Subject> get_subject_list(int userId, int teamId);
	
	/*
	 * 获取指定科目的信息
	 */
	@Select("select * from t_subject where id=#{subject}")
	public Subject get_subject_info(int subject);
	
	/*
	 * 更新科目信息
	 */
	@Update("update t_term set name=#{name}, type=#{type}, ta=#{ta}, klass=#{klass}, timeTotal=#{timeTotal}, "
			+ "timeTheory=#{timeTheory}, timePractice=#{timePractice}, term=#{term} where id=#{id}")
	public void update_subject (Subject subject);
	
	/*
	 * 新增一条科目信息
	 */
//	@Insert("insert into t_term(name, teacher, type, ta, klass, timeTotal, "
//			+ "timeTheory, timePractice, term)"
//			+ " value(#{name}, #{teacher},#{type}, #{ta}, #{klass}, #{timeTotal},"
//			+ "#{timeTheory}, #{timePractice}, #{term})")
	public void insert_subject (Subject subject);
	
	//删除某一科目
	@Delete("delete from t_term where id=#{id}")
	public void delete_subject(Subject subject);
	
	//删除某一教师的所有科目
	@Delete("delete from t_term where teacher=#{userId}")
	public void delete_subject_for_teacher(int userId);
}

