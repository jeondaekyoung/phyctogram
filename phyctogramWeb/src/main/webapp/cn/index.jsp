<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  <script src="<%=application.getContextPath()%>/resources/res/js/typewriter.js"></script>
  <script>
	  $(function () {
	      var typewriter = new Typewriter($("#typewriter"));
	      typewriter.typeText("最先进的幼儿专用成长管理仪器");
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
	        <p>phyctogram测孩子的身高和体重很容易和准确，把测量的数据自动分析，提供生长解决方案</p>
        </div>
      </div>
    </div>
  </div>

	<div class="section row" id="section0">
	    <div class="headlineProd animated">
	      <h3>指按压一次，很容易的测量</h3>
	      <p>通过简单的操作办法以及简洁的设计，让用户提供便利性和审美观</p>
	      <img src="<%=application.getContextPath()%>/resources/res/imgs/main_2.png" alt="phyctogram" class="animated fadeInUpBig col-12">
	    </div>
	</div>
	
	<div class="section row" id="section0-0">
	    <div class="headlineProd animated section0-0">
	      <h3>智能身高尺</h3>
	      <p>用LED显示屏可以知道用户的测量消息</p>
		  <p>用对人体无害的超声波传感器测量孩子的身高</p>
	      <img src="<%=application.getContextPath()%>/resources/res/imgs/stadio.png" alt="phyctogram-stadiometer" class="col-10 prefix-1 suffix-1">
	    </div>
	</div>
	
	<div class="section row" id="section0-1">
      <div class="col-8 animated scale-img">
      	<img src="<%=application.getContextPath()%>/resources/res/imgs/scale.jpg" alt="phyctogram-body-scale">
      </div>
      <div class="col-3">
	    <div class="headlineProd animated scale-box">
	      <h3>智能体重义</h3>
      	  <p>简单的Wi-Fi方式，可以与智能手机配对</p>
		  <p>透过简洁而高尚的设计，送上视觉的满足感</p>
		</div>
      </div>
	</div>
		
	<div class="section" id="section1">
		<div class="wrap row">
			<div class="col-7">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0-cn.png" id="iphone2" alt="main" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/2-cn.png" id="iphone3" alt="growth card" />
			</div>

			<div class="box box-1 col-5 m-col-12 animated">
				<h2>通过App<br>提供智能成长管理服务</h2>
				能使用Wi-Fi把测量的身高、体重数据上传到App—帮助系统管理孩子的成长
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section2">
		<div class="wrap row">
			<div class="col-6">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/4-cn.png" alt="report" id="iphone-red" class="animated img-4 dur400" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/3-cn.png" alt="check lists" id="iphone-blue" class="animated img-3" />
			</div>

			<div class="box box-2 col-6 m-col-12 animated">
				<h2>成长比较解决方案以及<br>提供预测数据</h2>
				<ul>
		          <li>儿童的成长情况不是抽象的比较，而是通过实际数据和具体的信息分析ㅡ可以提供成长过程的系统的管理及分析</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>提供成长相关内容</h2>
				可以擬写包括成长过程育儿日记、相关内容
			</div>
		    <div class="col-8 prefix-2 suffix-2 animated img-last dur500">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/11-cn.png" alt="baby book" />
			</div>
		</div>
	</div>
	
	<div class="section" id="section4">
        <div class="row contact animated">        
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
                    <li class="col-4 dur700">Suite 310, Startup Campus Building 3, 20, Pangyo-ro 289beon-gil, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea</li>
                    <li class="col-4 dur300">+82 70-8624-4536</li>
                    <li class="col-4 dur500">seek-knowledge@knowledge-seek.com</li>
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