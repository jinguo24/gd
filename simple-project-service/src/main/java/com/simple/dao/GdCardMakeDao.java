package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.GdCardMake;

@Repository
@DatabaseTemplate("st_all")
public class GdCardMakeDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdCardMake(GdCardMake cardMake) {
		this.sqlSession.update("gdcardmake.insert",cardMake);
	}
	
	public List<GdCardMake> queryList(String tanentId,String cardNo,String name, int status,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("name", name);
		param.put("status", status);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdcardmake.query",param);
	}

	public int queryCount(String tanentId,String cardNo,String name, int status) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("name", name);
		param.put("status", status);
		return this.sqlSession.selectOne("gdcardmake.queryCount",param);
	}
	
	public void make(GdCardMake cardMake) {
		this.sqlSession.update("gdcardmake.update",cardMake);
	}
	
	public GdCardMake queryOne(String tanentId,String cardNo) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		return this.sqlSession.selectOne("gdcardmake.queryOne",param);
	}
	
	public void delete(String tanentId,String cardNo) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		this.sqlSession.delete("gdcardmake.delete",param);
	}
}
