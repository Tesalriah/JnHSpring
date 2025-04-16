document.querySelector('#add_cart').addEventListener('click', function (){
    var cartSize = document.querySelector('[name="size"]');
    var cartQuan = document.querySelector('[name="quantity"]');
    if(cartSize.value == null || cartSize.value == ''){
        alert('사이즈를 선택해주세요.');
        return false;
    }if(cartQuan.value == null || cartQuan.value <= 0){
        alert('수량을 선택해주세요.');
        return false;
    }
    /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.product_id = document.querySelector('[name="product_id"]').value;
    reqJson.size = cartSize.value;
    reqJson.quantity = cartQuan.value;

    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                if(!!result.msg){
                    alert(result.msg);
                }
            } else {
                alert(httpRequest.status +  ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/add-cart/', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
})

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
    httpRequest.open('POST', '/add-cart/'+type, true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}
