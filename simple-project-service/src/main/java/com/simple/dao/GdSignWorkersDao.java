package com.simple.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.common.util.DateUtil;
import com.simple.model.GdSignWorkers;

@Repository
@DatabaseTemplate("st_all")
public class GdSignWorkersDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdSignWorkers(GdSignWorkers gdSignWorkers) {
		this.sqlSession.insert("gdsignworkers.insert", gdSignWorkers);
	}
	
	public GdSignWorkers queryBySC(String gdSignId,String cardNo) {
		Map param = new HashMap();
		param.put("gdSignId", gdSignId);
		param.put("cardNo", cardNo);
		return this.sqlSession.selectOne("gdsignworkers.queryBySC",param);
	}
	
	public GdSignWorkers queryByTCC(String tanentId,String cardNo,Date createDate) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("createDate", DateUtil.date2String(createDate));
		return this.sqlSession.selectOne("gdsignworkers.queryBySC",param);
	}
	
	public void updateGdSignWorker(GdSignWorkers gdSignWorkers) {
		this.sqlSession.update("gdsignworkers.update", gdSignWorkers);
	}
	
	
	public List<GdSignWorkers> query(String gsid,String tanentId,String groupName,String cardNo,Date date,
			String leaderName,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("gdSignId", gsid);
		param.put("tanentId", tanentId);
		param.put("groupName", groupName);
		param.put("cardNo", cardNo);
		param.put("createDate", date == null ? null:DateUtil.date2String(date));
		param.put("leaderName", leaderName);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdsignworkers.query",param);
	}
	
	public int queryCount(String gsid,String tanentId,String groupName,String cardNo,Date date,String leaderName) {
		Map param = new HashMap();
		param.put("gdSignId", gsid);
		param.put("tanentId", tanentId);
		param.put("groupName", groupName);
		param.put("cardNo", cardNo);
		if (null != date) {
			param.put("createDate", DateUtil.date2String(date));	
		}else {
			param.put("createDate", null);
		}
		param.put("leaderName", leaderName);
		return this.sqlSession.selectOne("gdsignworkers.queryCount",param);
	}
	
	public int queryCount(String gsid,int zonghe) {
		Map param = new HashMap();
		param.put("gdSignId", gsid);
		param.put("zonghe", zonghe);
		return this.sqlSession.selectOne("gdsignworkers.queryJudgeCount",param);
	}
}
