package com.iflysse.helper.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iflysse.helper.bean.Report;
import com.iflysse.helper.bean.Term;

public interface ReportDao {

	/*
	 * 获取某一学期某一周的已提交周报
	 */
	@Select("select * from t_report where isSubmit=true and term=#{termId} and week=#{week}")
	public List<Report> get_report_list_by_submit(Integer termId, Byte week);
	
	@Select("select * from t_term where term=#{termId}")
	public List<Report> get_report_list_by_term(Integer termId);
	
	/*
	 * 获取某用户的所有周报
	 */
	@Select("select * from t_report where author=#{userId}")
	public List<Report> get_report_list_by_user(Integer userId);
	
	/*
	 * 通过id查询指定周报
	 */
	@Select("select * from t_report where id=#{reportId}")
	public Report get_report_by_id(Integer reportId);
	
	/*
	 * 新增一条周报数据
	 */
	@Insert("insert into t_report(author, term, week, content, isSubmit)"
			+ " value(#{author}, #{term}, #{week}, #{content}, #{isSubmit})")
	public void insert_report (Report report);
	
	/*
	 * 删除指定id的周报
	 */
	@Delete("delete from t_report where id=#{reportId}")
	public void delete_report(Integer reportId);
	
	/*
	 * 删除某用户的所有周报
	 */
	@Delete("delete from t_report where author=#{userId}")
	public void delete_report_user(Integer userId);
	
	/*
	 * 更改某周报标题内容和提交状态
	 */
	@Update("update t_report set week=#{week}, content=#{content}, isSubmit=#{isSubmit} where id=#{id}")
	public void update_report(Report report);
	
	/*
	 * 更改某周报的提交状态
	 */
	@Update("update t_report set isSubmit=#{isSubmit} where id=#{id}")
	public void update_report_state(Integer reportId, boolean state);

}

