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
            <div class="pull-left m-r-sm text-white">THE MOST ADVANCED <div class="font-thin text-muted">growth management solutions for kids</div></div>
          </div>
          <div class="h4 text-muted m-t-sm">Phyctogram measures height with ultrasonic sensors and send data to an application for management and analysis.</div>
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
          <h3>Why Phyctogram?</h3>
          <!-- <p class="text-muted">
            <span class="text-info">Keeping children's growing up</span>,
            <span class="text-info">Early discovery of 'Precocious Puberty'</span>,
            <span class="text-info">and Learning proper growth management knowledge</span> in
          </p> -->
        </div>
        <div class="row m-t-xl m-b-xl text-center">
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="300">
            <p class="h3 m-b-lg"><i class="fa fa-lightbulb-o fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Keep children’s growth diary with you<br>at all times</h4>
              <p class="text-muted m-t-lg">An old photo album gives us a time to reminisce about the past. Every phase of children’s growth is kept in Phyctogram. History of children’s growth will be always by your side. Sense beautiful memories and share the pleasure with family.</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="600">
            <p class="h3 m-b-lg"><i class="fa fa-signal fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Discover abnormal growth issue<br>in an early stage</h4>
              <p class="text-muted m-t-lg">The number of precocious puberty children has increased nearly 28,000 people over the past 5 years in Korea. Growth failure is also a big concern of parents as well. The keys are to notice the sign of symptoms in an early stage and treat it appropriately.</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="900">
            <p class="h3 m-b-lg"><i class="fa fa-rocket fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">Adequate knowledge of growth management</h4>
              <p class="text-muted m-t-lg">All parents want to bring up their kids without any health issue. Phyctogram diagnose the symptoms of growth failure or precocious puberty based on statistical analysis at the right moment and help parents find solutions.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="responsive" class="bg-dark">
      <div class="text-center">
        <div class="container">
          <div class="m-t-xl m-b-xl wrapper">
            <h3 class="text-white">Our application helps you use Phyctogram smarter and easier.</h3>
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
            <p class="h4 m-b-lg l-h-1x">It is convenient to measure height and manage growth data.</p>
            <div class="row m-b-xl">
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Smart and easy input method</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Platform of children’s diary</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Systematic management of growth information</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>Open community to share the information</div>
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
            <h2 class="font-thin m-b-lg">Easy to use & Simple UI</h2>
            <p class="h4 m-b-lg l-h-1x">Phyctogram provides a simple method to measure height and its aesthetic design aims at simpleness.</p>
            <!-- <p class="m-b-xl">In addition, a kid is paired with an own character which grows up depending on kid’s growth in application.</p> -->
            <!-- <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>Manage the schedule and kid’s diary at the same time</li>
              <li><i class="fa fa-li fa-check text-muted"></i>Share kid’s growth data with character</li>
            </ul> -->
            <p class="m-t-xl m-b-xl h4"><i class="fa fa-quote-left fa-fw fa-1x icon-muted"></i>A baby book platform based on IoT techonolgy, 「Phyctogram」</p>
          </div>
        </div>
      </div>
    </div>        
    <div class="b-t b-light">
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><br><Br>Systematic management solution</h2>
            <p class="h4 m-b-lg l-h-1x">You can perform statistical analysis based on data all users input on Phyctogram and manage data of your kids specifically.</p>
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
            <p class="h4 m-b-lg l-h-1x">You can manage the schedule and kid’s diary with the exiting calendar app. In addition, a kid is paired with an own character which grows up depending on kid’s growth in application</p>
            <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>Manage the schedule and kid’s diary at the same time</li>
              <li><i class="fa fa-li fa-check text-muted"></i>Share growth data and characters with others</li>
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
                <h3 class="m-t-sm">Color</h3>
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