const contentsOpen = document.querySelectorAll('.contents_open');
const reportContents = document.querySelectorAll('.report_contents');

for(let i=0; i<contentsOpen.length; i++){
    contentsOpen[i].addEventListener('click', function(){
        reportContents[i].classList.toggle('contents_display');
    })
}