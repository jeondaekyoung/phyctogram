<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  
  <style>
	 p {width: 100%;}
     .linkProd {
         padding: 15px;
         background: rgba(0,0,0,0.9);
         border-radius: 50px;
         display: inline-block;
         width: 12em;
         margin-top: 1em;
         color: #fff;
     }
     .linkProd:hover {background: rgba(0,0,0,0.5);}
     #section0 {background: #000;}
  </style>
  
  <script type="text/javascript">
	$(document).ready(function() {
		$('#fullpage').fullpage({
			verticalCentered: true
		});
	});
  </script>
</head>
<body>
  <jsp:include page="../include/nav.jsp"/>
  
  <div id="fullpage">    
	<div class="section row" id="section0">
		<div class="col-6">
            <div id="map"></div>
            <script type="text/javascript">                
                function initMap(){
                  var myLatLng = { lat: 37.4046237, lng: 127.1058193 };
                  var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 12,
                    center: myLatLng
                  });
                  
                  var marker = new google.maps.Marker({
               	    position: myLatLng,
               	    map: map,
               	    title: 'Hello World!'
               	  });
                }
            </script>
            <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDKYcz4eMxSjG1Wio1cW_9C1m2Zjg0wCnA&signed_in=true&callback=initMap"></script>
        </div>
        
		<div class="col-6">
            <ul>
                <li>地址<span>Suite 310, Startup Campus Building 3, 20, Pangyo-ro 289beon-gil, Bundang-gu, Seongnam-si, Gyeonggi-do, Korea</span></li>
                <li>电话号码<span>+82 70-8624-4536</span></li>
                <li>电子邮件<span>seek-knowledge@knowledge-seek.com</span></li>
            </ul>
        </div>
	</div>
  </div>
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>