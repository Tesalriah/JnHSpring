const setMen = document.querySelector('#set_men');
const setWomen = document.querySelector('#set_women');
const setTops = document.querySelector('#set_tops');
const setBottom = document.querySelector('#set_bottom');
const setOuter = document.querySelector('#set_outer');
const arr = [setTops, setBottom, setOuter];
const arrG = [setMen, setWomen];
let setValue = '';

for(let i=0; i<arrG.length; i++){
    arrG[i].addEventListener('click', function(){
        arrG[i].style.backgroundColor="#f5f5f5";
        for(let j=0; j<arrG.length; j++){
            if(arrG[i] != arrG[j]){
                arrG[j].style.backgroundColor="#ffffff";
            }
        }
        if(i == 0){
            setValue = 'MEN';
        }else if(i == 1){
            setValue = 'WOMEN';
        }
        genderSet(setValue);
        unfold();
    })
}
for(let i=0; i<arr.length; i++){
    arr[i].addEventListener('click', function(){
        arr[i].style.backgroundColor="#f5f5f5";
        arr[i].lastElementChild.firstElementChild.style.color='#f5f5f5';
        for(let j=0; j<arr.length; j++){
            if(arr[i] != arr[j]){
                arr[j].style.backgroundColor="#ffffff";
                arr[j].lastElementChild.firstElementChild.style.color='#ffffff';
            }
        }
        if(i == 0){
            setValue = 'TOPS';
        }else if(i == 1){
            setValue = 'BOTTOM';
        }else if(i == 2){
            setValue = 'OUTER';
        }
        categorySet(setValue);
    })
}

const displayCategory = document.querySelector('.display_category');
const emptied = document.querySelector('.emptied');
const genderSpan = document.querySelector('#gender_span');
const categorySpan = document.querySelector('#category_span');
const productCategory = document.getElementsByName('category');
const productGender = document.getElementsByName('gender');
const arrow = document.querySelector('#arrow');

function genderSet(val){
    genderSpan.innerHTML = val;
    productGender[1].value = val;
}
function categorySet(val){
    categorySpan.innerHTML = val;
    productCategory[1].value = val;
    arrow.style.display = 'inline';
}
function unfold(){
    displayCategory.style.display = 'block';
    emptied.style.display = 'none';
}