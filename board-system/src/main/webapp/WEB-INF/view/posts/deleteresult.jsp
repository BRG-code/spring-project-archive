<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>댓글 삭제</title>
</head>
<body>
<c:if test="${result eq 'No Login Value'}">
    <script>
        alert("로그인이 되어 있지 않습니다.");
        history.go(-1);
    </script>
</c:if>

<c:if test="${result eq 'Fail'}">
    <script>
        alert("서버 문제로 삭제에 실패했습니다.");
        history.go(-1);
    </script>
</c:if>

<c:if test="${result eq 'Permission Error'} ">
    <script>
        alert("작업을 수행할 권한이 없습니다.");
        history.go(-1);
    </script>
</c:if>

<c:if test="${result eq 'Success'}">
    <script>
        location.href="/board/posts";
    </script>
</c:if>
</body>
</html>
