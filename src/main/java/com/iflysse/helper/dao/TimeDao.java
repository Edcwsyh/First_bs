package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Term;
import com.iflysse.helper.bean.Time;


public interface TimeDao {

	@Select("select * from t_time where subject=#{subjectId}")
	public List<Time> get_time_list_by_subject(Integer subjectId);
	
//	@Select("select * from t_term where id=#{termId}")
//	public Term get_term_by_id(int termId);
	
	@Update("update t_term set isCurrent=#{isCurrent} where id=#{id}")
	public void update_term_state (int id, boolean isCurrent);
	
	@Update("update t_term set subject=#{subject}, weeks=#{weeks}, classroom=#{classroom}, timeQuantum=#{timeQuantum} where id=#{id}")
	public void update_time (Time time);
	
	@Insert("insert into t_time(subject, classroom, timeQuantum, weeks) value(#{subject}, #{classroom},#{timeQuantum}, #{weeks})")
	public void insert_time (Time time);
	
	public void insert_time_list (List<Time> time);
	
	@Delete("delete from t_term where id=#{id}")
	public void delete_term(Integer id);
}

