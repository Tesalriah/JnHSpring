const type = document.getElementsByName('type');
const radioBtn = document.getElementsByClassName('radioBtn');
const typeReturn = document.querySelector('#return');
const typeExchange = document.querySelector('#exchange');
const changeSize = document.querySelector('.change_size');
const productInfo = document.querySelector('.product_info');
const reasonReturn = document.querySelector('.reason_return');
const reasonExchange = document.querySelector('.reason_exchange');
const initOption = document.querySelector('#init_option');

for(let i=0; i<radioBtn.length; i++){
    radioBtn[i].addEventListener('click',function(){
        if(typeReturn.checked){
            type[0].value='return';
            changeSize.style.display='none';
            productInfo.style.width = '85%';
            reasonReturn.style.display='block';
            reasonExchange.style.display='none';
            initOption.selected='true';
        }
        if(typeExchange.checked){
            type[0].value='exchange';
            changeSize.style.display='table-cell';
            productInfo.style.width = '60%';
            reasonReturn.style.display='none';
            reasonExchange.style.display='block';
            initOption.selected='true';
        }
    })
}