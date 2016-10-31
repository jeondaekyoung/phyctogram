<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				
                if(index == 1){                        
                    $('#header').css({'background-color': 'transparent'});
                    $('#footer').css({'color': '#fff'});
                    $('.timeline-item').addClass('fadeInLeft');
                    $('.timeline-item.alt').addClass('fadeInRight');
                }
			}
		});
	});
</script>