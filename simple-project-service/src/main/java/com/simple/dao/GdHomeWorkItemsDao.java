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
import com.simple.model.GdHomeWorkItems;
import com.simple.model.GdSign;

@Repository
@DatabaseTemplate("st_all")
public class GdHomeWorkItemsDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdHomeWorkItems(GdHomeWorkItems homeworkItems) {
		this.sqlSession.insert("gdhomeworkItems.insert", homeworkItems);
	}
	
	public void updateGdHomeWorkItems(GdHomeWorkItems homeworkItems) {
		this.sqlSession.insert("gdhomeworkItems.update", homeworkItems);
	}
	
	public List<GdHomeWorkItems> query(String tanentId,Integer homeworkId,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		if (pageIndex < 1) {
			pageIndex =1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdhomeworkItems.query",param);
	}
	
	public int queryCount(String tanentId,Integer homeworkId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		return this.sqlSession.selectOne("gdhomeworkItems.queryCount",param);
	}
	
	public GdHomeWorkItems queryOne(String tanentId,int homeworkId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("homeworkId", homeworkId);
		return this.sqlSession.selectOne("gdhomeworkItems.queryOne",param);
	}
	
	public void delete (String tanentId,String homeworkId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("homeworkId", homeworkId);
		this.sqlSession.delete("gdhomeworkItems.delete",param);
	}
}
