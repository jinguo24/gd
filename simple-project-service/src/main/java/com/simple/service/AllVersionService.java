package com.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.AllVersionDao;

@Service
public class AllVersionService {

	@Autowired
	private AllVersionDao versionDao;
	
	
	public void updateClassVersion() {
		versionDao.updateVersion(1);
	}
	
	public void updateCourseVersion() {
		versionDao.updateVersion(2);
	}
	
	public int queryClassVersion() {
		return versionDao.queryVersion(1);
	}
	
	public int queryCourseVersion() {
		return versionDao.queryVersion(2);
	}
	
}
