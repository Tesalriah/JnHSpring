const product_id = document.getElementsByName('product_id');
const size = document.getElementsByName('size');
const price = document.getElementsByName("price");
const quantity = document.getElementsByName("quantity")
const total = document.getElementById("total");
const q_minus = document.querySelectorAll('.quantity_minus');
const q_plus = document.querySelectorAll('.quantity_plus');
const each_price = document.querySelectorAll('.price');
const del_check = document.querySelectorAll('.delete_check');
const del_all = document.getElementById('delete_all');
let totalCal = 0;

window.onload = function(){
    for(let i=0; i<del_check.length; i++){
        each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        if(del_check[i].checked == true){
            totalCal += price[i].value * parseInt(quantity[i].value);
        }
        total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    }
}

del_all.addEventListener('click', function(){
    for(let i=0; i<del_check.length; i++){
        if(del_all.checked == true){
            totalCal += price[i].value * parseInt(quantity[i].value);
        }
        del_check.checked = del_all.checked;
    }
    if(del_all.checked == false){
        totalCal = 0;
    }
    total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
})

for(let i=0; i<del_check.length; i++){

    del_check[i].addEventListener('click', function(){
        if(del_check[i].checked == true){
            totalCal += price[i].value * quantity[i].value;
        }
        if(del_check[i].checked == false){
            totalCal -= price[i].value * quantity[i].value;
        }
        total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    })

    quantity[i].addEventListener('input',function(){
        console.log(del_check[i].checked)
        quantity[i].value = quantity[i].value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');

        if(Number(quantity[i].value) > 100){
            quantity[i].value = '100';
        }
        if(Number(quantity[i].value) <= 0){
            quantity[i].value = '1';
        }
        if(Number(quantity[i].value) >= 1){
            /* 입력된 데이터 Json 형식으로 변경 */
            var reqJson = new Object();
            reqJson.product_id = product_id[i].value;
            reqJson.size = size[i].value;
            reqJson.quantity = Number(quantity[i].value);

            setQuantity(reqJson);

            each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            if(del_check[i].checked == true) {
                totalCal = 0;
                for(let j=0; j<quantity.length; j++){
                    totalCal += price[j].value * quantity[j].value;
                }
                total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            }
        }
    })

    q_minus[i].addEventListener('click',function(){
        if(Number(quantity[i].value) > 1){
            /* 입력된 데이터 Json 형식으로 변경 */
            var reqJson = new Object();
            reqJson.product_id = product_id[i].value;
            reqJson.size = size[i].value;
            reqJson.quantity = Number(quantity[i].value) - 1;

            setQuantity(reqJson);

            if(del_check[i].checked == true) {
                totalCal -= price[i].value;
                total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            }
        }
    })

    q_plus[i].addEventListener('click',function(){
        if(Number(quantity[i].value) <= 100){
            /* 입력된 데이터 Json 형식으로 변경 */
            var reqJson = new Object();
            reqJson.product_id = product_id[i].value;
            reqJson.size = size[i].value;
            reqJson.quantity = Number(quantity[i].value) + 1;

            setQuantity(reqJson);

            if(del_check[i].checked == true) {
                totalCal += parseInt(price[i].value);
                total.innerHTML = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            }
        }
    })

    function setQuantity(reqJson){
        /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
        var httpRequest = new XMLHttpRequest();
        /* httpRequest의 readyState가 변화했을때 함수 실행 */
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    var result = httpRequest.response;
                    if(!!result.msg){
                        alert(result.msg);
                        return false;
                    }
                    quantity[i].value = result.quantity;
                    each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
                } else {
                    alert(httpRequest.status +  ' Error');
                }
            }
        };
        /* Post 방식으로 요청 */
        httpRequest.open('POST', '/set-quantity', true);
        /* Response Type을 Json으로 사전 정의 */
        httpRequest.responseType = "json";
        /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
        httpRequest.setRequestHeader('Content-Type', 'application/json');
        /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
        httpRequest.send(JSON.stringify(reqJson));
    }
}