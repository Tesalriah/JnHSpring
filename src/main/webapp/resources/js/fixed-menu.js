const header = document.querySelector('header');
    const headerHeight = document.querySelector('header').offsetHeight;
    const main = document.querySelector('main');
    let exCheck = 0;

    window.addEventListener('scroll',function(){
        if(window.scrollY < headerHeight){
            header.style.position = 'relative';
            header.style.top = '0';
            header.style.transition = 'none';
            exCheck=0;
        }
        if(window.scrollY >= headerHeight){
            header.style.position = 'sticky';
            exCheck++;
            if(exCheck == 1){
                header.style.opacity = '0';
                header.style.top = '-65px';
                setTimeout(function(){
                header.style.transition = 'all 0.5s';
                header.style.top = '0px';
                header.style.opacity = '1';
            },200);
            }
        }
    })