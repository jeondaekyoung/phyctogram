<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="include/head.jsp"/>
  <jsp:include page="include/head-contact.jsp"/>
</head>
<body>
  <jsp:include page="include/nav.jsp"/>
  <div id="fullpage">
    
	<div class="section" id="section0">
        <div class="row contact animated fadeInUp">        
            <div class="col-7 m-col-12">
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
            <!-- end: .col-7 -->
            
            <div class="col-5 m-col-12">
                <ul class="contact-info">
                    <li>경기도 성남시 분당구 판교로289번길 20, 3동 310호(삼평동, 스타트업캠퍼스)</li>
                    <li>070-8624-4536</li>
                    <li>seek-knowledge@knowledge-seek.com</li>
                </ul>
            </div>
            <!-- end: .col-5 -->
        </div>
        <!-- end: .contact -->
	</div>
    <!-- end: #section0 -->
  </div>
  
  <jsp:include page="include/footer.jsp"/>
</body>
</html>