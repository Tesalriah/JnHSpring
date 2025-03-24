const checkAll = document.querySelector('input[name="check_all"]');
const checkEach = document.getElementsByName('check_each');

//check_all 클릭시 체크박스 전부 체크
checkAll.addEventListener('click', function(){
    checkEach.forEach(checkbox => checkbox.checked = checkAll.checked);
});


// 전부 체크했을시 check_all도 체크하기 아닐시 체크 해제
checkEach.forEach(checkbox => {
    checkbox.addEventListener('click', function (){
        for(let i=0; i<checkEach.length; i++) {
            if (checkEach[i].checked == true) {
                if (i == checkEach.length - 1) {
                    checkAll.checked = true;
                }
            } else {
                checkAll.checked = false;
                return;
            }
        }
    });
});

// 상단 박스 클릭시 링크이동
document.querySelectorAll(".status_menu > div").forEach(div => {
    div.addEventListener('click', function (){
        location.href = div.dataset.link;
    })
});