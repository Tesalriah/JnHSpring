// 사유/내용 모달 제어
const body = document.querySelector('body');
const modalBg = document.querySelector('.modal_bg');
const contentsModal = document.querySelector('.contents_modal');
const modalReason = document.querySelector('#modal_reason');
const modalContent = document.querySelector('#modal_content');

// for(let i=0; i<modalOpenBtn.length; i++){
//     modalOpenBtn[i].addEventListener('click', function(){
//         modalOpen();
//         modalReason.innerHTML = pageReason[i].value;
//         modalContent.innerHTML = pageContent[i].value;
//     })
// }
document.querySelector('.table_box').addEventListener('click', function (event){
    if(event.target.classList.contains('contents_open_button')){
        modalOpen();
        modalReason.innerHTML = event.target.dataset.reason;
        modalContent.innerHTML = event.target.dataset.contents;
    }
})

document.querySelector('#modal_x').addEventListener('click',function(){
    modalClose();
})
document.querySelector('.modal_bg').addEventListener('click',function(){
    modalClose();
})


function modalOpen(){
    modalBg.style.display='block';
    contentsModal.style.display='block';
    body.style.overflow='hidden';
    body.style.marginRight='15px';
}
function modalClose(){
    modalBg.style.display='none';
    contentsModal.style.display='none';
    body.style.overflow='visible';
    body.style.marginRight='0px';
}