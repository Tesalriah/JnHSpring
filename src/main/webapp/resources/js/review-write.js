const range = document.getElementById('rating');
const star = document.querySelector('.star');

let width = 100;

range.addEventListener("input", function(){
    width = range.value*2*10+1;
    if(width > 100){
        width = 100;
    }
    star.style.width = width+"%";
})