<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="head.jsp"/>
<jsp:include page="header.jsp"/>
    <div>
        <script>
            msg = "${msg}";
            if(msg == "SEND_OK")alert("전송완료");
            if(msg == "SEND_FAIL")alert("전송실패");
            if(msg == "AUTH_FAIL")alert("인증번호가 맞지 않습니다.");
        </script>
        ${email} 로 인증번호를 전송하였습니다! ${user_id}
        <form action="<c:url value="/emailAuth"/>" method="post">
            <div>
                <input type="hidden" name="email" value="${email}">
                인증번호<input type="text" name="auth_num">
                <button type="submit">확인</button>
            </div>
        </form>
    </div>
</body>
</html>
