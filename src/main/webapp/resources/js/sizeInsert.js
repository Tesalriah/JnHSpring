const sizeBtn = document.querySelectorAll('.size_btn');
const sizeInput = document.getElementsByName('size');

for(let i=0; i<sizeBtn.length; i++){
    sizeBtn[i].addEventListener("click", function(){
        sizeInput[0].value = sizeBtn[i].innerHTML;
        initColor();
        sizeBtn[i].style.backgroundColor = '#eeeeee';
    })
}

function initColor(){
    for(let j=0; j<sizeBtn.length; j++){
        sizeBtn[j].style.backgroundColor = "#ffffff";
    }
}