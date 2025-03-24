// 링크이동하는 버튼들 처리
document.querySelector(".order_list").addEventListener("click", function (event){
    if(event.target.classList.contains('moveLink')){
        location.href = event.target.dataset.link;
    }
})

document.querySelectorAll('.cancel_btn').forEach( btn => {
    btn.addEventListener("click", function (event){
        if (!confirm("정말 취소하시겠습니까?")) {
            event.preventDefault(); // 기본 동작(폼 제출) 막기
        }
    });
});