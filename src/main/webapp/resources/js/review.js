const r_contents = document.querySelector('.reviews_contents');
const r_paging = document.querySelector('.review_paging');
let r_currentPage = 0;

function reviewList(page){
    // 처음 페이지가 로딩될때는 스크롤 이동하지않음
    if(r_currentPage != 0) {
        smoothScroll(r_contents);
    }

    r_currentPage = page;
    /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.product_id = product_id.value;
    reqJson.page = page;
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                var list = result.list;
                var ph = result.ph

                if(ph.totalCnt <= 0) {
                    r_contents.innerHTML += '<div class="empty_list">작성된 리뷰가 없습니다.</div>';
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
                            html += '<div class="review_img"><img class="each_img" src="/jnh/resources/img/upload/review-img/' + list[i].rno + '/' + list[i].image +'"/></div>';
                        }
                        html += list[i].contents +
                            '</div>' +
                            '</div>' +
                            '</div>';

                        r_contents.innerHTML = html;
                    }
                }
                if(ph.totalPage > 0){
                    let html = '';
                    if(ph.showPrev){
                        html += '<button type="button" class="page_event" data-page="' + ph.beginPage-1 +'"><i class="fa-solid fa-angle-left"></i></button>';
                    }
                    for(let i=ph.beginPage; i<=ph.endPage; i++){
                        if(i == r_currentPage){
                            html += '<button type="button" style="color:#FFAEC9; font-weight:bold;" class="page_event" data-page="'+ i +'">' + i +'</button>';
                        }else {
                            html += '<button type="button" class="page_event" data-page="'+ i +'">' + i +'</button>';
                        }
                    }
                    if(ph.showNext){
                        html += '<button type="button" class="page_event" data-page="' + ph.endPage+1 +'"><i class="fa-solid fa-angle-right"></i></button>';
                    }
                    r_paging.innerHTML = html;
                }
            } else {
                alert(httpRequest.status + ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/mypage/review/list', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

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
    httpRequest.open('POST', '/jnh/mypage/review/removeAjax', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

reviewList(1);

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
        location.href = '/jnh/mypage/review/modify?rno=' + review_no;
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











/*
const q_contents = document.querySelector('.questions_contents');
const q_paging = document.querySelector('.paging');
function  questionRequest(page){
    /!* 입력된 데이터 Json 형식으로 변경 *!/
    var reqJson = new Object();
    reqJson.page = page;

    /!* 통신에 사용 될 XMLHttpRequest 객체 정의 *!/
    var httpRequest = new XMLHttpRequest();
    /!* httpRequest의 readyState가 변화했을때 함수 실행 *!/
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var resilt = httpRequest.response;
                var list = result.list;
                var ph = result.ph;

                for (let i = 0; i < list.length; i++) {
                    let anq = "";
                    let anq_k = "";
                    if (list[i].ano == 1) {
                        anq = "question";
                        anq_k = "질문";
                    } else {
                        anq = "answer";
                        anq_k = "답변";
                    }
                    q_contents.innerHTML += '<div className="' + anq + '_each">' +
                        '<div className="question_top">' +
                        '<span className="' + anq + '_span">' + anq_k + '</span><a href="">' + list[i].product_id + '</a>' +
                        '<div style="float: right; font-weight:100;">' + list[i].reg_date + '</div>' +
                        '</div>' +
                        '<div className="question_bottom">' +
                        list[i].contents +
                        '</div>' +
                        '</div>';
                }
                if (ph.showPrev) {
                    q_paging.innerHTML += <button type="button" onclick=""><i className="fa-solid fa-angle-left"></i></button>
                }
                for (let i = ph.beginPage; i <= ph.endPage; i++) {
                    q_paging.innerHTML += '<button type="button" onclick="questionRequest(' + i +')">' + i + '</button>'
                }
                if (ph.showNext) {
                    q_paging.innerHTML += <button type="button"><i className="fa-solid fa-angle-right"></i></button>
                }


            } else {
                alert(httpRequest.status + ' Error');
            }
        }
    };
    /!* Post 방식으로 요청 *!/
    httpRequest.open('POST', '/jnh/mypage/qeustion/list', true);
    /!* Response Type을 Json으로 사전 정의 *!/
    httpRequest.responseType = "json";
    /!* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 *!/
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /!* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 *!/
    httpRequest.send(JSON.stringify(reqJson));
}

questionRequest(1);*/
