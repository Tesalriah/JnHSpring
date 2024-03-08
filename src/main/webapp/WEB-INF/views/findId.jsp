<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="head.jsp"/>
<link rel="stylesheet" href="<c:url value='/resources/css/findId.css'/>">
<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <div class="title">계정정보 찾기</div>
        <div class="select_var">
            <ul class="select_list">
                <li>
                    <a href="#" class="select_id">아이디 찾기</a>
                </li>
                <li>
                    <a href="findPwd.html" class="select_pwd">비밀번호 찾기</a>
                </li>
            </ul>
        </div>
        <div class="table_area">
            <form method="post" action="">
                <table class="info">
                    <tr>
                        <td class="table_bg_color">이름</td>
                        <td class="border_left"><input class="input_text" type="text" name="name"></td>
                    </tr>
                    <tr>
                        <td class="table_bg_color">이메일</td>
                        <td class="border_left"><input class="input_text" type="text" name="email">&nbsp;<button type="button" class="send_email">인증번호 전송</button></td>
                    </tr>
                    <tr>
                        <td class="table_bg_color">인증번호</td>
                        <td class="border_left"><input class="input_text" type="text" name="code_number"></td>
                    </tr>
                </table>
            </form>
            <button type="submit" id="submit">제출</button>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>