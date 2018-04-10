package com.zxin.mvc.auth.controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.hyperic.sigar.SigarException;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Scope;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.plugins.slog.annotation.Slog;

import com.zxin.mvc.auth.data.User;
import com.zxin.mvc.auth.service.AuthService;
import com.zxin.mvc.core.sigar.util.SigarUtils;
import com.zxin.mvc.core.util.CipherToolkit;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.gimpy.FishEyeGimpyRenderer;

@At("auth")
@IocBean
public class AuthController {
	private static Log log = Logs.get();
	
	@Inject("refer:authService")
	private AuthService service; 
	
	@At("/login")
	@Ok("jsp:jsp.auth.login")
	public void viewLogin(){
		
	}
	
	
	@At("/user/login")
    @Filters // 覆盖UserModule类的@Filter设置,因为登陆可不能要求是个已经登陆的Session
    @Ok("json")
    public Object login(@Param("userName")String userName, 
            @Param("password")String password, 
            @Param("captcha")String captcha,
            @Attr(scope=Scope.SESSION, value="nutz_captcha")String _captcha,
            HttpSession session) {
        NutMap re = new NutMap();
        if (!CipherToolkit.checkCaptcha(_captcha, captcha)) {
            return re.setv("ok", false).setv("msg", "验证码错误");
        }
        User user = service.fetch( Cnd.where("userName", "=", userName).and("password", "=", password) );
        if (user == null) {
            return re.setv("ok", false).setv("msg", "用户名或密码错误");
        } else {
        	SecurityUtils.getSubject().login(new SimpleShiroToken(user.getUserId()));
//        	SecurityUtils.getSubject().login(new UsernamePasswordToken("zhang", "123"));
            session.setAttribute("me", user.getUserId());
            return re.setv("ok", true);
        }
    }
	
	@At("/captcha/next")
    @Ok("raw:png")
    public BufferedImage next(HttpSession session, @Param("w") int w, @Param("h") int h) {
        if (w * h < 1) { //长或宽为0?重置为默认长宽.
            w = 200;
            h = 60;
        }
        Captcha captcha = new Captcha.Builder(w, h)
                                .addText().addBackground(new GradiatedBackgroundProducer())
                                .gimp(new FishEyeGimpyRenderer())
                                .build();
        String text = captcha.getAnswer();
        session.setAttribute(CipherToolkit.captcha_attr, text);
        return captcha.getImage();
    }
	
	
	@At
    @Ok("beetl:hello.html")
    @Fail("void") // beelt的机制导致只能使用void,详细原因看nutzbook中的代码吧
    public Object hello() {
        QueryResult qr = new QueryResult();
        Pager pager = service.dao().createPager(1, 20);
        pager.setRecordCount(service.dao().count(User.class));
        qr.setPager(pager);
        qr.setList(service.dao().query(User.class, null, pager));
        return qr;
    }
	
	@At("/user/view")
	@Ok("jsp:jsp.auth.index")
	@RequiresUser
	@Slog(tag="查看用户列表", after="用户数=")
	public List<User> viewUsers(HttpServletRequest request,HttpServletResponse response,Ioc ioc){
		List<User> list = new ArrayList<User>();				
		log.info("auth/user/view");
		return list;
	}
	
	@At("/user/select")
	@Ok("jsp:jsp.auth.index")
	@Slog(tag="获取用户", after="用户数=${re.size()}")
	public List<User> selectUsers(HttpServletRequest request,HttpServletResponse response){
		List<User> list = service.query();		
		log.info("auth/user/select");
		return list;
	}
	
	@At("/user/insert")
	@Ok("jsp:jsp.auth.index")
	@Slog(tag="插入用户", after="用户数=")
	public void insertUsers(HttpServletRequest request,HttpServletResponse response,Ioc ioc,User user){
		service._insert(user);
		log.info("auth/user/insert");
	}
	
	@At("/user/delete")
	@Ok("jsp:jsp.auth.index")
	@Slog(tag="删除用户", after="用户数=")
	public void deleteUsers(HttpServletRequest request,HttpServletResponse response,Ioc ioc,User user){
		try {
			log.info(SigarUtils.sigar().getCpu());
		} catch (SigarException e) {
			log.info("sigar",e);
		}
		log.info("auth/user/delete");
	}
	
	@At("/user/update")
	@Ok("jsp:jsp.auth.index")
	@Slog(tag="更新用户", after="用户数=")
	public void updateUsers(HttpServletRequest request,HttpServletResponse response,Ioc ioc,List<User> users){
		service.updates(users);
		log.info("auth/user/update");
	}


}
