<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/change-pwd.css'/>">
        <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
        <title>J&H 비밀번호 변경</title>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <main>
            <div class="container">
                <div class="title">계정정보 찾기</div>
                <div class="select_var">
                    <ul class="select_list">
                        <li>
                            <a href="<c:url value='/find-id'/>" class="select_id">아이디 찾기</a>
                        </li>
                        <li>
                            <a href="<c:url value='/find-pwd'/>" class="select_pwd">비밀번호 찾기</a>
                        </li>
                    </ul>
                </div>
                <div class="table_area">
<%--                    <form method="post" action="<c:url value='/change-pwd'/>">--%>
                    <form:form modelAttribute="changePwd" method="post">
                        <table class="info">
                            <tr>
                                <td class="table_bg_color">새 비밀번호</td>
                                <td class="border_left"><input class="input_text" type="password" name="newPwd"><div class="error"><form:errors path="newPwd"/></div></td>
                            </tr>
                            <tr>
                            </tr>
                            <tr>
                                <td class="table_bg_color">새 비밀번호 확인</td>
                                <td class="border_left"><input class="input_text" type="password" name="checkNewPwd"><div class="error"><form:errors path="checkNewPwd"/></div></td>
                            </tr>
                            <tr>
                                <td class="table_bg_color">생년월일 확인</td>
                                <td class="border_left"><input class="input_text" type="date" value="${birth}" name="birth"><div class="error"><form:errors path="birth"/></div></td>
                            </tr>
                        </table>
                        <button type="submit" id="submit">제출</button>
<%--                    </form>--%>
                    </form:form>
                </div>
            </div>
        </main>
        <%@ include file="../footer.jsp" %>
    </body>
</html>