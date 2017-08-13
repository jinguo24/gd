package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.CourseXl;
import com.simple.model.api.KeChengXiLie;

@Repository
@DatabaseTemplate("st_all")
public class CourseXlDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public CourseXl getById(int id) {
		return this.sqlSession.selectOne("courseXl.queryById", id);
	}
	
	public CourseXl getByCode(String code) {
		return this.sqlSession.selectOne("courseXl.queryByCode", code);
	}
	
	public void addCourseXl(CourseXl course) {
		this.sqlSession.insert("courseXl.insert", course);
	}
	
	public List<CourseXl> query(boolean isRoot,String xlbh,String xlmc,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("kcxlbh", xlbh);
		param.put("kcxlmc", xlmc);
		if (isRoot) {
			param.put("isRoot", 1);
		}else {
			param.put("isRoot", 0);
		}
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("courseXl.query",param);
	}
	
	public List<CourseXl> queryByParentCode(String code) {
		Map param = new HashMap();
		param.put("code", code);
		return this.sqlSession.selectList("courseXl.queryByParentCode",param);
	}
	
	public int queryCount(String xlbh,String xlmc) {
		Map param = new HashMap();
		param.put("kcxlbh", xlbh);
		param.put("kcxlmc", xlmc);
		return this.sqlSession.selectOne("courseXl.queryCount",param);
	}
	
	public void updateCourseXl(CourseXl course) {
		this.sqlSession.update("courseXl.update", course);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("courseXl.delete", id);
	}
	
	public List<KeChengXiLie> queryKeChengXiLie(List<String> xlbhList,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("xlbhlist", xlbhList);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("courseXl.queryXiLie",param);
	}
	
	public int queryKeChengXiLieCount(List<String> xlbhList) {
		Map param = new HashMap();
		param.put("xlbhlist", xlbhList);
		return this.sqlSession.selectOne("courseXl.queryXiLieCount",param);
	}
	
	public List<KeChengXiLie> queryKeChengXiLieByParent(String parentCode) {
		Map param = new HashMap();
		param.put("code", parentCode);
		return this.sqlSession.selectList("courseXl.queryXiLieByParent",param);
	}
	
}
