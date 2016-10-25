<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>	
  <jsp:include page="include/head.jsp"/>
  
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
  <jsp:include page="include/nav.jsp"/>
  
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
                          <h5>㈜나리지식앤컴퍼니 설립</h5>
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
                          <h5>벤처기업 인증 / 연구개발전담부서 설립</h5>
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
                          <h5>(특허) 지식정보 유료 거래 시스템</h5>
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
                          <h5>(특허) 시스템, 성장관리 시스템</h5>
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
                          <h5>중소기업진흥공단 청년창업사관학교 5기 졸업</h5>
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
                          <h5>판교 테크노밸리 스타트업 캠퍼스 內 입주</h5>
                        </div>
                      </div>
                    </div>
                </article>
            </div>
        </div>
    </div>
	<!-- //end : #section0 -->
    
  </div>
  
  <jsp:include page="include/footer.jsp"/>
</body>
</html>