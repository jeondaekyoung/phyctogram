<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  
  <style>
  /* Style for our header texts
  * --------------------------------------- */  
  #section00 p{
    width: 100%;
  }
  .linkProd {
    padding: 15px;
    background: rgba(0,0,0,0.7);
    border-radius: 50px;
    display: inline-block;
    width: 12em;
    margin-top: 1em;
    color: #fff;
  }
  .linkProd:hover {
    background: rgba(0,0,0,1)
  }
  /* Centered texts in each section
  * --------------------------------------- */
  #myVideo{
    position: absolute;
    right: 0;
    bottom: 0;
    top:0;
    right:0;
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
    background-color: black; /* in case the video doesn't fit the whole page*/
    background-image: ;/* our video */
    background-position: center center;
    background-size: contain;
    object-fit: cover; /*cover video background */
  }
  /* Layer with position absolute in order to have it over the video
  * --------------------------------------- */
  #section00 .layer{
    position: absolute;
    z-index: 1;
    width: 100%;
    left: 0;
    top: 35%;
    padding: 50px 0;
  }
  .modal {
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  }
  #section0 {
    background: radial-gradient(circle, #fff 0%, #fff 12%, #ddd 100%);
    background-size: cover;
    background-position: center;
  }
  /* Section 1
  * --------------------------------------- */
  #section1 img{
    position:absolute;
    left: 40px;
    top: 100px;
    height: 100%;
  }
  #section1 .box{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -192px;
    margin-left: 89px;
    z-index: 1;
  }
  #section1 .imgsContainer{
    display: block;
    position: absolute;
    z-index: 1;
    top: 42%;
    left: 58%;
    margin-top: -325px;
    margin-left: -747px;
    width: 800px;
    height: 696px;
  }
  #section1{
    background: #f5f5f5;
  }        
  </style>
  
  <script type="text/javascript">
	$(document).ready(function() {
		$('#fullpage').fullpage({
			'verticalCentered': false,
			'css3': true,
			'navigation': true,
			'navigationPosition': 'right',

			'afterLoad': function(anchorLink, index){
		         if(index == 1){
		           $('#header').css({'background-color': 'transparent'});
		           $('#footer').css({'color': 'transparent'});
		           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
		         }
		         if(index == 2){
		           $('.headlineProd').addClass('fadeInUp');
		           $('#header').css({'background-color': '#000'});
		           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
		         }
				 if(index == 3){
				   $('#iphone3, #iphone2, #iphone4').addClass('active');
		           $('.box-1').addClass('fadeInRight');
				 }
		         if(index == 4){
		           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
		           $('.box-2').addClass('fadeInUp');
		         }
		         if(index == 5){ 
		           $('#footer').css({'background-color': '#000', 'color':'#fff'});
		           $('.box-3').addClass('fadeInUp');
		         }
			},

			'onLeave': function(index, nextIndex, direction){
				if (index == 4 && direction == 'down'){
					$('.section').eq(index -1).removeClass('moveDown').addClass('moveUp');
           			$('#header').css({'background-color': '#000'});
				}
				else if(index == 4 && direction == 'up'){
					$('.section').eq(index -1).removeClass('moveUp').addClass('moveDown');
				}

				$('#staticImg').toggleClass('active', (index == 3 && direction == 'down' ) || (index == 5 && direction == 'up'));
				$('#staticImg').toggleClass('moveDown', nextIndex == 5);
				$('#staticImg').toggleClass('moveUp', index == 5 && direction == 'up');
			}
		});
	});
  </script>
</head>

<body>
  <jsp:include page="../include/nav.jsp"/>
  
<div id="fullpage">
  <div id="staticImg">
		<div class="imgsContainer">
			<img src="<%=application.getContextPath()%>/resources/res/imgs/2.png" width="322" alt="리포트 페이지" id="iphone-green" />
		</div>
	</div>

  <div class="section " id="section00">
    <video autoplay loop muted id="myVideo">
      <source src="<%=application.getContextPath()%>/resources/res/imgs/video-phyctogram.mp4" type="video/mp4">
    </video>
    <div class="modal">
      <div class="layer animated dur400 fadeInUp">
        <h1>最先进的幼儿专用成长管理解决方案</h1>
        <p>Phyctogram用超声波传感器测量子女的身高后传输到智能手机记录及分析</p>
        <!-- <a class="linkProd" href="product.html">제품보기</a> -->
      </div>
    </div>
  </div>

	<div class="section row" id="section0">
    <div class="headlineProd animated">
      <h3>操作方便&简单的UI</h3>
      <p>方便的操作办法和简单的UI设计会提供用户便利性和美观性</p>
      <img src="<%=application.getContextPath()%>/resources/res/imgs/main_2.png" alt="phyctogram" class="animated fadeInUpBig col-16">
    </div>
	</div>
	<div class="section" id="section1">
		<div class="wrap row">
			<div class="imgsContainer col-7">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/2.png" alt="인트로 이미지" id="iphone2" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0.png" alt="메인 페이지" id="iphone3" />
			</div>

			<div class="box box-1 col-5 animated">
				<h2>方便的操作办法和简单的UI设计会提供用户便利性和美观性</h2>
				能使用Wi-Fi把测量的身高数据上传到App—帮助父母会系统的管理子女的成长信息
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section2">
		<div class="wrap">
			<div class="imgsContainer">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/1.png" width="322" alt="캐릭터 페이지" id="iphone-red" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0-(1).png" height="322" alt="앨범 페이지" id="iphone-blue" />
			</div>

			<div class="box box-2 animated">
				<h2>IoT基础育儿日历平台</h2>
				<ul>
		          <li>简单的输入方式</li>
		          <li>可以记录成长过程的育儿日记平台</li>
		          <li>系统的成长数据管理</li>
		          <li>通过SNS信息共享</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>成长比较解决方案提供</h2>
				把儿童的成长情况不是抽象的比较，而是通过实在数据具体的信息提供ㅡ可以成长过程的系统的管理及分析
			</div>
		</div>
		<div class="imgsContainer">
			<img src="<%=application.getContextPath()%>/resources/res/imgs/11.png" alt="리포트 분석 페이지" id="iphone-two" />
		</div>
	</div>
  </div><!-- 끝: #fullpage -->
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>