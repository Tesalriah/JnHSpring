const report_rno = document.querySelector('[name="rno"]');
const report_id = document.querySelector('[name="report_id"]');
const report_reason = document.querySelector('[name="reason"]');
const report_contents = document.querySelector('[name="report_contents"]');
const report_btn = document.querySelector('#report_btn');
const reason = document.querySelector('[name="reason"]');

function report(){
    /* 입력된 데이터 Json 형식으로 변경 */
    var reqJson = new Object();
    reqJson.rno =  report_rno.value;
    reqJson.id = report_id.value;
    reqJson.reason = report_reason.value;
    reqJson.contents = report_contents.value;
    /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
    var httpRequest = new XMLHttpRequest();
    /* httpRequest의 readyState가 변화했을때 함수 실행 */
    httpRequest.onreadystatechange = () => {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                var result = httpRequest.response;
                alert(result.msg);
                if(result.result == 'close'){
                    reportClose();
                    reportId.value =  '';
                    reportRno.value = '';
                    reason.value = '';
                }
            } else {
                alert(httpRequest.status + ' Error');
            }
        }
    };
    /* Post 방식으로 요청 */
    httpRequest.open('POST', '/jnh/report', true);
    /* Response Type을 Json으로 사전 정의 */
    httpRequest.responseType = "json";
    /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
    httpRequest.send(JSON.stringify(reqJson));
}

report_btn.addEventListener('click', function (){
    report();
});