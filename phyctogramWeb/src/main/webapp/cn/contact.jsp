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
            	<h3>如果有疑问, 如何咨询</h3>
            	<form>
	                <input type="text" placeholder="名字">
	                <input type="text" placeholder="电子邮件">
	                <input type="text" placeholder="电话号码">
	                <textarea placeholder="咨询内容"></textarea>
	                <label for="upload">文件上传</label><input type="file" id="upload">
	                <a href="" class="btn">咨询</a>
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