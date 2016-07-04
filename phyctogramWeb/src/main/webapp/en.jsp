<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>Phyctogram</title>
  <meta name="naver-site-verification" content="b5c6de529108b528bdc4d119fd6a5086e67ad0c3"/>
  <meta name="description" content=", IoT" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />  
  <meta name="robots" content="all" />
  <meta property="og:title" content="Phyctogram">
  <meta property="og:description" content=", IoT" >
  <link rel="shortcut icon" href="<c:url value='/resources/images/favicon.ico'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css" type="text/css'/>" />
  <link rel="stylesheet" href="<c:url value='/resources/css/animate.css" type="text/css'/>" />
  <link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css" type="text/css'/>" />
  <link rel="stylesheet" href="<c:url value='/resources/css/landing.css" type="text/css'/>" />
  <link rel="stylesheet" href="<c:url value='/resources/css/app.css" type="text/css'/>" />
  <!--[if lt IE 9]>
    <script src="js/ie/html5shiv.js"></script>
    <script src="js/ie/respond.min.js"></script>
    <script src="js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body>
  <!-- header -->
<jsp:include page="headEn.jsp"/>
  <!-- / header -->
  
  <section id="content">
    <div class="bg-primary dk">
      <div class="text-center wrapper">
        <div class="m-t-xl m-b-xl">
          <div class="text-uc h1 font-bold inline">
            <div class="pull-left m-r-sm text-white">THE MOST ADVANCED <span class="font-thin text-muted">child growth management solutions</span></div>
          </div>
          <div class="h4 text-muted m-t-sm">Phyctogram Allows the recording and analysis of the height measurement by ultrasonic sensors to smartphones</div>
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
          <h3>Why You Need a Smart Growth Management</h3>
          <p class="text-muted">
            <span class="text-info">Keeping children's growing up</span>,
            <span class="text-info">Early discovery of 'Precocious Puberty'</span>,
            <span class="text-info">and Learning proper growth management knowledge</span> in
          </p>
        </div>
        <div class="row m-t-xl m-b-xl text-center">
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="300">
            <p class="h3 m-b-lg"><i class="fa fa-lightbulb-o fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Keep your children's life growing up</h4>
              <p class="text-muted m-t-lg">How funny watch the photo albums? We will give you that enjoyment. The process of growth recorded in the phyctogram does not disappear forever. Please present the memories for your children!</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="600">
            <p class="h3 m-b-lg"><i class="fa fa-signal fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Early discover of 'Precocious Puberty'</h4>
              <p class="text-muted m-t-lg">The symptoms of precocious puberty is secondary sexual characteristics which started unusually early. Precocious puberty children are 4.4 times increase over the past five years to nearly 28,000 people. Precocious puberty is difficult to treat and include depression if you are not early dicover.</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="900">
            <p class="h3 m-b-lg"><i class="fa fa-rocket fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Learn proper growth management knowledge</h4>
              <p class="text-muted m-t-lg">To grow your children proper, you need to help them. Phyctogram determine 'Precocious Puberty' or 'Short Stature' through statistical analysis and Gives you proper growth management knowledge.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="responsive" class="bg-dark">
      <div class="text-center">
        <div class="container">
          <div class="m-t-xl m-b-xl wrapper">
            <h3 class="text-white">This application helps you manage growth smarter.</h3>
          </div>
          <div class="row m-t-xl m-b-xl"></div>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><span class="text-info">IoT-based</span> growth data management platform</h2>
            <p class="h4 m-b-lg l-h-1x">Phyctogram makes measurement and management conveniently</p>
            <div class="row m-b-xl">
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Smart and easy input method</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Baby book platform containing the growth</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Systematic data management growth</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Information sharing through community</div>
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
                    <div class="item active text-center"><img src="<c:url value='/resources/images/phone0.png'/>" class="img-full"></div>
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
            <h2 class="font-thin m-b-lg">Easy operating and simple UI</h2>
            <p class="h4 m-b-lg l-h-1x">You can get the growth data by easy operating, and we consider aesthetic value by applying a simple UI design.</p>
            <p class="m-b-xl">Also, children's data can be linked up applilcation via Wi-Fi and manage systematic</p>
            <p class="m-t-xl m-b-xl h4"><i class="fa fa-quote-left fa-fw fa-1x icon-muted"></i> Baby book platforms based on IoT, 「Phyctogram」</p>
          </div>
        </div>
      </div>
    </div>        
    <div class="b-t b-light">
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><br><Br>Growth comparing solution available</h2>
            <p class="h4 m-b-lg l-h-1x">Phyctogram can be systematic growth management by comparing your children's data with other members data.</p>
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
                    <div class="item text-center"><img src="<c:url value='/resources/images/phone4.png'/>" class="img-full" ></div>
                  </div>
                </div>
              </div>
              <footer class="bg-dark text-center panel-footer no-border">
                <i class="fa fa-circle icon-muted fa-lg"></i>
              </footer>
            </section>
          </div>
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg">Calendar & Character</h2>
            <p class="h4 m-b-lg l-h-1x">We offer to calendarview the baby book list, children and character to grow together.</p>
            <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>Recording baby book in calendarview</li>
              <li><i class="fa fa-li fa-check text-muted"></i>Sharing children's character and growth data</li>
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
                <h3 class="m-t-sm">Choose a color</h3>
              </header>
              <ul class="list-group">
                <li class="list-group-item text-center bg-light lt">
                  <div class="padder-v"><span class="text-danger font-bold h1">Coming Soon</span></div>
                </li>
                <li class="list-group-item">
                    <label for="bk" ><input title="preparing" type="radio" id="bk" name="color" disabled>Stone Black</label>
                </li>
                <li class="list-group-item">
                    <label for="wh"><input type="radio" id="wh" name="color" checked>Ceramic white</label>
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
  <jsp:include page="footerEn.jsp"/>
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