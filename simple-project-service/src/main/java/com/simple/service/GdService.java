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
import com.simple.constant.Constant;
import com.simple.dao.DictionaryDao;
import com.simple.dao.GdCardMakeDao;
import com.simple.dao.GdJudgeItemsDao;
import com.simple.dao.GdHomeWorkWorkersItemDao;
import com.simple.dao.GdSignDao;
import com.simple.dao.GdSignWorkersDao;
import com.simple.dao.WxHomeWorkDao;
import com.simple.dao.WxMemberHomeWorkDao;
import com.simple.model.Dictionary;
import com.simple.model.GdCardMake;
import com.simple.model.GdJudgeItems;
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
	private GdJudgeItemsDao gdHomeWorkItemDao;
	@Autowired
	private WxMemberHomeWorkDao wxMemberHomeWorkDao;
	@Autowired
	private GdCardMakeDao gdCardMakeDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	public void addGdSign(GdSign gdSign) {
		gdSignDao.addGdSign(gdSign);
	}
	
	public GdSign querySignById(String gsId) {
		return gdSignDao.queryById(gsId);
	}
	
	public GdSign querySign(String tanentId,String groupName,Date date) {
		return gdSignDao.queryByTGD(tanentId, groupName, date);
	}
	
	public void addGdSignWorkers(GdSignWorkers gdSignWorkers) {
		gdSignWordersDao.addGdSignWorkers(gdSignWorkers);
	}
	
	public GdSignWorkers querySignWorker(String gdSignId,String cardNo) {
		return gdSignWordersDao.queryBySC(gdSignId, cardNo);
	}
	
	public GdSignWorkers querySignWorker(String tanentId,String cardNo,Date date) {
		return gdSignWordersDao.queryByTCC(tanentId, cardNo, date);
	}
	
	public void updateSignWorker(GdSignWorkers gdSignWorkers) {
		gdSignWordersDao.updateGdSignWorker(gdSignWorkers);
	}
	
	public PageResult queryWorkers(String gsid,String tanentId,String groupName,String cardNo,Date date,String leaderName,int pageIndex,int pageSize) {
		List<GdSignWorkers> registers = gdSignWordersDao.query(gsid, tanentId, groupName, cardNo, date, leaderName, pageIndex, pageSize);
		int count = queryWorkersCount(gsid, tanentId, groupName, cardNo, date, leaderName);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public int queryWorkersCount(String gsid,String tanentId,String groupName,String cardNo,Date date,String leaderName) {
		return gdSignWordersDao.queryCount(gsid, tanentId, groupName, cardNo, date, leaderName);
	}
	
	public PageResult queryHomeWorks(String tanentId,int pageIndex,int pageSize) {
		List<WxHomeWork> registers = wxHomeWorkDao.query(tanentId, pageIndex, pageSize);
		int count = wxHomeWorkDao.queryCount(tanentId);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public WxHomeWork queryWxHomeWork(int id) {
		return wxHomeWorkDao.queryById(id);
	}
	
	public void addGdJudgeItems(GdJudgeItems homeworkItems) {
		gdHomeWorkItemDao.addGdJudgeItems(homeworkItems);
	}
	
	public void updateGdJudgeItems(GdJudgeItems homeworkItems) {
		gdHomeWorkItemDao.updateGdJudgeItems(homeworkItems);
	}
	
	public GdJudgeItems queryGdJudgeItem(String tanentId) {
		return gdHomeWorkItemDao.queryOne(tanentId);
	}
	
	public void deleteGdJudgeItem (String tanentId) {
		gdHomeWorkItemDao.delete(tanentId);
	}
	
//	public void addGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
//		gdHomeWorkWorkersItemDao.addGdHomeWorkWorkersItem(homeworkWorkersItem);
//		if (isPass(homeworkWorkersItem)) {
//			addGdCardMake(homeworkWorkersItem);
//		}
//	}
	
//	public void updateGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
//		gdHomeWorkWorkersItemDao.updateGdHomeWorkWorkersItem(homeworkWorkersItem);
//		//如果每个项都是合格，则往制证表里面加入；如果有一项不合格，则从制证表中删除
//		GdCardMake cm = gdCardMakeDao.queryOne(homeworkWorkersItem.getCardNo(), homeworkWorkersItem.getHomeworkId());
//		if (isPass(homeworkWorkersItem)) {
//			if (null == cm ) {
//				try {
//					addGdCardMake(homeworkWorkersItem);
//				}catch(Exception e) {
//				}
//			}
//		}else {
//			if (null != cm ) {
//				try {
//					gdCardMakeDao.delete(homeworkWorkersItem.getCardNo(), homeworkWorkersItem.getHomeworkId());
//				}catch(Exception e) {
//				}
//			}
//		}
//	}
	
//	public PageResult queryHomeworkWorkersItem(String gsId,String tanentId,String cardNo,int homeworkId,String beginTime,
//			String endTime,int zongheScore,int pageIndex,int pageSize) {
//		List<GdHomeWorkWorkersItem> registers = gdHomeWorkWorkersItemDao.query(gsId,tanentId, cardNo, homeworkId, beginTime, endTime,zongheScore, pageIndex, pageSize);
//		int count = queryHomeworkWorkersCount(gsId,tanentId, cardNo, homeworkId, beginTime, endTime,zongheScore);
//		return new PageResult(count,pageSize,pageIndex,registers);
//	}
	
//	public int queryHomeworkWorkersCount(String gsId,String tanentId,String cardNo,int homeworkId,String beginTime,
//			String endTime,int zongheScore) {
//		return gdHomeWorkWorkersItemDao.queryCount(gsId,tanentId, cardNo, homeworkId, beginTime, endTime,zongheScore);
//	}
	
//	public int queryHomeworkWorkersPassCount(String gsId,String tanentId,String cardNo,int homeworkId,String beginTime,
//			String endTime) {
//		return gdHomeWorkWorkersItemDao.queryPassCount(gsId, tanentId, cardNo, homeworkId, beginTime, endTime);
//	}
	
//	public int queryHomeworkWorkersUnPassCount(String gsId,String tanentId,String cardNo,int homeworkId,String beginTime,
//			String endTime) {
//		return gdHomeWorkWorkersItemDao.queryUnPassCount(gsId, tanentId, cardNo, homeworkId, beginTime, endTime);
//	}
	
//	public GdHomeWorkWorkersItem queryHomeworkWorkersItem(String gdSignId,String cardNo) {
//		return gdHomeWorkWorkersItemDao.queryOne(gdSignId, cardNo);
//	}
//	
//	public void delete (String gdSignId,String cardNo) {
//		gdHomeWorkWorkersItemDao.delete(gdSignId, cardNo);
//	}
	
	public PageResult queryWxMemberHomeWork(String gsId,String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime,int pageIndex,int pageSize) {
		List<WxMemberHomeWork> registers = wxMemberHomeWorkDao.queryList(gsId,schoolId, studentNo, homeworkId, beginTime, endTime, pageIndex, pageSize);
		int count = queryWxMemberHomeWorkCount(gsId,schoolId, studentNo, homeworkId, beginTime, endTime);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public int queryWxMemberHomeWorkCount(String gsId,String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime) {
		return wxMemberHomeWorkDao.queryCount(gsId,schoolId, studentNo, homeworkId, beginTime, endTime);
	}
	
	public int queryWxMemberHomeWorkPassCount(String gsId,String schoolId,int homeworkId,String beginTime,
			String endTime,double minScore){
		return wxMemberHomeWorkDao.queryPassCount(gsId, schoolId, homeworkId, beginTime, endTime, minScore);
	}
	
	public WxMemberHomeWork queryWxMemberHomeWork(String studentNo,String signId) {
		return wxMemberHomeWorkDao.queryOne(studentNo, signId);
	}
	
	public WxMemberHomeWork queryLastWxMemberHomeWork(String schoolId,String studentNo) {
		return wxMemberHomeWorkDao.queryLastOne(schoolId, studentNo);
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
	
	public void downloadWorkerItemsReport(List<WxMemberHomeWork> items,GdJudgeItems gwi,HttpServletResponse response) {
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
			titles[6] = Constant.ZONGHECHNEGJI;
			for (int i = 0 ; i < ites.length; i ++ ) {
				titles[7+i] = ites[i];
			}
		}else {
			titles = new String[]{"工地名称","试卷","身份证号码","工人姓名","考试成绩","考试时间",Constant.ZONGHECHNEGJI};
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
						for (int i=7 ; i< finalTitiles.length; i++) {
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
	
	public PageResult queryCardMake(String tanentId,String cardNo,String name, int status,int pageIndex,int pageSize) {
		List<GdCardMake> registers = gdCardMakeDao.queryList(tanentId,cardNo, name, status, pageIndex, pageSize);
		int count = gdCardMakeDao.queryCount(tanentId,cardNo, name, status);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public void make(GdCardMake cardMake) {
		gdCardMakeDao.make(cardMake);
	}
	
	public GdCardMake queryCardMake(String tanentId,String cardNo) {
		return gdCardMakeDao.queryOne(tanentId,cardNo);
	}
	
	public void deleteCardMake(String tanentId,String cardNo) {
		gdCardMakeDao.delete(tanentId,cardNo);
	}
	
}
