package com.simple.admin.controller;

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

import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.common.util.DesEncrypt;
import com.simple.common.util.MD5;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.constant.Constant;
import com.simple.model.ClassRegister;
import com.simple.model.GdCardMake;
import com.simple.model.GdJudgeItems;
import com.simple.model.GdSign;
import com.simple.model.GdSignWorkers;
import com.simple.model.PageResult;
import com.simple.model.WxHomeWork;
import com.simple.model.WxMemberHomeWork;
import com.simple.service.ClassRegistorService;
import com.simple.service.CourseService;
import com.simple.service.GdService;
@Controller
@RequestMapping(value = "/gd")
public class GdController {
	
	private static final Logger log = LoggerFactory.getLogger(GdController.class);
	@Autowired
	GdService gdService;
	@Autowired
	ClassRegistorService classRegisterService;
	@Autowired
	CourseService courseService;
	
	@RequestMapping(value = "kaikeList",method=RequestMethod.GET)
	@ResponseBody
	public String kaikeList(String tanentId,String groupName,String leaderName,String date,Integer pageIndex,Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult gs = gdService.querySigns(tanentId, groupName, date, leaderName, pageIndex, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kaike",method=RequestMethod.POST)
	@ResponseBody
	public String kaike(String tanentId,String groupName,String leaderName,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdSign gs = gdService.querySign(tanentId, groupName, new Date());
			if (null == gs) {
				gs = new GdSign();
				gs.setCreateDate(new Date());
				gs.setCreateTime(new Date());
				gs.setId(PrimaryKeyUtil.getUUID());
				gs.setGroupName(groupName);
				gs.setLeaderName(leaderName);
				gs.setTanentId(tanentId);
				gs.setStatus(1);
				gdService.addGdSign(gs);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"开课成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"开课失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "sign",method=RequestMethod.POST)
	@ResponseBody
	public String sign(String gsid,String cardNo,String cardImage,String name,String sex,
			String tanentId,String people,String address,String validtermOfStart,String validtermOfEnd,String department,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(cardNo)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"身份证号不能为空", "身份证号不能为空");
			}
			
			GdSignWorkers gsw =gdService.querySignWorker(gsid, cardNo);
			if ( null != gsw ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"该身份证已经签到", "该身份证已经签到");
			}
			
			GdSign sign = gdService.querySignById(gsid);
			if ( null == sign ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"该签到记录不存在", "该签到记录不存在");
			}
			gsw = gdService.querySignWorker(tanentId, cardNo, new Date());
			if ( null != gsw ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"该身份证今天已经签到", "该身份证今天已经签到");
			}			
			
			gsw = new GdSignWorkers();
			gsw.setId(PrimaryKeyUtil.getUUID());
			gsw.setGsid(gsid);
			gsw.setTanentId(tanentId);
			gsw.setCardNo(cardNo);
			gsw.setCardImage(cardImage);
			gsw.setName(name);
			gsw.setCreateTime(new Date());
			gsw.setCreateDate(gsw.getCreateTime());
			gsw.setGroupName(sign.getGroupName());
			gsw.setLeaderName(sign.getLeaderName());
			gsw.setSex(sex);
			gsw.setAddress(address);
			gsw.setNation(people);
			gsw.setValidtermOfStart(validtermOfStart);
			gsw.setValidtermOfEnd(validtermOfEnd);
			gsw.setDepartment(department);
			gdService.addGdSignWorkers(gsw);
			
			GdCardMake gcm = gdService.queryCardMake(sign.getTanentId(),cardNo);
			if ( null == gcm) {
				gcm = new GdCardMake();
				gcm.setId(PrimaryKeyUtil.getUUID());
				gcm.setCardImage(cardImage);
				gcm.setCardNo(cardNo);
				gcm.setMakeCount(0);
				gcm.setName(name);
				gcm.setSex(sex);
				gcm.setTanentId(sign.getTanentId());
				gcm.setSequenceNo(PrimaryKeyUtil.getShortId());
				gdService.addGdCardMake(gcm);
			}
			
//			GdHomeWorkWorkersItem gi = new GdHomeWorkWorkersItem();
//			gi.setCardNo(cardNo);
//			gi.setGdSignId(gsid);
//			//gi.setHomeworkId(homeworkId);
//			gi.setName(name);
//			gi.setTanentId(tanentId);
//			gi.setSignTime(gsw.getCreateTime());
//			gi.setSex(gsw.getSex());
//			gi.setCardImage(gsw.getCardImage());
//			gdService.addGdHomeWorkWorkersItem(gi);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"签到成功", gsw);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"签到失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "workers",method=RequestMethod.GET)
	@ResponseBody
	public String workers(String gsid,String date,String tanentId,String groupName,String cardNo,String leaderName,Integer page,Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr =gdService.queryWorkers(gsid, tanentId, groupName, cardNo, DateUtil.stringToDate(date), leaderName, page, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "judgeItems",method=RequestMethod.GET)
	@ResponseBody
	public String judgeItems(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdJudgeItems gs = gdService.queryGdJudgeItem(tanentId);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", gs);
		}catch(Exception e) {
			log.error("gd kaike error.",e);
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
			
			//GdCardMake cardMake = gdService.queryCardMake(sign.getTanentId(),cardNo);
			//if ( null != cardMake && cardMake.getMakeCount() > 0 ) {
			//	return AjaxWebUtil.sendAjaxResponse(request, response, false,"已经制过证，不能修改", null);
			//}
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
	
	@RequestMapping(value = "makeList",method=RequestMethod.GET)
	@ResponseBody
	public String makeList(String tanentId,String cardNo,String name, int status,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult pr =gdService.queryCardMake(tanentId,cardNo, name, status, pageIndex, pageSize);
			if (null != pr.getDatas()) {
				for (int i = 0 ; i < pr.getDatas().size(); i++) {
					GdCardMake gsw = (GdCardMake) pr.getDatas().get(i);
					WxMemberHomeWork wmhw = gdService.queryLastWxMemberHomeWork(gsw.getTanentId(),gsw.getCardNo());
					if (null != wmhw) {
						gsw.setScore(wmhw.getScore());
						gsw.setHomeWorkTime(wmhw.getShowCreateTime());
						//总分
						WxHomeWork whw = gdService.queryWxHomeWork(wmhw.getHomeworkId());
						if ( null != whw) {
							gsw.setTotalScore(whw.getScore());
						}
					}
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}	
	
	@RequestMapping(value = "makeInfo",method=RequestMethod.GET)
	@ResponseBody
	public String makeInfo(String tanentId,String cardNo,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdCardMake pr =gdService.queryCardMake(tanentId,cardNo);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}	
	
	@RequestMapping(value = "makeSuccess",method=RequestMethod.POST)
	@ResponseBody
	public String makeSuccess(String tanentId,String cardNo,HttpServletRequest request, HttpServletResponse response) {
		try {
			GdCardMake pr =gdService.queryCardMake(tanentId,cardNo);
			pr.setMakeTime(new Date());
			gdService.make(pr);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", null);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "authKeCheng",method=RequestMethod.GET)
	@ResponseBody
	public String authKeCheng(String tanentId,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			//先从系列里面查询出课程列表
			List<String>  courseBhlist = classRegisterService.getAuthCourseIds(tanentId);
			if ( null != courseBhlist && courseBhlist.size() > 0) {
				//根据租户和课程ids来查询课程
				PageResult registerPage = courseService.queryKeCheng(courseBhlist,null,null,njbh,pageIndex,pageSize);
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registerPage);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", new PageResult(0,pageSize,pageIndex,null));
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "changePassword",method=RequestMethod.POST)
	@ResponseBody
	public String authKeCheng(String token,String oldpwd,String newpwd,String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(oldpwd) || StringUtils.isEmpty(newpwd) ||StringUtils.isEmpty(tanentId)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"参数错误", null);
			}
			String otoken = DesEncrypt.encrypt(oldpwd+newpwd+tanentId, com.simple.admin.constant.Constant.ENCRPTY_KEY_GLOBAL);
			if (!token.equals(otoken)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"参数错误", null);
			}
			String opwd = DesEncrypt.decrypt(oldpwd, com.simple.admin.constant.Constant.ENCRPTY_KEY_GLOBAL);
			ClassRegister cr = classRegisterService.getClassRegister(tanentId, null);
			if (!MD5.stringMD5(opwd).equals(cr.getSqmm())) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"密码错误", null);
			}
			String npwd = DesEncrypt.decrypt(newpwd, com.simple.admin.constant.Constant.ENCRPTY_KEY_GLOBAL);
			classRegisterService.updatePassword(cr.getSqzh(), MD5.stringMD5(npwd));
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "studentHomeWorkList",method=RequestMethod.GET)
	@ResponseBody
	public String studentHomeWorkList(String cardNo,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(cardNo)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", null);
			}
			PageResult pr =gdService.queryWxMemberHomeWork(null,null, cardNo, 0, null, null, pageIndex, pageSize);
			if ( null != pr && null != pr.getDatas()) {
				for (int i =0 ;i < pr.getDatas().size(); i ++) {
					WxMemberHomeWork gwwi = (WxMemberHomeWork) pr.getDatas().get(i);
					ClassRegister cr = classRegisterService.getClassRegister(gwwi.getSchoolId(), null);
					if ( null != cr ) {
						gwwi.setTanentName(cr.getJsmc());
					}
					WxHomeWork whw = gdService.queryWxHomeWork(gwwi.getHomeworkId());
					if (null != whw) {
						gwwi.setHomeworkName(whw.getTitle());
					}
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("gd sign error.",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
}
