<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="include/head.jsp"/>
  <jsp:include page="include/head-contact.jsp"/>
</head>
<body>
  <jsp:include page="include/nav.jsp"/>
  <div id="fullpage">
    
	<div class="section" id="section0">
        <div class="row contact">            
            <div class="col-8 m-col-12 qna">
            	<h3>궁금하신 사항을 문의해주세요</h3>
            	<form>
	                <input type="text" placeholder="이름">
	                <input type="text" placeholder="메일주소">
	                <input type="text" placeholder="연락처">
	                <textarea placeholder="문의 내용"></textarea>
	                <label for="upload">파일 업로드</label><input type="file" id="upload">
	                <a href="" class="btn">문의하기</a>
            	</form>
            </div>
            
        </div>
        <!-- end: .contact -->
	</div>
    <!-- end: #section0 -->
  </div>
  
  <jsp:include page="include/footer.jsp"/>
</body>
</html>