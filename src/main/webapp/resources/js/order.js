const submit_btn = document.getElementById('submit_btn');
const buy_form = document.getElementById('buy');

submit_btn.addEventListener('click', function(){
    if(document.getElementsByName('del_request')[0].value == ""){
        alert("배송 요청사항을 선택해주세요.")
        return false;
    }
    if(document.getElementsByName('address')[0].value == ""){
        alert("배송주소를 입력해주세요.")
        return false;
    }
    if(document.getElementsByName('name')[0].value == ""){
        alert("이름을 입력해주세요.")
        return false;
    }
    if(document.getElementsByName('phone')[0].value == ""){
        alert("연락처를 입력해주세요.")
        return false;
    }
    buy_form.submit();
})