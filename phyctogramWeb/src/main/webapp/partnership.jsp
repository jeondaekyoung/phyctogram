<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>가장 진보한 유아용 선장관리 솔루션 : 픽토그램</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/app.css" type="text/css" />
  <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
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
  <footer id="footer">
    <div class="bg-primary text-center">
      <div class="container wrapper">
        <div class="m-t-xl m-b"><!--  스마트폰으로 자녀의 성장 과정을 기록해보세요. 픽토그램이 자세히 분석해드립니다. -->
          <!-- <a href="#" target="_blank" class="btn btn-lg btn-dark b-white bg-empty m-sm">다운로드</a> -->
          <a href="know" target="_blank" class="btn btn-dark b-white bg-empty m-sm">나리지식</a>
          <a href="agreement.jsp" target="_blank" class="btn btn-dark b-white bg-empty m-sm">이용약관</a>
          <a href="privacy.jsp" target="_blank" class="btn btn-dark b-white bg-empty m-sm">개인정보취급방침</a>
          <a href="partnership.jsp" target="_blank" class="btn btn-dark b-white bg-empty m-sm">제휴안내</a>
        </div>
      </div>
      <i class="fa fa-caret-down fa-4x text-primary m-b-n-lg block"></i>
    </div>
    <div class="bg-dark dker wrapper">
      <div class="container text-center m-t-lg">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-4">
            <i class="fa fa-map-marker fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">주소</h5>
            <p class="text-sm">서울특별시 강남구 학동로56길 47<br>4층 (주)나리지식앤컴퍼니</p>
          </div>
          <div class="col-sm-4">
            <i class="fa fa-envelope-o fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">메일</h5>
            <p class="text-sm"><a href="mailto:hey@example.com">seek-knowledge@knowledge-seek.com</a></p>
          </div>
          <div class="col-sm-4">
            <i class="fa fa-phone fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">유선</h5>
            <p class="text-sm">070-8624-4532</p>
          </div>
        </div>
        <div class="m-t-xl m-b-xl">
          <!-- <p>
            <a href="#" class="btn btn-icon btn-rounded btn-facebook bg-empty m-sm"><i class="fa fa-facebook"></i></a>
            <a href="#" class="btn btn-icon btn-rounded btn-twitter bg-empty m-sm"><i class="fa fa-twitter"></i></a>
            <a href="#" class="btn btn-icon btn-rounded btn-gplus bg-empty m-sm"><i class="fa fa-google-plus"></i></a>
          </p> -->
          <p>
            <a href="#content" data-jump="true" class="btn btn-icon btn-rounded btn-dark b-dark bg-empty m-sm text-muted"><i class="fa fa-angle-up"></i></a>
          </p>
        </div>
      </div>
    </div>
  </footer>
  <!-- / footer -->
  
  <script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=application.getContextPath()%>/resources/js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=application.getContextPath()%>/resources/js/app.js"></script>
  <script src="<%=application.getContextPath()%>/resources/js/app.plugin.js"></script>
  <script src="<%=application.getContextPath()%>/resources/js/appear/jquery.appear.js"></script>
  <script src="<%=application.getContextPath()%>/resources/js/smoothscroll.js"></script>
</body>
</html>