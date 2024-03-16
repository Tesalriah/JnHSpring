<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="head.jsp"/>
<link rel="stylesheet" href="<c:url value='/resources/css/findPwd.css'/>">
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <div class="title">계정정보 찾기</div>
        <div class="select_var">
            <ul class="select_list">
                <li>
                    <a href="<c:url value="/findId"/>" class="select_id">아이디 찾기</a>
                </li>
                <li>
                    <a href="<c:url value="/findPwd"/>" class="select_pwd">비밀번호 찾기</a>
                </li>
            </ul>
        </div>
        <div class="table_area">
            <form method="post" action="<c:url value="/findPwd"/>">
                <table class="info">
                    <tr>
                        <td class="table_bg_color">아이디</td>
                        <td class="border_left"><input class="input_text" type="text" name="id"></td>
                    </tr>
                    <tr>
                        <td class="table_bg_color">이메일</td>
                        <td class="border_left"><input class="input_text" type="text" name="email" autocomplete="off">&nbsp;<button type="button" class="send_email">인증번호 전송</button></td>
                    </tr>
                    <tr>
                        <td class="table_bg_color">인증번호</td>
                        <td class="border_left"><input class="input_text" type="text" name="auth_num"></td>
                    </tr>
                </table>
            </form>
            <button type="submit" id="submit">비밀번호 찾기</button>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>