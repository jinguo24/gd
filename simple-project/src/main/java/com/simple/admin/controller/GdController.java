package com.simple.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.constant.Constant;
import com.simple.model.ClassRegister;
import com.simple.model.GdCardMake;
import com.simple.model.GdHomeWorkWorkersItem;
import com.simple.model.GdJudgeItems;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.model.WxHomeWork;
import com.simple.model.WxMemberHomeWork;
import com.simple.model.api.GdSingReport;
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
	
	private String scoreToJudge(int score) {
		switch (score) {
		case 70:
			return "不及格";
		case 80:
			return "及格";
		case 90:
			return "良好";
		case 100:
			return "优秀";
		default:
			return "优秀";
		}
	}
	@RequestMapping(value = "personReport",method=RequestMethod.GET)
	public String studentItemsInfo(String gsid,String cardNo,HttpServletRequest request, HttpServletResponse response) {
		GdSignWorkers gsw = gdService.querySignWorker(gsid, cardNo);
		if ( null == gsw ) {
			return "/gd/noworker";
		}
		ClassRegister cr = classRegistorService.getClassRegister(gsw.getTanentId(), null);
		request.setAttribute("tanentName", null == cr ? "":cr.getJsmc());
		request.setAttribute("gsw", gsw);
		
		GdJudgeItems gji = gdService.queryGdJudgeItem(gsw.getTanentId());
		if ( null != gji) {
			String[] itemNames = gji.getItemNameArray();
			if ( null != itemNames) {
				String itemJson = gsw.getItemJson();
				Map<String,String> judgeItems = new HashMap<String,String>();
				if (!StringUtils.isEmpty(itemJson)) {
					String[] items = itemJson.split(",");
					if ( null != items) {
						for (int i = 0 ; i < items.length; i ++) {
							String[] kvs = items[i].split(":");
							if ( null != kvs && kvs.length==2) {
								judgeItems.put(kvs[0], scoreToJudge(Integer.parseInt(kvs[1])));
							}
						}
					}
				}
				Map<String,String> judgeConfigItems = new HashMap<String,String>();
				for (int i = 0 ; i < itemNames.length ; i ++) {
					String itemName = itemNames[i];
					String iv = judgeItems.get(itemName);
					if (StringUtils.isEmpty(iv)) {
						judgeConfigItems.put(itemName, "优秀");
					}else {
						judgeConfigItems.put(itemName, iv);
					}
				}
				request.setAttribute("judgeItems", judgeConfigItems);
			}
		}
		request.setAttribute("zonghe", scoreToJudge(gsw.getZonghe()));
		//查询考试
		WxMemberHomeWork wmhw = gdService.queryLastWxMemberHomeWork(gsw.getTanentId(), gsw.getCardNo());
		if ( null == wmhw || null == wmhw.getCreateTime()) {
			request.setAttribute("homeworkTime", "");
		}else {
			request.setAttribute("homeworkTime", DateUtil.date2AllString(wmhw.getCreateTime()));
		}
		String jkcj = "合格";//机考成绩
		if (null != wmhw && (!wmhw.isPassed())) {
			jkcj = "不合格";
		}
		request.setAttribute("jkcj", jkcj);
		
		GdCardMake gcm = gdService.queryCardMake(gsw.getTanentId(),gsw.getCardNo());
		if ( null == gcm || null == gcm.getMakeTime()) {
			request.setAttribute("maketime", "");
		}else {
			request.setAttribute("maketime", DateUtil.date2AllString(gcm.getMakeTime()));
		}
		return "/gd/personReport";
	}
	
	@RequestMapping(value = "signReport",method=RequestMethod.GET)
	public String signItemsInfo(String gsid,HttpServletRequest request, HttpServletResponse response) {
		GdSingReport gsr = new GdSingReport();
		GdSign gs = gdService.querySignById(gsid);
		if (null == gs) {
			return "/gd/nosign";
		}
		gsr.setSignCounts(gdService.queryWorkersCount(gsid, null, null, null, null, null));
		//查询考试
		gsr.setJudgePass(gdService.queryJudgeCounts(gsid, -999));
		gsr.setBujigeCounts(gdService.queryJudgeCounts(gsid, 70));
		gsr.setJigeCounts(gdService.queryJudgeCounts(gsid, 80));
		gsr.setLianghaoCounts(gdService.queryJudgeCounts(gsid, 90));
		gsr.setYouxiuCounts(gdService.queryJudgeCounts(gsid, 100));
		gsr.setHomeworkCounts(gdService.queryWxMemberHomeWorkCount(gsid, gs.getTanentId(), null, 0, null, null));
		gsr.setJudgeDate(DateUtil.date2String(new Date()));
		gsr.setAllpersons(gdService.queryWxMemberHomeWorkCount(null, gs.getTanentId(), null, 0, null, null));
		gsr.setHomeworkPass(gdService.queryWxMemberHomeWorkPassCount(gsid, gs.getTanentId(), 0, null, null));
		ClassRegister cr = classRegistorService.getClassRegister(gs.getTanentId(), null);
		if ( null != cr ) {
			gsr.setTanentName(cr.getJsmc());
		}
		request.setAttribute("gsr", gsr);
		return "/gd/signReport";
	}
	
}
