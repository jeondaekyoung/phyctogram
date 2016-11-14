<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function() {
	$('#fullpage').fullpage({
		'verticalCentered': false,
		'css3': true,
		'navigation': true,
		'navigationPosition': 'right',

		'afterLoad': function(anchorLink, index){
			 if(index == 1){
	           $('.modal').css({'display': 'block'});
	           $('#header').css({'background-color': 'transparent'});
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	         }
	         if(index == 2){
	           $('.headlineProd').addClass('fadeInUp');
	           $('.modal').css({'display': 'none'});
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
	         }
	         if(index == 3){
	           $('.section0-0').addClass('fadeInUp');
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
	         }
	         if(index == 4){
	           $('.scale-box').addClass('fadeInRight');
	           $('.scale-img').addClass('fadeInLeft');
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
	         }
			 if(index == 5){
			   $('#iphone3, #iphone2, #iphone4').addClass('active');
	           $('.box-1').addClass('fadeInRight');
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
			 }
	         if(index == 6){
	           $('.box-2').addClass('fadeInUp');
	           $('.img-4').addClass('fadeInUp');
	           $('.img-3').addClass('fadeInUp');
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
	         }
	         if(index == 7){
	           $('.box-3').addClass('fadeInUp');
	           $('.img-last').addClass('fadeInUp');
	           $('#footer').css({'background-color': 'transparent', 'color':'transparent'});
	           $('#header').css({'background-color': '#776096'});
	         }
	         if(index == 8){
	           $('.contact-info>li').addClass('fadeIn');
		       $('#footer').css({'background-color': '#776096', 'color':'#fff'});
		       $('#header').css({'background-color': '#776096'});
             }
		},

		'onLeave': function(index, nextIndex, direction){
			if(index == 2 && direction == 'down'){
		        $('.section0-0').removeClass('fadeInUp');
			}
			else if(index == 2 && direction == 'up'){
		        $('.section0-0').removeClass('fadeInUp');
			}
			if(index == 3 && direction == 'down'){
		        $('.headlineProd').removeClass('fadeInUp');
			}
			else if(index == 3 && direction == 'up'){
		        $('.headlineProd').removeClass('fadeInUp');
			}
			if(index == 4 && direction == 'down'){
				$('.scale-box').removeClass('fadeInRight');
		        $('.scale-img').removeClass('fadeInLeft');
			}
			else if(index == 4 && direction == 'up'){
				$('.scale-box').removeClass('fadeInRight');
		        $('.scale-img').removeClass('fadeInLeft');
			}
			if(index == 5 && direction == 'down'){
				$('#iphone3, #iphone2, #iphone4').removeClass('active');
		        $('.box-1').removeClass('fadeInRight');
			}
			else if(index == 5 && direction == 'up'){
				$('#iphone3, #iphone2, #iphone4').removeClass('active');
		        $('.box-1').removeClass('fadeInRight');
			}
			if (index == 6 && direction == 'down'){
				$('.section').eq(index -1).removeClass('moveDown').addClass('moveUp');
       			$('#header').css({'background-color': '#776096'});
   				$('.box-2').removeClass('fadeInUp');
 	            $('.img-4').removeClass('fadeInUp');
 	            $('.img-3').removeClass('fadeInUp');
			}
			else if(index == 6 && direction == 'up'){
				$('.section').eq(index -1).removeClass('moveUp').addClass('moveDown');
				$('.box-2').removeClass('fadeInUp');
		        $('.img-4').removeClass('fadeInUp');
		        $('.img-3').removeClass('fadeInUp');
			}
			if (index == 7 && direction == 'down'){
				$('.box-3').removeClass('fadeInUp');
		        $('.img-last').removeClass('fadeInUp');
			}
			else if(index == 7 && direction == 'up'){
				$('.box-3').removeClass('fadeInUp');
		        $('.img-last').removeClass('fadeInUp');
			}
		},
		
		'afterRender': function(section){
			//playing the video
			$('video').get(0).play();
		}
	});
});
</script>
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
    top: 180px;
    width: 322px;
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
