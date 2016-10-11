<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    function fnMove(name){
        var offset = $("#" + name).offset();
        $('html, body').animate({scrollTop : offset.top}, 400);
    }
</script>
<header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light" data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">
              
        <a href="<c:url value='${nowPage}'/>" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse" style="float: right; margin: 8px 0">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse">
       <a href="cn.jsp" style="float:right; margin:15px 5px 0"><img src="<%=application.getContextPath()%>/resources/images/cn.jpg" alt="chinese"/></a>
        <a href="en.jsp" style="float:right; margin:15px 5px 0"><img src="<%=application.getContextPath()%>/resources/images/en.jpg" alt="english"/></a>
        <a href="index.jsp" style="float:right; margin:15px 5px 0 15px"><img src="<%=application.getContextPath()%>/resources/images/kr.jpg" alt="korean"/></a>
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a onclick="fnMove('content')">Home</a></li>
          <li><a onclick="fnMove('about')">Features</a></li>
          <li><a onclick="fnMove('purchase')" >Purchase</a></li>
        </ul>
      </div>
    </div>
  </header>