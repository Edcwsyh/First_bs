package com.iflysse.helper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iflysse.helper.bean.Report;
import com.iflysse.helper.dao.ReportDao;
import com.iflysse.helper.service.ReportServer;

@Service
public class ReportServerImpl implements ReportServer {
	
	@Autowired
	ReportDao reportDao;

	@Override
	public Report get_report_by_id(Integer reportId) {
		return reportDao.get_report_by_id(reportId);
	}

	@Override
	public List<Report> get_report_list_by_submit() {
		return reportDao.get_report_list_by_submit();
	}

	@Override
	public List<Report> get_report_list_by_user(Integer id) {
		return reportDao.get_report_list_by_user(id);
	}

	@Override
	public void insert_report(Report report) {
		reportDao.insert_report(report);
	}

	@Override
	public void update_report(Report report) {
		reportDao.update_report(report);
	}

	@Override
	public void delete_report(Integer reportId) {
		reportDao.delete_report(reportId);
	}

	@Override
	public void delete_report_by_user(Integer userId) {
		reportDao.delete_report_user(userId);
	}

	@Override
	public void update_report_state(Integer reportId, boolean state) {
		reportDao.update_report_state(reportId, state);
	}

}
