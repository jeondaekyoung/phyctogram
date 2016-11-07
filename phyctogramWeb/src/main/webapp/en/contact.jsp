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
				
				if (confirm("Are you sure you want to cancel your creation?")!=1) {
					event.preventDefault();
					return false;}
				
				break;
				
			case "submit":
				if(!f.name.value){
					alert("Please enter your name.");
					event.preventDefault();
					f.name.focus();
					return false;
				}

				if(!f.email.value){
					alert("Please enter your email.");
					event.preventDefault();
					f.email.focus();
					return false;
				}
				if(f.email.value.indexOf("@")==-1){
					alert("This is not an email format. Please re-enter.");					
					event.preventDefault();
					f.email.value="";
					f.email.focus();
					return false;
				}
				if(!f.tel.value){
					alert("Please enter a phone number.");
					event.preventDefault();
					f.tel.focus();
					return false;
				}
				if(!f.contents.value){
					alert("Please enter your content.");
					event.preventDefault();
					f.contents.focus();
					return false;
				}
				
				if (confirm("You cannot edit your content.  \n Would you like to send it to your manager? ")!=1) {
					event.preventDefault();
					return false;
					}
				
				alert("We will respond to your inquiry by email or phone. Thank you.");
				
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
			alert('Please enter the number only.');
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
			if(keyID == 107){
				return;
			}
			event.target.value = event.target.value.replace(/[^0-9\+]/g, "");
	}
	</script>
  <div id="fullpage">    
	<div class="section" id="section0">
        <div class="row contact">            
            <div class="col-8 m-col-12 qna">
            	<h3>Ask Us Anytime</h3>
            	<form action="<c:url value="/QaWeb/write.do"/>" name="adForm" method="post">
            		<input type="hidden" name="state" value="답변대기">
	                <input type="text" name="name"  placeholder="NAME">
	                <input type="text" name="email"  placeholder="E-MAIL">
	                <input type="text" name="tel" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)'  placeholder="PHONE NUMBER">
	                <textarea name="contents" placeholder="MESSAGES"></textarea>
	                <!-- <label for="upload">FILE UPLOAD</label><input type="file" id="upload"> -->
	                <input type="submit" name="submit" onclick="ecilck('submit')" class="btn" value="CONTACT US"/>

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