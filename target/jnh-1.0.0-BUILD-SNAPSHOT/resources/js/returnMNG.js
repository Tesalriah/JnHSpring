// 사유/내용 모달 제어
const body = document.querySelector('body');

const modalBg = document.querySelector('.modal_bg');
const contentsModal = document.querySelector('.contents_modal');
const modalX = document.querySelector('#modal_x');
const modalOpenBtn = document.querySelectorAll('.contents_open_button');
const pageReason = document.getElementsByName('reason');
const pageContent = document.getElementsByName('content');
const modalReason = document.querySelector('#modal_reason');
const modalContent = document.querySelector('#modal_content');

for(let i=0; i<modalOpenBtn.length; i++){
    modalOpenBtn[i].addEventListener('click', function(){
        modalOpen();
        modalReason.innerHTML = pageReason[i].value;
        modalContent.innerHTML = pageContent[i].value;
    })
}

modalX.addEventListener('click',function(){
    modalClose();
})
modalBg.addEventListener('click',function(){
    modalClose();
})


function modalOpen(){
    modalBg.style.display='block';
    contentsModal.style.display='block';
    body.style.overflow='hidden';
}
function modalClose(){
    modalBg.style.display='none';
    contentsModal.style.display='none';
    body.style.overflow='visible';
}