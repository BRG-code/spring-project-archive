<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>댓글 달기</title>
</head>
<body>
<c:if test="${status eq 'server'}">
    <script>
        alert("서버 오류로 업로드하지 못했습니다.");
        history.go(-1);
    </script>
</c:if>

<c:if test="${status eq 'login'}">
    <script>
        alert("비로그인 상태에서는 댓글을 작성할 수 없습니다.");
        history.go(-1);
    </script>
</c:if>

<c:if test="${status eq 'success'}">
    <c:redirect url="/board/read/${postId}" />
</c:if>

</body>
</html>
