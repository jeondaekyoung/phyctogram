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
            <section class="list-group alt">
                <ul class="list-group list-group-lg">
                  <li class="list-group-item"><!-- 제목영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="h2 text-center-xs m-b-sm">텍스트뷰어, 그릅콜 중 친구 초대 기능 등 2.0.9 업데이트 안내</div>
                        <p class="text-muted text-center-xs">2016/01/12</p>
                      </div>
                    </div>
                  </li>
                  <!-- /제목영역 -->
                  
                  <li class="list-group-item"><!-- 내용영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">카카오톡 사용자 여러분, 안녕하세요. <br>
							요즘 이애란 선생님의 백세인생 인기가 대단한데요.<br><br>
							2010년 3월생인 귀요미 카카오톡은, 이제 곧 일곱 살이 되는 어린이입니다. <br>
							(카카오팀은 어른이… 평균 나이는 비밀입니다.)<br><br>
							카카오톡이 일곱 살 먹을 때까지 일어서 걷고 뛰다가, 가끔은 또 넘어지고 하면서도 <br>
							이만큼 무럭무럭 잘 자랄 수 있었던 건… 전부 사용자 여러분 덕분이라고 생각합니다.<br><br>
							진심으로 감사합니다.<br><br>
							백세카톡 되려면 아직 멀었지만, 백 세가 되어서도 <br>
							사용자 여러분들 곁에 함께이고 싶다 전해라~♫<br><br>
							…네, 2.0.9 업데이트 내역 나갑니다.<br><br>
							■ 장문의 메시지,이제 한번에 보세요. <br><br>
							학교나 회사에서 장문의 메시지 주고받을 때,  꼭 저장하고 열어야해서 번거로우셨을텐데요.<br>
							이젠 장문의 메시지를 클릭 한번에 볼 수 있는 '텍스트뷰어'가 생겼습니다. <br><br>
							장문의 메시지, 이젠 퀵하게 보세요.<br><br>
							■ 톡프로필로 대동 단결, 오픈 채팅 ‘프로필 제한’기능 <br><br>
							명함에도 모임에도… 요즘 흥한다는 오픈채팅 아시죠.<br><br>
							이젠 누구? 하지 말고, 오픈채팅방 개설할 때<br>
							‘카카오톡 프로필로 제한’ 옵션을 선택해 보세요.<br><br>
							프렌즈 캐릭터로도 변신 가능했던 오픈채팅,<br>
							이제부터는 채팅방에 참여하는 방장도, 손님도 카톡 프로필만으로 대화하게 할 수 있습니다.<br>
							(철수인데 영희인 척 속이지 말라 이거예요…)<br><br>
							■ 그룹콜 중에도 친구 초대 <br>
							기존에는 그룹콜 중 친구를 초대하려면 다시 그룹콜을 걸어야 했었는데요. <br>
							이제는 그룹콜 통화 중에 친구를 더 초대할 수 있습니다. <br>
							그룹콜 하다 심심하면,  친구 더 부르라고 전해라 ~♫<br><br>
							■그밖에 기타 사용성 개선<br>
							- 접근성 개선 <br>
							- 그외 버그 수정 및 안정성 개선 <br><br>
							크리스마스엔 여러분께 산타모자 씌워드리고픈 @카카오팀 드림</div>
                      </div>
                    </div>
                  </li><!-- 내용영역 -->
                </ul>
                
            	<a href="javascript:history.back()" class="btn btn-danger btn-block btn-lg m-b-sm">목록으로 가기</a>
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