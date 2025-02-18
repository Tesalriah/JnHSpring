const heart = document.getElementById('heart');
const wishBtn = document.querySelector('.wish_btn');
const wishCnt = document.getElementById('wish_cnt');

wishBtn.addEventListener('click', function(){
    if(heart.classList == 'fa-regular fa-heart'){
        setWish('add');
    }else if(heart.classList == 'fa-solid fa-heart'){
        setWish('del');
    }
});


/* 입력된 데이터 Json 형식으로 변경 */
var reqJson = new Object();
reqJson.product_id = product_id.value;

function setWish(type){
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                if(!!result.msg){
                    if(result.result != "fail"){
                        wishCnt.innerText =wishCnt.innerText.replace(/,/g, "")
                        if(type == 'add'){
                            wishCnt.innerHTML = "&nbsp;" + (Number(wishCnt.innerText) + 1).toLocaleString();
                            heart.className = 'fa-solid fa-heart';
                        }else{
                            wishCnt.innerHTML = "&nbsp;" + (Number(wishCnt.innerText) - 1).toLocaleString();
                            heart.className = 'fa-regular fa-heart';
                        }
                    }
                    alert(result.msg);
                    return;
                }
            } else {
                alert(httpRequest.status +  ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/mypage/wish/'+type, true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}
