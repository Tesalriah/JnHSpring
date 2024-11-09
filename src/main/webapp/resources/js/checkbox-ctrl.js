const checkAll = document.getElementsByName('check_all');
const checkEach = document.getElementsByName('check_each');

checkAll[0].addEventListener('click', function(){
    console.log(checkAll[0].checked == true)
    if(checkAll[0].checked == true){
        for(let i=0; i<checkEach.length; i++){
            checkEach[i].checked = true;
        }
    }else{
        for(let i=0; i<checkEach.length; i++){
            checkEach[i].checked = false;
        }
    }
})