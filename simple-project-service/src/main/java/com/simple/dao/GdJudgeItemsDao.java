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
import com.simple.model.GdJudgeItems;
import com.simple.model.GdSign;

@Repository
@DatabaseTemplate("st_all")
public class GdJudgeItemsDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdJudgeItems(GdJudgeItems homeworkItems) {
		this.sqlSession.insert("gdhomeworkItems.insert", homeworkItems);
	}
	
	public void updateGdJudgeItems(GdJudgeItems homeworkItems) {
		this.sqlSession.insert("gdhomeworkItems.update", homeworkItems);
	}
	
	public GdJudgeItems queryOne(String tanentId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		return this.sqlSession.selectOne("gdhomeworkItems.queryOne",param);
	}
	
	public void delete (String tanentId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		this.sqlSession.delete("gdhomeworkItems.delete",param);
	}
}
