package com.simple.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.common.util.DesEncrypt;
import com.simple.common.util.MD5;
import com.simple.model.AppLog;
import com.simple.model.ClassRegister;
import com.simple.model.CourseImage;
import com.simple.model.CourseWenjuanTeacher;
import com.simple.model.CourseXlImage;
import com.simple.model.RegisterImage;
import com.simple.model.SysUser;
import com.simple.model.api.AllClass;
import com.simple.model.api.AllCourse;
import com.simple.model.api.AllKaiKe;
import com.simple.model.api.BanJi;
import com.simple.model.api.CheckVersion;
import com.simple.model.api.KaiKe;
import com.simple.model.api.KaiKeHeJi;
import com.simple.model.api.KeCheng;
import com.simple.model.api.KeChengInfo;
import com.simple.model.api.KeChengXiLie;
import com.simple.model.api.NianJi;
import com.simple.model.api.UserInfo;
import com.simple.model.api.XueYuan;
import com.simple.service.AllVersionService;
import com.simple.service.AppLogService;
import com.simple.service.AppService;
import com.simple.service.ClassRegistorService;
import com.simple.service.CourseService;
import com.simple.service.CourseWenjuanTeacherService;
import com.simple.service.DecorateService;
import com.simple.service.SysUserService;
import com.simple.service.TClassService;
import com.simple.service.TClassStudentService;
import com.simple.service.TeachingService;
@Controller
@RequestMapping(value = "/app")
public class ApiController {
	
	private static final Logger log = LoggerFactory.getLogger(ApiController.class);
	@Autowired
	ClassRegistorService registerService;
	@Autowired
	TClassService tclassService;
	@Autowired
	TClassStudentService tclassStudentService;
	@Autowired
	CourseService courseService;
	@Autowired
	AppService appService;
	@Autowired
	TeachingService teachingService;
	@Autowired
	DecorateService decorateService;
	@Autowired
	AppLogService appLogService;
	@Autowired
	CourseWenjuanTeacherService courseWenjuanTeacherService;
	@Autowired
	SysUserService userService;
	@Autowired
	AllVersionService versionService;
	
	private boolean isValid(String tanentId,String jsbh) {
		ClassRegister register = registerService.getClassRegister(tanentId,jsbh);
		if ("y".equals(register.getYxzt())) {
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "doLogin",method=RequestMethod.POST)
	@ResponseBody
	public String doLogin(String sqzh,String password,HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegister user = registerService.queryClassRegister(sqzh);
			if ( user == null ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:工号不存在.", null);
			}
			if (!user.getSqmm().equals(MD5.stringMD5(password))) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:密码错误.", null);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"登录成功", new UserInfo(user.getXxmc(),user.getXxbh(),user.getTanentid()));
		}catch(Exception e) {
			log.error("登录失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败", e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "njlb",method=RequestMethod.GET)
	@ResponseBody
	public String njlb(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			List<NianJi> registers = tclassService.njList(tanentId,0,null);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "bjlb",method=RequestMethod.GET)
	@ResponseBody
	public String bjlb(String tanentId,String njbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			//int xn = DateUtil.getXueNian();
			int xn = 0;
			List<BanJi> registers = tclassService.bjList(tanentId,xn,njbh,null);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "xylb",method=RequestMethod.GET)
	@ResponseBody
	public String xylb(String tanentId,String xxbh,String njbh,String bjbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			List<XueYuan> registers = tclassStudentService.queryXueYuan(tanentId, xxbh, njbh, bjbh);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "cxllb",method=RequestMethod.GET)
	@ResponseBody
	public String cxllb(String xlbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<KeChengXiLie> xls = courseService.queryKeChengXiLieByParent(xlbh);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", xls);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kclb",method=RequestMethod.GET)
	@ResponseBody
	public String kclb(String tanentId,String xlbh,String cxlbh,String njbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			//先从系列里面查询出课程列表
			List<String>  courseBhlist = registerService.getAuthCourseIds(tanentId);
			if ( null != courseBhlist && courseBhlist.size() > 0) {
				//根据租户和课程ids来查询课程
				if (StringUtils.isEmpty(cxlbh)) {
					cxlbh = "non";
				}
				StringBuffer sb = new StringBuffer();
				for (int i = 0 ; i < courseBhlist.size() ; i ++) {
					sb.append(courseBhlist.get(i)).append(",");
				}
				List<KeCheng> registers = courseService.queryKeChengList(courseBhlist,xlbh,cxlbh,njbh,1,1000);
				if (null != registers && registers.size() > 0 ) {
					for (int i = 0 ; i < registers.size(); i++) {
						KeCheng kc = registers.get(i);
						CourseImage  ci = decorateService.queryCourseImage(tanentId, null, kc.getCode());
						if ( null != ci ) {
							kc.setKctp(ci.getKctp());
						}
					}
					return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：未查询到授权的该系列课程", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kcxx",method=RequestMethod.GET)
	@ResponseBody
	public String kcxx(String teacherId,String tanentId,String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			//根据租户查询授权课程列表id
			List<String> courseIds =  registerService.getAuthCourseIds(tanentId);
			if (null == courseIds || (!courseIds.contains(code))) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：该课程未授权", null);
			}
			KeChengInfo registers = courseService.queryKeChengInfo(code);
//			//查询系列图片
//			if ( !StringUtils.isEmpty(registers.getKcxlbh())) {
//				List<CourseXl> xls =  courseService.queryXl(registers.getKcxlbh(), null);
//				if ( null != xls && xls.size() > 0 ) {
//					registers.setKcxlmc(xls.get(0).getKcxlmc()==null ? "" : xls.get(0).getKcxlmc());
//					registers.setKcxltp(xls.get(0).getKctp()==null ? "" : xls.get(0).getKctp());
//				}
//			}
//			if ( !StringUtils.isEmpty(registers.getChildKcxlbh())) {
//				List<CourseXl> xls =  courseService.queryXl(registers.getChildKcxlbh(), null);
//				if ( null != xls && xls.size() > 0 ) {
//					registers.setChildKcxlmc(xls.get(0).getKcxlmc()==null?"":xls.get(0).getKcxlmc());
//					registers.setChildKcxltp(xls.get(0).getKctp()==null?"":xls.get(0).getKctp());
//				}
//			}
			
			//查询老师设置的问卷编号
			CourseWenjuanTeacher courseWenjuan = courseWenjuanTeacherService.query(code, teacherId);
			if ( null != courseWenjuan) {
				if (!StringUtils.isEmpty(courseWenjuan.getTeacherWenjuan())) {
					registers.setTeacherWenjuan(courseWenjuan.getTeacherWenjuan());
				}
				if (!StringUtils.isEmpty(courseWenjuan.getStudentWenjuan())) {
					registers.setStudentWenjuan(courseWenjuan.getStudentWenjuan());
				}
			}
			CourseImage  ci = decorateService.queryCourseImage(tanentId, null, registers.getCode());
			if ( null != ci ) {
				registers.setKctp(ci.getKctp());
			}
			try {
				//统计信息
				courseService.updateCourseWatch(tanentId, code);
			}catch(Exception e) {
			}
			ClassRegister classregister = registerService.getClassRegister(tanentId, null);
			Map result = new HashMap();
			if (StringUtils.isEmpty(classregister.getKey())) {
				result.put("ss", "");
				result.put("kcxx", JSONObject.toJSONString(registers));
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
			}else {
				result.put("ss", DesEncrypt.encrypt(classregister.getKey().trim(), com.simple.admin.constant.Constant.ENCRPTY_KEY_GLOBAL));
				result.put("kcxx", DesEncrypt.encrypt(JSONObject.toJSONString(registers), classregister.getKey().trim()));
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", result);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kcxllb",method=RequestMethod.GET)
	@ResponseBody
	public String kcxllb(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return  AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			//根据租户查询授权课程列表id
			List<String> courseIds =  registerService.getAuthCourseIds(tanentId);
			//根据课程Id查询系列ids
			if ( null != courseIds && courseIds.size() > 0 ) {
				List<String> xilieIds = courseService.getXiLieBHList(courseIds);
				//根据系列ids查询
				if ( null != xilieIds && xilieIds.size() > 0 ) {
					List<KeChengXiLie> registers = courseService.queryKeChengXiLieList(xilieIds,1,1000);
					for (int i = 0 ; i < registers.size(); i++) {
						KeChengXiLie kc = registers.get(i);
						CourseXlImage  ci = decorateService.queryCourseXlImage(tanentId, null, kc.getKcxlbh());
						if ( null != ci ) {
							kc.setKctp(ci.getKcxltp());
						}
					}
					return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：未查询到授权课程系列.", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "pf",method=RequestMethod.GET)
	@ResponseBody
	public String pf(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			List<RegisterImage> registers = registerService.queryImages(tanentId,null,null);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "checkVersion",method=RequestMethod.GET)
	@ResponseBody
	public String checkVersion(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			CheckVersion registers = appService.queryValidApp(tanentId);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "kaike",method=RequestMethod.POST)
	@ResponseBody
	public String kaike(KaiKe kaike,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(kaike.getTanentId(),null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			String skbh = teachingService.addTeachingLog(kaike);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"开课成功", skbh);
		}catch(Exception e) {
			log.error("开课失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"开课失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "teachers",method=RequestMethod.GET)
	@ResponseBody
	public String teachers(String tanentId,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> teachers = courseWenjuanTeacherService.queryValidTeacher(tanentId);
			List<SysUser> users = userService.queryTeachers(tanentId, teachers, pageIndex, pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", users);
		}catch(Exception e) {
			log.error("开课失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "qiandao",method=RequestMethod.POST)
	@ResponseBody
	public String qiandao(String tanentId,String skbh,String gh,String name,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			if (StringUtils.isEmpty(gh)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"参数无效", null);
			}
			String[] ghs = gh.split(",");
			teachingService.addTeachingStudent(tanentId, skbh, ghs);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"签到成功", skbh);
		}catch(Exception e) {
			log.error("签到失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"签到失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "xuexikc",method=RequestMethod.POST)
	@ResponseBody
	public String xuexikc(String tanentId,String skbh,String kcbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			teachingService.addTeachingCourse(tanentId, skbh, kcbh);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"学习课程成功", skbh);
		}catch(Exception e) {
			log.error("学习课程失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"学习课程失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "applog",method=RequestMethod.POST)
	@ResponseBody
	public String applog(AppLog log,HttpServletRequest request, HttpServletResponse response) {
		try {
			log.setCreateTime(new Date());
			appLogService.addAppLog(log);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"记录日志成功", null);
		}catch(Exception e) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"记录日志失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	/**
	 * 离线查询租户年级，班级，学生，老师信息
	 * @param tanentId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "allClass",method=RequestMethod.GET)
	@ResponseBody
	public String allClass(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegister register = registerService.getClassRegister(tanentId,null);
			if (!"y".equals(register.getYxzt())) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			AllClass ac = new AllClass();
			List<NianJi> nglist = tclassService.njList(tanentId,0,null);
			if ( null != nglist && nglist.size() > 0) {
				ac.setNjlist(nglist);
				for (int i = 0 ; i < nglist.size(); i++) {
					NianJi nj = nglist.get(i);
					//int xn = DateUtil.getXueNian();
					int xn = 0;
					List<BanJi> bjlist = tclassService.bjList(tanentId,xn,nj.getNjbh(),null);
					if ( null != bjlist && bjlist.size() > 0 ) {
						nj.setBjlist(bjlist);
						for ( int j = 0 ; j < bjlist.size() ; j ++) {
							BanJi bj = bjlist.get(j);
							List<XueYuan> xylist = tclassStudentService.queryXueYuan(tanentId, register.getXxbh(), nj.getNjbh(), bj.getBjbh());
							if (null != xylist && xylist.size() > 0 ) {
								bj.setXylist(xylist);
							}
						}
					}
				}
			}
			
			List<String> teachers = courseWenjuanTeacherService.queryValidTeacher(tanentId);
			List<SysUser> users = userService.queryTeachers(tanentId, teachers, 1, 1000);
			if ( null != users && users.size() > 0 ) {
				ac.setTeachers(users);
			}
			int version = versionService.queryClassVersion();
			ac.setVersion(version);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", ac);
		}catch(Exception e) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "allCourse",method=RequestMethod.GET)
	@ResponseBody
	public String allCourse(String tanentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean isvalid = isValid(tanentId,null);
			if (!isvalid) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"教室无效", null);
			}
			AllCourse ac = new AllCourse();
			//根据租户查询授权课程列表id
			List<String> courseIds =  registerService.getAuthCourseIds(tanentId);
			//根据课程Id查询主系列ids
			if ( null != courseIds && courseIds.size() > 0 ) {
				List<String> xilieIds = courseService.getXiLieBHList(courseIds);
				//根据主系列ids查询
				if ( null != xilieIds && xilieIds.size() > 0 ) {
					List<KeChengXiLie> registers = courseService.queryKeChengXiLieList(xilieIds,1,1000);
					for (int i = 0 ; i < registers.size(); i++) {
						KeChengXiLie kc = registers.get(i);
						CourseXlImage  ci = decorateService.queryCourseXlImage(tanentId, null, kc.getKcxlbh());
						if ( null != ci ) {
							kc.setKctp(ci.getKcxltp());
						}
					}
					if ( null != registers && registers.size() > 0 ) {
						ac.setXllist(registers);
						List<String>  courseBhlist = registerService.getAuthCourseIds(tanentId);
						for ( int j = 0 ; j < registers.size(); j++) {
							KeChengXiLie xl = registers.get(j);
							//查询主系列下面的课程
							List<KeCheng> zkclist = this.getMainKeChengList(courseBhlist, xl.getKcxlbh(), null, tanentId);
							if (null != zkclist) {
								xl.setKclist(zkclist);
							}else {
								xl.setKclist(new ArrayList<KeCheng>());
							}
							//查询子系列
							List<KeChengXiLie> xls = courseService.queryKeChengXiLieByParent(xl.getKcxlbh());
							if ( null != xls ) {
								xl.setZxllist(xls);
								for (int k = 0 ; k < xls.size(); k++) {
									KeChengXiLie zxl = xls.get(k);
									//先从系列里面查询出课程列表
									List<KeCheng> kclist = this.getKeChengList(courseBhlist, xl.getKcxlbh(), zxl.getKcxlbh(), null, tanentId);
									zxl.setKclist(kclist);
								}
							}else {
								xl.setZxllist(new ArrayList<KeChengXiLie>());
							}
						}
					}
				}
			}
			int version = versionService.queryCourseVersion();
			ac.setVersion(version);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", ac);
		}catch(Exception e) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	private List<KeCheng> getKeChengList(List<String>  courseBhlist,String xlbh,String cxlbh,String njbh,String tanentId) {
		if ( null != courseBhlist && courseBhlist.size() > 0) {
			//根据租户和课程ids来查询课程
			List<KeCheng> registers = courseService.queryKeChengList(courseBhlist,xlbh,cxlbh,njbh,1,1000);
			if (null != registers && registers.size() > 0 ) {
				for (int i = 0 ; i < registers.size(); i++) {
					KeCheng kc = registers.get(i);
					CourseImage  ci = decorateService.queryCourseImage(tanentId, null, kc.getCode());
					if ( null != ci ) {
						kc.setKctp(ci.getKctp());
					}
				}
			}
			return registers;
		}
		return null;
	}
	
	private List<KeCheng> getMainKeChengList(List<String>  courseBhlist,String xlbh,String njbh,String tanentId) {
		if ( null != courseBhlist && courseBhlist.size() > 0) {
			//根据租户和课程ids来查询课程
			List<KeCheng> registers = courseService.queryMainKeChengList(courseBhlist,xlbh,njbh,1,1000);
			if (null != registers && registers.size() > 0 ) {
				for (int i = 0 ; i < registers.size(); i++) {
					KeCheng kc = registers.get(i);
					CourseImage  ci = decorateService.queryCourseImage(tanentId, null, kc.getCode());
					if ( null != ci ) {
						kc.setKctp(ci.getKctp());
					}
				}
			}
			return registers;
		}
		return null;
	}
	
	
	@RequestMapping(value = "allShouke",method=RequestMethod.POST)
	@ResponseBody
	public String allShouke(String kaike,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<KaiKeHeJi> kaikes = JSONObject.parseArray(kaike, KaiKeHeJi.class);
			teachingService.addTeaching(kaikes);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"记录日志成功", null);
		}catch(Exception e) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"记录日志失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "allVersion",method=RequestMethod.GET)
	@ResponseBody
	public String allVersion(HttpServletRequest request, HttpServletResponse response) {
		try {
			int classv = versionService.queryClassVersion();
			int coursev = versionService.queryCourseVersion();
			Map r = new HashMap();
			r.put("class", classv);
			r.put("course", coursev);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", r);
		}catch(Exception e) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
}
