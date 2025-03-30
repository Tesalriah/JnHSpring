<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/find-pwd.css'/>">
        <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>" defer></script>
        <title>J&H 비밀번호 찾기</title>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <c:if test="${not empty sessionScope.msg}">
            <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
            <c:remove var="msg" scope="session"/>
        </c:if>
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
                    <form method="post" action="<c:url value="/find-pwd"/>">
                        <table class="info">
                            <tr>
                                <td class="table_bg_color">아이디</td>
                                <td class="border_left"><input class="input_text" value="${id}" type="text" name="id"></td>
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
                        <button type="submit" id="submit">비밀번호 찾기</button>
                    </form>
                </div>
            </div>
        </main>
        <%@ include file="../footer.jsp" %>
    </body>
</html>
<script>
    let sendMail = document.querySelector(".send_email");
    let id = document.getElementsByName("id");
    let email = document.getElementsByName("email");
    const eventless = document.querySelector(".table_area");
    const loading = document.querySelector(".loading_fix");

    function resetCircle(){
        eventless.style.pointerEvents = "auto";
        loading.style.display = "none";
    }

    sendMail.addEventListener("click", function(){
        eventless.style.pointerEvents = "none";
        loading.style.display = "block";
        if(!id[0].value){
            alert("아이디를 입력해주세요.")
            resetCircle()
            return;
        }
        if(!email[0].value){
            alert("이메일을 입력해주세요.")
            resetCircle()
            return;
        }
        let idVal = id[0].value;
        let emailVal = email[0].value;

        $.ajax({
            type:'POST',       // 요청 메서드
            url: '/jnh/pwd-auth',  // 요청 URI
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({"user_id":idVal, "email":emailVal}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(data){
                alert(data.msg);
                resetCircle()
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()\
    });
</script>