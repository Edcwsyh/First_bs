package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.Time;
import com.iflysse.helper.bean.TimeVO;


public interface TimeDao {

	@Select("select * from t_time where subject=#{subjectId}")
	public List<Time> get_time_list_by_subject(Integer subjectId);
	
	public List<Time> get_time_list_by_id(List<TimeVO> timeList);
	
//	@Select("select * from t_term where id=#{termId}")
//	public Term get_term_by_id(int termId);
	
	public void update_time_list (List<Time> timeList);
	
	@Update("update t_term set subject=#{subject}, weeks=#{weeks}, classroom=#{classroom}, timeQuantum=#{timeQuantum} where id=#{id}")
	public void update_time (Time time);
	
	@Insert("insert into t_time(subject, classroom, timeQuantum, weeks) value(#{subject}, #{classroom},#{timeQuantum}, #{weeks})")
	public void insert_time (Time time);
	
	public void insert_time_list (List<Time> timeList);
	
	@Delete("delete from t_time where id=#{id}")
	public void delete_time_by_id(Integer id);
	
	/**
	 * 删除某科目的所有时间段
	 * @param subjectId
	 */
	@Delete("delete from t_time where subject=#{subjectId}")
	public void delete_time_by_subject(Integer subjectId);
}

