//슬라이드를 위한 클론생성
const bannerList = document.querySelector('.move_img');
const banner = bannerList.children;

const cloneFirst = banner[0].cloneNode(true);
const cloneLast =  banner[banner.length-1].cloneNode(true);

// 배너 글
const bannerWriting = document.querySelectorAll('.banner_writing');

//배너 글 애니매이션 초기화
function MoveReady(){
    for(let i=0; i<=bannerWriting.length-1; i++){
        bannerWriting[i].style.transition = 'none';
        bannerWriting[i].style.transform = 'translateY(100px)';
        bannerWriting[i].style.opacity = '0';
    }
}

// 배너 글 애니매이션
function bannerMove(){
    setTimeout(function(){
        for(let i=0; i<=bannerWriting.length-1; i++){
            bannerWriting[i].style.transition = 'all 1s';
            bannerWriting[i].style.transform = 'translateY(0px)';
            bannerWriting[i].style.opacity = '1';
        }
    },500)
}

// 마지막 클론 좌측으로 숨기기
window.onload = function(){
    if(window.innerWidth+15 > 1235){
        bannerList.style.right = ""+window.innerWidth+"px";
    }
    if(window.innerWidth < 1235){
        bannerList.style.right = "1235px";
    }
    bannerList.prepend(cloneLast);
    checkCurcle(set);
    bannerMove();
}


// 창크기에 따른 슬라이드 위치 맞추기
window.onresize = function(){
    if(window.innerWidth+15 > 1235){
        bannerList.style.right = ""+window.innerWidth+"px";
        autoSize = - window.innerWidth * set;
        bannerList.style.transform = 'translate('+autoSize+'px)';
        size = autoSize;
    }
    if(window.innerWidth < 1235){
        bannerList.style.right = "1235px";
        autoSize = - 1235*set;
        bannerList.style.transform = 'translate('+autoSize+'px)';
        size = autoSize;
    }
}

let set = 0;
let size = 0;
const moveImg = document.querySelector('.move_img');
const bannerLeft = document.querySelector('.banner_left');
const bannerRight = document.querySelector('.banner_right');
let moveBanner = setInterval(moveRight,5000);

// 버튼 클릭시 오른쪽으로 슬라이드
bannerRight.addEventListener('click',function(){
    MoveReady();
    clearInterval(moveBanner);
    if(set == 0 ){
        set=null;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            set = 1;
            checkCurcle(set);
        },600)
    }
    else if(set == 1 ){
        set=null;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            set = 2;
            checkCurcle(set);
        },600)
    }
    else if(set == 2 ){
        set=null;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        bannerList.append(cloneFirst);
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            moveImg.style.transition = 'none';
            size = 0;
            moveImg.style.transform = 'translate('+size+'px)';
            setTimeout(function(){
                moveImg.style.transition = 'transform 0.5s';
                cloneFirst.remove();
            },100)
        },530)
        setTimeout(function(){
            set = 0;
            checkCurcle(set);
        },600)
    }
    moveBanner =  setInterval(moveRight,5000);
    bannerMove();
})

// 자동 오른쪽으로 슬라이드
function moveRight(){
    MoveReady();
    clearInterval(moveBanner);
    if(set == 0 ){
        set = 1;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        bannerMove();
    }
    else if(set == 1 ){
        set = 2;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        bannerMove();
    }
    else if(set == 2 ){
        set = 0;
        if(window.innerWidth < 1235){
            size-=1235;
        }else{
            size-=window.innerWidth;
        }
        bannerList.append(cloneFirst);
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            moveImg.style.transition = 'none';
            size = 0;
            moveImg.style.transform = 'translate('+size+'px)';
            setTimeout(function(){
                moveImg.style.transition = 'transform 0.5s';
                cloneFirst.remove();
            },100)
            bannerMove();
        },400)
    }
    checkCurcle(set)
    moveBanner =  setInterval(moveRight,5000);
    bannerMove();
}

// 클릭시 왼쪽으로 슬라이드
bannerLeft.addEventListener('click',function(){
    MoveReady();
    clearInterval(moveBanner);
    if(set == 0 ){
        set = null;
        if(window.innerWidth < 1235){
            size+=1235;
        }else{
            size+=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            moveImg.style.transition = 'none';
            if(window.innerWidth < 1235){
                size= 0 - 1235 - 1235;
            }else{
                size = 0 - window.innerWidth - window.innerWidth;
            }
            moveImg.style.transform = 'translate('+size+'px)';
            setTimeout(function(){
                moveImg.style.transition = 'transform 0.5s';
                cloneFirst.remove();
            },100)
        },530)
        setTimeout(function(){
            set = 2;
            checkCurcle(set)
        },600)
    }
    else if(set == 2 ){
        set = null;
        if(window.innerWidth < 1235){
            size+=1235;
        }else{
            size+=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            set = 1;
            checkCurcle(set)
        },600)
    }
    else if(set == 1 ){
        set = null;
        if(window.innerWidth < 1235){
            size+=1235;
        }else{
            size+=window.innerWidth;
        }
        moveImg.style.transform = 'translate('+size+'px)';
        setTimeout(function(){
            set = 0;
            checkCurcle(set)
        },600)
    }
    bannerMove();
    moveBanner =  setInterval(moveRight,5000);
})


// 슬라이드 공모양 클릭시 해당 슬라이드 이동
document.querySelector('.curcle1').addEventListener('click', function(){
    MoveReady();
    clearInterval(moveBanner);
    set=0;
    size = 0;
    moveImg.style.transform = 'translate('+size+'px)';
    checkCurcle(set);
    moveBanner =  setInterval(moveRight,5000);
    bannerMove();
});
document.querySelector('.curcle2').addEventListener('click', function(){
    MoveReady();
    clearInterval(moveBanner);
    set=1;
    console.log(size);
    if(window.innerWidth < 1235){
        if(size != -1235){
            if(size == 0){
                size -= 1235 + 1235;
            }if(size % -1235 == 0){
                size += 1235;
            }
        }
    }else{
        if(size != -window.innerWidth){
            if(size == 0){
                size -= window.innerWidth + window.innerWidth;
            }if(size % -window.innerWidth == 0){
                size += window.innerWidth;
            }
        }
    }
    moveImg.style.transform = 'translate('+size+'px)';
    checkCurcle(set);
    moveBanner =  setInterval(moveRight,5000);
    bannerMove();
});
document.querySelector('.curcle3').addEventListener('click', function(){
    MoveReady();
    clearInterval(moveBanner);
    set=2;
    if(window.innerWidth < 1235){
        size=0 - 1235 - 1235;
    }else{
        size=0 - window.innerWidth - window.innerWidth;
    }
    moveImg.style.transform = 'translate('+size+'px)';
    checkCurcle(set);
    moveBanner =  setInterval(moveRight,5000);
    bannerMove();
});

//set값 확인후 해당 슬라이드에 맞는 공모양 선택되게하기
function checkCurcle(set){
    if(set==0){
    document.querySelector('#banner_curcle1').style.opacity = '1';
    document.querySelector('#banner_curcle2').style.opacity = '0.5';
    document.querySelector('#banner_curcle3').style.opacity = '0.5';
    }else if(set==1){
    document.querySelector('#banner_curcle1').style.opacity = '0.5';
    document.querySelector('#banner_curcle2').style.opacity = '1';
    document.querySelector('#banner_curcle3').style.opacity = '0.5';
    }else if(set==2){
    document.querySelector('#banner_curcle1').style.opacity = '0.5';
    document.querySelector('#banner_curcle2').style.opacity = '0.5';
    document.querySelector('#banner_curcle3').style.opacity = '1';
    }
}