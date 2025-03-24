const okBtn = document.querySelector('.ok_btn');
const cancelBtn = document.querySelector('.cancel_btn');;
const allBtn = document.querySelector('#agree_all');

okBtn.addEventListener("click",function(){
    if(allBtn.checked == false)
        alert('J&H 이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.');
    if(allBtn.checked == true)
        var form = document.createElement("form");
        form.method = "POST";
        form.action = "register";
        document.body.appendChild(form);
        form.submit();
})

cancelBtn.addEventListener('click', function (){
    history.back();
})

allBtn.addEventListener("click", function(){
    const checkBox = document.querySelectorAll('.check_box');
    if(checkBox[0].checked == true){
        for(let i=0; i<checkBox.length; i++){
            checkBox[i].checked = true;
        }
    }else if(checkBox[0].checked == false){
        for(let i=0; i<checkBox.length; i++){
            checkBox[i].checked = false;
        }
    }
})

var checkAll = document.querySelectorAll('.check_box');

for(let i=1; i<checkAll.length; i++){
    checkAll[i].addEventListener("click", function(){
        console.log(i);
        if(checkAll[1].checked == true && checkAll[2].checked == true && checkAll[3].checked == true){
            checkAll[0].checked = true;
        }
        if(checkAll[i].checked == false){
            checkAll[0].checked = checkAll[i].checked;
        }
    })
}