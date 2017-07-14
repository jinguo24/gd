package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.WxHomeWork;

@Repository
@DatabaseTemplate("st_all")
public class WxHomeWorkDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public List<WxHomeWork> query(String schoolId,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("wxhomework.queryBySchoolId",param);
	}
	
	public int queryCount(String schoolId) {
		Map param = new HashMap();
		param.put("schoolId", schoolId);
		return this.sqlSession.selectOne("wxhomework.queryCount",param);
	}
	
	public WxHomeWork queryById(int id) {
		return this.sqlSession.selectOne("wxhomework.queryById",id);
	}
}
