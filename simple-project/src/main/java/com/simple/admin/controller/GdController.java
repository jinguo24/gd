package com.simple.admin.controller;

import java.util.Date;

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

import com.alibaba.fastjson.JSONObject;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.GdCardMake;
import com.simple.model.GdHomeWorkItems;
import com.simple.model.GdHomeWorkWorkersItem;
import com.simple.model.PageResult;
import com.simple.model.WxHomeWork;
import com.simple.model.WxMemberHomeWork;
import com.simple.service.GdService;
@Controller
@RequestMapping(value = "/gd")
public class GdController {
	
	private static final Logger log = LoggerFactory.getLogger(GdController.class);
	@Autowired
	GdService gdService;
	
	@RequestMapping(value = "homeworks",method=RequestMethod.GET)
	@ResponseBody
	public String homeworks(int page,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult gs = gdService.queryHomeWorks(LoginUserUtil.getLeaseholderId(request), page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkItems",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkItems(Integer homeworkId,int page,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult gs = gdService.queryHomeworkItems(LoginUserUtil.getLeaseholderId(request), homeworkId, page, pageSize);
			if (null != gs && null != gs.getDatas()) {
				for (int i = 0 ; i < gs.getDatas().size();i++) {
					GdHomeWorkItems wh = (GdHomeWorkItems) gs.getDatas().get(i);
					wh.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
					WxHomeWork whw = gdService.queryWxHomeWork(wh.getHomeworkId());
					if (null != whw) {
						wh.setHomeworkName(whw.getTitle());
					}
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}

	@RequestMapping(value = "homeworkItemsInfo",method=RequestMethod.GET)
	@ResponseBody
	public String homeworksInfo(int homeworkId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			gs.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
			WxHomeWork whw = gdService.queryWxHomeWork(homeworkId);
			if (null != whw) {
				gs.setHomeworkName(whw.getTitle());
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkItemList",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkItemList(int homeworkId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs.getItemNames());
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}

	@RequestMapping(value = "addHomeworkItems",method=RequestMethod.POST)
	@ResponseBody
	public String addHomeworkItems(int homeworkId,String items,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			if ( null != gs) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"该考卷已经设置了评分项", gs);
			}	
			gs = new GdHomeWorkItems();
			gs.setHomeworkId(homeworkId);
			gs.setTanentId(LoginUserUtil.getLeaseholderId(request));
			gs.setItemNames(items);
			gdService.addGdHomeWorkItems(gs);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updateHomeworkItems",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkItems(int homeworkId,String items,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkItems gs = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
			gs.setItemNames(items);
			gdService.updateGdHomeWorkItems(gs);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemList",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkWorkersItemList(String cardNo,Integer homeworkId,String beginTime,String endTime, int page,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr = gdService.queryWxMemberHomeWork(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, page, pageSize);
			if ( null != pr && null != pr.getDatas()) {
				for (int i =0 ;i < pr.getDatas().size(); i ++) {
					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
					//查询评分内容
					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
					if ( null != wi) {
						gwwi.setContent(wi.getItemJson());
					}
					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
					if (null != whw) {
						gwwi.setHomeworkName(whw.getTitle());
					}
					if (StringUtils.isEmpty(gwwi.getContent())) {
						GdHomeWorkItems ghi = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), gwwi.getHomeworkId());
						if (null != ghi && (null != ghi.getItemNameArray())) {
							StringBuffer sb = new StringBuffer();
							String[] items = ghi.getItemNameArray();
							for (int j = 0 ; j < items.length; j++ ) {
								sb.append(items[j]+":0,");
							}
							if (sb.length()>0) {
								gwwi.setContent(sb.toString().substring(0,sb.length()-1));
							}
						}
					}
					//TODO 查询是否已经制证
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}

	@RequestMapping(value = "homeworkWorkersItemExport",method=RequestMethod.GET)
	public void homeworkWorkersItemExport(String cardNo,Integer homeworkId,String beginTime,String endTime,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr = gdService.queryWxMemberHomeWork(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, 1, 60000);
			if ( null != pr && null != pr.getDatas()) {
				for (int i =0 ;i < pr.getDatas().size(); i ++) {
					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
					//查询评分内容
					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
					if ( null != wi) {
						gwwi.setContent(wi.getItemJson());
					}
					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
					if (null != whw) {
						gwwi.setHomeworkName(whw.getTitle());
					}
				}
				gdService.downloadWorkerItems(pr.getDatas(),response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemInfo",method=RequestMethod.GET)
	@ResponseBody
	public String homeworkWorkersItemInfo(String cardNo,String gdSignId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdHomeWorkWorkersItem gwwi = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
			gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
			WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
			if (null != whw) {
				gwwi.setHomeworkName(whw.getTitle());
			}
			//查询分数和考试时间
			WxMemberHomeWork wmhw = gdService.queryWxMemberHomeWork(LoginUserUtil.getLeaseholderId(request), cardNo, gwwi.getGdSignId(), gwwi.getHomeworkId());
			if ( null != wmhw) {
				gwwi.setScore(wmhw.getScore());
				gwwi.setHomeworkTime(wmhw.getCreateTime());
			}
			//查询是否已经制证
			GdCardMake cardMake = gdService.queryCardMake(cardNo, gwwi.getHomeworkId());
			if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
				gwwi.setHasMake(1);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gwwi);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updateHomeworkWorkersItem",method=RequestMethod.POST)
	@ResponseBody
	public String updateHomeworkWorkersItem(String gdSignId,String cardNo,String itemJson,HttpServletRequest request, HttpServletResponse response) {
		try {
			//查询是否已经制证，如果已经制证，则不能修改
			GdHomeWorkWorkersItem gi = gdService.queryHomeworkWorkersItem(gdSignId, cardNo);
			GdCardMake cardMake = gdService.queryCardMake(cardNo, gi.getHomeworkId());
			if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"已经制过证，不能修改", null);
			}
			
			gi.setCreateTime(new Date());
			gi.setItemJson(itemJson);
			gdService.updateGdHomeWorkWorkersItem(gi);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "homeworkWorkersItemReportExport",method=RequestMethod.GET)
	public String homeworkWorkersItemReportExport(String cardNo,Integer homeworkId,String beginTime,String endTime,HttpServletRequest request, HttpServletResponse response) {
		try {
			if (null == homeworkId || homeworkId <= 0) {
				return "请选择问卷导出";
			}
			PageResult pr = gdService.queryWxMemberHomeWork(LoginUserUtil.getLeaseholderId(request), cardNo, homeworkId, beginTime, endTime, 1, 60000);
			if ( null != pr && null != pr.getDatas()) {
				for (int i =0 ;i < pr.getDatas().size(); i ++) {
					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
					gwwi.setTanentName(LoginUserUtil.getCurrentUser(request).getName());
					//查询评分内容
					GdHomeWorkWorkersItem  wi = gdService.queryHomeworkWorkersItem(gwwi.getSignId(), gwwi.getStudentNo());
					if ( null != wi) {
						gwwi.setContent(wi.getItemJson());
					}
					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
					if (null != whw) {
						gwwi.setHomeworkName(whw.getTitle());
					}
				}
				GdHomeWorkItems gwi = gdService.queryHomeWorkItem(LoginUserUtil.getLeaseholderId(request), homeworkId);
				gdService.downloadWorkerItemsReport(pr.getDatas(),gwi,response);
			}
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
