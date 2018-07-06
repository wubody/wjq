package cn.edu.uilt;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//异常处理类
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        CustomException customException=null;
        if(ex instanceof CustomException){
            customException=(CustomException)ex;
        }else {
            customException=new CustomException("未知错误");
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("errorMsg",customException.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
