let sendMail = document.querySelector(".send_email");
let id = document.getElementsByName("inputId");
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