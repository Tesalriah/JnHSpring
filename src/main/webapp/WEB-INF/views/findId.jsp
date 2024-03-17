<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/findId.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>J&H</title>
</head>
<body>
    <%@ include file="header.jsp" %>
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
            <div class="table_area">
                <form class="findForm" method="post" action="<c:url value="/findid"/>">
                    <table class="info">
                        <tr>
                            <td class="table_bg_color">이름</td>
                            <td class="border_left"><input class="input_text" type="text" name="name"></td>
                        </tr>
                        <div class="loading_fix">
                            <div class="loading_circle"></div>
                        </div>
                        <tr>
                            <td class="table_bg_color">이메일</td>
                            <td class="border_left"><input class="input_text" type="text" name="email" autocomplete="off">&nbsp;<button type="button" class="send_email">인증번호 전송</button></td>
                        </tr>
                        <tr>
                            <td class="table_bg_color">인증번호</td>
                            <td class="border_left"><input class="input_text" type="text" name="auth_number" autocomplete="off"></td>
                        </tr>
                    </table>
                    <button type="button" id="submit">아이디 찾기</button>
                </form>
                <div class="resultForm" style="text-align: center; border:1px solid #dddddd; background-color:#fdfdfd; padding: 50px; display: none">
                    <div>
                        <div><span class="result_name">홍길동</span> 회원님의 아이디입니다.</div><br>
                        <div style="font-weight: bold;">아이디 : <span class="result_id" style="color:#FFAEC9;">asd123</span></div>
                        <a href="<c:url value="/login"/>" class="result_login" style="margin-top: 30px; background-color: #FFAEC9; color:#ffffff;padding:15px 20px;display: inline-block;font-size: 20px; font-weight: bold;border-radius: 5px;">로그인</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>
<script>
    const sendMail = document.querySelector(".send_email");
    const submitBtn = document.querySelector("#submit");
    let name = document.getElementsByName("name");
    let email = document.getElementsByName("email");
    let authNum = document.getElementsByName("auth_number");
    const eventless = document.querySelector(".table_area");
    const loading = document.querySelector(".loading_fix");
    const findForm = document.querySelector(".findForm");
    const resultForm = document.querySelector(".resultForm");
    const resultId = document.querySelector(".result_id");
    const resultName = document.querySelector(".result_name");

    sendMail.addEventListener("click", function(){
        eventless.style.pointerEvents = "none";
        loading.style.display = "block";
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
                if(data.msg == "전송완료"){
                    sendMail.style.backgroundColor = "#dddddd";
                    sendMail.style.pointerEvents = "none";
                    name[0].style.pointerEvents = "none";
                    email[0].style.pointerEvents = "none";
                }
                eventless.style.pointerEvents = "auto";
                loading.style.display = "none";
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()\
    });


    submitBtn.addEventListener("click", function (){
        if(!email[0].value){
            alert("이메일을 입력해주세요.")
            return;
        }if(!authNum[0].value){
            alert("인증번호를 입력해주세요.")
            return;
        }
        let emailVal = email[0].value;
        let authNumVal = authNum[0].value;
        console.log(emailVal);
        console.log(authNumVal);

        $.ajax({
            type:'POST',       // 요청 메서드
            url: '/jnh/findid',  // 요청 URI
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({"email":emailVal, "auth_number":authNumVal}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(data){
                if(!!data.msg){
                    alert(data.msg);
                    return;
                }
                if(!!data.id){
                    findForm.style.display = "none";
                    resultForm.style.display = "block";
                    resultId.innerHTML = data.id;
                    resultName.innerHTML = name[0].value;
                }
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()\
    });
</script>