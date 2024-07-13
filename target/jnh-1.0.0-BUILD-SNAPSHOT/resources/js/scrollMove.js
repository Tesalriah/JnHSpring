const moveReviews = document.querySelector('#move_reviews');
const moveQuestions = document.querySelector('#move_questions');
const reviewsPoint = document.querySelector('#reviews');
const questionsPoint = document.querySelector('#questions');
const moveTop = document.querySelector('.move_top');

moveReviews.addEventListener('click',function(){
    window.scrollBy({top : reviewsPoint.getBoundingClientRect().top-65, behavior:'smooth'});
    document.querySelector('.move_top').style.display = 'block';
});
moveQuestions.addEventListener('click',function(){
    window.scrollBy({top : questionsPoint.getBoundingClientRect().top-65, behavior:'smooth'});
    document.querySelector('.move_top').style.display = 'block';
});

moveTop.addEventListener('click',function(){
    window.scrollTo({top: 0, behavior: 'smooth'});
});

window.addEventListener('scroll',function(){
    if(window.scrollY < reviewsPoint.offsetTop-100){
        document.querySelector('.move_top').style.display = 'none';
    }
    if(window.scrollY > reviewsPoint.offsetTop-100){
        document.querySelector('.move_top').style.display = 'block';
    }
});