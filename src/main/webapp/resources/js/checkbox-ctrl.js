const checkAll = document.querySelector('input[name="check_all"]');
const checkEach = document.getElementsByName('check_each');

checkAll.addEventListener('click', function(){
    checkEach.forEach(checkbox => checkbox.checked = checkAll.checked);
});


checkEach.forEach(checkbox => {
    checkbox.addEventListener('click', function (){
        for(let i=0; i<checkEach.length; i++) {
            if (checkEach[i].checked == true) {
                if (i == checkEach.length - 1) {
                    checkAll.checked = true;
                }
            } else {
                checkAll.checked = false;
                return;
            }
        }
    });
});
