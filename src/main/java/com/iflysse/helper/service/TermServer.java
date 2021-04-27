package com.iflysse.helper.service;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;

public interface TermServer {
	/**
	 * 通过科目查找学期
	 * @param subject 科目
	 * @return
	 */
	public Term get_term_by_subject(Subject subject);
}
