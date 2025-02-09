function dateFormatter(date){
    // 연도, 월, 일 추출
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
    var day = String(date.getDate()).padStart(2, '0'); // 하루의 날짜 추출
    // YYYY-MM-DD 형식으로 포맷팅
    var formattedDate = year + '-' + month + '-' + day;
    return formattedDate;
}

const product_id = document.querySelector('[name="product_id"]');
const contents = document.querySelector('.questions_contents'); //class
const paging = document.querySelector('.question_paging');
let currentPage = 0;

function sendReqeust(page){
    if(currentPage != 0){
        contents.scrollIntoView();
        window.scrollBy(0, -150);
    }
    currentPage = page;

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
                var ph = result.ph;
                if(ph.totalCnt <= 0){
                    contents.innerHTML += '<div class="empty_list">작성된 문의가 없습니다.</div>';
                }else{
                    contents.innerHTML = '';
                    // 리스트를 가져오는 function
                    for(let i=0; i<list.length; i++) {
                        if (list[i].ano != 2) {
                            contents.innerHTML += '<div class="questions_each">'
                                + '<div class="question_top">'
                                + '<span class="question_span">질문</span>' + list[i].user_id + '<div style="float: right; font-weight:100;">' + dateFormatter(new Date(list[i].reg_date)) + '</div>'
                                + '</div>'
                                + '<div class="question_bottom">'
                                + list[i].contents
                                + '</div>'
                                + '</div>';
                        } else {
                            contents.innerHTML += '<div class="answer_each">'
                                + '<div class="question_top">'
                                + '<span class="answer_span">답변</span>관리자<div style="float: right; font-weight:100;">' + dateFormatter(new Date(list[i].reg_date)) + '</div>'
                                + '</div>'
                                + '<div class="question_bottom">'
                                + list[i].contents
                                + '</div>'
                                + '</div>';
                        }
                    }
                    if(ph.totalPage > 0){
                        paging.innerHTML = '';
                        if(ph.showPrev){
                            paging.innerHTML += '<div data-page="' + ph.beginPage-1 +'"><i class="fa-solid fa-angle-left"></i></div>';
                        }
                        for(let i=ph.beginPage; i<=ph.endPage; i++){
                            if(i == currentPage){
                                paging.innerHTML += '<div onclick="sendReqeust(' + i +')" style="color:#FFAEC9; font-weight:bold;" class="page_event" data-page="'+ i +'">' + i +'</div>';
                            }else {
                                paging.innerHTML += '<div onclick="sendReqeust(' + i +')">' + i +'</div>';
                            }
                        }
                        if(ph.showNext){
                            paging.innerHTML += '<div data-page="' + ph.endPage+1 +'"><i class="fa-solid fa-angle-right"></i></div>';
                        }
                    }
                }
            } else {
                alert(httpRequest.status +  ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/question/list', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

sendReqeust(1);

// const 로 선언하기
const write_btn = document.getElementById('write');
const q_contents = document.querySelector('[name="question_contents"]');

write_btn.addEventListener('click', function (){
    /* HTML에 입력된 데이터를 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.product_id = product_id.value;
    reqJson.contents = q_contents.value;
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                var success = result.success;
                var fail = result.fail;
                if (!!fail){
                    alert(fail);
                    if (fail == '로그인이 필요합니다.'){
                        location.href='login';
                    }
                }
                if(!!success){
                    alert(success);
                    qBg.style.display='none';
                    qModal.style.display='none';
                    q_contents.value = '';
                    sendReqeust(1);
                    body.style.overflow='visible';
                }
            } else {
                alert(httpRequest.status +  ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/question/write', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
})




/*const pid1=document.getElementsByName('product_id');
const wrt1 = document.getElementById('write');
const cnt1 = document.getElementsByName('question_contents');

wrt1.addEventListener('click', function (){
    var j = new Object();
    j.pid1 = pid1.value;
    j.cnt1 = cnt1.value;

    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = () => {
        if(httpRequest.readyState === )
    }


})*/






const wrt = document.getElementById('write');
const cnt = document.getElementsByName('question_contents');
const pid = document.getElementsByName('product_id')
wrt.addEventListener('click', function () {
    /* HTML에 입력된 데이터를 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.pid = pid.value;
    reqJson.cnt= cnt.value;

    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();

    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                // 컨트롤러 리턴값 -> httpRequest.response == "바보";

            } else {
            }
        }
    };

    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/question/write1', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));

})