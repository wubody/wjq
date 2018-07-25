package cn.edu.aop;

import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Aspect
@Component
public class Jurisdiction {
    @Autowired
    AuctionuserService auctionuserService;
    Auctionuser auctionuser=null;
    @Pointcut("@annotation(cn.edu.anno.AdminOnly)")
    public void a(){
    }

    @Before("a()")
    public void before() throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpSession session=request.getSession();
        auctionuser=(Auctionuser) session.getAttribute("user");
        if (auctionuser.getUserisadmin()!=1){
            throw new Exception("没有权限");
        }
    }
}
