package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Term;


public interface TermDao {

	@Select("select * from t_term")
	public List<Term> get_term_list();
	
	@Select("select id, name, startTime, weeks from t_term where isCurrent=true")
	public Term get_current_term();
	
	@Select("select * from t_term where name=#{termName}")
	public Term get_term_by_name(String termName);
	
	@Select("select * from t_term where id=#{termId}")
	public Term get_term_by_id(int termId);
	
	@Update("update t_term set isCurrent=#{isCurrent} where id=#{id}")
	public void update_term_state (int id, boolean isCurrent);
	
	@Update("update t_term set name=#{name}, weeks=#{weeks}, startTime=#{startTime} where id=#{id}")
	public void update_term (Term term);
	
	//@Insert("insert into t_term(name, startTime, weeks, isCurrent) value(#{name}, #{startTime},#{weeks}, #{isCurrent})")
	public void insert_term (Term term);
	
	@Delete("delete from t_term where id=#{id}")
	public void delete_term(Integer id);
}

