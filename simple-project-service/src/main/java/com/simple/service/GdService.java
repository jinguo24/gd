package com.simple.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.GdHomeWorkItemsDao;
import com.simple.dao.GdHomeWorkWorkersItemDao;
import com.simple.dao.GdSignDao;
import com.simple.dao.GdSignWorkersDao;
import com.simple.dao.WxHomeWorkDao;
import com.simple.model.GdHomeWorkItems;
import com.simple.model.GdHomeWorkWorkersItem;
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
	@Autowired
	private GdHomeWorkItemsDao gdHomeWorkItemDao;
	@Autowired
	private GdHomeWorkWorkersItemDao gdHomeWorkWorkersItemDao;
	
	public void addGdSign(GdSign gdSign) {
		gdSignDao.addGdSign(gdSign);
	}
	
	public GdSign queryByTHD(String tanentId,int homeworkId,Date date) {
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
	
	public void addGdHomeWorkItems(GdHomeWorkItems homeworkItems) {
		gdHomeWorkItemDao.addGdHomeWorkItems(homeworkItems);
	}
	
	public void updateGdHomeWorkItems(GdHomeWorkItems homeworkItems) {
		gdHomeWorkItemDao.updateGdHomeWorkItems(homeworkItems);
	}
	
	public PageResult queryHomeworkItems(String tanentId,Integer homeworkId,int pageIndex,int pageSize) {
		List<GdHomeWorkItems> registers = gdHomeWorkItemDao.query(tanentId,homeworkId, pageIndex, pageSize);
		int count = gdHomeWorkItemDao.queryCount(tanentId,homeworkId);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public GdHomeWorkItems queryHomeWorkItem(String tanentId,int homeworkId) {
		return gdHomeWorkItemDao.queryOne(tanentId, homeworkId);
	}
	
	public void deleteHomeWorkItem (String tanentId,String homeworkId) {
		gdHomeWorkItemDao.delete(tanentId, homeworkId);
	}
	
	public void addGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
		gdHomeWorkWorkersItemDao.addGdHomeWorkWorkersItem(homeworkWorkersItem);
	}
	
	public void updateGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
		gdHomeWorkWorkersItemDao.updateGdHomeWorkWorkersItem(homeworkWorkersItem);
	}
	
	public PageResult queryHomeworkWorkersItem(String tanentId,String cardNo,int homeworkId,String beginTime,
			String endTime,int pageIndex,int pageSize) {
		List<GdHomeWorkWorkersItem> registers = gdHomeWorkWorkersItemDao.query(tanentId, cardNo, homeworkId, beginTime, endTime, pageIndex, pageSize);
		int count = gdHomeWorkWorkersItemDao.queryCount(tanentId, cardNo, homeworkId, beginTime, endTime);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public GdHomeWorkWorkersItem queryHomeworkWorkersItem(String gdSignId,String cardNo) {
		return gdHomeWorkWorkersItemDao.queryOne(gdSignId, cardNo);
	}
	
	public void delete (String gdSignId,String cardNo) {
		gdHomeWorkWorkersItemDao.delete(gdSignId, cardNo);
	}
}
