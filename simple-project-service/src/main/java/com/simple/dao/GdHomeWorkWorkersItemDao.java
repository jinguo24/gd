package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.GdHomeWorkWorkersItem;

@Repository
@DatabaseTemplate("st_all")
public class GdHomeWorkWorkersItemDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
		this.sqlSession.insert("gdhomeworkWorkersItem.insert", homeworkWorkersItem);
	}
	
	public void updateGdHomeWorkWorkersItem(GdHomeWorkWorkersItem homeworkWorkersItem) {
		this.sqlSession.insert("gdhomeworkWorkersItem.update", homeworkWorkersItem);
	}
	
	public List<GdHomeWorkWorkersItem> query(String gsid,String tanentId,String cardNo,int homeworkId,String beginTime,String endTime,int zongheScore,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("gsId", gsid);
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("homeworkId", homeworkId);
		param.put("zongheScore", zongheScore);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		if (pageIndex < 1) {
			pageIndex =1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("gdhomeworkWorkersItem.query",param);
	}
	
	public int queryCount(String gsid,String tanentId,String cardNo,int homeworkId,String beginTime,String endTime,int zongheScore) {
		Map param = new HashMap();
		param.put("gsId", gsid);
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("homeworkId", homeworkId);
		param.put("zongheScore", zongheScore);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("gdhomeworkWorkersItem.queryCount",param);
	}
	
	public int queryPassCount(String gsid,String tanentId,String cardNo,int homeworkId,String beginTime,String endTime) {
		Map param = new HashMap();
		param.put("gsId", gsid);
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("homeworkId", homeworkId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("gdhomeworkWorkersItem.queryPassCount",param);
	}
	
	public int queryUnPassCount(String gsid,String tanentId,String cardNo,int homeworkId,String beginTime,String endTime) {
		Map param = new HashMap();
		param.put("gsId", gsid);
		param.put("tanentId", tanentId);
		param.put("cardNo", cardNo);
		param.put("homeworkId", homeworkId);
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		return this.sqlSession.selectOne("gdhomeworkWorkersItem.queryUnPassCount",param);
	}
	
	public GdHomeWorkWorkersItem queryOne(String gdSignId,String cardNo) {
		Map param = new HashMap();
		param.put("gdSignId", gdSignId);
		param.put("cardNo", cardNo);
		return this.sqlSession.selectOne("gdhomeworkWorkersItem.queryOne",param);
	}
	
	public void delete (String gdSignId,String cardNo) {
		Map param = new HashMap();
		param.put("gdSignId", gdSignId);
		param.put("cardNo", cardNo);
		this.sqlSession.delete("gdhomeworkWorkersItem.delete",param);
	}
}
