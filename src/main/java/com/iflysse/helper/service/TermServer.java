package com.iflysse.helper.service;

import java.util.List;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;

public interface TermServer {
	/**
	 * 通过科目查找学期
	 * @param subject 科目
	 * @return
	 */
	public Term get_term_by_subject(Subject subject);

	/**
	 * 获取学期列表
	 * @return
	 */
	public List<Term> get_term_list();
	/**
	 * 更新学期的激活状态
	 * @param oldTerm 旧的学期(即要被关闭的学期)
	 * @param newTerm 新的学期(即要被开启的学期)
	 */
	public void term_activate(Term oldTerm, Term newTerm);
	/**
	 * 插入一个新学期
	 * @param newTerm 新的学期对象
	 */
	public void insert_term(Term newTerm);
	/**
	 * 删除学期(该学期不能被其他科目所引用)
	 * @param termId 学期id
	 */
	public void delete_term(Integer termId);
	/**
	 * 更新学期信息(不包含激活状态)
	 * @param term 学期对象
	 */
	public void update_term_info(Term term);
	/**
	 * 通过id查询学期
	 * @param termId 学期id
	 * @return
	 */
	public Term get_term_by_id(Integer termId);
	/**
	 * 更新某个学期的激活状态
	 * @param id 要被更新的学期id
	 * @param state(true表示激活, false表示关闭)
	 */
	public void update_term_state(Integer id, boolean state);
}
