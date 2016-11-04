<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>문의사항</title>
  <jsp:include page="include/head.jsp"/>  
</head>
    
<body>
  
  <section id="content">
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
  
</body>
</html>