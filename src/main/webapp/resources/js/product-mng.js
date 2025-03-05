const mngTable = document.querySelector('.MNG_table');
let savePrevPrice = 0;
let savePrevstock = 0;
let savePrevDiscount = 0;

// 이벤트 위임 방식으로 동적으로 추가된 요소에도 이벤트 적용
mngTable.addEventListener("input", function(event) {
    if (event.target.classList.contains("product_price")) {
        cantMinus(event.target)
    }
    if (event.target.classList.contains("product_discount")) {
        set100(event.target);
    }
    if (event.target.classList.contains("product_stock")) {
        Slimit(event.target);
    }
});

mngTable.addEventListener("click", function(event) {
    if (event.target.matches(".send-btn")) {
        let btn = event.target; // 클릭된 버튼
        let dynamicValue = btn.previousElementSibling; // 버튼 바로 앞의 input 가져오기

        /* 입력된 데이터 Json 형식으로 변경 */
        var reqJson = new Object();
        reqJson.dynamic_value = dynamicValue.value;
        reqJson.product_id = btn.dataset.product_id;
        reqJson.size = btn.dataset.size;
        /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
        var httpRequest = new XMLHttpRequest();
        /* httpRequest의 readyState가 변화했을때 함수 실행 */
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    alert("성공");
                    document.querySelectorAll(".product_price").forEach(input => {
                        if (input.dataset.product_id === reqJson.product_id) { // 특정 data-product_id 확인
                            input.value = reqJson.dynamic_value; // 값 변경
                        }
                    });
                } else {
                    alert(httpRequest.status + ' Error');
                }
            }
        };
        /* Post 방식으로 요청 */
        httpRequest.open('POST', btn.dataset.action, true);
        /* Response Type을 Json으로 사전 정의 */
        httpRequest.responseType = "json";
        /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
        httpRequest.setRequestHeader('Content-Type', 'application/json');
        /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
        httpRequest.send(JSON.stringify(reqJson));
    }
});


// for(let i=0; i<productPrice.length; i++){
//     productPrice[i].addEventListener('input', function(){
//         cantMinus(productPrice[i]);
//     })
// }
//
// for(let i=0; i<productDiscount.length; i++){
//     productDiscount[i].addEventListener('input',function(){
//         set100(productDiscount[i]);
//     })
// }
//
// for(let i=0; i<productStock.length; i++){
//     productStock[i].addEventListener('input',function(){
//         Slimit(productStock[i]);
//     })
// }

// 가격, 문자제한
function cantMinus(set){
    if(set.value < 0){
        alert('양수만 입력가능합니다.');
        if(savePrevPrice == null){
            set.value = 0;
        }else{
            set.value = savePrevPrice;
            return false;
        }
    }

    if(set.value > 1000000000){
        alert('가격은 1,000,000,000을 초과 할 수 없습니다.');
        if(savePrevPrice == null){
            set.value = 0;
        }else{
            set.value = savePrevPrice;
            return false;
        }
    }

    let isvalPrice = Number(set.value);
    var str = savePrevPrice.toString();
    str = str.slice(0,-1);
    if( str == isvalPrice){
        return;
    }
    if(isNaN(isvalPrice)){
        set.value = savePrevPrice;
    }else{
        set.value = isvalPrice;
        savePrevPrice = isvalPrice;
    }
}

// 할인율, 문자 제한
function set100(set){
    if(set.value > 90){
        alert('최대 90까지 입력가능합니다.');
        set.value = savePrevDiscount;
    }
    if(set.value < 0){
        alert('최소 0까지 입력가능합니다.');
        set.value = savePrevDiscount;
    }

    let isvalDiscount = Number(set.value);
    var str = savePrevDiscount.toString();
    str = str.slice(0,-1);
    if( str == isvalDiscount){
        return;
    }
    if(isNaN(isvalDiscount)){
        set.value = savePrevDiscount;
    }else{
        set.value = isvalDiscount;
        savePrevDiscount = isvalDiscount;
    }
}

//재고수량, 문자제한
function Slimit(stock){
    if(stock.value > 99999){
        alert('재고수량은 99,999를 초과 할 수 없습니다.')
        stock.value = savePrevstock;
        return;
    }

    let isvalStock = Number(stock.value);
    var str = savePrevstock.toString();
    str = str.slice(0,-1);
    if( str == isvalStock){
        return;
    }
    if(isNaN(isvalStock)){
        stock.value = savePrevstock;
    }else{
        stock.value = isvalStock;
        savePrevstock = isvalStock;
    }
}