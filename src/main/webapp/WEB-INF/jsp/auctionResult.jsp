<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>竞拍结果</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrap">
			<!-- main begin-->
			<div class="sale">
				<h1 class="lf">拍卖结束的商品</h1>
				<div class="right rulse">
					当前用户是：<span class="blue strong">${sessionScope.user.username}</span>
				</div>
				<div class="cl"></div>
			</div>
			<c:if test="${fn:length(auctionCustomList)!=0}">
			<div class="items">
				<ul class="rows even strong">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="list-wd">成交价</li>
					<li class="borderno">买家</li>
				</ul> 
				<c:forEach var="auction" items="${auctionCustomList}" varStatus="state">
					<ul 
					    <c:if test="${state.index%2!=0}">class="rows even"</c:if>
					    <c:if test="${state.index%2==0}">class="rows"</c:if>
					 >
						<li>${auction.auctionname}</li>
						<li>
						   <fmt:formatDate value="${auction.auctionstarttime}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</li>
						<li>
						   <fmt:formatDate value="${auction.auctionendtime}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</li>
						<li>${auction.auctionstartprice}</li>
						<li class="list-wd">${auction.auctionprice}</li>
						<li class="borderno red">${auction.username}</li>
					</ul>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(auctionCustomList)==0}">
				<ul class="rows even strong">
					<li>无记录</li>
				</ul>
		</c:if>

		<c:if test="${fn:length(auctionList)!=0}">
			<h1>拍卖中的商品</h1>
			<div class="items records">
				<ul class="rows even strong rowh">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno record">出价记录</li>
					<div class="cl"></div>
				</ul>
				<c:forEach var="auctionDetail" items="${auctionList}" varStatus="state">
					<ul 
					    <c:if test="${state.index%2!=0}">class="rows even"</c:if>
					    <c:if test="${state.index%2==0}">class="rows"</c:if>
				     >
						<li>${auctionDetail.auctionname}</li>
						<li>
							<fmt:formatDate value="${auctionDetail.auctionstarttime}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</li>
						<li>
							<fmt:formatDate value="${auctionDetail.auctionendtime}" pattern="yyyy-MM-dd hh:mm:ss"/>
						</li>
						<li>${auctionDetail.auctionstartprice}</li>
						
						<li class="borderno blue record">
							<c:forEach var="record" items="${auctionDetail.recordList}">
								<p>
								   ${record.user.username}
								   &nbsp;&nbsp;
								   ${record.auctionprice}元</p>	
							</c:forEach>
						</li>
						
						<div class="cl"></div>
					</ul>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${fn:length(auctionList)==0}">
			<ul class="rows even strong">
				<li>无记录</li>
			</ul>
		</c:if>
		<!-- main end-->
	 <br/>
	 <input type="button" value="返回列表" class="spbg buttombg f14" 
    		onclick="location='${pageContext.request.contextPath}/auction/queryAuctions'" />
	</div>
</body>
</html>
