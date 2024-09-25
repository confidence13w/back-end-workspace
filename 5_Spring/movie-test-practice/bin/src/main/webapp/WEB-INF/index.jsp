<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<div>
		<a href="/">영화 목록</a>
		<a href="add">영화 정보 추가</a>
	</div>

	<div class="contents">
		<h1>영화 목록</h1>
		<table>
			<tr>
				<th>제목</th>
				<th>장르</th>
				<th>영화배우</th>
			<tr>
				<c:forEach items="${list}" var="movie">
					<tr>
						<td>${movie.title}</td>
						<td>${movie.genre}</td>
						<td>${movie.actor}</td>
					</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>

