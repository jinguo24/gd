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
import com.simple.model.WxMemberHomeWork;

@Repository
@DatabaseTemplate("st_all")
public class WxMemberHomeWorkDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public WxMemberHomeWork queryOne(String studentNo,String signId) {
		Map param = new HashMap();
		param.put("studentNo", studentNo);
		param.put("signId", signId);
		return this.sqlSession.selectOne("wxmemberhomework.queryOne",param);
	}
	
	public WxMemberHomeWork queryLastOne(String schoolId,String studentNo) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		return this.sqlSession.selectOne("wxmemberhomework.queryLastOne",param);
	}
	
	public List<WxMemberHomeWork> queryList(String gsId,String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		param.put("homeworkId", homeworkId);
		param.put("gsId", gsId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		if (pageIndex<1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("wxmemberhomework.query",param);
	}
	
	public int queryCount(String gsId,String schoolId,String studentNo,int homeworkId,String beginTime,
			String endTime) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("studentNo", studentNo);
		param.put("homeworkId", homeworkId);
		param.put("gsId", gsId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("wxmemberhomework.queryCount",param);
	}
	
	public int queryPassCount(String gsId,String schoolId,int homeworkId,String beginTime,
			String endTime) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		param.put("homeworkId", homeworkId);
		param.put("gsId", gsId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("wxmemberhomework.queryPassCount",param);
	}
}
