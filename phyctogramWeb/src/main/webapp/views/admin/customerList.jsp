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
            <a href="<%=application.getContextPath()%>/views/admin/customer.jsp">문의하기</a>
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
          <h3 class="m-b-none">고객센터</h3>
          <small class="text-muted">Customer</small>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
          	<div class="panel-group m-b min-h" id="accordion2">
             	<div class="panel panel-default">
	                <header class="panel-heading">
		              <div class="row text-sm wrapper">
		                <div class="col-sm-3">
		                  <div class="input-group">
		                    <input type="checkbox"> 답변 대기만 보기
		                    <input type="checkbox"> 전체
		                    <span class="input-group-btn">
		                      <button class="btn btn-sm btn-danger" type="button">검색</button>
		                    </span>
		                  </div>
		                </div>
		               </div>
	                 </header>
	             </div>
             	 <div class="panel panel-default">
                  <div class="panel-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                      <span class="col-sm-2">2016/02/16</span>
                      <span class="col-sm-2">글쓴이</span>
                      <span class="col-sm-6">제품 구입 관련하여 문의하려고 합니다.</span>
                      <span class="col-sm-2">
						<a href="#" class="active" data-toggle="class"><i class="fa fa-check text-success text-active">답변완료</i><i class="fa fa-times text-danger text">답변대기</i></a>
					  </span>
                    </a>
                  </div>
                  <div id="collapseOne" class="panel-collapse collapse">
                    <div class="panel-body text-sm">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                    <div class="panel-body text-sm">
                      <h5>답변</h5>
                      <div>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
                      <div>
                      	<textarea class="col-sm-11 min-h-xs"></textarea>
                      	<button class="btn btn-sm btn-danger col-sm-1" type="button">답변</button>
                      	<button class="btn btn-sm col-sm-1" type="button">수정</button>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="panel panel-default">
                  <div class="panel-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                      <span class="col-sm-2">2016/02/16</span>
                      <span class="col-sm-2">글쓴이</span>
                      <span class="col-sm-6">제품 구입 관련하여 문의하려고 합니다요.</span>
                      <span class="col-sm-2">
						<a href="#" class="active" data-toggle="class"><i class="fa fa-check text-success text">답변완료</i><i class="fa fa-times text-danger text-active">답변대기</i></a>
					  </span>
                    </a>
                  </div>
                  <div id="collapseTwo" class="panel-collapse collapse">
                    <div class="panel-body text-sm">
                      <div>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</div>
                    </div>
                    <div class="panel-body text-sm">
                      <h5>답변</h5>
                      <div>
                      	<textarea class="col-sm-11 min-h-xs"></textarea>
                      	<button class="btn btn-sm btn-danger col-sm-1" type="button">답변</button>
                      	<button class="btn btn-sm col-sm-1" type="button">수정</button>
                      </div>
                    </div>
                  </div>
                </div>
                <!-- <div class="panel panel-default">
                  <div class="panel-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                      Collapsible Group Item #3
                    </a>
                  </div>
                  <div id="collapseThree" class="panel-collapse collapse">
                    <div class="panel-body text-sm">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div> -->
              </div>
          
              <footer class="panel-footer">
                <div class="row">
                  <!-- <div class="col-sm-4 text-right text-center-xs">                
                    <ul class="pagination pagination-sm m-t-none m-b-none">
                      <li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
                      <li><a href="#">1</a></li>
                      <li><a href="#">2</a></li>
                      <li><a href="#">3</a></li>
                      <li><a href="#">4</a></li>
                      <li><a href="#">5</a></li>
                      <li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                  </div> -->
                </div>
              </footer>
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