const checkboxes = document.querySelectorAll('.delete_check');
const selectAll = document.querySelector('.select_all');

selectAll.addEventListener('click', function(){
    checkboxes.forEach((checkbox) =>{
        checkbox.checked = selectAll.checked;
    })
})

for(let k=0; k<checkboxes.length; k++){
    checkboxes[k].addEventListener('click',function(){
        for(let i=0; i<checkboxes.length; i++){
            if(checkboxes[i].checked == true){
                if(i==checkboxes.length-1){
                    selectAll.checked = true;
                }
            }else{
                selectAll.checked = false;
                return;
            }
        }
    })
}