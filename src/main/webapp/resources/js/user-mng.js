document.querySelector('.user_MNG').addEventListener("click", function(event){
    if(event.target.classList.contains("address_blind")){
        alert(event.target.dataset.address);
    }
    if (event.target.classList.contains("user_suspension")) { // 정지는 1을 주고
        const user_id = event.target.dataset.user_id;
        changeStatus(user_id, 1).then(result => {
            if(result === "success"){
                var span =  document.querySelector('span[data-user_id="' + user_id +'"]');
                span.innerHTML = '정지';
                span.style.color = 'red';
                event.target.innerHTML = '해제';
                event.target.classList.replace("user_suspension", "user_unsuspend"); // 정지 > 해제
            }
        });
    }
    if (event.target.classList.contains("user_unsuspend")) { // 해제는 0
        const user_id = event.target.dataset.user_id;
        changeStatus(user_id, 0).then(result => {
            if(result == "success"){
                var span =  document.querySelector('span[data-user_id="' + user_id +'"]');
                span.innerHTML = '정상';
                span.style.color = 'lightgreen';
                event.target.innerHTML = '정지';
                event.target.classList.replace("user_unsuspend", "user_suspension"); // 해제 > 정지
            }
        });
    }
})

function changeStatus(id, status){
    return new Promise((resolve, reject) => {
        /* 입력된 데이터 Json 형식으로 변경 */
        var reqJson = new Object();
        reqJson.user_id = id;
        reqJson.status = status;
        /* 통신에 사용 될 XMLHttpRequest 객체 정의 */
        var httpRequest = new XMLHttpRequest();
        /* httpRequest의 readyState가 변화했을때 함수 실행 */
        httpRequest.onreadystatechange = () => {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    var result = httpRequest.response;
                    alert(result.msg);
                    if(result.result === "success"){
                        fnResult = result.result;
                        resolve(result.result);
                    }
                } else {
                    alert(httpRequest.status + ' Error');
                }
            }
        };
        /* Post 방식으로 요청 */
        httpRequest.open('POST', '/admin/change-status', true);
        /* Response Type을 Json으로 사전 정의 */
        httpRequest.responseType = "json";
        /* 요청 Header에 컨텐츠 타입은 Json으로 사전 정의 */
        httpRequest.setRequestHeader('Content-Type', 'application/json');
        /* 정의된 서버에 Json 형식의 요청 Data를 포함하여 요청을 전송 */
        httpRequest.send(JSON.stringify(reqJson));
    })
}