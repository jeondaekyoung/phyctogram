<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<table>
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
