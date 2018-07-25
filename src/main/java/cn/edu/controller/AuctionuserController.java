package cn.edu.controller;

import cn.edu.pojo.Auctionuser;
import cn.edu.service.AuctionuserService;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class AuctionuserController {
    @Resource
    AuctionuserService auctionuserService;
    //跳转到登陆界面
    @RequestMapping(value = "/index.html")
    public String b(HttpServletResponse response,HttpSession session){
        session.invalidate();
        return "login";
    }
    //用户登陆，登陆成功把名字存进cookie
    @RequestMapping(value = "/login.html")
    public ModelAndView a(String username,
                          String userpassword,
                          String inputCode,
                          String rem_u,
                          HttpServletRequest request,
                          HttpServletResponse response
                          ){
        ModelAndView mv=new ModelAndView("login");
        String msg;
        HttpSession session=request.getSession();
        String box1=(String)session.getAttribute("numrand");
        if(box1.equals(inputCode)){
            Auctionuser auctionuser=auctionuserService.selectByUserName(username);
            if(auctionuser!=null){
                if(auctionuser.getUserpassword().compareTo(userpassword)==0){
                    session.setAttribute("user",auctionuser);

                    if (rem_u!=""){
                        Cookie usernameCookie=new Cookie("username",auctionuser.getUsername());
                        usernameCookie.setMaxAge(Integer.MAX_VALUE);
                        usernameCookie.setPath("/");
                        response.addCookie(usernameCookie);
                    }

                    mv.setViewName("redirect:/auction/queryAuctions.html");
                }else{
                    mv.addObject("username",username);
                    msg="loginError";
                    mv.addObject("msg",msg);
                }
            }else{
                msg="loginError";
                mv.addObject("msg",msg);
            }
        }else{
            msg="validateCodeError";
            mv.addObject("msg",msg);
        }
        return mv;
    }
    //跳转到注册页面
    @RequestMapping("/goregister.html")
    public String goregister(){
        return "register";
    }
    //注销登陆，清除cookie
    @RequestMapping("/doLogout.html")
    public String doLgin(HttpSession session,HttpServletResponse response){

        Auctionuser auctionuser=(Auctionuser) session.getAttribute("user");
        Cookie usernameCookie=new Cookie("username",auctionuser.getUsername());

        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");

        response.addCookie(usernameCookie);

        session.invalidate();
        return "login";
    }
    //获取前端信息，注册用户
    @RequestMapping("/register.html")
    public String register(Model model,@Validated Auctionuser user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors=bindingResult.getAllErrors();
            if(auctionuserService.selectByUserName(user.getUsername())==null) {
                for (ObjectError error : errors) {
                    System.out.println(error.getArguments());

                }
            }else {
                model.addAttribute("a","用户名已存在");
            }

            model.addAttribute("errors",errors);
            return "register";
        }
        auctionuserService.addUser(user);

        return "login";
    }

    //注册用户时异步验证用户名
    @RequestMapping(value = "/username",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  Map<String,Object> username(@RequestParam String username)throws Exception{
        Map<String,Object> result=new HashMap<String, Object>();
        try{
            Auctionuser auctionuser=auctionuserService.selectByUserName(username);
            if(auctionuser==null){
                System.out.println(username+"no");
                result.put("u","YES");
            }else{
                System.out.println(username+"yes");
                result.put("u","NO");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
