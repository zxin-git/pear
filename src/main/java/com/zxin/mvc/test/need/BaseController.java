package com.zxin.mvc.test.need;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.http.HttpRequest;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@At("/need")
@IocBean
public class BaseController {

	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@At("/index")
	@Ok("jsp:jsp.need.first")
	public void index(HttpRequest request,Ioc ioc){
		
	}
	
	@At("/upload")
	@Ok("json")
	@AdaptBy(type=UploadAdaptor.class)
	public void upload(HttpRequest request,Ioc ioc,@Param("file")TempFile tempFile){
		
	}
		
	@At("/insert")
	@Ok("json")
	public void insert(HttpServletRequest request,Ioc ioc,@Param("..")DataInfo data){
		String file = request.getParameter("file");
//		try {
//			Part part = request.getPart("file");
//			logger.info("",part);
//			String savePath = request.getServletContext().getRealPath("/WEB-INF/uploadFile/");
//			part.write(savePath+System.currentTimeMillis());
//		} catch (IOException e) {
//			logger.debug("",e);
//		} catch (ServletException e) {
//			logger.debug("",e);
//		}
		logger.info(file);
	}
}

