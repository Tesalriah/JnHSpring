const body = document.querySelector('body');

const reportBtn = document.querySelectorAll('.report_open_btn');
const rBg = document.querySelector('.review_bg');
const rModal = document.querySelector('.review_report');
const reviewX = document.querySelector('#review_x');
const getId = document.getElementsByName('review_id');
const reportId = document.querySelector('[name="report_id"]');
const reportRno = document.querySelector('[name="rno"]');

/*for(let i=0; i<reportBtn.length; i++ ){
    reportBtn[i].addEventListener('click',function(){
        reportOpen();
        reportId[0].value =  getId[i].value;
    })
}*/

// 이벤트 위임 방식으로 동적으로 추가된 요소에도 이벤트 적용
document.querySelector(".reviews_contents").addEventListener("click", function(event) {
    if (event.target.classList.contains("report_open_btn")) {
        reportOpen();
        reportId.value =  event.target.dataset.id;
        reportRno.value = event.target.dataset.rno;
    }
});

reviewX.addEventListener('click',function(){
    reportClose();
})
rBg.addEventListener('click',function(){
    reportClose();
})

const openQuetions = document.querySelector('#open_question');
const qBg = document.querySelector('.question_bg');
const qModal = document.querySelector('.add_question');
const questionX = document.querySelector('#question_x');

openQuetions.addEventListener('click',function(){
    questionOpen();
})
questionX.addEventListener('click',function(){
    questionClose();
})
qBg.addEventListener('click',function(){
    questionClose();
})


// const eachImg = document.getElementsByClassName('each_img');
const imgBg = document.querySelector('.reviews_bg');
const reviewImg = document.querySelector('#reviews_img');
const reviewImgX = document.querySelector('#review_img_x');
const enlargeImg = document.querySelector('#enlarge_img');

/*for(let i=0; i<eachImg.length; i++){
    eachImg[i].addEventListener('click',function(){
        openImg();
        enlargeImg.src = eachImg[i].src;
    })
}*/

// 이벤트 위임 방식으로 동적으로 추가된 요소에도 이벤트 적용
document.querySelector(".reviews_contents").addEventListener("click", function(event) {
    if (event.target.classList.contains("each_img")) {
        openImg();
        enlargeImg.src = event.target.src;
    }
});
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
function reportOpen(){
    rBg.style.display='block';
    rModal.style.display='block';
    body.style.overflow='hidden';
}
function reportClose(){
    rBg.style.display='none';
    rModal.style.display='none';
    body.style.overflow='visible';
}
function questionOpen(){
    qBg.style.display='block';
    qModal.style.display='block';
    body.style.overflow='hidden';
}
function questionClose(){
    qBg.style.display='none';
    qModal.style.display='none';
    body.style.overflow='visible';
}