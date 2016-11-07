<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  <jsp:include page="../include/head-contact.jsp"/>
</head>
<body>
  <jsp:include page="../include/nav.jsp"/>
  <script type="text/javascript">
	function ecilck(mod) {
		var f=document.adForm;
			switch (mod) {
			case "reset":
				
				if (confirm("你确定要取消您所写的内容？")!=1) {
					event.preventDefault();
					return false;}
				
				break;
				
			case "submit":
				if(!f.name.value){
					alert("请输入您的姓名。");
					event.preventDefault();
					f.name.focus();
					return false;
				}

				if(!f.email.value){
					alert("请输入您的电邮。");
					event.preventDefault();
					f.email.focus();
					return false;
				}
				if(f.email.value.indexOf("@")==-1){
					alert("不是电邮格式。请重新输入。");
					event.preventDefault();
					f.email.value="";
					f.email.focus();
					return false;
				}
				if(!f.tel.value){
					alert("请输入您的手机号码。");
					event.preventDefault();
					f.tel.focus();
					return false;
				}
				if(!f.contents.value){
					alert("请输入内容。");
					event.preventDefault();
					f.contents.focus();
					return false;
				}
				
				
				
				if (confirm("您确认以后内容将无法修改。仍要发送，请按是。")!=1) {
					event.preventDefault();
					return false;
					}
				
				alert("将通过电邮或者电话回答您的查询，谢谢。");
				
				break;
			}
			
	} 
	function onlyNumber(event){
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		//키번호 48~57 위쪽 번호키 , 96~105 오른쪽 번호키 , 8 backSpace , 46 delete , 37,39 방향키 왼쪽 오른쪽 , 107 오른쪽 +
		if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) ||
				keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID == 9 || keyID == 107) 
			return;
		else{
			alert('请只输入数字。');
			return false;
		}
	}
	function removeChar(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 || keyID==107 ){
			
			return;
		}
		else
			event.target.value = event.target.value.replace(/[^0-9\+]/g, "");
	}
	</script>
  <div id="fullpage">    
	<div class="section" id="section0">
        <div class="row contact">            
            <div class="col-8 m-col-12 qna">
            	<h3>如果有疑问, 如何咨询</h3>
            	<form action="<c:url value="/QaWeb/write.do"/>" name="adForm" method="post">
	                <input type="hidden" name="state" value="답변대기">
	                <input type="text" name="name" placeholder="名字">
	                <input type="text" name="email" placeholder="电子邮件">
	                <input type="text" name="tel" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' placeholder="电话号码">
	                <textarea name="contents" placeholder="咨询内容"></textarea>
	                <!-- <label for="upload">文件上传</label><input type="file" id="upload"> -->
	                <input type="submit" name="submit" onclick="ecilck('submit')" class="btn" value="咨询"/>
	                
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