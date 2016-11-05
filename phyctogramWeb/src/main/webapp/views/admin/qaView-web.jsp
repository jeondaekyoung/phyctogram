<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>문의사항</title>
  <jsp:include page="include/head.jsp"/>
</head>
    
<body> 
  <!-- header -->
 	<jsp:include page="include/header.jsp" flush="false"/>
  <!-- / header -->
  
  <section id="content">
    <div class="bg-dark lt">
      <div class="container">
        <div class="m-b-lg m-t-lg">
          <h3 class="m-b-none">고객센터(Web)</h3>
          <small class="text-muted">Customer (Web)</small>
        </div>
      </div>
    </div>
    
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
            <section class="list-group alt">
                <ul class="list-group list-group-lg">
                  
                  <li class="list-group-item min-h"><!-- 작성자 정보 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">
                        <span class="text-muted text-center-xs">문의날짜: <fmt:formatDate value="${QaWeb.writng_de }" type="both" pattern="yyyy/MM/dd"/></span>
                        	<p class="text-muted text-center-xs">이름 : ${QaWeb.name }</p>
                        	<p class="text-muted text-center-xs">이메일 : ${QaWeb.email }</p>
                        	<p class="text-muted text-center-xs">연락처 : ${QaWeb.tel }</p>
                        	<p class="text-muted text-center-xs">답변상태 : ${QaWeb.state }</p>
                        </div>
                       </div>
                    </div>
                  </li><!-- 작성자 정보 -->
                  
                  <li class="list-group-item min-h"><!-- 내용영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">내용</div>
                        ${QaWeb.contents }
                       </div>
                    </div>
                  </li><!-- 내용영역 -->
                </ul>
                <!-- 답변/목록/삭제 -->
            </section>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
 <!-- footer -->
 	<jsp:include page="include/footer.jsp" flush="false"/>
 <!-- / footer --> 

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