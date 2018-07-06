package cn.edu.interceptor;

import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;
import cn.edu.service.impl.AuctionuserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class CheckUserInterceptor implements HandlerInterceptor{
    @Autowired
    AuctionuserServiceImpl auctionuserService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session=httpServletRequest.getSession();
        String path=httpServletRequest.getRequestURI();
        if(path.indexOf("user/index.html")!=-1||path.indexOf("user/username")!=-1||path.indexOf("user/goregister.html")!=-1||path.indexOf("user/login.html")!=-1||path.indexOf("user/register.html")!=-1){
            return true;
        }
        if(session.getAttribute("user")!=null){
            System.out.println("当前用户为合法用户");
            return true;
        }else {
            String username="";
            String userpassword="";
            Cookie[] cookies=httpServletRequest.getCookies();
            if(cookies!=null){
                for (Cookie cookie:cookies) {
                    if("username".equals(cookie.getName())){
                        username=cookie.getValue();
                    }
                    if("userpassword".equals(cookie.getName())){
                        userpassword=cookie.getValue();
                    }
                }
                if(!"".equals(username)&&!"".equals(userpassword)){
                    System.out.println(username);

                    Auctionuser auctionuser=auctionuserService.selectByUserName(username);
                    session.setAttribute("user",auctionuser);
                    return true;
                } else {

                    System.out.println("拦截到一个非法用户");
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/user/index.html");
                    return false;
                }
            }else {

                System.out.println("拦截到一个非法用户");
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/user/index.html");
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
