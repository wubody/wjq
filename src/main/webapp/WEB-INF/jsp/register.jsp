<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>

</head>

<body>
	<div class="wrap">

		<form action="${pageContext.request.contextPath}/user/register.html"
			  id="adduser"
			method="post">
			<input type="hidden" name="action" value="doRegister" />
			<div class="zclf login logns">
				<h1 class="blue">用户注册</h1>
				<dl>
					<dd>
						<label> <small>*</small>用户名
						</label> <input id="username"  name="username" onblur="t1()" type="text" class="inputh lf"
							value="${registerUser.username}" /> 
							<span class="red">${username}</span>
						<div id="usernameerror" class="lf red laba" ></div>
					</dd>
					<dd>
						<label> <small>*</small>密码
						</label> <input name="userpassword" type="password"  onblur="t2()" id="userpassword" class="inputh lf"
							value="${registerUser.userpassword}" /> 
							<span class="red">${userpassword}</span>
						<div class="lf red laba" id="userpassworderror"></div>
					</dd>
					<dd>
						<label> <small>*</small>身份证号
						</label> <input name="usercardno" type="text" onblur="t3()" class="inputh lf" id="usercardno"
							value="${registerUser.usercardno}" /> 
							<span class="red">${usercardno}</span>
						<div class="lf red laba" id="usercardnoerror"></div>
					</dd>
					<dd>
						<label> <small>*</small>电话
						</label> <input name="usertel" type="text" onblur="t4()" class="inputh lf" id="usertel"
							value="${registerUser.usertel}" /> <span class="red">${usertel}</span>
						<div class="lf red laba" id="usertelerror"></div>
					</dd>
					<dd>
						<label> <small>*</small>住址
						</label> <input name="useraddress" type="text" onblur="t5()" class="inputh lf" id="useraddress"
							value="${registerUser.useraddress}" />
						<div class="lf red laba" id="useraddresserror"></div>
					</dd>
					<dd>
						<label> <small>*</small>邮政编码
						</label> <input name="userpostnumber" type="text" onblur="t6()" class="inputh lf" id="userpostnumber"
							value="${registerUser.userpostnumber}" />
						<div class="lf red laba" id="userpostnumbererror"></div>
					</dd>
					<!-- 
					<dd class="hegas">
						<label> <small>*</small>验证码
						</label> <input name="inputCode" type="text" class="inputh inputs lf"
							value="" /> <span class="wordp lf"><img id="validateCode"
							src="Number.jsp" width="96" height="27" alt="" /></span> <span
							class="blues lf"><a id="changeCode"
							href="javascript:void(0);" title="">看不清</a></span>
					</dd> -->
					<dd class="hegas">
						<label>&nbsp;</label> <input type="button" style="color: #0096ff"  onclick="su()" value="立即注册"
							class="spbg buttombg buttombgs f14 lf" />
					</dd>
				</dl>
			</div>
		</form>
		<!-- main end-->
		<!-- footer begin-->
	</div>
	<!--footer end-->
</body>
</html>
