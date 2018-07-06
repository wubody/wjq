<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>竞拍详情</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
<!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
	    <!-- 如果用户没有登录进来，显示“登录”链接，否则显示“注销” -->
	    <c:if test="${sessionScope.user != null}">
	    	 <div class="logout right"><a href="${pageContext.request.contextPath}/user/doLogout.html" title="注销">注销</a></div>
	    </c:if>
	    <c:if test="${sessionScope.user == null}">
	    	 <div class="logout right"><a href="${pageContext.request.contextPath}/login.jsp" title="登录">登录</a></div>
	    </c:if>  
  </div>
  <div class="items sg-font lf">
      <ul class="rows">
        <li>名称：</li>
        <li class="borderno">${auctionDetail.auctionname}</li>
      </ul>
      <ul class="rows">
        <li>描述：</li>
        <li class="borderno">${auctionDetail.auctiondesc}</li>
      </ul>
      <ul class="rows">
        <li>开始时间：</li>
        <li class="borderno">
         <fmt:formatDate value="${auctionDetail.auctionstarttime}" pattern="yyyy-MM-dd hh:mm:ss"/>
        </li>
      </ul>
      <ul class="rows">
        <li>结束时间：</li>
        <li class="borderno">
         <fmt:formatDate value="${auctionDetail.auctionendtime}" pattern="yyyy-MM-dd hh:mm:ss"/>
       </li>
      </ul>
      <ul class="rows border-no">
        <li>起拍价：</li>
        <li class="borderno">${auctionDetail.auctionstartprice}</li>
      </ul>
  </div>
  <div class="rg borders"><img src="${pageContext.request.contextPath}/upload/${auctionDetail.auctionpic}" width="270" height="185" alt="" /></div>
  <div class="cl"></div>
  
  <form action="${pageContext.request.contextPath}/auction/saveAuctionRecord.html" method="post">
	  <div class="top10 salebd">
	       <p>
		       <%--@declare id="sale"--%><label for="sale">出价：</label>
		       <input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" class="inputwd" name="auctionprice" />
		       <input type="hidden" name="auctionid" value="${auctionDetail.auctionid}"/>
		       <input type="submit" style="background-color: #0096ff" value="竞 拍" class="spbg buttombg f14  sale-buttom" />
	       </p>
	      <!--<p class="f14 red">${sessionScope.errorMessage}</p>  --> 
	  </div>
  </form>
 
  <div class="top10">
    <input type="submit" value="刷 新" class="spbg buttombg f14" />
    <input type="button" value="返回列表" class="spbg buttombg f14" 
    		onclick="location='${pageContext.request.contextPath}/auction/queryAuctions.html'" />
  </div>
  <div class="offer">
    <h3>出价记录</h3>
    <div class="items sg-font">
      <ul class="rows even strong">
        <li>竞拍时间</li>
        <li>竞拍价格</li>
        <li class="borderno">竞拍人</li>
      </ul>
      
    <!-- 迭代输出竞拍记录 -->
    <c:if test="${fn:length(auctionDetail.recordList)>0}">
		<c:forEach var="auctionRecord" items="${auctionDetail.recordList}">
	      	<ul class="rows">
		        <li>
		        	<fmt:formatDate value="${auctionRecord.auctiontime}" pattern="yyyy-MM-dd hh:mm:ss"/>
		        </li>
		        <li>
		        	${auctionRecord.auctionprice}
		        </li>
		        <li class="borderno">
		        	${auctionRecord.user.username}
		        </li>
	        </ul>
	      </c:forEach>
    </c:if>
   	<!-- 迭代输出竞拍记录 -->
  	<c:if test="${fn:length(auctionDetail.recordList)==0}">
			<ul class="strong" style="color:#0096ff;text-align: center;">
				<li>无竞拍记录</li>
			</ul>
	</c:if>
  </div>
  </div>
<!-- main end-->
</div>
</body>
</html>
