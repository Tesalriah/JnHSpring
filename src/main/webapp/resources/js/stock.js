const insertHere = document.getElementById('insert_here');
const tbody = document.querySelector('#insert_here tbody');
let cnt = 0;

function removeCell(here){
    cnt--;
    if(cnt == 0){
        document.querySelector('.display_set').style.display = 'table-row';
    }
    tbody.removeChild(here.parentElement.parentElement);
}

document.getElementById('add_stock').addEventListener("click", function(){
    cnt++;
    document.querySelector('.display_set').style.display = 'none';
    let row = insertHere.insertRow();

    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let cell3 = row.insertCell(2);

    cell1.innerHTML = '<input type="text" name="size" autocomplete="off">';
    cell2.innerHTML = '<input type="text" name="stock" oninput="Slimit(this);" autocomplete="off">';
    cell3.innerHTML = '<button type="button" class="del_table" onclick="removeCell(this);">삭제</button>'
});