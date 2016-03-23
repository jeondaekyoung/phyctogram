<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<style>
        /* *{padding:0; margin: 0; border-spacing: 0; border-collapse:collapse; font-size: 12px; font-family: 'malgun gothic', sans-serif; text-decoration: none;}
		div {width:480px; height:924px; background: url('${pageContext.request.contextPath}/resources/img.png') no-repeat; }
		p {color:#fff; font-size:33px; font-weight:bold; padding-left:260px; padding-top:30px; text-align:center}
		.p2, .p3 {padding-top:9px;} */
	</style>
	<script type="text/javascript">
		//setTimeout("location.reload()", 2000);
	</script>
</head>

<body>

	<div>
		<c:if test="${not empty heights }">
			<c:forEach var="height" items="${heights }">
				<p>${height.height }, ${height.input_date } </p>
			</c:forEach>
		</c:if>
	</div>


<%-- <table>
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

<h3>날짜순위로 25개 조회.</h3> --%>


</body>
</html>
