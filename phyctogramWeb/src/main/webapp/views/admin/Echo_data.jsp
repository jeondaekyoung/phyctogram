<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <title>Testing websockets</title>
    <script src="<%=application.getContextPath()%>/resources/res/js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
			openSocket();		
		
	});
	</script>
<title>Echo Chamber</title>
</head>
<body>
    <!-- Server responses get written here -->
    <div id="messages"></div>
    
    <!-- Script to utilise the WebSocket -->
    <script type="text/javascript">
        var webSocket;
        var messages = document.getElementById("messages");

        function openSocket() {
            // Ensures only one connection is open at a time
            if (webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED) {
                writeResponse("WebSocket is already opened.");
                return;
            }
            //로컬인지 아닌지 판단
            if( window.location.host =='localhost:8080'){
          	    var rootPath = window.location.host+'/phyctogramWeb';  
            }
            else{
            	var rootPath = window.location.host+":8080";	
            }
            // Create a new instance of the websocket
            webSocket = new WebSocket("ws://"+rootPath+"/echo?user_seq="+"<c:out value='${user_seq}'/>");
            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function(event) {
                // For reasons I can't determine, onopen gets called twice
                // and the first time event.data is undefined.
                // Leave a comment if you know the answer.
                if (event.data === undefined)
                    return;
                alert("open");
                writeResponse(event.data);
            };
            webSocket.onmessage = function(event) {
            	writeResponse(event.data);
            };
            webSocket.onclose = function(event) {
                writeResponse("Connection closed");
            };
        }

        /**
         * Sends the value of the text input to the server
         */
        function send() {
            var text = document.getElementById("messageinput").value;
            webSocket.send(text);
        }

        function closeSocket() {
            webSocket.close();
        }

        function writeResponse(text) {
        	if(text.indexOf(",")!= -1){
	        		var currentOS;
	   			 var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
	   			
	   			 var sp_text=text.split(",");
	       	if (mobile) {
		   				// 유저에이전트를 불러와서 OS를 구분합니다.
		   				var userAgent = navigator.userAgent.toLowerCase();
		   				if (userAgent.search("android") > -1){
		   					currentOS = "android";
		   	   	    		window.AppJs.setHeight(sp_text[0],sp_text[1],sp_text[2],sp_text[3]);
		   				}
		   				else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1)
		   							|| (userAgent.search("ipad") > -1)){
		   					currentOS = "ios";
		   	   			}
		   				else{
		   					currentOS = "else";
		   				}
	   					
	   			} else {
	   				// 모바일이 아닐 때
	   				currentOS = "nomobile";
	        	}
	    	}	
	            messages.innerHTML += "<br/>" + text;
        }
        
    </script>

</body>
</html>
