<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="/include/head.jsp"/>
  
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
  <jsp:include page="include/nav.jsp"/>
  
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
        <h1>가장 진보한 유아용 성장관리 솔루션</h1>
        <p>픽토그램은 초음파 센서로 측정된 자녀의 키를 스마트폰으로 기록·분석 해줍니다.</p>
        <!-- <a class="linkProd" href="product.html">제품보기</a> -->
      </div>
    </div>
  </div>

	<div class="section row" id="section0">
    <div class="headlineProd animated">
      <h3>단 한 번의 터치로 손쉬운 측정</h3>
      <p>간편한 조작 방법과 심플한 UI디자인으로 사용자에게 편리성과 심미적인 가치를 제공합니다.</p>
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
				<h2>어플리케이션이 스마트한<br>성장관리를 도와줍니다.</h2>
				측정된 신장 데이터는 WI-FI를 통해 자동으로 어플리케이션으로 연동되어 부모가 아이의 성장 정보를 체계적으로 관리할 수 있도록 도와줍니다.
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
				<h2>IoT 기반 성장데이터 관리 플랫폼</h2>
				<ul>
		          <li>간편한 입력방식</li>
		          <li>성장 과정을 담은 육아일기 플랫폼</li>
		          <li>체계적인 성장 데이터 관리</li>
		          <li>SNS를 통한 정보 공유</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>성장비교 솔루션 제공</h2>
				아이의 성장 정도를 추상적인 비교가 아닌 실제 데이터 기반의 수치를 제공함으로써 성장 과정의 체계적인 관리 및 분석이 가능합니다.
			</div>
		</div>
		<div class="imgsContainer">
			<img src="<%=application.getContextPath()%>/resources/res/imgs/11.png" alt="리포트 분석 페이지" id="iphone-two" />
		</div>
	</div>
  </div><!-- 끝: #fullpage -->
  
  <jsp:include page="include/footer.jsp"/>
</body>
</html>