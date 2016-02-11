<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" >
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="apple-mobile-web-app-capable" content="yes" >
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<title>로그인</title>
	
	<style>
        *{padding:0; margin:0; font-size: 13px; font-family: '맑은 고딕', sans-serif}
        body {background: #C2ABE2;}
        #wrap {width: 100%; height: 100%; overflow:hidden;}
        #container {width: 400px; height:auto; background: #fff; border-radius: 3px; margin: 200px auto 0}
        #header {background: #ede5e2; border-radius: 3px; padding: 15px 0}
        #header h1 {text-align: center}
        #contents {margin-top: 30px; padding: 20px;}
        #contents .formgroup {margin-bottom: 10px}
        #contents .formgroup label {display: inline-block;}
        #contents .formgroup input {width: 100%; padding: 15px 0; border-radius: 3px; border: 1px solid #ddd;}
        #contents .loginBtn {width:100%; padding: 15px 0; margin-top: 30px; background: #F6D14C; border:none; border-radius: 3px;}
    </style>
</head>

<body>
        <div id="wrap">
            <div id="container">
                <div id="header">
                    <h1>로그인</h1>
                </div>
                
                <form action="<%=application.getContextPath()%>/admin/login.do" method="POST">
	                <div id="contents">
	                    <div class="formgroup">
	                        <label>ID</label>
	                        <input type="text" name="id" placeholder=" 아이디를 입력하세요">
	                    </div>
	                    <div class="formgroup">
	                        <label>Password</label>
	                        <input type="password" name="pw" placeholder=" 비밀번호를 입력하세요">
	                    </div>
	                    <input type="submit" value="로그인" class="loginBtn">
	                </div>
                </form>
                
            </div>
        </div>
</body>
</html>