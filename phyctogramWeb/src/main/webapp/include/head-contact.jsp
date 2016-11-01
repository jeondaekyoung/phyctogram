<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
   #section0 {
      background: #776096; 
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