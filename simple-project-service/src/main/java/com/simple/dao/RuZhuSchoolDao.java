package com.simple.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.RuZhuSchool;

@Repository
@DatabaseTemplate("st_all")
public class RuZhuSchoolDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void add(RuZhuSchool ruzhuSchool) {
		this.sqlSession.update("ruzhuschool.insert",ruzhuSchool);
	}
	
	public void update(RuZhuSchool ruzhuSchool) {
		this.sqlSession.update("ruzhuschool.update",ruzhuSchool);
	}
	
	public RuZhuSchool query() {
		return this.sqlSession.selectOne("ruzhuschool.query");
	}
}
