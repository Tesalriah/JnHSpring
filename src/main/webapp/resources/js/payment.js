var orderList = [];

var orderProductId = document.querySelectorAll('input[name="product_id"]');
var orderSize = document.querySelectorAll('input[name="size"]');
var orderQuantity = document.querySelectorAll('input[name="quantity"]');

// 결제 준비 버튼 클릭 시
document.getElementById("btn-pay-ready").addEventListener("click", function(e) {
    var orderName = document.querySelector('input[name="name"]');
    var orderDelReq = document.querySelector('select[name="del_request"]');
    var orderAddress = document.querySelector('input[name="address"]');
    var orderAddress2 = document.querySelector('input[name="address2"]')
    var orderPhone = document.querySelector('input[name="phone"]');
    console.log(orderPhone.value.trim());

    var realAddress = '';

    realAddress += orderAddress.value;

    if(orderAddress2 && orderAddress2.value.trim() ){
        realAddress += orderAddress2.value;
    }

    if(!orderDelReq || !orderDelReq.value.trim()){
        alert("배송 요청사항을 선택해주세요.")
        return false;
    }
    if(!realAddress.trim()){
        alert("배송주소를 입력해주세요.")
        return false;
    }
    if(!orderName || !orderName.value.trim()){
        alert("이름을 입력해주세요.")
        return false;
    }
    if(!orderPhone || !orderPhone.value.trim()){
        alert("연락처를 입력해주세요.")
        return false;
    }

    orderProductId.forEach(function (input, index){
        var order = {
            name : orderName.value,
            address : realAddress,
            phone : orderPhone.value,
            del_request : orderDelReq.value,
            product_id : input.value,
            size : orderSize[index].value,
            quantity : orderQuantity[index].value
        };

        orderList.push(order);
    })

    var data = {
        orderList : orderList
    };

    // XMLHttpRequest 객체 정의
    var httpRequest = new XMLHttpRequest();

    // httpRequest의 readyState가 변화했을 때 함수 실행
    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
            // 서버 응답이 성공적일 때
            var response = httpRequest.response;
            // 카카오페이 결제 페이지로 리다이렉트
            location.href = response.next_redirect_pc_url;
            } else {
                // 실패 시 에러 메시지 출력
                alert(httpRequest.status + ' Error');
            }
        }
    };

    // POST 방식으로 요청
    httpRequest.open('POST', '/mypage/order/pay/ready', true);
    // Response Type을 JSON으로 설정
    httpRequest.responseType = "json";
    // 요청 Header에 Content-Type을 JSON으로 설정
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    // 요청 데이터를 JSON 형식으로 전송
    httpRequest.send(JSON.stringify(data));
});