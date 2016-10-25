<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="/include/head.jsp"/>
  <jsp:include page="/include/head-index.jsp"/>
   <script src="<%=application.getContextPath()%>/resources/res/js/typewriter.js"></script>  
  <script src="<%=application.getContextPath()%>/resources/res/js/typewriter-bundle.js"></script>
  <script src="<%=application.getContextPath()%>/resources/res/js/hangul.min.js"></script>
  <script>
   /* 가장 진보한 유아용 성장 측정, 관리 기기 */
	/*     var typewriter = require('#typewriter');

	    var twSpan = document.getElementById('typewriter');
	    var tw = typewriter(twSpan).withAccuracy(100)
	        .withMinimumSpeed(5)
	        .withMaximumSpeed(17)
	        .build();

	    var msg = 'asdasd';
	    msg = Hangul.disassemble(msg);
	    msg = Hangul.typewrite(msg);

	    tw.wait(1000)
	        .type(msg)
	        .wait(500)
	        .type('', function () {
	        document.getElementById("caret").style.display = 'none';
	    });
 */	    
 $(function () {
     var typewriter = new Typewriter($("#typewriter"));
     typewriter.typeText("가장 진보한 유아용 성장 측정, 관리 기기");
 });
	    
</script>
</head>

<body>
  <jsp:include page="include/nav.jsp"/>
  
<div id="fullpage">
  <div class="section " id="section00">
    <video autoplay loop muted id="myVideo">
      <source src="<%=application.getContextPath()%>/resources/res/imgs/video-phyctogram.mp4" type="video/mp4">
    </video>
    <div class="modal">
      <div class="layer ">
		<h1 id="typewriter"></h1>
        <p>픽토그램은 자녀의 키와 체중을 쉽고 정확하게 측정하고, 자동으로 분석하여 성장 솔루션을 제공합니다</p> 
      </div>
    </div> 
  </div> 
	<div class="section row" id="section0">
    <div class="headlineProd animated">
      <h3>단 한 번의 터치로 손쉬운 측정</h3>
      <p>간편한 조작 방법과 간결한 디자인으로 사용자에게 편리함과 심미적 가치를 제공합니다</p>
      <img src="<%=application.getContextPath()%>/resources/res/imgs/main_2.png" alt="phyctogram" class="animated fadeInUpBig col-16">
    </div>
	</div>
	<div class="section" id="section1">
		<div class="wrap row">
			<div class="imgsContainer col-7">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/0.png" alt="메인 이미지" id="iphone2" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/2.png" alt="성장카드 페이지" id="iphone3" />
			</div>

			<div class="box box-1 col-5 animated">
				<h2>어플리케이션을 통한<br>스마트 성장관리 서비스</h2>
				측정된 키·체중 데이터는 Wi-Fi를 통해 자동으로 어플리케이션에 연동되어 아이의 성장을 체계적으로 관리할 수 있게 합니다
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section2">
		<div class="wrap">
			<div class="imgsContainer">
				<img src="<%=application.getContextPath()%>/resources/res/imgs/4.png" width="322" alt="리포트" id="iphone-red" />
				<img src="<%=application.getContextPath()%>/resources/res/imgs/3.png" width="322" alt="체크리스트" id="iphone-blue" />
			</div>

			<div class="box box-2 animated">
				<h2>성장비교 솔루션 및 예측 수치 제공</h2>
				<ul>
		          <li>아이의 성장 정도를 추상적인 비교가 아닌 실제 데이터를 기반으로 제공하여, 성장 과정의 체계적인 관리·분석이 가능합니다</li>
		        </ul>
			</div>
		</div>
	</div>

	<div class="section moveDown" id="section3">
		<div class="wrap">
			<div class="box box-3 animated">
				<h2>성장 관련 콘텐츠 제공</h2>
				성장 과정을 포함한 육아일기 및 관련 콘텐츠 작성이 가능합니다
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