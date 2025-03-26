<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/find-id.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/find-id.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>" defer></script>
        <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
        <title>J&H 아이디 찾기</title>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <c:if test="${not empty sessionScope.msg}">
            <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
            <c:remove var="msg" scope="session"/>
        </c:if>
        <%--<script>
            msg = "${msg}";
            if(msg == "AUTH_FAIL")alert("잘못된 인증번호입니다. 다시 입력해주세요.");
        </script>--%>
        <main>
            <div class="container">
                <div class="title">계정정보 찾기</div>
                <div class="select_var">
                    <ul class="select_list">
                        <li>
                            <a href="<c:url value="/find-id"/>" class="select_id">아이디 찾기</a>
                        </li>
                        <li>
                            <a href="<c:url value="/find-pwd"/>" class="select_pwd">비밀번호 찾기</a>
                        </li>
                    </ul>
                </div>
                <div class="table_area">
                    <form method="post" action="<c:url value="/find-id"/>">
                        <table class="info">
                            <tr>
                                <td class="table_bg_color">이름</td>
                                <td class="border_left"><input class="input_text" type="text" name="name" value="${name}"></td>
                            </tr>
                            <div class="loading_fix">
                                <div class="loading_circle"></div>
                            </div>
                            <tr>
                                <td class="table_bg_color">이메일</td>
                                <td class="border_left"><input class="input_text" type="text" name="email" value="${email}" autocomplete="off">&nbsp;<button type="button" class="send_email">인증번호 전송</button></td>
                            </tr>
                            <tr>
                                <td class="table_bg_color">인증번호</td>
                                <td class="border_left"><input class="input_text" type="text" name="auth_number" autocomplete="off"></td>
                            </tr>
                        </table>
                        <button type="submit" id="submit">아이디 찾기</button>
                    </form>
                </div>
            </div>
        </main>
        <%@ include file="../footer.jsp" %>
    </body>
</html>