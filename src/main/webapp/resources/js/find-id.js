let sendMail = document.querySelector(".send_email");
let name = document.querySelector('input[name="name"]');
let email = document.querySelector('input[name="email"]');
const eventless = document.querySelector(".table_area");
const loading = document.querySelector(".loading_fix");

function resetCircle(){
    eventless.style.pointerEvents = "auto";
    loading.style.display = "none";
}

sendMail.addEventListener("click", function(){
    eventless.style.pointerEvents = "none";
    loading.style.display = "block";
    if(!name.value){
        alert("이름을 입력해주세요.")
        resetCircle();
        return;
    }
    if(!email.value){
        alert("이메일을 입력해주세요.")
        resetCircle();
        return;
    }
    let nameVal = name.value;
    let emailVal = email.value;

    $.ajax({
        type:'POST',       // 요청 메서드
        url: '/jnh/id-auth',  // 요청 URI
        headers : { "content-type": "application/json"}, // 요청 헤더
        data : JSON.stringify({"user_name":nameVal, "email":emailVal}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
        success : function(data){
            alert(data.msg);
            resetCircle();
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()\
});