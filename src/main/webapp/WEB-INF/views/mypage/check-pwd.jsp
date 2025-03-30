<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/user-info.css"/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="contents">
                <h2>회원 정보 확인</h2>
                <div class="ex">개인정보 보호를 위해 비밀번호를 다시 확인합니다.</div>
                <form action="<c:url value="/mypage/user/check-pwd"/>" method="post">
                    <table class="user_info">
                        <tr>
                            <td>아이디</td>
                            <td>${id}</td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td><input id="user_pwd" name='user_pwd' type="password"></td>
                        </tr>
                    </table>
                    <div class="check_pwd">
                        <button type="submit">확인</button>&nbsp;
                        <button type="button">취소</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>