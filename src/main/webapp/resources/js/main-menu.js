const mouseonImg = document.querySelectorAll(".menu_img");

    for(let i=0; i<mouseonImg.length; i++){
        mouseonImg[i].addEventListener('mouseover',function(){
            mouseonImg[i].style.opacity = '0.5';
            mouseonImg[i].style.cursor = 'pointer';
        })
        mouseonImg[i].addEventListener('mouseout',function(){
            mouseonImg[i].style.opacity = '1';
        })
        
}