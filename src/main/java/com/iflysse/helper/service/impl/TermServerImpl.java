package com.iflysse.helper.service.impl;

import java.util.List;

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

	@Override
	public List<Term> get_term_list() {
		return termDao.get_term_list();
	}

	@Override
	public void update_term_state(Integer id, boolean state) {
		termDao.update_term_state(id, state);;
	}

	@Override
	public void insert_term(Term newTerm) {
		termDao.insert_term(newTerm);
		
	}

	@Override
	public void delete_term(Integer termId) {
		termDao.delete_term(termId);
		
	}

	@Override
	public void update_term_info(Term term) {
		termDao.update_term(term);
	}

	@Override
	public Term get_term_by_id(Integer termId) {
		return termDao.get_term_by_id(termId);
	}

}
