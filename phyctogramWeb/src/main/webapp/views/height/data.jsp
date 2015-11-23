<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<style>
        *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none;}
		table {width:100%; }
		tbody tr:hover {background: #f7f7f7}
		th {background:#777; color:#fff; border: 1px solid #f7f7f7; padding:10px 5px; font-size:18px}
		td {border: 1px solid #f7f7f7; padding:5px; text-align: center; font-size: 18px}
		
	</style>
</head>

<table>
<colgroup>
	<col style="width:25%;"/>
	<col style="width:40%;"/>
	<col style="width:35%;"/>
</colgroup>
	<thead><tr>
		<th>사용자번호(user_seq)</th>
		<th>날짜</th>
		<th>키</th>
	</tr></thead>
	<tbody>
		<c:if test="${not empty heights }">
			<c:forEach var="height" items="${heights }">
			<tr>
				<td>${height.user_seq }</td>
				<td>${height.mesure_date }</td>
				<td>${height.height }</td>
			</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>

<h3>날짜순위로 25개 조회.</h3>


</body>
</html>
