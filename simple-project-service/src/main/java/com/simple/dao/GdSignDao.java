package com.simple.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.GdSign;

@Repository
@DatabaseTemplate("st_all")
public class GdSignDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdSign(GdSign gdSign) {
		this.sqlSession.insert("gdsign.insert", gdSign);
	}
	
	public GdSign queryByTHD(String tanentId,int homeworkId,Date date) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("homeworkId", homeworkId);
		param.put("createDate", date);
		return this.sqlSession.selectOne("gdsign.queryByTHD",param);
	}
}
