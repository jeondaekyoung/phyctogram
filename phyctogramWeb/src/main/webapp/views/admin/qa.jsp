<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>문의사항</title>
  <jsp:include page="include/head.jsp"/>
</head>

<body>
  	
  <!-- header -->
 	<jsp:include page="include/header.jsp" flush="false"/>
  <!-- / header -->
  
  <section id="content">
    <div class="bg-dark lt">
      <div class="container">
        <div class="m-b-lg m-t-lg">
          <h3 class="m-b-none">문의하기</h3>
          <p class="text-muted">customer</p>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
          	<form class="form-horizontal" data-validate="parsley">
               <section class="panel panel-default">
                 <header class="panel-heading"></header>
                 <div class="panel-body">
                   <div class="form-group">
                     <label class="col-sm-3 control-label">연락받으실 이메일 주소</label>
                     <div class="col-sm-9">
                       <input type="text" class="form-control" data-type="email" data-required="true" placeholder="example@phyctogram.com">    
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">연락받으실 전화번호</label>
                     <div class="col-sm-9">
                       <input type="text" data-type="phone" class="form-control" placeholder="010-1234-5678">
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">문의하실 내용</label>
                     <div class="col-sm-9">
                       <div id="editor">
							<textarea rows="20" cols="100" class="form-control" style="overflow:auto;min-height:300px;" placeholder="문의내용"></textarea>
						</div>
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">파일 첨부</label>
                     <div class="col-sm-9">
                       <input type="file">                         
                     </div>
                   </div>
                 </div>
                 <footer class="panel-footer bg-light lter">
                   <p>1. 수집하는 개인정보의 항목 및 수집방법<br>
						1) 픽토그램은 이용자의 원활한 고객상담 및 불만처리 등을 위하여 아래와 같은 개인정보를 수집하고 있습니다.<br>
						- 필수정보: 이메일, 휴대폰번호<br><br>
						2. 개인정보의 수집 및 이용 목적<br>
						1) 픽토그램은 수집된 개인정보를 고객문의 및 상담요청에 대하여 회신을 하거나, 상담을 위한 서비스 이용기록 조회 목적으로 활용합니다.<br><br>
						3. 개인정보의 보유 및 이용기간<br>
						이용자의 개인정보는 개인정보의 수집 및 이용 목적이 달성되면 관련 법령 또는 회사 내부 방침에 의해 보존할 필요가 있는 경우를 제외하고는 지체 없이 파기됩니다.<br><br>
						더 자세한 내용에 대해서는 픽토그램 개인정보취급방침을 참고하시기 바랍니다.</p>
					<div class="text-right"><label for="agree"><input type="checkbox" id="agree">위 내용에 동의합니다.</label></div>
                 </footer>
               </section>
           	   <button class="btn btn-danger btn-block btn-lg m-b-md">문의하기</button>
             </form>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
  <!-- footer -->
	<jsp:include page="include/footer.jsp" flush="false"/>
  <!-- / footer -->

</body>
</html>