package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
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
	
	
	public List<GdSignWorkers> query(String gsid,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("gdSignId", gsid);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdsignworkers.query",param);
	}
	
	public int queryCount(String gsid) {
		Map param = new HashMap();
		param.put("gdSignId", gsid);
		return this.sqlSession.selectOne("gdsignworkers.queryCount",param);
	}
}
