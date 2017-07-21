package com.simple.admin.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.excel.FileUploadUtil;
import com.simple.common.util.AjaxWebUtil;
@Controller
@RequestMapping(value = "/image")
public class ImageUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageUploadController.class);


	@RequestMapping(value="/upload64",method = RequestMethod.POST)
	@ResponseBody
	public String upload(String content, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			byte[] fbytes = getStringImage(content);
			String suffix = "bmp";
			File f = FileUploadUtil.getFileByInputStream(new ByteArrayInputStream(fbytes), suffix, FileUploadUtil.UPLOAD_IMAGE_DIR);
			if ( null != f) {
				String path = f.getPath().replace(EnvPropertiesConfiger.getValue("uploadDir"), "");
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"上传成功", EnvPropertiesConfiger.getValue("fileDomain")+"/file/"+path);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:文件未创建成功", null);
		}catch(Exception e) {
			log.error("上传失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	private static byte[] getStringImage(String base64String) throws IOException {
	    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	    return base64String != null ? decoder.decodeBuffer(base64String) : null;
	}
}
