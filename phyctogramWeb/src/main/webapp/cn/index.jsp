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
	        <p>phyctogram测孩子的身高和体重很容易和准确，把测量的数据自动分析，提供生长解决方案。</p>
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
	<div class="section" id="section1">
		<div class="wrap row">
			<div class="imgsContainer col-7">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0-cn.png" alt="main" id="iphone2" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/2-cn.png" alt="growth card" id="iphone3" />
			</div>

			<div class="box box-1 col-5 m-col-12 animated">
				<h2>通过App<br>提供智能成长管理服务</h2>
				能使用Wi-Fi把测量的身高、体重数据上传到App—帮助系统管理孩子的成长
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section2">
		<div class="wrap">
			<div class="imgsContainer">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/4-cn.png" alt="report" id="iphone-red" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/3-cn.png" alt="check lists" id="iphone-blue" />
			</div>

			<div class="box box-2 m-col-12 animated">
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
			
			<div class="row">
				<div class="col-8 prefix-2 suffix-2">		
					<img src="<%=application.getContextPath()%>/resources/res/imgs/11-cn.png" alt="baby book" id="iphone-two" />
				</div>
			</div>
		</div>
	</div>
  </div><!-- 끝: #fullpage -->
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>