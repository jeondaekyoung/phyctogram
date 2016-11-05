<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8" />
    <title>메일 작성</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <jsp:include page="include/head.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
 
    <!-- 플러그인 참조 -->
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
    <script src="http://cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
    <style type="text/css">
      @media (min-width: 980px) {
        body {
          padding-top: 60px
          padding-bottom: 40px
        }
      }
    </style>
    
    <script type="text/javascript">
      $(function() {
        // submit 될 때, 유효성 검사 기능 수행
        $("#join_form").validate({
          // 유효성 검사 규칙
          rules : {
            sender : "required",
            sender_pw : "required",
            receiver : "required",
            subject : "required",
            content : "required"
          },
          // 사용자에게 보여질 메시지
          messages : {
            sender : "보내는 분의 메일 주소를 입력하세요.",
            sender_pw : "비밀번호를 입력하세요.",
            receiver : "받는 분의 메일 주소를 입력하세요.",
            subject : "제목을 입력하세요.",
            content : "내용을 입력하세요."
          }
        })
      })
    </script>
     <script type="text/javascript">
  $(document).ready(function(){
	  
	  ${script}
  });
  </script>
  </head>
  <body>
    <!-- header -->
 	<%-- <jsp:include page="include/header.jsp" flush="false"/> --%>
  <!-- / header -->
 
    <!-- 내용영역 시작 -->
    <div class="container">
 
      <form id="join_form" class="form-horizontal" method="post" action="">
      <input type="hidden" value="${QaWeb.qa_Web_seq }" name="qa_Web_seq">
        <fieldset>
          <legend>
            <strong>메일 작성</strong>
          </legend>
 
          <div class="form-group">
            <label class="control-label col-sm-2" for="sender">보내는주소 <font color='red'>*</font></label>
            <div class="col-sm-10">
              <input type="text" name="sender" id="sender" class="form-control" placeholder="보내는 분의 이메일 주소를 입력하세요."/>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-2" for="sender">비밀번호 <font color='red'>*</font></label>
            <div class="col-sm-10">
              <input type="password" name="sender_pw" id="sender_pw" class="form-control" placeholder="보내는 분의 이메일의 비밀번호를 입력하세요."/>
            </div>
          </div>
 
          <div class="form-group">
            <label class="control-label col-sm-2" for="receiver">받는주소 <font color='red'>*</font></label>
            <div class="col-sm-10">
              <input type="text" name="receiver" id="receiver" class="form-control" placeholder="받는 분의 이메일 주소를 입력하세요." />
            </div>
          </div>
 
          <div class="form-group">
            <label class="control-label col-sm-2" for="subject">메일 제목<font color='red'>*</font></label>
            <div class="col-sm-10">
              <input type="text" name="subject" id="subject" class="form-control" placeholder="이메일의 제목을 입력하세요." />
            </div>
          </div>
 
          <div class="form-group">
            <label class="control-label col-sm-2" for="content">내용입력</label>
            <div class="col-sm-10">
              <textarea name="content" id="content" class="ckeditor"></textarea>
            </div>
          </div>
 
          <!-- 버튼 영역 -->
          <div class="form-actions text-right">
            <input type="submit" class="btn btn-primary" value="메일보내기" />
            <input type="reset" class="btn" value="다시작성" />
          </div>
 
        </fieldset>
      </form>
 	<fieldset>
          <legend>
            <strong>문의 내용</strong>
          </legend>
          
    <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
           
                <ul class="list-group list-group-lg">
                  
                  <li class="list-group-item min-h"><!-- 작성자 정보 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">
                        <span class="text-muted text-center-xs">문의날짜: <fmt:formatDate value="${QaWeb.writng_de }" type="both" pattern="yyyy/MM/dd"/></span>
                        	<p class="text-muted text-center-xs">이름 : ${QaWeb.name }</p>
                        	<p class="text-muted text-center-xs">이메일 : ${QaWeb.email }</p>
                        	<p class="text-muted text-center-xs">연락처 : ${QaWeb.tel }</p>
                        	<p class="text-muted text-center-xs">답변상태 : ${QaWeb.state }</p>
                        </div>
                       </div>
                    </div>
                  </li><!-- 작성자 정보 -->
                  
                  <li class="list-group-item min-h"><!-- 내용영역 -->
                    <div class="media">
                      <div class="media-body">
                        <div class="l-h-2x">내용</div>
                        ${QaWeb.contents }
                       </div>
                    </div>
                  </li><!-- 내용영역 -->
                </ul>
                <!-- 답변/목록/삭제 -->
        
          </div>
        </div>        
      </div>
 	</fieldset>
    </div>
       
    <!--// 내용영역 끝 -->
     <!-- footer -->
 	<jsp:include page="include/footer.jsp" flush="false"/>
 <!-- / footer --> 
  </body>
</html>