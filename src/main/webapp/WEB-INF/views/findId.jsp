<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="head.jsp"/>
<link rel="stylesheet" href="<c:url value='/resources/css/findId.css'/>">
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
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
            <form method="post" action="<c:url value="/findId"/>">
                <table class="info">
                    <tr>
                        <td class="table_bg_color">이름</td>
                        <td class="border_left"><input class="input_text" type="text" name="name"></td>
                    </tr>
                    <div class="loding_fix">
                        <div class="loading_circle"></div>
                    </div>
                    <tr>
                        <td class="table_bg_color">이메일</td>
                        <td class="border_left"><input class="input_text" type="text" name="email" autocomplete="off">&nbsp;<button type="button" class="send_email">인증번호 전송</button></td>
                    </tr>
                    <tr>
                        <td class="table_bg_color">인증번호</td>
                        <td class="border_left"><input class="input_text" type="text" name="auth_num" autocomplete="off"></td>
                    </tr>
                </table>
                <button type="submit" id="submit">아이디 찾기</button>
            </form>
        </div>
    </div>
</main>
<jsp:include page="footer.jsp"/>
<script>
    let sendMail = document.querySelector(".send_email");
    let name = document.getElementsByName("name");
    let email = document.getElementsByName("email");
    const eventless = document.querySelector(".table_area");
    const loding = document.querySelector(".loding_fix");

    sendMail.addEventListener("click", function(){
        eventless.style.pointerEvents = "none";
        loding.style.display = "block";
        if(!name[0].value){
            alert("이름을 입력해주세요.")
            return;
        }
        if(!email[0].value){
            alert("이메일을 입력해주세요.")
            return;
        }
        let nameVal = name[0].value;
        let emailVal = email[0].value;

        $.ajax({
            type:'POST',       // 요청 메서드
            url: '/jnh/idauth',  // 요청 URI
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({"user_name":nameVal, "email":emailVal}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(data){
                alert(data.msg);
                eventless.style.pointerEvents = "auto";
                loding.style.display = "none";
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()\

        $.ajax({
            type:'POST',       // 요청 메서드
            url: '/jnh/findId',  // 요청 URI
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({"user_name":nameVal, "email":emailVal}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(data){
                alert(data.msg);
                eventless.style.pointerEvents = "auto";
                loding.style.display = "none";
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()\
    });
</script>