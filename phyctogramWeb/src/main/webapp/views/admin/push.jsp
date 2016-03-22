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
            <a href="<%=application.getContextPath()%>/views/admin/noticeList.jsp">공지사항</a>
          </li>
          <li>
            <a href="<%=application.getContextPath()%>/views/admin/push.jsp">푸쉬보내기</a>
          </li>
          <li>
            <a href="<%=application.getContextPath()%>/views/admin/qaList.jsp">문의하기</a>
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
          <h3 class="m-b-none">푸쉬보내기</h3>
          <p class="text-muted">push</p>
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
                     <label class="col-sm-3 control-label">제목</label>
                     <div class="col-sm-9">
                       <input type="text" class="form-control" data-type="email" data-required="true" placeholder="제목을 입력하세요">    
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">내용</label>
                     <div class="col-sm-9">
                       <div id="editor">
							<textarea rows="20" cols="100" class="form-control" style="overflow:auto;min-height:300px;" placeholder="내용을 입력하세요"></textarea>
						</div>
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">대상</label>
                     <div class="col-sm-9">
                       <select>
                       	<option>전체</option>
                       	<option>유저1</option>
                       	<option>유저2</option>
                       </select>                         
                     </div>
                   </div>
                 </div>
               </section>
           	   <div class="col-sm-6"><button class="btn btn-default btn-block btn-lg m-b">취소</button></div>
           	   <div class="col-sm-6"><button class="btn btn-danger btn-block btn-lg m-b">전송</button></div>
             </form>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
  <!-- footer -->
  <footer id="footer">
    <div class="bg-dark dker wrapper">
      <div class="container text-center m-t-lg">
        <div class="row m-t-xl m-b-xl">
          <div class="col-sm-4"><i class="fa fa-map-marker fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">주소</h5>
            <p class="text-sm">서울특별시 강남구 학동로56길 47<br>4층 (주)나리지식앤컴퍼니</p>
          </div>
          <div class="col-sm-4"><i class="fa fa-envelope-o fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">메일</h5>
            <p class="text-sm"><a href="mailto:hey@example.com">seek-knowledge@knowledge-seek.com</a></p>
          </div>
          <div class="col-sm-4"><i class="fa fa-phone fa-3x icon-muted"></i>
            <h5 class="text-uc m-b m-t-lg">유선</h5>
            <p class="text-sm">070-8624-4532</p>
          </div>
        </div>
      </div>
    </div>
  </footer>
  <!-- / footer --> 
  
    
  <script src="<%=application.getContextPath()%>/resources/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=application.getContextPath()%>/resources/js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=application.getContextPath()%>/resources/js/app.js"></script>

</body>
</html>