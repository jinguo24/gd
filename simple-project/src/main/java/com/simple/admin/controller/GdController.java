package com.simple.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.constant.Constant;
import com.simple.model.ClassRegister;
import com.simple.model.GdCardMake;
import com.simple.model.GdJudgeItems;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.service.ClassRegistorService;
import com.simple.service.GdService;
@Controller
@RequestMapping(value = "/gd")
public class GdController {
	
	private static final Logger log = LoggerFactory.getLogger(GdController.class);
	@Autowired
	GdService gdService;
	@Autowired
	ClassRegistorService classRegistorService;
	
	@RequestMapping(value = "judgeItem",method=RequestMethod.GET)
	@ResponseBody
	public String judgeItem(String tanentId,int page,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdJudgeItems gs = gdService.queryGdJudgeItem(tanentId);
			PageResult pr = null;
			if (null != gs) {
				ClassRegister cr = classRegistorService.getClassRegister(tanentId, null);
				if (null != cr) {
					gs.setTanentName(cr.getJsmc());
				}
				List l = new ArrayList();
				l.add(gs);
				pr = new PageResult(1,pageSize,page,l);
			}else {
				pr = new PageResult(0,pageSize,page,null);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "currentJudgeItem",method=RequestMethod.GET)
	@ResponseBody
	public String currentJudgeItem(int page,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdJudgeItems gs = gdService.queryGdJudgeItem(LoginUserUtil.getLeaseholderId(request));
			PageResult pr = null;
			if (null != gs) {
				gs.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
				List l = new ArrayList();
				l.add(gs);
				pr = new PageResult(1,pageSize,page,l);
			}else {
				pr = new PageResult(0,pageSize,page,null);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updateCurrentJudgeItem",method=RequestMethod.POST)
	@ResponseBody
	public String updateCurrentJudgeItem(String items,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdJudgeItems gs = gdService.queryGdJudgeItem(LoginUserUtil.getLeaseholderId(request));
			if ( null == gs) {
				gs = new GdJudgeItems();
				gs.setTanentId(LoginUserUtil.getLeaseholderId(request));
				gs.setItemNames(items);
				gdService.addGdJudgeItems(gs);
			}else {
				gs.setItemNames(items);
				gdService.updateGdJudgeItems(gs);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updateJudgeItem",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkItems(String tanentId,String items,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdJudgeItems gs = gdService.queryGdJudgeItem(tanentId);
			if ( null == gs) {
				gs = new GdJudgeItems();
				gs.setTanentId(tanentId);
				gs.setItemNames(items);
				gdService.addGdJudgeItems(gs);
			}else {
				gs.setItemNames(items);
				gdService.updateGdJudgeItems(gs);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "workers",method=RequestMethod.GET)
	@ResponseBody
	public String workers(String gsid,String date,String tanentId,String groupName,String cardNo,String leaderName,Integer page,Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr =gdService.queryWorkers(gsid, LoginUserUtil.getLeaseholderId(request), groupName, cardNo, DateUtil.stringToDate(date), leaderName, page, pageSize);
			if (null != pr && null != pr.getDatas()) {
				for (int i = 0 ; i < pr.getDatas().size(); i++) {
					GdSignWorkers sw = (GdSignWorkers) pr.getDatas().get(i);
					if (StringUtils.isEmpty(sw.getItemJson())) {
						GdJudgeItems ghi = gdService.queryGdJudgeItem(sw.getTanentId());
						if (null != ghi && (null != ghi.getItemNameArray())) {
							StringBuffer sb = new StringBuffer();
							String[] items = ghi.getItemNameArray();
							for (int j = 0 ; j < items.length; j++ ) {
								sb.append(items[j]+":0,");
							}
							sb.append(Constant.ZONGHECHNEGJI+":0");
							sw.setItemJson(sb.toString());
						}
					}else {
						sw.setItemJson(sw.getItemJson()+","+Constant.ZONGHECHNEGJI+":"+sw.getZonghe());
					}
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "signJudge",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkWorkersItem(String gsid,String cardNo,String itemJson,HttpServletRequest request, HttpServletResponse response) {
		try {
			//查询是否已经制证，如果已经制证，则不能修改
			GdSign sign = gdService.querySignById(gsid);
			if (null == sign) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"签到记录不存在", null);
			}
			GdSignWorkers gi = gdService.querySignWorker(gsid, cardNo);
			if ( null == gi) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"该身份证这次签到记录不存在", null);
			}
			
			GdCardMake cardMake = gdService.queryCardMake(sign.getTanentId(),cardNo);
			if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"已经制过证，不能修改", null);
			}
			if (!StringUtils.isEmpty(itemJson)) {
				StringBuffer realItemJson = new StringBuffer();
				int zonghe = 0;
				String[] ivs = itemJson.split(",");
				if ( null != ivs ) {
					for (String iv : ivs) {
						String[] is = iv.split(":");
						if (!Constant.ZONGHECHNEGJI.equals(is[0])) {
							realItemJson.append(is[0]).append(":").append(Integer.parseInt(is[1])).append(",");
						}else {
							zonghe = Integer.parseInt(is[1]);
						}
					}
				}
				if (realItemJson.length()>0) {
					gi.setItemJson(realItemJson.toString().substring(0,realItemJson.length()-1));
				}
				gi.setZonghe(zonghe);
			}
			gi.setJudgeTime(new Date());
			gdService.updateSignWorker(gi);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
//	@RequestMapping(value = "homeworkWorkersItemList",method=RequestMethod.GET)
//	@ResponseBody
//	public String homeworkWorkersItemList(String cardNo,Integer homeworkId,String beginTime,String endTime, int page,
//			int pageSize,HttpServletRequest request, HttpServletResponse response) {
//		try {
//			PageResult pr = gdService.queryWxMemberHomeWork(null,LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, page, pageSize);
//			if ( null != pr && null != pr.getDatas()) {
//				for (int i =0 ;i < pr.getDatas().size(); i ++) {
//					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
//					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
//					//查询评分内容
//					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
//					if ( null != wi) {
//						gwwi.setContent(wi.getItemJson());
//					}
//					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
//					if (null != whw) {
//						gwwi.setHomeworkName(whw.getTitle());
//					}
//					if (StringUtils.isEmpty(gwwi.getContent())) {
//						GdJudgeItems ghi = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request));
//						if (null != ghi && (null != ghi.getItemNameArray())) {
//							StringBuffer sb = new StringBuffer();
//							String[] items = ghi.getItemNameArray();
//							for (int j = 0 ; j < items.length; j++ ) {
//								sb.append(items[j]+":0,");
//							}
//							sb.append(Constant.ZONGHECHNEGJI+":0");
//							gwwi.setContent(sb.toString());
//						}
//					}else {
//						gwwi.setContent(gwwi.getContent()+","+Constant.ZONGHECHNEGJI+":"+wi.getZonghe());
//					}
//					//TODO 查询是否已经制证
//				}
//			}
//			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
//		}catch(Exception e) {
//			log.error("gd kaike error.",e);
//			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
//		}
//	}
//
//	@RequestMapping(value = "homeworkWorkersItemExport",method=RequestMethod.GET)
//	public void homeworkWorkersItemExport(String cardNo,Integer homeworkId,String beginTime,String endTime,HttpServletRequest request, HttpServletResponse response) {
//		try {
//			PageResult pr = gdService.queryWxMemberHomeWork(null,LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, 1, 60000);
//			if ( null != pr && null != pr.getDatas()) {
//				for (int i =0 ;i < pr.getDatas().size(); i ++) {
//					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
//					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
//					//查询评分内容
//					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
//					if ( null != wi) {
//						gwwi.setContent(wi.getItemJson());
//					}
//					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
//					if (null != whw) {
//						gwwi.setHomeworkName(whw.getTitle());
//					}
//				}
//				gdService.downloadWorkerItems(pr.getDatas(),response);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@RequestMapping(value = "homeworkWorkersItemInfo",method=RequestMethod.GET)
//	@ResponseBody
//	public String homeworkWorkersItemInfo(String cardNo,String gdSignId,HttpServletRequest request, HttpServletResponse response) {
//		try {
//			GdHomeWorkWorkersItem gwwi = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
//			gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
//			WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
//			if (null != whw) {
//				gwwi.setHomeworkName(whw.getTitle());
//			}
//			//查询分数和考试时间
//			WxMemberHomeWork wmhw = gdService.queryWxMemberHomeWork(LoginUserUtil.getLeaseholderId(request), cardNo, gwwi.getGdSignId(), gwwi.getHomeworkId());
//			if ( null != wmhw) {
//				gwwi.setScore(wmhw.getScore());
//				gwwi.setHomeworkTime(wmhw.getCreateTime());
//			}
//			//查询是否已经制证
//			GdCardMake cardMake = gdService.queryCardMake(cardNo, gwwi.getHomeworkId());
//			if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
//				gwwi.setHasMake(1);
//			}
//			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gwwi);
//		}catch(Exception e) {
//			log.error("gd kaike error.",e);
//			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
//		}
//	}
//	
//	@RequestMapping(value = "updateHomeworkWorkersItem",method=RequestMethod.POST)
//	@ResponseBody
//	public String updateHomeworkWorkersItem(String gdSignId,String cardNo,String itemJson,HttpServletRequest request, HttpServletResponse response) {
//		try {
//			//查询是否已经制证，如果已经制证，则不能修改
//			GdHomeWorkWorkersItem gi = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
//			GdCardMake cardMake = gdService.queryCardMake(cardNo, gi.getHomeworkId());
//			if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
//				return AjaxWebUtil.sendAjaxResponse(request, response, false,"已经制过证，不能修改", null);
//			}
//			if (!StringUtils.isEmpty(itemJson)) {
//				StringBuffer realItemJson = new StringBuffer();
//				int zonghe = 0;
//				String[] ivs = itemJson.split(",");
//				if ( null != ivs ) {
//					for (String iv : ivs) {
//						String[] is = iv.split(":");
//						if (!Constant.ZONGHECHNEGJI.equals(is[0])) {
//							realItemJson.append(is[0]).append(":").append(Integer.parseInt(is[1])).append(",");
//						}else {
//							zonghe = Integer.parseInt(is[1]);
//						}
//					}
//				}
//				if (realItemJson.length()>0) {
//					gi.setItemJson(realItemJson.toString().substring(0,realItemJson.length()-1));
//				}
//				gi.setZonghe(zonghe);
//			}
//			gi.setCreateTime(new Date());
//			gdService.updateGdHomeWorkWorkersItem(gi);
//			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
//		}catch(Exception e) {
//			log.error("gd kaike error.",e);
//			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
//		}
//	}
//	
//	@RequestMapping(value = "homeworkWorkersItemReportExport",method=RequestMethod.GET)
//	public String homeworkWorkersItemReportExport(String cardNo,Integer homeworkId,String beginTime,String endTime,HttpServletRequest request, HttpServletResponse response) {
//		try {
//			if (null == homeworkId || homeworkId <= 0) {
//				return "请选择问卷导出";
//			}
//			PageResult pr = gdService.queryWxMemberHomeWork(null,LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, 1, 60000);
//			if ( null != pr && null != pr.getDatas()) {
//				for (int i =0 ;i < pr.getDatas().size(); i ++) {
//					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
//					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
//					//查询评分内容
//					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
//					if ( null != wi) {
//						gwwi.setContent(wi.getItemJson());
//					}
//					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
//					if (null != whw) {
//						gwwi.setHomeworkName(whw.getTitle());
//					}
//				}
//				GdJudgeItems gwi = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request));
//				gdService.downloadWorkerItemsReport(pr.getDatas(),gwi,response);
//			}
//			return null;
//		}catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
}
