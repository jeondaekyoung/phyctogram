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
    <script type="text/javascript">
	function ecilck(mod) {
		var f=document.adForm;
			switch (mod) {
			case "reset":
				
				if (confirm("작성하신 내용을 취소 하시겠습니까?")!=1) {
					event.preventDefault();
					return false;}
				
				break;
				
			case "submit":
				if(!f.name.value){
					alert("이름을 입력하세요.");
					event.preventDefault();
					f.name.focus();
					return false;
				}

				if(!f.email.value){
					alert("이메일을 입력하세요.");
					event.preventDefault();
					f.email.focus();
					return false;
				}
				if(f.email.value.indexOf("@")==-1){
					alert("이메일 형식이 아닙니다. 다시 입력해주세요.");
					event.preventDefault();
					f.email.value="";
					f.email.focus();
					return false;
				}
				if(!f.tel.value){
					alert("전화번호를 입력하세요.");
					event.preventDefault();
					f.tel.focus();
					return false;
				}
				if(!f.content.value){
					alert("내용을 입력하세요.");
					event.preventDefault();
					f.content.focus();
					return false;
				}
				
				
				
				if (confirm("작성하신 내용은 수정 하실수 없습니다.  \n 이대로 관리자에게 전송하시겠습니까? ")!=1) {
					event.preventDefault();
					return false;
					}
				
				alert("문의 하신 내용은 2~3일 안에 입력하신 정보로 \n 이메일이나 전화를 통해 답변이 갈 예정 입니다.감사합니다.");
				
				break;
			}
			
	} 
	function onlyNumber(event){
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
			return;
		else{
			alert('번호만 입력해주세요.');
			return false;
		}
	}
	function removeChar(event) {
		event = event || window.event;
		var keyID = (event.which) ? event.which : event.keyCode;
		if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ){
			
			return;
		}
		else
			event.target.value = event.target.value.replace(/[^0-9]/g, "");
	}
	</script>
	<div class="section" id="section0">
        <div class="row contact">            
            <div class="col-8 m-col-12 qna">
            	<h3>궁금하신 사항을 문의해주세요</h3>
            	<form action="" name="adForm" accept="">
	                <input type="text" name="name"  placeholder="이름">
	                <input type="text" name="email" placeholder="메일주소">
	                <input type="text" name="tel" onkeydown='return onlyNumber(event)' onkeyup='removeChar(event)' placeholder="연락처">
	                <textarea name="content" placeholder="문의 내용"></textarea>
	                <!-- <label for="upload">파일 업로드</label><input type="file" id="upload"> -->
	                <input type="submit" name="submit" onclick="ecilck('submit')" class="btn" value="문의하기"/>
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