<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light" data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">        
        <a href="index.jsp" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse" style="float: right; margin: 8px 0">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <a href="en.jsp" style="float:right; margin:15px 5px 0"><img src="<c:url value='/resources/images/en.jpg'/>" alt="english"/></a>
        <a href="index.jsp" style="float:right; margin:15px 5px 0 15px"><img src="<c:url value='/resources/images/kr.jpg'/>" alt="korean"/></a>
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="en.jsp#content">Home</a></li>
          <li><a href="en.jsp#about">Features</a></li>
          <li><a href="en.jsp#purchase">Purchase</a></li>
        </ul>
      </div>
    </div>
  </header>