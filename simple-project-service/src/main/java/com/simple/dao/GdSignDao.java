package com.simple.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
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
}
