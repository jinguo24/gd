package com.simple.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.util.DateUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.dao.DictionaryDao;
import com.simple.dao.GdCardMakeDao;
import com.simple.dao.GdHomeWorkItemsDao;
import com.simple.dao.GdHomeWorkWorkersItemDao;
import com.simple.dao.GdSignDao;
import com.simple.dao.GdSignWorkersDao;
import com.simple.dao.WxHomeWorkDao;
import com.simple.dao.WxMemberHomeWorkDao;
import com.simple.model.Dictionary;
import com.simple.model.GdCardMake;
import com.simple.model.GdHomeWorkItems;
import com.simple.model.GdHomeWorkWorkersItem;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.model.WxHomeWork;
import com.simple.model.WxMemberHomeWork;

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
	@Autowired
	private WxMemberHomeWorkDao wxMemberHomeWorkDao;
	@Autowired
	private GdCardMakeDao gdCardMakeDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
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
	
	public WxHomeWork queryWxHomeWork(int id) {
		return wxHomeWorkDao.queryById(id);
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
		if (isPass(homeworkWorkersItem)) {
			addGdCardMake(homeworkWorkersItem);
		}
	}
	
	private boolean isPass(GdHomeWorkWorkersItem homeworkWorkersItem) {
		if (StringUtils.isEmpty(homeworkWorkersItem.getItemJson())) {
			return false;
		}
		if (homeworkWorkersItem.getZonghe()<80) {
			return false;
		}
		String[] ivs = homeworkWorkersItem.getItemJson().split(",");
		boolean ispass = true;
		//查询考试成绩，如果小于60%，则不通过
		WxMemberHomeWork wmhw = wxMemberHomeWorkDao.queryOne(homeworkWorkersItem.getTanentId(), homeworkWorkersItem.getCardNo(), 
				homeworkWorkersItem.getGdSignId(), homeworkWorkersItem.getHomeworkId());
		if (null == wmhw) {
			return false;
		}
		WxHomeWork whw = wxHomeWorkDao.queryById(homeworkWorkersItem.getHomeworkId());
		if (null == whw) {
			return false;
		}
		if ((whw.getScore().doubleValue()*0.6) > wmhw.getScore().doubleValue()) {
			return false;
		}
		
		for (int i = 0 ; i < ivs.length; i ++) {
			String iv = ivs[i];
			if (!StringUtils.isEmpty(iv)) {
				String[] is = iv.split(":");
				int v = Integer.parseInt(is[1]);
				if (v < 80) {
					ispass = false;
					break;
				}
			}
		}
		return ispass;
	}
	
	private void addGdCardMake(GdHomeWorkWorkersItem homeworkWorkersItem) {
		//如果每个项都是合格，则往制证表里面加入
		GdCardMake cm = new GdCardMake();
		cm.setCardNo(homeworkWorkersItem.getCardNo());
		cm.setHomeworkId(homeworkWorkersItem.getHomeworkId());
		cm.setMakeCount(0);
		cm.setName(homeworkWorkersItem.getName());
		cm.setSex(homeworkWorkersItem.getSex());
		//查询问卷的制证类型
		WxHomeWork whw = wxHomeWorkDao.queryById(homeworkWorkersItem.getHomeworkId());
		if (null != whw && (!StringUtils.isEmpty(whw.getCategoryId()))) {
			Dictionary dictionary = dictionaryDao.queryByCode(whw.getCategoryId());
			if (null != dictionary ) {
				cm.setType(dictionary.getName());
			}
		}
		cm.setCardImage(homeworkWorkersItem.getCardImage());
		cm.setSequenceNo(PrimaryKeyUtil.getShortId());
		gdCardMakeDao.addGdCardMake(cm);
	}
	
	public void updateGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
		gdHomeWorkWorkersItemDao.updateGdHomeWorkWorkersItem(homeworkWorkersItem);
		//如果每个项都是合格，则往制证表里面加入；如果有一项不合格，则从制证表中删除
		GdCardMake cm = gdCardMakeDao.queryOne(homeworkWorkersItem.getCardNo(), homeworkWorkersItem.getHomeworkId());
		if (isPass(homeworkWorkersItem)) {
			if (null == cm ) {
				try {
					addGdCardMake(homeworkWorkersItem);
				}catch(Exception e) {
				}
			}
		}else {
			if (null != cm ) {
				try {
					gdCardMakeDao.delete(homeworkWorkersItem.getCardNo(), homeworkWorkersItem.getHomeworkId());
				}catch(Exception e) {
				}
			}
		}
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
	
	public PageResult queryWxMemberHomeWork(String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime,int pageIndex,int pageSize) {
		List<WxMemberHomeWork> registers = wxMemberHomeWorkDao.queryList(schoolId, studentNo, homeworkId, beginTime, endTime, pageIndex, pageSize);
		int count = wxMemberHomeWorkDao.queryCount(schoolId, studentNo, homeworkId, beginTime, endTime);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public WxMemberHomeWork queryWxMemberHomeWork(String schoolId,String studentNo,String signId,int homeworkId) {
		return wxMemberHomeWorkDao.queryOne(schoolId, studentNo, signId, homeworkId);
	}
	
	public void downloadWorkerItems(List<WxMemberHomeWork> items,HttpServletResponse response) {
		String[] titles = new String[]{"工地名称","试卷","身份证号码","工人姓名","考试成绩","考试时间","评分结果"};
		DownLoadExcel.download(items, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				WxMemberHomeWork log = (WxMemberHomeWork) o;
				List<String> sl = new ArrayList<String>();
				sl.add(String.valueOf(log.getTanentName()));
				sl.add(log.getHomeworkName());
				sl.add(log.getStudentNo());
				sl.add(log.getStudentName());
				sl.add(String.valueOf(log.getScore()));
				sl.add(DateUtil.date2AllString(log.getCreateTime()));
				sl.add(StringUtils.trimToEmpty(log.getContent()));
				return sl;
			}
		},response);
	}
	
	public void downloadWorkerItemsReport(List<WxMemberHomeWork> items,GdHomeWorkItems gwi,HttpServletResponse response) {
		final String[] ites = gwi.getItemNameArray();
		String[] titles = null;
		if ( null != ites) {
			titles = new String[6+ites.length];
			titles[0] = "工地名称";
			titles[1] = "试卷";
			titles[2] = "身份证号码";
			titles[3] = "工人姓名";
			titles[4] = "考试成绩";
			titles[5] = "考试时间";
			for (int i = 0 ; i < ites.length; i ++ ) {
				titles[6+i] = ites[i];
			}
		}else {
			titles = new String[]{"工地名称","试卷","身份证号码","工人姓名","考试成绩","考试时间"};
		}
		final String[] finalTitiles = titles;
		DownLoadExcel.download(items, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				WxMemberHomeWork log = (WxMemberHomeWork) o;
				List<String> sl = new ArrayList<String>();
				sl.add(String.valueOf(log.getTanentName()));
				sl.add(log.getHomeworkName());
				sl.add(log.getStudentNo());
				sl.add(log.getStudentName());
				sl.add(String.valueOf(log.getScore()));
				sl.add(DateUtil.date2AllString(log.getCreateTime()));
				if ( null != ites ) {
					String content = log.getContent();
					if (!StringUtils.isEmpty(content)) {
						Map<String,String> cmap = new HashMap<String,String>();
						String[] ivs = content.split(",");
						if ( null != ivs ) {
							for (int i = 0 ;i < ivs.length; i ++) {
								String iv = ivs[i];
								if (!StringUtils.isEmpty(iv)) {
									String[] is = iv.split(":");
									cmap.put(is[0], is[1]);
								}
							}
						}
						for (int i=6 ; i< finalTitiles.length; i++) {
							sl.add(StringUtils.trimToEmpty(cmap.get(finalTitiles[i])));
						}
					}
				}
				return sl;
			}
		},response);
	}
	
	public void addGdCardMake(GdCardMake cardMake) {
		gdCardMakeDao.addGdCardMake(cardMake);
	}
	
	public PageResult queryCardMake(String cardNo,int homeworkId,String name, int status,int pageIndex,int pageSize) {
		List<GdCardMake> registers = gdCardMakeDao.queryList(cardNo, homeworkId, name, status, pageIndex, pageSize);
		int count = gdCardMakeDao.queryCount(cardNo, homeworkId, name, status);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public void make(GdCardMake cardMake) {
		gdCardMakeDao.make(cardMake);
	}
	
	public GdCardMake queryCardMake(String cardNo,int homeworkId) {
		return gdCardMakeDao.queryOne(cardNo, homeworkId);
	}
	
	public void delete(String cardNo,int homeworkId) {
		gdCardMakeDao.delete(cardNo, homeworkId);
	}
	
}
