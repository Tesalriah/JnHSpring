const setMen = document.querySelector('#set_men');
const setWomen = document.querySelector('#set_women');
const setTops = document.querySelector('#set_tops');
const setBottom = document.querySelector('#set_bottom');
const setOuter = document.querySelector('#set_outer');
let setValue = '';

setMen.addEventListener('click', function(){
    setValue = 'MEN';
    setMen.style.backgroundColor = '#f5f5f5';
    setWomen.style.backgroundColor = '#ffffff';
    genderSet(setValue);
    unfold();
})
setWomen.addEventListener('click', function(){
    setValue = 'WOMEN';
    setWomen.style.backgroundColor = '#f5f5f5';
    setMen.style.backgroundColor = '#ffffff';
    genderSet(setValue);
    unfold();
})
setTops.addEventListener('click', function(){
    setValue = 'TOPS';
    setTops.style.backgroundColor="#f5f5f5";
    setBottom.style.backgroundColor="#ffffff";
    setOuter.style.backgroundColor="#ffffff";
    categorySet(setValue);
})
setBottom.addEventListener('click', function(){
    setValue = 'BOTTOM';
    setTops.style.backgroundColor="#ffffff";
    setBottom.style.backgroundColor="#f5f5f5";
    setOuter.style.backgroundColor="#ffffff";
    categorySet(setValue);
})
setOuter.addEventListener('click', function(){
    setValue = 'OUTER';
    setTops.style.backgroundColor="#ffffff";
    setBottom.style.backgroundColor="#ffffff";
    setOuter.style.backgroundColor="#f5f5f5";
    categorySet(setValue);
})

const displayCategory = document.querySelector('.display_category');
const emptied = document.querySelector('.emptied');
const genderSpan = document.querySelector('#gender_span');
const categorySpan = document.querySelector('#category_span');
const productCategory = document.getElementsByName('category');
const productGender = document.getElementsByName('product_gender');
const arrow = document.querySelector('#arrow');

function genderSet(val){
    genderSpan.innerHTML = val;
    productGender[0].value = val;
}
function categorySet(val){
    categorySpan.innerHTML = val;
    productCategory[0].value = val;
    arrow.style.display = 'inline';
}
function unfold(){
    displayCategory.style.display = 'block';
    emptied.style.display = 'none';
}