<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8" />
  <title>픽토그램-제휴안내</title>
  <jsp:include page="head.jsp"/>
</head>
<body>
  <!-- header -->
  <header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light" data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">        
        <a href="index.jsp" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li class="active"><a href="index.jsp#content">Home</a></li>
          <li><a href="index.jsp#about">Features</a></li>
          <li><a href="index.jsp#purchase">Purchase</a></li>
        </ul>
      </div>
    </div>
  </header>	
  <!-- / header -->
  
  <section id="content">
    
    <div class="m-t-xl m-b-xl text-center wrapper">
    <h3>제휴 문의</h3>
      <pre style="text-align:left">    	
기회의 문은 항상 열려 있습니다.
독창적인 아이디어나 사업모델을 가지고 계신 분은 언제든지 제안을 주세요


제안 절차
1. 제휴 및 제안 접수 ( 보내실 곳: aram@knowledge-seek.com) 연락처와 성함 필수
2. 담당자 검토: 2~3일 정도 소요 됩니다.
3. 연락: 좋은 제안에 대해 연락을 드립니다.
4. 채택 및 실행: 채택이 되면 계약을 체결하고 실행을 합니다.


접수 방법
아래의 이메일로 제휴 문의 바랍니다.
aram@knowledge-seek.com
      </pre>
    </div>
        
  </section>
  
  <!-- footer -->
  <jsp:include page="footer.jsp"/>
  <!-- / footer -->
  
  <script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
  <!-- Bootstrap -->
  <script src="<c:url value='/resources/js/bootstrap.js'/>"></script>
  <!-- App -->
  <script src="<c:url value='/resources/js/app.js'/>"></script>
  <script src="<c:url value='/resources/js/app.plugin.js'/>"></script>
  <script src="<c:url value='/resources/js/appear/jquery.appear.js'/>"></script>
  <script src="<c:url value='/resources/js/smoothscroll.js'/>"></script>
  <script src="<c:url value='/resources/js/landing.js'/>"></script>
</body>
</html>