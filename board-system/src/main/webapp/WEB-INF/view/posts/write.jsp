<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>글 쓰기</title>
</head>
<body>
    <c:if test="${mode eq 'modify'}">
    <p>수정 모드 입니다.</p>
        <form action="${pageContext.request.contextPath}/board/modifypost?postId=${postId}" method="post">
    </c:if>

    <c:if test="${mode eq null}">
        <form action="${pageContext.request.contextPath}/board/insertpost" method="post">
    </c:if>

        <label>제목<input type="text" id="title" name="title" value="${post.title}"></label><br>
        <label>내용<br>
        <textarea id="content" name="content">${post.content}</textarea>

        <script src="https://cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
        <script>
            CKEDITOR.replace('content');
        </script>
        </label>
        <input type="submit" value="업로드">
    </form>
</body>
</html>
