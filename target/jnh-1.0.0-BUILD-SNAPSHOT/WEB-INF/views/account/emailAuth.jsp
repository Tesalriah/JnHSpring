<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="kr">
<<<<<<<< HEAD:target/jnh-1.0.0-BUILD-SNAPSHOT/WEB-INF/views/account/emailAuth.jsp
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/emailAuth.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@include file="../header.jsp"%>
        <main>
            <div class="auth_box">
                <script>
                    msg = "${msg}";
                    if(!msg){
                        msg = "${param.msg}";
                    }
                    if(msg == "CHECK_EMAIL")alert("이메일 인증을 완료해주세요.");
                    if(msg == "AUTH_FAIL")alert("인증번호가 맞지 않습니다.");
                </script>
                <div class="auth_title">
                    ${sessionScope.id}님 회원가입 해주셔서 감사합니다!
                    <div class="email">${email}</div>
                    으로 메일을 보냈습니다.
                </div>
                <form action="<c:url value="/emailAuth"/>" method="post">
                    <div class="auth_form">
                        <input type="hidden" name="email" value="${email}">
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
========
<head>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/auth.css'/>">
    <title>J&H</title>
</head>
<body>
<%@include file="header.jsp"%>
    <main>
        <div class="auth_box">
            <script>
                msg = "${msg}";
                if(!msg){
                    msg = "${param.msg}";
                }
                if(msg == "CHECK_EMAIL")alert("이메일 인증을 완료해주세요.");
                if(msg == "AUTH_FAIL")alert("인증번호가 맞지 않습니다.");
            </script>
            <div class="auth_title">
                ${sessionScope.id}님 회원가입 해주셔서 감사합니다!
                <div class="email">${email}</div>
                으로 메일을 보냈습니다.
            </div>
            <form action="<c:url value="/emailauth"/>" method="post">
                <div class="auth_form">
                    <input type="hidden" name="email" value="${email}">
                    인증번호<input type="text" name="auth_num" autocomplete="off">
                </div>
                <div class="submit_btn">
                    <button type="submit">확인</button>
                </div>
            </form>
        </div>
    </main>
    <jsp:include page="footer.jsp"/>
</body>
>>>>>>>> origin/H:src/main/webapp/WEB-INF/views/emailAuth.jsp
</html>
