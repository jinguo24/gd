package com.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.RuZhuSchoolDao;
import com.simple.model.RuZhuSchool;

@Service
public class RuZhuSchoolService {

	@Autowired
	private RuZhuSchoolDao ruzhuSchoolDao;
	
	
	public void addOrUpdate(RuZhuSchool ruzhuSchool) {
		RuZhuSchool rs = query();
		if ( null == rs ) {
			ruzhuSchoolDao.add(ruzhuSchool);
		}else {
			ruzhuSchool.setId(rs.getId());
			ruzhuSchoolDao.update(ruzhuSchool);
		}
	}
	
	public RuZhuSchool query() {
		return ruzhuSchoolDao.query();
	}
	
	
}
