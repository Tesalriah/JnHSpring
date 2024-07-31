const range = document.getElementsByName('rating');
const star = document.querySelector('.star');

let width = 100;

range[0].addEventListener("input", function(){
    width = range[0].value*2*10+1;
    if(width > 100){
        width = 100;
    }
    star.style.width = width+"%";
})