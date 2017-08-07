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
import com.simple.model.GdSign;

@Repository
@DatabaseTemplate("st_all")
public class GdSignDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdSign(GdSign gdSign) {
		this.sqlSession.insert("gdsign.insert", gdSign);
	}
	
	public GdSign queryByTGD(String tanentId,String groupName,Date date) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("groupName", groupName);
		param.put("createDate", DateUtil.date2String(date));
		return this.sqlSession.selectOne("gdsign.queryByTGD",param);
	}
	
	public GdSign queryById(String gsId) {
		return this.sqlSession.selectOne("gdsign.queryById",gsId);
	}
	
	public List<GdSign> queryList(String tanentId,String groupName,String date,String leaderName,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("groupName", groupName);
		param.put("createDate", date);
		param.put("leaderName", leaderName);
		if (pageIndex  < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdsign.query",param);
	}
	
	public int queryCount(String tanentId,String groupName,String date,String leaderName) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("groupName", groupName);
		param.put("createDate", date);
		param.put("leaderName", leaderName);
		return this.sqlSession.selectOne("gdsign.queryCount",param);
	}
}
