<%@ page contentType="text/html; charset=utf-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/login_css.css" type="text/css">
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <h1>로그인</h1><br>
        <c:if test="${param.loginfail eq 1}">
            <h3>로그인에 실패하였습니다.</h3><br>
        </c:if>


            <form method="post" action="/authenticate">
                <input type="text" name="userId" class="fadeIn second" placeholder="****@exapmle.com">
                <input type="password" name="password" class="fadeIn third" placeholder="비밀번호">
                <input type="submit" value="로그인" class="fadeIn fourth">
            </form>

            <div id="formFooter">
                <a class="underlineHover" href="/members/register">회원가입 하시겠습니까?</a>
            </div>
    </div>
</div>

</body>
</html>
