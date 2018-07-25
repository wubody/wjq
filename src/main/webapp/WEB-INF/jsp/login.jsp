<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.servlet.ModelAndView" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%=request.getContextPath()%>/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>

	
	<script type="text/javascript">

        function  tt() {
            document.location.href="${pageContext.request.contextPath}/user/goregister.html"
        }

	</script>

  </head>
  
<body>
<div class="wrap">
<!-- main begin-->
 <div class="main">
   <div class="sidebar">
     <p><img src="<%=request.getContextPath() %>/images/img1.jpg" width="443" height="314" alt="" /></p>
   </div>
   <div class="sidebarg">
     <form action="/user/login.html" method="post">
    <div class="login">
      <dl>
        <dt class="blues">用户登陆</dt>
        <dd><label for="name">用户名：</label>
        	<input type="text" name="username" class="inputh"  value="${username}" id="name"/></dd>
        <dd><label for="password">密 码：</label>
        	<input type="password" name="userpassword" class="inputh" onblur="" value="${userpassword}" id="password"/><p id="userpasswordError" style="display: none"></p></dd>
        <dd>
           <label class="lf" for="passwords">验证码：</label>
           <input type="text" name="inputCode" class="inputh inputs lf" onblur="" value="" id="passwords"/>
            <p id="inputCodeError" style="display: none"></p>
           <span class="wordp lf">
                 <img id="validateCode" src="<%=request.getContextPath()%>/Number.jsp" width="96" height="27" alt="" />
           </span>
           <span class="blues lf"><a id="changeCode" href="javascript:void(0);" title="">看不清</a></span>
        </dd>
        <dd>
           <input  type="checkbox" id="rem_u" name="rem_u" value="1" />
           <span class="rem_u" >下次自动登录</span>
            <span></span>
        </dd>
        <dd class="buttom">
           <input name="" type="submit" value="登 录" class="spbg buttombg f14 lf" style="background-color: #0096ff" />
            <input id="register" name="" type="button" value="注 册" class="spbg buttombg f14 lf" onclick="tt()" style="background-color: #0096ff"/>
           <span class="blues  lf"><a href="" title="">忘记密码?</a></span>

           <div class="cl"></div>
        </dd>
       
      </dl>
    </div>
    </form>
   </div>

<!-- main end-->
 
<!-- footer begin-->
</div>
 <!--footer end-->
</body>
</html>
