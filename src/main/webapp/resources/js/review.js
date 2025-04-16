const r_contents = document.querySelector('.reviews_contents');
const r_paging = document.querySelector('.review_paging');
let r_currentPage = 0;
let option = 'rating'; // 옵션 기본값(평점순)

function reviewList(page){
    /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.product_id = product_id.value;
    reqJson.page = page;
    reqJson.option = option;
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                var list = result.list;
                var ph = result.ph

                if(ph.totalCnt <= 0) { // 받아온 리스트가 없을경우
                    r_contents.innerHTML = '<div class="empty_list">작성된 리뷰가 없습니다.</div>';
                }else{
                    let html = '';
                    for (let i = 0; i < list.length; i++) {
                        html += '<div class="reviews_each">' +
                            '<div class="review_top">' +
                            '<div class="id">' + list[i].user_id + '</div>' +
                            '<div class="reviews_tools">';
                        if(list[i].user_id === result.id){
                            html += '<div class="edit_del" style="font-size: 14px;"><button type="button" class="modify" data-rno="' + list[i].rno + '">수정</button>|<button type="button" class="remove" data-rno="' + list[i].rno + '">삭제</button></div>';
                        }else{
                            html += '<button class="report_open_btn" type="button" data-id="' + list[i].user_id + '" data-rno="'+ list[i].rno +'">신고하기</button>';
                        }
                        let rating_width = list[i].rating * 20 + 1;
                        html += '</div>' +
                            '</div>' +
                            '<div class="review_middle" style="display:flex; align-items: center;">' +
                            '<div class="rating">' +
                            '<i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>' +
                            '<div class="star" style="width:' + rating_width + '%"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>' +
                            '</div>';
                        if(!!list[i].up_date){
                            html += '<div class="reviews_date">' + dateFormatter(new Date(list[i].up_date)) + '</div>';
                        }else{
                            html += '<div class="reviews_date">' + dateFormatter(new Date(list[i].reg_date)) + '</div>';
                        }
                        html += '</div>' +
                            '<div class="review_bottom">' +
                            '<div>';
                        if(!!list[i].image){
                            html += '<div class="review_img"><img class="each_img" src="/upload/review-img/' + list[i].rno + '/' + list[i].image +'"/></div>';
                        }
                        html += '<pre>' + list[i].contents + '</pre>' +
                            '</div>' +
                            '</div>' +
                            '</div>';

                        r_contents.innerHTML = html;
                    }
                } // 리스트가 있을경우 문자열로 html문을 중첩하여 r_contents에 innerHTML
                if(ph.totalPage > 0){
                    let html = '';
                    if(ph.showPrev){
                        html += '<button type="button" class="page_event" data-page="' + ph.beginPage-1 +'"><i class="fa-solid fa-angle-left"></i></button>';
                    }
                    for(let i=ph.beginPage; i<=ph.endPage; i++){
                        if(i == page){
                            html += '<button type="button" style="color:#FFAEC9; font-weight:bold;" class="page_event" data-page="'+ i +'">' + i +'</button>';
                        }else {
                            html += '<button type="button" class="page_event" data-page="'+ i +'">' + i +'</button>';
                        }
                    }
                    if(ph.showNext){
                        html += '<button type="button" class="page_event" data-page="' + ph.endPage+1 +'"><i class="fa-solid fa-angle-right"></i></button>';
                    }
                    r_paging.innerHTML = html;

                    // 처음 페이지가 로딩될때는 스크롤 이동하지않음
                    if(r_currentPage != 0) {
                        smoothScroll(r_contents);
                    }

                    r_currentPage = page;
                }
            } else {
                alert(httpRequest.status + ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/mypage/review/list', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

reviewList(1);

document.querySelector('#rating').addEventListener('click', function (event){
    event.target.style.fontWeight = 'bold';
    document.querySelector('#reg_date').style.fontWeight = 'normal';
    option = 'rating';
    reviewList(1);
})
document.querySelector('#reg_date').addEventListener('click', function (event){
    event.target.style.fontWeight = 'bold';
    document.querySelector('#rating').style.fontWeight = 'normal';
    option = 'reg_date';
    reviewList(1);
})

function remove(rno){
    /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.rno = rno;
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                alert(result.msg);
                if(result.success){
                    reviewList(1);
                }
            } else {
                alert(httpRequest.status + ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/mypage/review/removeAjax', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

// 이벤트 위임 방식으로 동적으로 추가된 요소에도 이벤트 적용
r_paging.addEventListener("click", function(event) {
    if (event.target.classList.contains("page_event")) {
        const page = event.target.dataset.page;
        reviewList(parseInt(page));
    }
});

r_contents.addEventListener("click",  function(event){
    if(event.target.classList.contains("modify")){
        const review_no = event.target.dataset.rno;
        location.href = '/mypage/review/modify?rno=' + review_no;
    }
    if(event.target.classList.contains("remove")){
        if(confirm("정말 삭제하시겠습니까?")){
            const review_no = event.target.dataset.rno;
            remove(parseInt(review_no));
        }else{
            return;
        }
    }
});