package com.iflysse.helper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflysse.helper.bean.Subject;
import com.iflysse.helper.bean.Term;
import com.iflysse.helper.dao.TermDao;
import com.iflysse.helper.service.TermServer;

@Service
public class TermServerImpl implements TermServer {

	@Autowired
	private TermDao termDao;
	
	@Override
	public Term get_term_by_subject(Subject subject) {
		return termDao.get_term_by_id( subject.getTerm() );
	}

}
