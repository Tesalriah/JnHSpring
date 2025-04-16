<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/loading.js"/>" defer></script>
        <link rel="icon" href="<c:url value="/resources/favicon.ico"/>" type="image/x-icon">
        <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>" defer></script>
        <title>J&H 로그인</title>
    </head>
    <body>
    <div class="container">
        <c:if test="${not empty sessionScope.msg}">
            <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
            <c:remove var="msg" scope="session"/>
        </c:if>
        <div class="logo"><a href="<c:url value='/'/>">J&H</a></div>
        <form method="post" action="<c:url value='/login'/>">
            <div class="input_id"><i class="fa-regular fa-id-card"></i><input type="text" name="id" value="${cookie.id.value}" placeholder="아이디"><br></div>
            <div class="loading_fix">
                <div class="loading_circle"></div>
            </div>
            <div class="input_pwd"><i class="fa-solid fa-lock"></i><input type="password" name="pwd" placeholder="비밀번호"><br></div>
            <div class="menu"><input type="checkbox" name="rememberId" id="save_id"  ${empty cookie.id.value ? "":"checked"}><label for="save_id">아이디 저장</label> <a href="<c:url value="/find-id"/>">아이디 / 비밀번호 찾기</a></div>
            <input type="hidden" name="prevPage" value="${prevPage}">
            <div class="button_menu">
                <input id="submit" type="submit" value="로그인">
                <a href="<c:url value='/register'/>">회원가입</a>
            </div>
        </form>
    </div>
    </body>
</html>