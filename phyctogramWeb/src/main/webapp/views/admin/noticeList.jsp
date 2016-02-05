<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  	
  <!-- header -->
  <header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light"  data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">        
        <a href="#" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li class="active">
            <a href="noticeList.jsp">공지사항</a>
          </li>
          <li>
            <a href="customer.jsp">문의하기</a>
          </li>
        </ul>
      </div>
    </div>
  </header>
  <!-- / header -->
  
  <section id="content">
    <div class="bg-dark lt">
      <div class="container">
        <div class="m-b-lg m-t-lg">
          <h3 class="m-b-none">공지사항</h3>
          <small class="text-muted">Notice</small>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
            <a href="noticeWrite.jsp" class="btn btn-danger btn-block btn-lg m-b-sm">공지 작성하기</a>
            <section class="list-group alt">
                <ul class="list-group list-group-lg">
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a><!-- 수정하기 -->
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>        <!-- 삭제하기 -->
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="noticeView.jsp">장문 메시지를 바로 볼 수 있는 텍스트뷰어, 그릅콜 중 친구 초대 기능 등 2.0.9 업데이트 안내</a></div>
                        <small class="text-muted">2016/01/12</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">기록하고 싶은 메시지를 나와의 채팅방으로 보내세요. (2.0.8 )</a></div>
                        <small class="text-muted">2015/12/21</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">친구 추가없이도 퀵하게, 링크로 대화하는 오픈채팅</a></div>
                        <small class="text-muted">2015/10/21</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">친구그룹 관리부터 백업/복원까지되는 2.0.7 안내</a></div>
                        <small class="text-muted">2015/08/31</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">장문 메시지를 바로 볼 수 있는 텍스트뷰어, 그릅콜 중 친구 초대 기능 등 2.0.9 업데이트 안내</a></div>
                        <small class="text-muted">2016/01/12</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">기록하고 싶은 메시지를 나와의 채팅방으로 보내세요. (2.0.8 )</a></div>
                        <small class="text-muted">2015/12/21</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">친구 추가없이도 퀵하게, 링크로 대화하는 오픈채팅</a></div>
                        <small class="text-muted">2015/10/21</small>
                      </div>
                    </div>
                  </li>
                  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-pencil icon-muted fa-fw m-r-xs"></i></a>
                      <a href="#"><i class="fa fa-times icon-muted fa-fw"></i></a>                  
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="#">친구그룹 관리부터 백업/복원까지되는 2.0.7 안내</a></div>
                        <small class="text-muted">2015/08/31</small>
                      </div>
                    </div>
                  </li>
                </ul>
            </section>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
  <!-- footer -->
  <footer id="footer">
    <div class="bg-dark dker wrapper">
      <div class="container text-center m-t-lg">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-4"><i class="fa fa-map-marker fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">주소</h5>
            <p class="text-sm">서울특별시 강남구 학동로56길 47<br>4층 (주)나리지식앤컴퍼니</p>
          </div>
          <div class="col-sm-4"><i class="fa fa-envelope-o fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">메일</h5>
            <p class="text-sm"><a href="mailto:hey@example.com">seek-knowledge@knowledge-seek.com</a></p>
          </div>
          <div class="col-sm-4"><i class="fa fa-phone fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">유선</h5>
            <p class="text-sm">070-8624-4532</p>
          </div>
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

</body>
</html>