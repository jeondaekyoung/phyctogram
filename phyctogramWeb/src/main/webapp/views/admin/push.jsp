<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>공지사항</title>
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
                       <input id="pushTitle" type="text" class="form-control" data-type="email" data-required="true" placeholder="제목을 입력하세요">    
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">내용</label>
                     <div class="col-sm-9">
                       <div id="editor">
							<textarea id="pushContents" rows="20" cols="100" class="form-control" style="overflow:auto;min-height:300px;" placeholder="내용을 입력하세요"></textarea>
						</div>
                     </div>
                   </div>
                   <div class="line line-dashed line-lg pull-in"></div>
                   <div class="form-group">
                     <label class="col-sm-3 control-label">대상</label>
                     <div class="col-sm-9">
                       <select id="pushSelectBox" multiple="multiple" style="height:50px;width:100px;">
                       	<option value="0">전체</option>
                       </select>                         
                     </div>
                   </div>
                 </div>
               </section>
	           	 <div class="col-sm-6"><button class="btn btn-default btn-block btn-lg m-b">취소</button></div>
             </form>
           	 <div class="col-sm-6"><button class="btn btn-danger btn-block btn-lg m-b" id="send">전송</button></div>
          </div>
        </div>        
      </div>
    </div>
  </section>
  
  <!-- footer -->
	<jsp:include page="include/footer.jsp" flush="false"/>
  <!-- / footer --> 
  
  <script type="text/javascript">
		
		var tokenArray = new Array();
		
		$(document).ready(function() {
			//목록읽어오기
			selectList();
			
			$("#pushTitle").val("Phytogram");
			
		      //전송
			  $("#send").click(function(){
				  sendPush();
			  });
			  $("#pushSelectBox option:eq(0)").click(function(){
		    	  $('#pushSelectBox option').each( function(i){
		    		  if(i==0) return;
		    		  $("#pushSelectBox option:eq("+i+")").removeAttr('selected'); 
	    		  });
		      });
		});
		
		//푸쉬 전송
		function sendPush(){
			
			var arr = new Array();
			
			if($("#pushTitle").val().length<=0){
				alert("푸쉬 타이틀을 입력해주세요.");
			}else if($("#pushContents").val().length<=0){
				alert("푸쉬 내용을 입력해주세요.");
			}else if($("#pushSelectBox").val()==null){
				alert("푸쉬 대상자를 선택해주세요.");
			}else{
				var tempArray = $("#pushSelectBox").val();
				for(var i=0; i<tempArray.length; i++){
					if(tempArray[i] == 0){
						for(var j=0; j<tokenArray.length; j++){
							arr.push(tokenArray[j]);
						}
						break;
					}else{
						arr.push(tempArray[i]);
					}
				}
				var allData = {"token":arr, "title":$("#pushTitle").val(), "contents":$("#pushContents").val()};
				sendPushAjax(allData);
			}
		}
		
		//푸쉬 전송하기
		var sendPushAjax = function(allData) {
			$.ajax({
				url : rootPath + "/push/sendPush.do",
				type : "POST",
				data : allData,
				success : sendPushSuccess,
				error : errorCallback
			})
		};
		
		//푸쉬 전송 성공
		var sendPushSuccess = function(resultData) {
			if(resultData=="success"){
				alert("전송 되었습니다.");
				$("#pushContents").val("");
			}else{
				alert("전송에 실패 하였습니다. 다시 시도해 주세요.");
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
		};

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
				  tokenArray.push(item.token);
				  var sbmTr = '<option value="'+(item.token)+'" onclick="javascript:clickOption();" >'+item.name+'</option>';	
	          return sbmTr;
			  }
		  }
		
		function clickOption(){
			$("#pushSelectBox option:eq(0)").removeAttr('selected'); 
		}

		//Ajax 에러 콜백함수
		var errorCallback = function(request,status,error) {
			//alert(request.status+":"+request.responseText+":"+error);
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