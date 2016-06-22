<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>공지사항</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" /> 
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/landing.css" type="text/css" />
  <link rel="stylesheet" href="<%=application.getContextPath()%>/resources/css/app.css" type="text/css" />
  
</head>

<body>
  	
  <!-- header -->
  <header id="header" class="navbar navbar-fixed-top bg-white box-shadow b-b b-light"  data-spy="affix" data-offset-top="1">
    <div class="container">
      <div class="navbar-header">        
        <a href="#" class="navbar-brand"><span class="text-muted">Phyctogram</span></a>
        <button class="btn btn-link visible-xs" type="button" data-toggle="collapse" data-target=".navbar-collapse">
          <i class="fa fa-bars"></i>
        </button>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
          <li class="active">
            <a href="noticeList.jsp">공지사항</a>
          </li>
          <li>
            <a href="customer.jsp">문의하기</a>
          </li>
        </ul>
      </div>
    </div>
  </header>
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
                 <header class="panel-heading">
                 </header>
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
<!-- 
  <footer id="footer">
    <div class="bg-dark dker wrapper">
      <div class="container text-center m-t-lg">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-4"><i class="fa fa-map-marker fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">주소</h5>
            <p class="text-sm">경기도 성남시 분당구 판교로 289번길 20<br> (삼평동, 스타트업 캠퍼스) 3동 310호</p>
          </div>
          <div class="col-sm-4"><i class="fa fa-envelope-o fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">메일</h5>
            <p class="text-sm"><a href="mailto:hey@example.com">seek-knowledge@knowledge-seek.com</a></p>
          </div>
          <div class="col-sm-4"><i class="fa fa-phone fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">유선</h5>
            <p class="text-sm">070-8624-4536</p>
          </div>
        </div>        
      </div>
    </div>
  </footer>
 -->
<jsp:include page="footer.jsp" flush="false"/>
  <!-- / footer --> 
  
    
  <script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=application.getContextPath()%>/resources/js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=application.getContextPath()%>/resources/js/app.js"></script>

</body>
</html>