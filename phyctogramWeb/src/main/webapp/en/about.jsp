<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>	
  <jsp:include page="../include/head.jsp"/>
  
  <style>
	h1 {
		font-size: 5em;
		font-family: arial,helvetica;
		color: #fff;
		margin:0;
		padding:0;
	}
	.section {text-align:center;}	
	.section {background-size: cover;}
	#section0{
		background-image: url(<c:url value='/resources/res/imgs/bg_main_04.jpg'/>);
		padding: 10% 0 0 0;
	}
	#section1{
		background: #eee;
		padding: 6% 0 0 0;
	}
  </style>
  
 <script type="text/javascript">
	$(document).ready(function() {
		$('#fullpage').fullpage({
			verticalCentered: false,
			'navigation': true,
			'navigationPosition': 'right',
			scrollOverflow: true,

			'afterLoad': function(anchorLink, index){
				if(index == 2){
                       $('#header').css({'background-color': '#000'});
                       $('#footer').css({'background-color': '#000', 'color':'#fff'});
				}
                if(index == 1){                        
                    $('#header').css({'background-color': 'transparent'});
                    $('#footer').css({'color': 'transparent'});
                }
			}
		});
	});
</script>
<body>
  <jsp:include page="../include/nav.jsp"/>
  
  <div id="fullpage">
  
	<!-- //start : #section0 -->
	<div class="section" id="section0">
        <h1>About Us</h1>
        
        <div class="row">        
            <div class="col-12 timeline">
                <article class="timeline-item">
                    <div class="timeline-caption">
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow left"></span>
                          <span class="timeline-icon"><i class="time-icon bg-primary"></i></span>
                          <span class="timeline-date">2013.09.04</span>
                          <h5>Establish Knowledge-Seek Co. Ltd.</h5>
                        </div>       
                      </div>
                    </div>
                </article>
                <article class="timeline-item alt">
                    <div class="timeline-caption">                
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow right"></span>
                          <span class="timeline-icon"><i class="time-icon bg-white"></i></span>
                          <span class="timeline-date">2013</span>
                          <h5>Venture Business Certification / R&D Complete Charge Department</h5>
                        </div>
                      </div>
                    </div>
                </article>
                <article class="timeline-item">
                    <div class="timeline-caption">
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow left"></span>
                          <span class="timeline-icon"><i class="time-icon bg-primary"></i></span>
                          <span class="timeline-date">2014</span>
                          <h5>(Patent) Knowledge Information Paid Trading System</h5>
                        </div>       
                      </div>
                    </div>
                </article>
                <article class="timeline-item alt">
                    <div class="timeline-caption">                
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow right"></span>
                          <span class="timeline-icon"><i class="time-icon bg-white"></i></span>
                          <span class="timeline-date">2015</span>
                          <h5>(Patent) Growth Management System</h5>
                        </div>
                      </div>
                    </div>
                </article>
                <article class="timeline-item">
                    <div class="timeline-caption">
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow left"></span>
                          <span class="timeline-icon"><i class="time-icon bg-primary"></i></span>
                          <span class="timeline-date">2016</span>
                          <h5>5th Graduation SBC Youth Startup School</h5>
                        </div>       
                      </div>
                    </div>
                </article>
                <article class="timeline-item alt">
                    <div class="timeline-caption">                
                      <div class="panel panel-default">
                        <div class="panel-body">
                          <span class="arrow right"></span>
                          <span class="timeline-icon"><i class="time-icon bg-white"></i></span>
                          <span class="timeline-date">2016</span>
                          <h5>Moved in Pangyo Techno-Valley Startup Campus</h5>
                        </div>
                      </div>
                    </div>
                </article>
            </div>
        </div>
    </div>
	<!-- //end : #section0 -->
    
  </div>
  
  <jsp:include page="../include/footer.jsp"/>
</body>
</html>