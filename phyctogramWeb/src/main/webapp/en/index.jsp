<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  <script src="<%=application.getContextPath()%>/resources/res/js/typewriter.js"></script>
  <script>
	  $(function () {
	      var typewriter = new Typewriter($("#typewriter"));
	      typewriter.typeText("The Most Advanced Growth Measuring, Management Device For Kids");
	  });
  </script>
  <jsp:include page="../include/head-index.jsp"/>
</head>

<body>
  <jsp:include page="../include/nav.jsp"/>
  
<div id="fullpage">

  <div class="section " id="section00">
    <video autoplay loop muted id="myVideo">
      <source src="<%=application.getContextPath()%>/resources/res/imgs/video-phyctogram.mp4" type="video/mp4">
    </video>
    <div class="modal">
      <div class="layer animated dur400 fadeInUp">
        <div class="col-6 m-col-12 prefix-3 suffix-3">
	        <h1 id="typewriter"></h1>
	        <p>Phyctogram measures height and weight easily and accurately, and provides growth solution by automatically analysis</p>
        </div>
      </div>
    </div>
  </div>

	<div class="section row" id="section0">
	    <div class="headlineProd animated">
	      <h3>Easy to use & Simple UI</h3>
	      <p>Phyctogram provides a simple method to measure height and its aesthetic design aims at simpleness</p>
	      <img src="<%=application.getContextPath()%>/resources/res/imgs/main_2.png" alt="phyctogram" class="animated fadeInUpBig col-12">
	    </div>
	</div>
	<div class="section" id="section1">
		<div class="wrap row">
			<div class="col-7">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0.png" alt="main" id="iphone2" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/2.png" alt="growth card" id="iphone3" />
			</div>

			<div class="box box-1 col-5 m-col-12 animated">
				<h2>Smart growth management service by the application</h2>
				Children’s data can be linked up application via WI-FI and manage systematic
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section2">
		<div class="wrap">
			<div class="col-6">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/4-en.png" alt="report" id="iphone-red" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/3-en.png" alt="check lists" id="iphone-blue" />
			</div>

			<div class="box box-2 col-6 m-col-12 animated">
				<h2>Provide growth comparison solution and predicted data</h2>
				<ul>
		          <li>You can perform statistical analysis based on data all users input on Phyctogram and manage data of your kids specifically</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>Offer contents associated growth</h2>
				You can creating contents about baby book and childcare  including the process of growth
			</div>
			
			<div class="row">
				<div class="col-8 prefix-2 suffix-2">		
					<img src="<%=application.getContextPath()%>/resources/res/imgs/11-en.png" alt="baby book" width="100%" />
				</div>
			</div>
		</div>
	</div>
	
	<div class="section" id="section4">
        <div class="row contact animated fadeInUp">        
            <div class="col-12">
              <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=s96duQ89XbZlmLcPnKIc"></script>
	          <div id="map" class="box-map" style="width:100%;height:450px;"></div>
	          <script type="text/javascript">
	          var position = new naver.maps.LatLng(37.4045237, 127.1064496);

              var map = new naver.maps.Map('map', {
                  center: position,
                  scrollWheel : false,
                  scaleControl: false,
                  logoControl: false,
                  mapDataControl: false,
                  zoomControl: true,
                  minZoom: 1,
                  zoom: 13
              });

              var markerOptions = {
                  position: position,
                  map: map
              };

              var marker = new naver.maps.Marker(markerOptions);
	            /* 이미지 마커 추가 예정 */
	          </script>
            </div>
            <!-- end: .col-7 -->
            
            <div class="col-12">
                <ul class="contact-info row">
                    <li class="col-4">Suite 310, Startup Campus Building 3, 20, Pangyo-ro 289beon-gil, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea</li>
                    <li class="col-4">+82 70-8624-4536</li>
                    <li class="col-4">seek-knowledge@knowledge-seek.com</li>
                </ul>
            </div>
            <!-- end: .col-5 -->
        </div>
        <!-- end: .contact -->
	</div>
    <!-- end: #section4 -->
  </div><!-- 끝: #fullpage -->
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>