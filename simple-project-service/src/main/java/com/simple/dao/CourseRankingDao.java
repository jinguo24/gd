package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.CourseRanking;
import com.simple.model.CourseWatch;
@Repository
@DatabaseTemplate("st_all")
public class CourseRankingDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addCourseWatch(CourseWatch courseWatch) {
		this.sqlSession.insert("courseRanking.insert", courseWatch);
	}
	
	public CourseWatch queryByTanentIdAndCourse(String tanentId,String courseCode) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("courseCode", courseCode);
		return this.sqlSession.selectOne("courseRanking.queryByTanentIdAndCourse", param);
	}
	
	public void updateWatchCount(String tanentId,String courseCode) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("courseCode", courseCode);
		this.sqlSession.update("courseRanking.updateWatchCount",param);
	}
	
	public List<CourseRanking> query(int pageIndex,int pageSize) {
		Map param = new HashMap();
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("courseRanking.query",param);
	}
	
	public int queryCount() {
		Map param = new HashMap();
		return this.sqlSession.selectOne("courseRanking.queryCount",param);
	}
}
