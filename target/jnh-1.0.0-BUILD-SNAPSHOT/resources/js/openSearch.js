const openSearchBtn = document.getElementById('open_search');
const closeSearchBtn = document.getElementById('close_search');

openSearchBtn.addEventListener('click',function(){
    let searchArea = openSearchBtn.nextElementSibling;
    searchArea.style.top = '70px';
    searchArea.style.opacity= '1';
})

closeSearchBtn.addEventListener('click',function(){
    let searchArea = closeSearchBtn.parentElement;
    searchArea.style.opacity= '0';
    setTimeout(function(){
        searchArea.style.top = '-130px';
    },300);
})