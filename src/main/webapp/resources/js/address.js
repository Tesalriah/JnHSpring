window.onload = function (){

    const juso = document.querySelector(".find_address");
    const addressBox = document.querySelector(".address");

    juso.addEventListener("click",function (){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                addressBox.value = data.address;
                if(!!data.buildingName){
                    addressBox.value += " ("+data.buildingName +") ";
                }
                showDetail = document.querySelector(".address_detail").parentElement;
                showDetail.style.display = 'block';
            }
        }).open();
    });
}