<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
  <title>无标题文档</title>
  <link href="/css/common.css" rel="stylesheet" type="text/css" />
  <link href="/css/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/WebCalendar.js"></script>
</head>

<body>
<div class="wrap">
  <!-- main begin-->
  <div class="sale">
    <h1 class="lf">在线拍卖系统</h1>
    <div class="logout right"><a href="${pageContext.request.contextPath}/user/doLogout.html" title="注销">注销</a></div>
  </div>
  <div class="forms">
    <form id="form_query" action="${pageContext.request.contextPath}/auction/queryAuctions.html" method="post">
      <input id="page" name="pageNum" type="hidden" value="1"/>
      <label for="name">名称</label>
      <input name="auctionname" value="${condition.auctionname}" type="text" class="nwinput" id="name"/>
      <label for="names">描述</label>
      <input name="auctiondesc" value="${condition.auctiondesc}" type="text" id="names" class="nwinput"/>

      <label for="time">开始时间</label>

      <input name="auctionstarttime"
             value="<fmt:formatDate value="${condition.auctionstarttime}" pattern="yyyy-MM-dd HH:mm:ss" />"
             type="text" id="time" class="nwinput" readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>

      <label for="end-time">结束时间</label>
      <input name="auctionendtime"
             value="<fmt:formatDate value="${condition.auctionendtime}" pattern="yyyy-MM-dd HH:mm:ss" />"
             type="text" id="end-time" class="nwinput" readonly="readonly" onclick="selectDate(this,'yyyy-MM-dd hh:mm:ss')"/>

      <label for="price">起拍价</label>
      <input name="auctionstartprice" onkeyup="value=value.replace(/[^\d]/g,'')"  value="${condition.auctionstartprice}" type="text" id="price" class="nwinput" />
      <input type="submit" style="color: #0096ff" name="" value="查询" class="spbg buttombg f14  sale-buttom"/>
    </form>
       <button class="spbg buttombg f14  sale-buttom" style="color: #0096ff" ><a href="goadd.html">发布</a></button>
       <button class="spbg buttombg f14  sale-buttom" style="color: #0096ff" ><a href="toAuctionResult.html">竞拍结果</a></button>
  </div>
  <div class="items">
    <ul class="rows even strong">
      <li>名称</li>
      <li class="list-wd">描述</li>
      <li>开始时间</li>
      <li>结束时间</li>
      <li>起拍价</li>
      <li class="borderno">操作</li>
    </ul>
    <c:forEach varStatus="status" var="item" items="${pageInfo.list}">
      <ul
              <c:if test="${status.index%2==0}">class="rows"</c:if>
              <c:if test="${status.index%2!=0}">class="rows even"</c:if>
      >
        <li><a href="${pageContext.request.contextPath}/auction/toDetail/${item.auctionid}.html" title="" >${item.auctionname}</a></li>
        <li class="list-wd">${item.auctiondesc}</li>
        <li><fmt:formatDate value="${item.auctionstarttime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
        <li><fmt:formatDate value="${item.auctionendtime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
        <li>${item.auctionstartprice}</li>
        <li class="borderno red">
          <c:if test="${sessionScope.user.userisadmin==0}">
                  <a href="${pageContext.request.contextPath}/auction/toDetail/${item.auctionid}.html" title="竞拍" >竞拍</a>
          </c:if>
          <c:if test="${sessionScope.user.userisadmin==1}">
              <a href="${pageContext.request.contextPath}/auction/toupdate/${item.auctionid}.html" title="竞拍" onclick="dele();">修改</a>|
              <a href="${pageContext.request.contextPath}/auction/deleteAuction/${item.auctionid}.html" title="竞拍" onclick="abc()">删除</a>
        </c:if></li>
      </ul>
    </c:forEach>


    <div class="page">
      <a href="javascript:jumpPage(1)" title="">首页</a>
      <a href="javascript:jumpPage(${pageInfo.prePage})" title="">上一页</a>
      <a href="javascript:jumpPage(${pageInfo.nextPage})" title="">下一页</a>
      <a href="javascript:jumpPage(${pageInfo.lastPage})" title="">尾页</a>
    </div>

  </div>
</div>
<script>
    function jumpPage(pagenum) {
        document.getElementById("page").value=pagenum;
        document.getElementById("form_query").submit();
    }
    function abc(){

        if(confirm("你真的确认要删除吗？请确认")){

            return true;
        }
        else{
            return false;
        }

    };
    function dele(){
        if(confirm("你真的确认要修改吗？请确认")){
            return true;
        }
        else{
            return false;
        }
    }
</script>
<!-- main end-->
</div>
</body>
</html>
