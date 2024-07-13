const checkboxes = document.querySelectorAll('.delete_check');

function selectAll(selectAll){
    checkboxes.forEach((checkbox) =>{
        checkbox.checked = selectAll.checked;
    })
}

function checkAll(checkAll){
    for(let i=0; i<checkboxes.length; i++){
        if(checkboxes[i].checked == true){
            if(i==checkboxes.length-1){
                document.querySelector('.select_all').checked = true;
            }
        }else{
            document.querySelector('.select_all').checked = false;
            return;
        }
    }
}