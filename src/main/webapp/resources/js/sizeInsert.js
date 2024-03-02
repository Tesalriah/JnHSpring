let sizeArr = ["small","medium", "large","xlarge"];
const sizeInput = document.getElementsByName('size');

for(let i=0; i<sizeArr.length; i++){
    let size = document.querySelector('#'+sizeArr[i]);
    size.addEventListener('click',function(){
        initColor();
        size.style.backgroundColor = '#eeeeee';
        sizeInput[0].value = sizeArr[i];
    })
};

function initColor(){
    for(let j=0; j<sizeArr.length; j++){
        let size = document.querySelector('#'+sizeArr[j]);
        size.style.backgroundColor = "#ffffff";
    }
}