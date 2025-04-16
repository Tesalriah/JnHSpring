const body = document.querySelector('body');
const imgBg = document.querySelector('.reviews_bg');
const reviewImg = document.querySelector('#reviews_img');
const reviewImgX = document.querySelector('#review_img_x');
const enlargeImg = document.querySelector('#enlarge_img');

document.querySelector('.table_box').addEventListener("click", function (event){
    // 이미지 클릭시 숨겨놓은 모달에 이미지를 확대하여 보여줌
    if(event.target.classList.contains('each_img')){
        openImg();
        enlargeImg.src = event.target.src;
    }

    // 보기 클릭시 해당하는 리뷰를 아래에 보여줌
    if(event.target.classList.contains('show_detail')){
        var showRno = document.querySelector('tr[data-rno="' + event.target.dataset.rno +'"]');
        showRno.style.display = 'table-row';
        event.target.innerHTML = '닫기';
        event.target.classList.replace("show_detail", "close_detail");
    }else if(event.target.classList.contains('close_detail')){ // 닫기
        var showRno = document.querySelector('tr[data-rno="' + event.target.dataset.rno +'"]');
        showRno.style.display = 'none';
        event.target.innerHTML = '보기';
        event.target.classList.replace("close_detail", "show_detail");
    }

    if(event.target.classList.contains('blind-review')){
        if(confirm("정말 삭제하시겠습니까?")){
            /* 입력된 데이터 Json 형식으로 변경 */
            var reqJson = new Object();
            reqJson.user_id = event.target.dataset.user_id;
            reqJson.rno = event.target.dataset.rno;
            /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
            var httpRequest = new XMLHttpRequest();
            /* httpRequest의 readyState가 변화했을때 함수 실행 */
            httpRequest.onreadystatechange = () => {
                if (httpRequest.readyState === XMLHttpRequest.DONE) {
                    if (httpRequest.status === 200) {
                        var result = httpRequest.response;
                        // 결과 메세지 출력
                        alert(result.msg);
                        if(result.result === 'success'){
                            event.target.style.color = '#dddddd';
                            event.target.parentElement.parentElement.style.color = '#dddddd';
                        }
                    } else {
                        alert(httpRequest.status + ' Error');
                    }
                }
            };
            /* Post 방식으로 요청 */
            httpRequest.open('POST', "/admin/blind-review", true);
            /* Response Type을 Json으로 사전 정의 */
            httpRequest.responseType = "json";
            /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
            httpRequest.setRequestHeader('Content-Type', 'application/json');
            /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
            httpRequest.send(JSON.stringify(reqJson));
        }
    }
})

// 이미지 외부나 X버튼 클릭시 이미지모달 다시 숨김
reviewImgX.addEventListener('click',function(){
    closeImg();
})
imgBg.addEventListener('click',function(){
    closeImg();
})

function openImg(){
    imgBg.style.display = 'block';
    reviewImg.style.display = 'block';
    body.style.overflow='hidden';
}
function closeImg(){
    imgBg.style.display = 'none';
    reviewImg.style.display = 'none';
    body.style.overflow='visible';
}