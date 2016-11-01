<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  <jsp:include page="../include/head-contact.jsp"/>
</head>
<body>
  <jsp:include page="../include/nav.jsp"/>
  
  <div id="fullpage">    
	<div class="section" id="section0">
        <div class="row contact">            
            <div class="col-8 m-col-12 qna">
            	<h3>Ask Us Anytime</h3>
            	<form>
	                <input type="text" placeholder="NAME">
	                <input type="text" placeholder="E-MAIL">
	                <input type="text" placeholder="PHONE NUMBER">
	                <textarea placeholder="MESSAGES"></textarea>
	                <label for="upload">FILE UPLOAD</label><input type="file" id="upload">
	                <a href="" class="btn">CONTACT US</a>
            	</form>
            </div>
            
        </div>
        <!-- end: .contact -->
	</div>
    <!-- end: #section0 -->
  </div>
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>