<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/email-auth.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>"></script>
        <title>J&H 이메일 인증</title>
    </head>
    <body>
    <%@include file="../header.jsp"%>
        <main>
            <c:if test="${not empty sessionScope.msg}">
                <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
                <c:remove var="msg" scope="session"/>
            </c:if>
            <div class="auth_box">
                <div class="auth_title">
                    ${sessionScope.user.user_id}님 회원가입 해주셔서 감사합니다!
                    <div class="email">${sessionScope.user.email}</div>
                    으로 메일을 보냈습니다.
                </div>
                <form action="<c:url value="/email-auth"/>" method="post">
                    <div class="auth_form">
                        <input type="hidden" name="email" value="${sessionScope.user.email}">
                        인증번호<input type="text" name="auth_num" autocomplete="off">
                    </div>
                    <div class="submit_btn">
                        <button type="submit">확인</button>
                    </div>
                </form>
            </div>
        </main>
        <%@ include file="../footer.jsp" %>
    </body>
</html>
