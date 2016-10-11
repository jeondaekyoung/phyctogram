<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>Phyctogram</title>
  <jsp:include page="head.jsp"/>
</head>
<body>
  <!-- header -->
<jsp:include page="top.jsp"/>
  <!-- / header -->
  
  <section id="content">
    <div class="bg-primary dk">
      <div class="text-center wrapper">
        <div class="m-t-xl m-b-xl">
          <div class="text-uc h1 font-bold inline">
            <div class="pull-left m-r-sm text-white">最先进的 <div class="font-thin text-muted">幼儿专用成长管理解决方案</div></div>
          </div>
          <div class="h4 text-muted m-t-sm">Phyctogram用超声波传感器测量孩子的身高后传输到智能手机进行记录和分析。</div>
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
          <h3>为什么需要智能成长管理呢？</h3>
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
              <h4 class="m-t-none">成长过程可以一生收藏</h4>
              <p class="text-muted m-t-lg">有比看看老照片还有趣的事吗？Phyctogram通过管理成长过程的资料带给你一份快乐。 App上记录的成长过程随时可以查询。给您的孩子送去有意义的回忆!</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="600">
            <p class="h3 m-b-lg"><i class="fa fa-signal fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">成长期异常症状早期发现及应对</h4>
              <p class="text-muted m-t-lg">性早熟是二次性征提前发育的症状。在韩国近5年间性早熟儿童数量增加了4.4倍，已经接近2万8千名。儿童成长迟慢也是很多父母担心的问题，此类问题尽早发现及时干预是非常有必要的。</p>
            </div>
          </div>
          <div class="col-sm-4" data-ride="animated" data-animation="fadeInLeft" data-delay="900">
            <p class="h3 m-b-lg"><i class="fa fa-rocket fa-3x text-info"></i></p>
            <div class="">
              <h4 class="m-t-none">正确的成长管理知识习得</h4>
              <p class="text-muted m-t-lg">为了子女的健康成长, 父母的帮助是很有必要的。Phyctogram通过子女成长速度是不是成长特别慢或者异常的快来判断，对个人的症状提供解决方案。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="responsive" class="bg-dark">
      <div class="text-center">
        <div class="container">
          <div class="m-t-xl m-b-xl wrapper">
            <h3 class="text-white">APP会帮助智能成长管理</h3>
          </div>
          <div class="row m-t-xl m-b-xl"></div>
        </div>
      </div>
      
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><span class="text-info">loT基础</span>成长data管理平台</h2>
            <p class="h4 m-b-lg l-h-1x">把您子女的身高使您方便测量及管理。</p>
            <div class="row m-b-xl">
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>简单的输入方式</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>可以记录成长过程的育儿日记平台</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>系统的成长数据管理</div>
              <div class="col-sm-6"><i class="fa fa-fw fa-angle-right"></i>通过SNS信息共享</div>
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
                    <div class="item active text-center"><img src="<c:url value='/resources/images/cn/phone1.jpg'/>" class="img-full"></div>
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
            <h2 class="font-thin m-b-lg">操作方便&简单的UI</h2>
            <p class="h4 m-b-lg l-h-1x">方便的操作办法和简单的UI设计会给用户提供便利性和美观感。</p>
            <!-- <p class="m-b-xl">In addition, a kid is paired with an own character which grows up depending on kid’s growth in application.</p> -->
            <!-- <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>Manage the schedule and kid’s diary at the same time</li>
              <li><i class="fa fa-li fa-check text-muted"></i>Share kid’s growth data with character</li>
            </ul> -->
            <p class="m-t-xl m-b-xl h4"><i class="fa fa-quote-left fa-fw fa-1x icon-muted"></i>IoT基础育儿日历平台「Phyctogram」</p>
          </div>
        </div>
      </div>
    </div>        
    <div class="b-t b-light">
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg"><br><Br>成长比较解决方案提供</h2>
            <p class="h4 m-b-lg l-h-1x">儿童的成长情况不是抽象的比较，而是通过实际数据和具体的信息分析ㅡ可以提供成长过程的系统的管理及分析。</p>
          </div>
          <div class="col-sm-5 text-center"  data-ride="animated" data-animation="fadeInUp" >
            <section class="panel bg-dark inline aside-md no-border device phone animated fadeInUp">
              <header class="panel-heading text-center">
                <i class="fa fa-minus fa-2x icon-muted m-b-n-xs block"></i>
              </header>
              <div class="m-l-xs m-r-xs"><img src="<c:url value='/resources/images/cn/phone2.jpg'/>" class="img-full" ></div>
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
                    <div class="item active text-center"><img src="<c:url value='/resources/images/cn/phone3.jpg'/>" class="img-full" ></div>
                    <div class="item text-center"><img src="<c:url value='/resources/images/cn/phone4.jpg'/>" class="img-full" ></div>
                  </div>
                </div>
              </div>
              <footer class="bg-dark text-center panel-footer no-border">
                <i class="fa fa-circle icon-muted fa-lg"></i>
              </footer>
            </section>
          </div>
          <div class="col-sm-7">
            <h2 class="font-thin m-b-lg">日历&角色</h2>
            <p class="h4 m-b-lg l-h-1x">用户以前用的日历App联动功能ㅡ日程和育儿日记会一起管理, App内提供卡通和儿童一起成长功能</p>
            <ul class="m-b-xl fa-ul">
              <li><i class="fa fa-li fa-check text-muted"></i>日程和育儿日记同时记录</li>
              <li><i class="fa fa-li fa-check text-muted"></i>儿童的卡通化，成长信息共享</li>
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
                <h3 class="m-t-sm">颜色</h3>
              </header>
              <ul class="list-group">
                <li class="list-group-item text-center bg-light lt">
                  <div class="padder-v"><span class="text-danger font-bold h1">预定上市</span></div>
                </li>
                <li class="list-group-item">
                    <label for="bk" ><input title="preparing" type="radio" id="bk" name="color" disabled>黑色</label>
                </li>
                <li class="list-group-item">
                    <label for="wh"><input type="radio" id="wh" name="color" checked>白色</label>
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