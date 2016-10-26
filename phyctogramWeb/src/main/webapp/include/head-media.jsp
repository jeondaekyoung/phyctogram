<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    /* Style for our header texts
    * --------------------------------------- */
    h3{
        font-size: 1.5em;
        margin: 0.5rem 0 0;
    }
    h4{
        margin: 0.5rem 0 0;
    }
    .intro p{
        font-size: 0.875rem;
        line-height: 1.5;
        color: #444;
        text-shadow: none;
        width: 100%;
    }
    /* Centered texts in each section
    * --------------------------------------- */
    .section{
        text-align:center;
    }
    #section0{
        padding: 80px 0;
    }
</style>

<script type="text/javascript">
    $(document).ready(function() {
        $('#fullpage').fullpage({
            sectionsColor: ['#eeeeee'],
            autoScrolling: false,
			fitToSection: false
        });
    });
    $(window).scroll(function(){
        if($(this).scrollTop()>=80){
            $('#header').css({'background-color': '#000'});
            $('#footer').css({'display':'block', 'background-color': '#000', 'color':'#fff'});
        } else {
            $('#header').css({'background-color': 'transparent'});
            $('#footer').css({'display': 'none'});
        }
    });
</script>