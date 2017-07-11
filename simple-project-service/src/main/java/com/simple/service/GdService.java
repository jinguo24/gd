package com.simple.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.GdSignDao;
import com.simple.dao.GdSignWorkersDao;
import com.simple.dao.WxHomeWorkDao;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.model.WxHomeWork;

@Service
public class GdService {

	@Autowired
	private GdSignDao gdSignDao;
	@Autowired
	private GdSignWorkersDao gdSignWordersDao;
	@Autowired
	private WxHomeWorkDao wxHomeWorkDao;
	
	public void addGdSign(GdSign gdSign) {
		gdSignDao.addGdSign(gdSign);
	}
	
	public GdSign queryByTHD(String tanentId,String homeworkId,Date date) {
		return gdSignDao.queryByTHD(tanentId, homeworkId, date);
	}
	
	public void addGdSignWorkers(GdSignWorkers gdSignWorkers) {
		gdSignWordersDao.addGdSignWorkers(gdSignWorkers);
	}
	
	public GdSignWorkers queryBySC(String gdSignId,String cardNo) {
		return gdSignWordersDao.queryBySC(gdSignId, cardNo);
	}
	
	public PageResult queryWorkers(String gsid,int pageIndex,int pageSize) {
		List<GdSignWorkers> registers = gdSignWordersDao.query(gsid, pageIndex, pageSize);
		int count = gdSignWordersDao.queryCount(gsid);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public PageResult queryHomeWorks(String tanentId,int pageIndex,int pageSize) {
		List<WxHomeWork> registers = wxHomeWorkDao.query(tanentId, pageIndex, pageSize);
		int count = wxHomeWorkDao.queryCount(tanentId);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
}
