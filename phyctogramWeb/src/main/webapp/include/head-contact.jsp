<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
 /* Style for our header texts
* --------------------------------------- */	
       p{
           width: 100%;
       }
       .linkProd {
           padding: 15px;
           background: rgba(0,0,0,0.9);
           border-radius: 50px;
           display: inline-block;
           width: 12em;
           margin-top: 1em;
           color: #fff;
       }
       .linkProd:hover {
           background: rgba(0,0,0,0.5)
       }        
       #section0 {
          background: #000; 
       }
</style>
 
<script type="text/javascript">
$(document).ready(function() {
	$('#fullpage').fullpage({
		verticalCentered: true,
              'afterLoad': function(anchorLink, index){
                if(index == 1){                        
                  $('#footer').css({'color':'#fff'});
                }
              }
	});
});
</script>