package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Subject;

public interface SubjectDao {

	/**
	 * 通过用户查询科目列表
	 * @param userId
	 * @return
	 */
	@Select("select * from t_subject where teacher=#{userId}")
	public List<Subject> get_subject_list_by_user(int userId);
	/**
	 * 通过游湖id和学期id查询科目列表
	 * @param userId 用户id
	 * @param termId 学期id
	 * @return
	 */
	@Select("select * from t_subject where teacher=#{userId} and term=#{termId}")
	public List<Subject> get_subject_list(int userId, int termId);
	
	/**
	 * 通过时间id来查询科目对象
	 * @param time 时间表中的id
	 * @return
	 */
	@Select("select t_subject.id id, t_subject.term term, t_subject.teacher teacher, t_subject.name name,"
			+ "t_subject.type type, t_subject.ta ta, t_subject.klass klass, t_subject.timeTotal timeTotal,"
			+ "t_subject.timeTheory timeTheory, t_subject.timePractice timePractice"
			+ " from t_subject join t_time where t_time.subject=t_subject.id")
	public Subject get_subject_by_time(int timeId);
	
	/**
	 * 通过课程id查询所属课表
	 */
	@Select("select t_subject.id id, t_subject.teacher teacher from t_subject, t_course where "
			+ "t_subject.id=t_course.subject and t_course.id=#{courseId}")
	public Subject get_subject_by_course(Integer courseId);
	
	/*
	 * 获取指定用户及指定学期的科目列表
	 */
	@Select("select * from t_subject where term=#{termId}")
	public List<Subject> get_subject_list_by_term(int teamId);
	
	/*
	 * 获取指定科目的信息
	 */
	@Select("select * from t_subject where id=#{id}")
	public Subject get_subject_by_id(int id);
	
	/*
	 * 更新科目信息
	 */
	@Update("update t_subject set name=#{name}, type=#{type}, ta=#{ta}, klass=#{klass}, timeTotal=#{timeTotal}, "
			+ "timeTheory=#{timeTheory}, timePractice=#{timePractice} where id=#{id}")
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
	@Delete("delete from t_subject where id=#{subjectId}")
	public void delete_subject(Integer subjectId);
	
	//删除某一教师的所有科目
	@Delete("delete from t_term where teacher=#{userId}")
	public void delete_subject_for_teacher(int userId);
	
	/*
	 * 删除某一科目表对应的时间表和课程表
	 */
	@Delete("delete t_time, t_course from t_time join t_course on t_course.time=t_time.id where t_time.subject=#{subjectId}")
	public void delete_time_and_course_by_subject(int subjectId);
}

