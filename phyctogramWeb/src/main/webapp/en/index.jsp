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
        <h1>THE MOST ADVANCED GROWTH MANAGEMENT SOLUTIONS FOR KIDS</h1>
        <p>Phyctogram measures height with ultrasonic sensors and send data to an application for management and analysis.</p>
        <!-- <a class="linkProd" href="product.html">제품보기</a> -->
      </div>
    </div>
  </div>

	<div class="section row" id="section0">
    <div class="headlineProd animated">
      <h3>Easy to use & Simple UI</h3>
      <p>Phyctogram provides a simple method to measure height and its aesthetic design aims at simpleness.</p>
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
				<h2>Our application helps you use Phyctogram smarter and easier.</h2>
				Children’s data can be linked up application via WI-FI and manage systematic.
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
				<h2>IoT-based growth data management platform</h2>
				<ul>
		          <li>Smart and easy input method</li>
		          <li>Platform of kid’s diary</li>
		          <li>Systematic management of growth information</li>
		          <li>Open community to share the information</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>Systematic management solution</h2>
				You can perform statistical analysis based on data all users input on Phyctogram and manage data of your kids specifically.
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