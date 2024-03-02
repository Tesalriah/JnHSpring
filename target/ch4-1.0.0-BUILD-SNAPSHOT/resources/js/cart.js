let quantity = document.getElementsByName('quantity');
const q_minus = document.querySelectorAll('.quantity_minus');
const q_plus = document.querySelectorAll('.quantity_plus');

for(let i=0; i<quantity.length; i++){
    quantity[i].addEventListener('input',function(){
        quantity[i].value = quantity[i].value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');

        if(Number(quantity[i].value) > 100){
            quantity[i].value = '100';
        }
        if(Number(quantity[i].value) <= 0){
            quantity[i].value = '1';
        }
    })

    q_minus[i].addEventListener('click',function(){
        let n_quantity = Number(quantity[i].value)-1;
        if(n_quantity >= 1){
            quantity[i].value = String(n_quantity);
        }
    })

    q_plus[i].addEventListener('click',function(){
        let n_quantity = Number(quantity[i].value)+1;
        if(n_quantity <= 100){
            quantity[i].value = String(n_quantity);
        }
    })
}