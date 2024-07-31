const body = document.querySelector('body');
const eachImg = document.getElementsByClassName('each_img');
const imgBg = document.querySelector('.reviews_bg');
const reviewImg = document.querySelector('#reviews_img');
const reviewImgX = document.querySelector('#review_img_x');
const enlargeImg = document.querySelector('#enlarge_img');

for(let i=0; i<eachImg.length; i++){
    eachImg[i].addEventListener('click',function(){
        openImg();
        enlargeImg.src = eachImg[i].src;
    })
}
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