package com.iflysse.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iflysse.helper.bean.Report;

@Service
public interface ReportServer {
	/**
	 * 通过id获取周报
	 * @param reportId 周报id
	 * @return 
	 */
	Report get_report_by_id(Integer reportId);
	/**
	 * 获取所有已提交的周报
	 * @return
	 */
	List<Report> get_report_list_by_submit(Integer termId, Byte week);
	/**
	 * 获取某用户的所有周报
	 * @param id 用户id
	 * @return
	 */
	List<Report> get_report_list_by_user(Integer id);
	/**
	 * 插入新的周报
	 * @param report 周报对象
	 */
	void insert_report(Report report);
	/**
	 * 更新周报信息(包含提交状态)
	 * @param report
	 */
	void update_report(Report report);
	/**
	 * 删除某一周报
	 * @param reportId 周报id
	 */
	void delete_report(Integer reportId);
	/**
	 * 删除某用户的所有周报
	 * @param userId 用户id
	 */
	void delete_report_by_user( Integer userId );
	/**
	 * 更新周报的提交状态
	 * @param reportId 周报id
	 * @param state 状态, true为提交, false为未提交
	 */
	void update_report_state(Integer reportId, boolean state);
	/**
	 * 获取某学期的所有周报
	 * @param termId 学期id
	 * @return
	 */
	List<Report> get_report_list_by_term(Integer termId);

}
