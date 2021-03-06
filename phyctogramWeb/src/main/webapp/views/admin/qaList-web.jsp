<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>문의(Web)</title>
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
			<h3 class="m-b-none">고객센터 (Web)</h3>
			<small class="text-muted">Customer (Web)</small>
        </div>
      </div>
    </div>
    <div>
      <div class="container m-t-xl">
        <div class="row">
          <div class="col-sm-12">
            <section class="list-group alt">
                <ul class="list-group list-group-lg min-h" id="myul">
                
                 <!--  <li class="list-group-item">
                    <span class="pull-right" >
                      <a href="#"><i class="fa fa-check text-success text" id="answerState'+item.qa_seq+'">답변완료</i></a>
                      <a href="#"><i class="fa fa-times text-danger text" id="answerState'+item.qa_seq+'">답변대기</i></a>
                    </span>
                    <div class="media">
                      <div class="media-body m-b">
                        <div><a href="qaView-web.jsp">장문 메시지를 바로 볼 수 있는 텍스트뷰어, 그릅콜 중 친구 초대 기능 등 2.0.9 업데이트 안내</a></div>
                        <small class="text-muted">2016/01/12</small>
                      </div>
                    </div>
                  </li> -->
                  
                </ul>
                <button class="btn btn-danger btn-block btn-lg m-b-sm" id="moreBtn">더보기</button>
            </section>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
 <!-- footer -->
 	<jsp:include page="include/footer.jsp" flush="false"/>
 <!-- / footer --> 
  
    
  
  <script type="text/javascript">
  
  var pageCnt = 0;
  var pageEnd = false;
  
  $(document).ready(function(){
	  //목록읽어오기
	  qaWebList();
	  
	  //더보기
	  $("#moreBtn").click(function(){
		  //console.log("더보기 클릭 " + pageCnt);
		  if(!pageEnd){
			  qaWebList();
		  } else {
			  alert("마지막입니다");
		  }
	  });
  });
  
  //목록 읽어오기
  var qaWebList = function(){
	  $.ajax({
		  url: rootPath + "/QaWeb/list.do"
		  ,type: "POST"
		  ,data: {
			  pageCnt: pageCnt
		  }
		  ,success: listSuccess
		  ,error: errorCallback
	  })
  }
  
  
  //공지사항 리스트 조회
  var listSuccess = function(resultData){
	  console.log("결과 - " + resultData.length);
	  $.each(resultData, function(index, item){
		  var sysdate = new Date(item.writng_de);
		  console.log(index + " - " + item.title + ", " + item.writng_de + ", " + formatDate(sysdate));
		  
		  var sbmTr = sbmHtmlTemplate.makesbmTr(index, item);
		  $("#myul").append(sbmTr);
	  });
	  pageCnt++;
	  
	  if(resultData.length < 10){
		  pageEnd = true;
	  }
  };
  var sbmHtmlTemplate = {
		  makesbmTr : function(index, item){
			  var sysdate = new Date(item.writng_de);
			  var sbmTr = "<li class='list-group-item'><span class='pull-right'>"
			  +"<a href='javascript:answer("+item.qa_Web_seq+")'  class='pull-left m-r-sm'><i class='fa fa-check text-success text'>수동 답변</i></a>"
			  +"<a href='javascript:answer_mail("+item.qa_Web_seq+")' class='pull-left m-r-sm'><i class='fa fa-check text-success text'>메일로 답변</i></a>";
			  			//+ "<i onclick='modify(" + item.qa_Web_seq + ")' class='fa fa-pencil icon-muted fa-fw m-r-xs'></i> &nbsp;"
 					  		if(item.state=='답변대기'){
 					  			sbmTr=sbmTr+'<a href="#"><i class="fa fa-times text-danger text" >답변대기</i></a>';
 					  			
 					  		}
 					  		else{
 					  			sbmTr=sbmTr+('<a href="#"><i class="fa fa-check text-success text">답변완료</i></a>');
 					  			
 					  		}
 					  
 			   sbmTr=sbmTr.concat( "<i onclick='erase(" + item.qa_Web_seq + ")' class='fa fa-times icon-muted fa-fw'></i></span>"
			  			+ "<div class='media'><div class='media-body m-b'><div onclick='view(" + item.qa_Web_seq + ")'>"
			  			+"연락처:"+item.tel +"<br/>이메일:"+item.email + "<br>내용:"+item.contents +"</div></div><div>"
			  			+ "<small class='text-muted'>" + formatDate(sysdate) + "</small></li>");

          return sbmTr;
		  }
  }
  //수동 manual_answer.do 
  //수정하기
  var modify = function(data){
	  console.log("수정하기 - " + data);
	  var f = document.createElement('form');
	  var objs = document.createElement('input');
	  objs.setAttribute('type','hidden');
	  objs.setAttribute('name', 'notice_seq');
	  objs.setAttribute('value', data);
	  f.appendChild(objs);
	  f.setAttribute('action', rootPath+"/QaWeb/modify.do");
	  f.setAttribute('method','post');
	  document.body.appendChild(f);
	  f.submit();
  }
  
  //삭제하기
  var erase = function(data){
	  console.log("삭제하기 - " + data);
	  var f = document.createElement('form');
	  var objs = document.createElement('input');
	  objs.setAttribute('type','hidden');
	  objs.setAttribute('name', 'qa_Web_seq');
	  objs.setAttribute('value', data);
	  f.appendChild(objs);
	  f.setAttribute('action', rootPath+"/QaWeb/erase.do");
	  f.setAttribute('method','post');
	  document.body.appendChild(f);
	  f.submit();
  }
  
  //보기
  var view = function(data){
	  //console.log("보기 - " + data);
	  window.location.href = rootPath + "/QaWeb/view.do?qa_Web_seq=" + data;
  }
  
  //메일로 답변하기
  var answer_mail = function(data){
	  //console.log("보기 - " + data);
	  window.location.href = rootPath + "/QaWeb/answerForm.do?qa_Web_seq=" + data;
  }
//수동 답변하기
  var answer = function(data){
	  //console.log("보기 - " + data);
	  window.location.href = rootPath + "/QaWeb/manual_answer.do?qa_Web_seq=" + data;
	  
  }
  
  
  //Ajax 에러 콜백함수
  var errorCallback = function(){
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