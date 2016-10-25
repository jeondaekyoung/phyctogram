<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  <jsp:include page="../include/head-contact.jsp"/>
</head>
<body>
  <jsp:include page="../include/nav.jsp"/>
  
  <div id="fullpage">    
	<div class="section" id="section0">
		<div class="row contact">
			<div class="col-7">
				<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=s96duQ89XbZlmLcPnKIc"></script>
		          <div id="map" class="box-map" style="width:100%;height:450px;"></div>
		          <script type="text/javascript">
		            var mapOptions = {
		              center: new naver.maps.LatLng(37.4046237, 127.1058193),
		              zoom: 14,
		              scaleControl: false,
		              logoControl: false,
		              mapDataControl: false,
		              zoomControl: true
		            };
		            var marker = new naver.maps.Marker({
		              position: new naver.maps.LatLng(37.4046237, 127.1058193),
		              map: map
		            });
		            var map = new naver.maps.Map('map', mapOptions);
		            /* 이미지 마커 추가 예정 */
		          </script>
		        </div>
		        
				<div class="col-5">
		            <ul class="contact-info">
	                <li>Address<span>Suite 310, Startup Campus Building 3, 20, Pangyo-ro 289beon-gil, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea</span></li>
	                <li>Call<span>+82 70-8624-4536</span></li>
	                <li>E-Mail<span>seek-knowledge@knowledge-seek.com</span></li>
	            </ul>
	        </div>
		</div>
	</div>
  </div>
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>