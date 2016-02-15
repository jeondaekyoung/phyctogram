<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
          <h3 class="m-b-none">공지사항</h3>
          <small class="text-muted">Notice</small>
        </div>
      </div>
    </div> -->
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
            <section class="list-group alt">
                <ul class="list-group list-group-lg">
                  <li class="list-group-item"><!-- 제목영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="h2 text-center-xs m-b-sm">${notice.title }</div>
                        <p class="text-muted text-center-xs" id="noticeDate"><fmt:formatDate value="${notice.writng_de }" type="both" pattern="yyyy/MM/dd"/></p>
                      </div>
                    </div>
                  </li>
                  <!-- /제목영역 -->
                  
                  <li class="list-group-item min-h"><!-- 내용영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">${notice.notice }</div>
                       </div>
                    </div>
                  </li><!-- 내용영역 -->
                </ul>
                
            </section>
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

  <script type="text/javascript">
  $(document).ready(function(){
	  
  });
  
  
  
//시간포맷
var formatDate = function(dateObj){
  	var curr_year = date.getFullYear();
  	var curr_month = date.getMonth() + 1;
  	var curr_date = date.getDate();
  	var curr_hr = date.getHours();
  	var curr_min = date.getMinutes();
  	
  	if(curr_month.toString().length == 1) {
  		curr_month = '0' + curr_month;
  	}
  	if(curr_date.toString().length == 1){
  		curr_date = '0' + curr_date;
  	}
  	if(curr_hr.toString().length == 1){
  		curr_hr = '0' + curr_hr;
  	}
  	if(curr_min.toString().length == 1){
  		curr_min = '0' + curr_min;
  	}
  	return curr_year + "/"+ curr_month + "/" + curr_date;
  }
  
  </script>
</body>
</html>