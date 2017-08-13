package com.simple.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.simple.dao.CourseDao;
import com.simple.dao.CourseRankingDao;
import com.simple.dao.CourseXlDao;
import com.simple.model.Course;
import com.simple.model.CourseForm;
import com.simple.model.CourseRanking;
import com.simple.model.CourseWatch;
import com.simple.model.CourseXl;
import com.simple.model.PageResult;
import com.simple.model.api.KeCheng;
import com.simple.model.api.KeChengInfo;
import com.simple.model.api.KeChengXiLie;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseXlDao courseXlDao;
	@Autowired
	private CourseRankingDao courseRankingDao;
	
	
	public void addCourse(CourseForm form,String loginName)throws Exception{
		if ( null != form ) {
			Course course = form.castToCourse();
			Course queryCourse = new Course();
			if (StringUtils.isEmpty(course.getCode())) {
				throw new Exception("课程编号不能为空!");
			}
			//if (StringUtils.isEmpty(course.getTeacherWenjuan())) {
			//	throw new Exception("教师问卷不能为空!");
			//}
			//if (StringUtils.isEmpty(course.getStudentWenjuan())) {
			//	throw new Exception("学生问卷不能为空!");
			//}
			queryCourse.setCode(course.getCode());
			int count =  courseDao.queryCount(queryCourse);
			if (count>=1) {
				throw new Exception("课程编号重复!");
			}
			course.setCjr(loginName);
			course.setCjsj(new Date());
			course.setFlag(1);
			courseDao.addCourse(course);
		}
	}
	
	public void update(Course c, CourseForm form) throws Exception{
		Course cf = form.castToCourse();
		//if (StringUtils.isEmpty(cf.getTeacherWenjuan())) {
		//	throw new Exception("教师问卷不能为空!");
		//}
		//if (StringUtils.isEmpty(cf.getStudentWenjuan())) {
		//	throw new Exception("学生问卷不能为空!");
		//}
		cf.setCjsj(c.getCjsj());
		cf.setCode(c.getCode());
		BeanUtils.copyProperties(c, cf);
		c.setFlag(c.getFlag()+1);
		courseDao.updateCourse(c);
	}
	
	public List<CourseXl> queryXl(boolean isRoot,String xlbh,String xlmc) {
		return courseXlDao.query(isRoot,xlbh, xlmc, 1, 1000);
	}
	
	public List<CourseXl> queryXlByParentCode(String code) {
		return courseXlDao.queryByParentCode(code);
	}
	
	public PageResult query(Course register,int pageIndex,int pageSize) {
		List<Course> registers = queryList(register, pageIndex, pageSize);
		int count = courseDao.queryCount(register);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public List<Course> queryList(Course register,int pageIndex,int pageSize) {
		return courseDao.query(register, pageIndex, pageSize);
	}
	
	public List<KeCheng> queryKeChengList(List<String> courseIds,String xlbh,String cxlbh,String njbh,int pageIndex,int pageSize) {
		return courseDao.queryKeCheng( courseIds,xlbh,cxlbh,njbh,pageIndex,pageSize);
	}
	
	public List<KeCheng> queryMainKeChengList(List<String> courseIds,String xlbh,String njbh,int pageIndex,int pageSize) {
		return courseDao.queryMainKeCheng( courseIds,xlbh,njbh,pageIndex,pageSize);
	}
	
	public PageResult queryKeCheng(List<String> courseIds,String xlbh,String cxlbh,String njbh,int pageIndex,int pageSize) {
		List<KeCheng> kechengs = queryKeChengList(courseIds,xlbh,cxlbh,njbh,pageIndex,pageSize);
		int count = courseDao.queryKeChengCount(courseIds, xlbh,cxlbh,njbh);
		return new PageResult(count,pageSize,pageIndex,kechengs);
	}
	
	public KeChengInfo queryKeChengInfo(String code) {
		return courseDao.queryKeChengInfo( code);
	}
	
	public List<String> getXiLieBHList(List<String> courseBhList) {
		return courseDao.getXiLieBHList(courseBhList);
	}
	
	public List<KeChengXiLie> queryKeChengXiLieList(List<String> xlbhList,int pageIndex,int pageSize) {
		return courseXlDao.queryKeChengXiLie(xlbhList,pageIndex,pageSize);
	}
	
	public List<KeChengXiLie> queryKeChengXiLieByParent(String parentCode) {
		return courseXlDao.queryKeChengXiLieByParent(parentCode);
	}
	
	public PageResult queryKeChengXiLie(List<String> xlbhList,int pageIndex,int pageSize) {
		List<KeChengXiLie> xilies = queryKeChengXiLieList(xlbhList,pageIndex,pageSize);
		int count = courseXlDao.queryKeChengXiLieCount(xlbhList);
		return new PageResult(count,pageSize,pageIndex,xilies);
	}
	
	public List<String> getCourseBhList(String xlbh) {
		return courseDao.getCourseIds(xlbh);
	}
	
	public void updateCourseWatch(String tanentId,String courseCode) {
		CourseWatch courseWatch = courseRankingDao.queryByTanentIdAndCourse(tanentId, courseCode);
		if ( null == courseWatch ) {
			courseWatch = new CourseWatch();
			courseWatch.setCourseCode(courseCode);
			courseWatch.setTanentId(tanentId);
			courseWatch.setWatchCount(1);
			courseRankingDao.addCourseWatch(courseWatch);
		}else {
			courseRankingDao.updateWatchCount(tanentId, courseCode);
		}
	}
	
	public PageResult queryCourseRanking(int pageIndex,int pageSize) {
		List<CourseRanking> rankings = courseRankingDao.query(pageIndex, pageSize);
		if ( null != rankings ) {
			for (int i = 0 ; i < rankings.size() ; i++ ) {
				CourseRanking cr = rankings.get(i);
				KeChengInfo ki = courseDao.queryKeChengInfo(cr.getCourseCode());
				if ( null != ki ) {
					cr.setCourseName(ki.getName());
				}
			}
		}
		int count = courseRankingDao.queryCount();
		return new PageResult(count,pageSize,pageIndex,rankings);
	}
	
	public void updateCourseXl(CourseXl coursexl) {
		courseXlDao.updateCourseXl(coursexl);
	}
	
	public void addCourseXl(CourseXl coursexl) {
		courseXlDao.addCourseXl(coursexl);
	}
	
	public CourseXl queryXlById(int id) {
		return courseXlDao.getById(id);
	}
	
	public CourseXl queryXlByCode(String code) {
		return courseXlDao.getByCode(code);
	}
	
	public void deleteXlById(int id) {
		courseXlDao.deleteById(id);
	}
}
