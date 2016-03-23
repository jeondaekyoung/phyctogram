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
                       <select id="pushSelectBox">
                       	<option value="0">전체</option>
                       </select>                         
                     </div>
                   </div>
                 </div>
               </section>
           	   <div class="col-sm-6"><button class="btn btn-default btn-block btn-lg m-b">취소</button></div>
           	   <div class="col-sm-6"><button class="btn btn-danger btn-block btn-lg m-b" id="send">전송</button></div>
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
  
  <script type="text/javascript">
		var rootPath = window.location.protocol + '//' + window.location.host;
		var pageCnt = 0;
		var pageEnd = false;
		var searchState = "answer";
		var answerState;
		
		$(document).ready(function() {
			//목록읽어오기
			selectList();
			
		      //전송
			  $("#sned").click(function(){
				  alert("전송");
			  });
		});
		
		//답변 저장
		function saveAnswer(obj){
			if($(obj).prev().val().length<=0){
				alert("답변 내용을 입력해주세요.");
			}else{
				answerState="answerState"+$(obj).prev().attr('id');
				saveAnswerAjax($(obj).prev().attr('id'), $(obj).prev().val());
			}
		}
		
		//답변 저장하기
		var saveAnswerAjax = function(qa_seq, answer) {
			$.ajax({
				url : rootPath + "/qa/modify.do",
				type : "POST",
				data : {
					qa_seq : qa_seq,
					answer : answer
				},
				success : answerSuccess,
				error : errorCallback
			})
		}
		
		//답변 저장하기
		var answerSuccess = function(resultData) {
			if(resultData=="success"){
				$("#"+answerState).removeClass();
				$("#"+answerState).addClass("fa fa-check text-success text");
				$("#"+answerState).text("답변완료");
				alert("저장 되었습니다.");
			}else{
				alert("저장에 실패 하였습니다. 다시 시도해 주세요.");
			}
		};

		//selectBox 리스트 읽어오기
		var selectList = function() {
			$.ajax({
				url : rootPath + "/push/selectBoxList.do",
				type : "POST",
				success : listSuccess,
				error : errorCallback
			})
		}

		//selectBox 리스트 조회
		var listSuccess = function(resultData) {
			$.each(resultData, function(index, item) {
				var sbmTr = sbmHtmlTemplate.makesbmTr(index, item);
				$("#pushSelectBox").append(sbmTr);
			});
		};
		
		//테이블 생성 html
		var sbmHtmlTemplate = {
			  makesbmTr : function(index, item){
				  var sbmTr = '<option value="'+(index+1)+'">'+item+'</option>';	
	          return sbmTr;
			  }
		  }

		//Ajax 에러 콜백함수
		var errorCallback = function() {
			alert("수행 중 오류가 발생했습니다");
		};
		
		//시간포맷
		  var formatDate = function(dateObj){
		  	var curr_year = dateObj.getFullYear();
		  	var curr_month = dateObj.getMonth() + 1;
		  	var curr_date = dateObj.getDate();
		  	var curr_hr = dateObj.getHours();
		  	var curr_min = dateObj.getMinutes();
		  	
		  	if(curr_month.toString().length == 1) {
		  		curr_month = '0' + curr_month;
		  	}
		  	if(curr_date.toString().length == 1){
		  		curr_date = '0' + curr_date;
		  	}
		  	if(curr_hr.toString().length == 1){
		  		curr_hr = '0' + curr_hr;
		  	}
		  	if(curr_min.toString().length == 1){
		  		curr_min = '0' + curr_min;
		  	}
		  	
		  	//return curr_year + "-"+ curr_month + "-" + curr_date + "  " + curr_hr + ":" + curr_min;
		  	return curr_year + "/"+ curr_month + "/" + curr_date;
		  }
	</script>

</body>
</html>