<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>공지사항</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/landing.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/app.css" type="text/css" />
  
</head>
    
<body>
  
  <section id="content">
    <!-- <div class="bg-dark lt">
      <div class="container">
        <div class="m-b-lg m-t-lg">
          <h3 class="m-b-none">고객센터</h3>
          <small class="text-muted">Customer</small>
        </div>
      </div>
    </div> -->
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
          	<div class="panel-group m-b min-h">
             	<div class="panel panel-default">
	              <div class="panel-heading">
                    <span class="text-info"><fmt:formatDate value="${qa.writng_de }" type="both" pattern="yyyy/MM/dd"/></span>
                  </div>
                  <div class="panel-heading">
                    <span class="text-info">${qa.title }</span>
                  </div>
                  <div class="panel-heading">
                    <span class="text-dark">${qa.contents }</span>
                  </div>
                  <div class="panel-heading">
                  	<h5>답변</h5>
                    <span class="text-dark">
                    	<c:choose>
                    		<c:when test="${qa.answer != null }">${qa.answer }</c:when>
                    		<c:otherwise>답변 대기 중입니다</c:otherwise>
                    	</c:choose>
                    
                    </span>
                  </div>
                </div>
              </div>
          </div>
        </div>        
      </div>
    </div>
  </section>
      
  <script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=application.getContextPath()%>/resources/js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=application.getContextPath()%>/resources/js/app.js"></script>
</body>
</html>