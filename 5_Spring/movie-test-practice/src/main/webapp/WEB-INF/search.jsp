<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>영화 검색</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<div>
		<a href="/">영화 목록</a>
		<a href="add">영화 정보 추가</a>
	</div>
	
	<div class="contents">
		<h1>영화 검색</h1>
		<form action="${pageContext.request.contextPath}/search" method="post">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" value="${title}" />
            <label for="genre">장르:</label>
            <input type="text" id="genre" name="genre" value="${genre}" />
            <label for="actor">영화배우:</label>
            <input type="text" id="actor" name="actor" value="${actor}" />
            <button type="submit">검색</button>
        </form>
        
        <h2>검색 결과</h2>
        <table>
            <tr>
                <th>제목</th>
                <th>장르</th>
                <th>영화배우</th>
                <th>삭제</th>
            </tr>
            <c:forEach items="${list}" var="movie">
                <tr>
                    <td>${movie.title}</td>
                    <td>${movie.genre}</td>
                    <td>${movie.actor}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete?id=${movie.id}">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>