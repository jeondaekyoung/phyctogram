<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>픽토그램</title>
  <jsp:include page="head.jsp"/>
</head>
<body>
  <!-- header -->
  <header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light" data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">        
        <a href="index.jsp" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse" style="float: right; margin: 8px 0">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse"> 
        <a href="en.jsp" style="float:right; margin:15px 5px 0"><img src="<c:url value='/resources/images/en.jpg'/>" alt="english"/></a>
        <a href="index.jsp" style="float:right; margin:15px 5px 0 15px"><img src="<c:url value='/resources/images/kr.jpg'/>" alt="korean"/></a>
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
    <div class="bg-primary dk">
      <div class="text-center wrapper">
        <div class="m-t-xl m-b-xl">
          <div class="text-uc h1 font-bold inline">
            <div class="pull-left m-r-sm text-white">가장 진보한 <span class="font-thin text-muted">유아용 성장관리 솔루션</span></div>
          </div>
          <div class="h4 text-muted m-t-sm">픽토그램은 초음파 센서로 측정된 키를 스마트폰으로 기록·분석 해줍니다.</div>
        </div>
        <!-- <p class="text-center m-b-xl">
          <a href="http://themeforest.net/user/Flatfull/portfolio?ref=flatfull" target="_blank" class="btn btn-lg btn-dark m-sm">앱 다운로드</a>
        </p> -->
      </div>
      <div class="padder">
        <div class="hbox">
          <aside class="col-md-3 v-bottom text-right"></aside>
          <aside class="col-md-6">
            <section class="m-b-n-lg no-border device animated fadeInUp">
            
              <img src="<c:url value='/resources/images/main1.jpg'/>" class="img-full" >
            </section>
          </aside>
          <aside class="col-md-3 v-bottom"></aside>
        </div>
      </div>
      <div class="dker pos-rlt">
        <div class="container wrapper">
          <div class="m-t-lg m-b-lg text-center"></div>
        </div>
      </div>
    </div>
    <div id="about">
      <div class="container">
        <div class="m-t-xl m-b-xl text-center wrapper">
          <h3>스마트한 성장관리가 필요한 이유</h3>
          <p class="text-muted">
            <span class="text-info">자녀의 성장과정 평생소장</span>,
            <span class="text-info">성조숙증 조기 발견</span>,
            <span class="text-info">올바른 성장관리 지식습득</span>에 있습니다.
          </p>
        </div>
        <div class="row m-t-xl m-b-xl text-center">
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="300">
            <p class="h3 m-b-lg"><i class="fa fa-lightbulb-o fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">성장과정 평생소장</h4>
              <p class="text-muted m-t-lg">오래된 앨범을 구경하는 것만큼 재밌는 게 있을까요? 픽토그램이 그런 즐거움을 제공 해드리겠습니다. 스마트폰에 기록된 성장과정은 평생 없어지지 않습니다. 자녀에게 뜻깊은 추억을 선물해주세요!</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="600">
            <p class="h3 m-b-lg"><i class="fa fa-signal fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">성조숙증 조기 발견</h4>
              <p class="text-muted m-t-lg">성조숙증이란 2차 성징이 비정상적으로 빨리 시작되는 증세입니다. 최근 5년간 성조숙증 어린이가 4.4배 증가하여 2만 8천명에 육박합니다. 때를 놓치면 치료할 수 없고 저(低)신장, 우울증을 동반하기 때문에 조기에 발견하여 치료해야 합니다.</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="900">
            <p class="h3 m-b-lg"><i class="fa fa-rocket fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">올바른 성장관리 지식습득</h4>
              <p class="text-muted m-t-lg">자녀를 올바르게 성장시키는 것만큼 부모의 도움이 필요한 것이 있을까요? 자녀의 성장 추이를 분석하여 키가 더디게 자라는 지, 비정상적으로 빨리 자라는 지 통계 분석을 통해 판단해주고 올바른 길을 제시 해드립니다.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="responsive" class="bg-dark">
      <div class="text-center">
        <div class="container">
          <div class="m-t-xl m-b-xl wrapper">
            <h3 class="text-white">어플리케이션이 더욱 똑똑한 성장관리를 도와줍니다.</h3>
            <p>This application helps you manage growth smarter.</p>
          </div>
          <div class="row m-t-xl m-b-xl"></div>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><span class="text-info">IoT 기반</span> 성장데이터 관리 플랫폼</h2>
            <p class="h4 m-b-lg l-h-1x">자녀의 키를 편리하게 측정하고 관리할 수 있게 해줍니다</p>
            <div class="row m-b-xl">
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>스마트하고 쉬운 입력방식</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>성장 과정을 담은 육아일기 플랫폼</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>체계적인 성장 데이터 관리</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>커뮤니티를 통한 정보 공유</div>
            </div>
            <p class="clearfix">&nbsp;</p>
            <div class="row m-b-xl">
              <div class="col-xs-2"><i class="fa fa-desktop fa-4x icon-muted"></i></div>
              <div class="col-xs-2"><i class="fa fa-plus icon-muted m-t"></i></div>
              <div class="col-xs-2"><i class="fa fa-tablet fa-4x icon-muted"></i></div>
              <div class="col-xs-2"><i class="fa fa-plus icon-muted m-t"></i></div>
              <div class="col-xs-2"><i class="fa fa-mobile fa-4x icon-muted"></i></div>
            </div>
          </div>
          <div class="col-sm-5 text-center">
            <section class="panel bg-dark inline aside-md no-border device phone animated fadeInRightBig">
              <header class="panel-heading text-center">
                <i class="fa fa-minus fa-2x icon-muted m-b-n-xs block"></i>
              </header>
              <div class="m-l-xs m-r-xs">
                <div class="carousel auto slide" id="c-fade" data-interval="3000">
                  <div class="carousel-inner">
                    <div class="item active text-center"><img src=<c:url value='/resources/images/phone0.png'/> class="img-full"></div>
                    <div class="item text-center"><img src="<c:url value='/resources/images/phone1.png'/>" class="img-full"></div>
                  </div>
                </div>
              </div>
              <footer class="bg-dark text-center panel-footer no-border">
                <i class="fa fa-circle icon-muted fa-lg"></i>
              </footer>
            </section>
          </div>
        </div>        
      </div>
    </div>
    <div class="bg-white b-t b-light">
      <div class="container">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-5 text-center clearfix m-t-xl" data-ride="animated" data-animation="fadeInLeftBig">
            <img src="<c:url value='/resources/images/Phyctogram.jpg'/>" width="360px">
          </div>
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg">단순한 조작 & 심플한 UI</h2>
            <p class="h4 m-b-lg l-h-1x">정보를 손쉽게 얻을 수 있으며, 단순한 조작&심플한 UI적용 디자인으로 심미적 가치를 고려하였습니다.</p>
            <p class="m-b-xl">또한 신장 정보를 Wi-Fi를 통해 가정용 AP에 전송하여 웹 및 어플리케이션까지 연동할 수 있어, 아동의 성장 정보를 체계적으로 관리할 수 있습니다.</p>
            <p class="m-t-xl m-b-xl h4"><i class="fa fa-quote-left fa-fw fa-1x icon-muted"></i> IoT 기반 육아일기 플랫폼 「픽토그램」</p>
          </div>
        </div>
      </div>
    </div>        
    <div class="b-t b-light">
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><br><Br>성장비교 솔루션 제공</h2>
            <p class="h4 m-b-lg l-h-1x">자녀의 성장 정도를 기존의 추상적 비교가 아닌 다른 회원들의 사례와 수치를 통한 비교가 가능함으로써 성장 발달 사항의 체계적인 관리가 가능합니다.</p>
          </div>
          <div class="col-sm-5 text-center"  data-ride="animated" data-animation="fadeInUp" >
            <section class="panel bg-dark inline aside-md no-border device phone animated fadeInUp">
              <header class="panel-heading text-center">
                <i class="fa fa-minus fa-2x icon-muted m-b-n-xs block"></i>
              </header>
              <div class="m-l-xs m-r-xs"><img src="<c:url value='/resources/images/phone1.png'/>" class="img-full" ></div>
              <footer class="bg-dark text-center panel-footer no-border">
                <i class="fa fa-circle icon-muted fa-lg"></i>
              </footer>
            </section>
          </div>
        </div>        
      </div>
    </div>
    <div class="bg-white b-t b-light pos-rlt">
      <div class="container">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-5 text-center clearfix m-t-xl" data-ride="animated" data-animation="fadeInLeftBig">
            <div class="h3 font-bold m-t-xl m-b-xl">
              <i class="fa fa-circle text-primary fa-2x"></i>
              <i class="fa fa-circle text-info fa-2x"></i>
              <i class="fa fa-circle text-success fa-2x"></i>
              <i class="fa fa-circle text-warning fa-2x"></i>
              <i class="fa fa-circle text-danger fa-2x"></i>
              <i class="fa fa-circle text-dark fa-2x"></i>
              <i class="fa fa-circle text-light fa-2x"></i>              
            </div>
          </div>
            <div class="clearfix"></div>
          <div class="col-sm-5 text-center">
            <section class="panel bg-dark inline aside-md no-border device phone animated fadeInLeftBig">
              <header class="panel-heading text-center">
                <i class="fa fa-minus fa-2x icon-muted m-b-n-xs block"></i>
              </header>
              <div class="m-l-xs m-r-xs">
                <div class="carousel auto slide" id="c-fade" data-interval="3000">
                  <div class="carousel-inner">
                    <div class="item active text-center"><img src="<c:url value='/resources/images/phone3.png'/>" class="img-full" ></div>
                    <div class="item text-center"><img src="<c:url value ='/resources/images/phone4.png'/>" class="img-full" ></div>
                  </div>
                </div>
              </div>
              <footer class="bg-dark text-center panel-footer no-border">
                <i class="fa fa-circle icon-muted fa-lg"></i>
              </footer>
            </section>
          </div>
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg">캘린더 & 캐릭터</h2>
            <p class="h4 m-b-lg l-h-1x">사용자가 기존에 사용하던 캘린더 앱을 연동하여 스케줄과 육아일지를 동시에 기록할 수 있으며, 자신의 아이를 캐릭터화하여 함께 성장합니다.</p>
            <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>스케줄과 육아일지를 동시에 기록</li>
              <li><i class="fa fa-li fa-check text-muted"></i>아이를 캐릭터화하여 성장정보 공유</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div id="purchase" class="container bg-white" style="background: url(<c:url value='/resources/images/Phyctogram.jpg'/>); background-size:100% 100%">
      <div class="clearfix">
        <div class="row m-b-xl">
          <div class="col-sm-4"></div>
          <div class="col-sm-4 animated fadeInUp" style="padding: 150px 0 50px">
            <section class="panel b-primary text-center">
              <header class="panel-heading bg-primary">
                <h3 class="m-t-sm">색상을 고르세요</h3>
                <p>Choose a color</p>
              </header>
              <ul class="list-group">
                <li class="list-group-item text-center bg-light lt">
                  <div class="padder-v"><span class="text-danger font-bold h1">출시 예정</span></div>
                </li>
                <li class="list-group-item">
                    <label for="bk"><input title="준비중입니다." type="radio" id="bk" name="color" disabled>스톤 블랙 (Stone Black)</label>
                </li>
                <li class="list-group-item">
                    <label for="wh"><input type="radio" id="wh" name="color" checked>세라믹 화이트 (Ceramic white)</label>
                </li>
              </ul>
              <footer class="panel-footer">
                <!-- <a href="#" class="btn btn-primary m-t m-b">선택 완료</a> -->
              </footer>
            </section>
          </div>
        </div>
      </div>      
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