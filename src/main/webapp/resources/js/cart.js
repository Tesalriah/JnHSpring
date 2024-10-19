const price = document.getElementsByName("price");
const quantity = document.getElementsByName("quantity")
const total = document.getElementById("total");
const q_minus = document.querySelectorAll('.quantity_minus');
const q_plus = document.querySelectorAll('.quantity_plus');
const each_price = document.querySelectorAll('.price');
let totalCal = 0;

window.onload = function(){
    for(let i=0; i<quantity.length; i++){
        totalCal += price[i].value * quantity[i].value;
    }
    total.innerText = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
}

for(let i=0; i<quantity.length; i++){
    quantity[i].addEventListener('input',function(){
        quantity[i].value = quantity[i].value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');

        if(Number(quantity[i].value) > 100){
            quantity[i].value = '100';
        }
        if(Number(quantity[i].value) <= 0){
            quantity[i].value = '1';
        }
        totalCal = 0;
        each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        for(let j=0; j<quantity.length; j++){
            totalCal += price[j].value * parseInt(quantity[j].value);
        }
        total.innerText = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
    })

    q_minus[i].addEventListener('click',function(){
        let n_quantity = Number(quantity[i].value)-1;
        if(n_quantity >= 1){
            quantity[i].value = String(n_quantity);
            totalCal -= price[i].value;
            each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            total.innerText = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        }
    })

    q_plus[i].addEventListener('click',function(){
        let n_quantity = Number(quantity[i].value)+1;
        if(n_quantity <= 100){
            quantity[i].value = String(n_quantity);
            totalCal += parseInt(price[i].value);
            each_price[i].innerText = (price[i].value * quantity[i].value).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
            total.innerText = totalCal.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
        }
    })
}