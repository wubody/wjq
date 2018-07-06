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

    @RequestMapping(value = "/index.html")
    public String b(HttpServletResponse response,HttpSession session){
        session.invalidate();
        return "login";
    }

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
        String box1=(String) (session.getAttribute("numrand"));
        if(box1.compareTo(inputCode)==0){
            Auctionuser auctionuser=auctionuserService.selectByUserName(username);
            if(auctionuser!=null){
                if(auctionuser.getUserpassword().compareTo(userpassword)==0){
                    session.setAttribute("user",auctionuser);

                    if (rem_u!=""){
                        Cookie usernameCookie=new Cookie("username",auctionuser.getUsername());
                        Cookie userpasswordCookie=new Cookie("userpassword",auctionuser.getUserpassword());
                        usernameCookie.setMaxAge(Integer.MAX_VALUE);
                        usernameCookie.setPath("/");
                        userpasswordCookie.setMaxAge(Integer.MAX_VALUE);
                        userpasswordCookie.setPath("/");
                        response.addCookie(usernameCookie);
                        response.addCookie(userpasswordCookie);
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
    @RequestMapping("/goregister.html")
    public String goregister(){
        return "register";
    }

    @RequestMapping("/doLogout.html")
    public String doLgin(HttpSession session,HttpServletResponse response){

        Auctionuser auctionuser=(Auctionuser) session.getAttribute("user");
        Cookie usernameCookie=new Cookie("username",auctionuser.getUsername());
        Cookie userpasswordCookie=new Cookie("userpassword",auctionuser.getUserpassword());
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        userpasswordCookie.setMaxAge(0);
        userpasswordCookie.setPath("/");
        response.addCookie(usernameCookie);
        response.addCookie(userpasswordCookie);
        session.invalidate();
        return "login";
    }
    @RequestMapping("/register.html")
    public String register(Model model,@Validated Auctionuser user, BindingResult bindingResult){


        auctionuserService.addUser(user);

        return "login";
    }


    @RequestMapping(value = "/username",method= RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  Map<String,Object> username(@RequestParam String username, HttpServletRequest request, HttpServletResponse response)throws Exception{
        Map<String,Object> result=new HashMap<String, Object>();

        //ModelAndView mv=new ModelAndView("login");
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
