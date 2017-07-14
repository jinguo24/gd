package com.simple.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.WxMemberHomeWork;

@Repository
@DatabaseTemplate("st_all")
public class WxMemberHomeWorkDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public WxMemberHomeWork queryOne(String schoolId,String studentNo,String signId,int homeworkId) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		param.put("signId", signId);
		param.put("homeworkId", homeworkId);
		return this.sqlSession.selectOne("wxmemberhomework.queryOne",param);
	}
}
