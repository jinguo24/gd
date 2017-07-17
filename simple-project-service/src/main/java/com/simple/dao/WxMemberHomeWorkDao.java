package com.simple.dao;

import java.util.HashMap;
import java.util.List;
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
	
	public List<WxMemberHomeWork> queryList(String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		param.put("homeworkId", homeworkId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		if (pageIndex<1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("wxmemberhomework.query",param);
	}
	
	public int queryCount(String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		param.put("homeworkId", homeworkId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("wxmemberhomework.queryCount",param);
	}
}
