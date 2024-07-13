<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/changePwd.css'/>">
    <title>J&H</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">계정정보 찾기</div>
            <div class="select_var">
                <ul class="select_list">
                    <li>
                        <a href="<c:url value="/findid"/>" class="select_id">아이디 찾기</a>
                    </li>
                    <li>
                        <a href="<c:url value="/findpwd"/>" class="select_pwd">비밀번호 찾기</a>
                    </li>
                </ul>
            </div>
            <script>
                msg = "${msg}";
                if(msg == "NOT_MATCH_PWD")alert("비밀번호가 일치하지 않습니다.");
                if(msg == "NOT_MATCH_BIRTH")alert("생년월일이 일치하지 않습니다.");
                if(msg == "INCORRECT_PWD")alert("비밀번호의 길이는 5~20사이여야 합니다.");
            </script>
            <div class="table_area">
                <form method="post" action="<c:url value="/changepwd"/>">
                    <input type="hidden" name="user_id" value="${id}">
                    <table class="info">
                        <tr>
                            <td class="table_bg_color">새 비밀번호</td>
                            <td class="border_left"><input class="input_text" type="password" name="user_pwd"></td>
                        </tr>
                        <tr>
                            <td class="table_bg_color">새 비밀번호 확인</td>
                            <td class="border_left"><input class="input_text" type="password" name="check_pwd"></td>
                        </tr>
                        <tr>
                            <td class="table_bg_color">생년월일 확인</td>
                            <td class="border_left"><input class="input_text" type="date" name="birth"></td>
                        </tr>
                    </table>
                    <button type="submit" id="submit">비밀번호 변경</button>
                </form>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
