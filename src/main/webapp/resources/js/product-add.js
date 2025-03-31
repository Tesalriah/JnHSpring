const productPrice = document.getElementsByName('price');
const productDiscount = document.getElementsByName('discount');
const productStock = document.getElementsByName('stock');
let savePrevPrice = 0;
let savePrevStock = 0;
let savePrevDiscount = 0;

for(let i=0; i<productPrice.length; i++){
    productPrice[i].addEventListener('input', function(){
        cantMinus(productPrice[i]);
    })
}

for(let i=0; i<productDiscount.length; i++){
    productDiscount[i].addEventListener('input',function(){
        set100(productDiscount[i]);
    })
}

for(let i=0; i<productStock.length; i++){
    productStock[i].addEventListener('input',function(){
        Slimit(productStock[i]);
    })
}

// 가격, 문자제한
function cantMinus(set){
    if(set.value < 0){
        alert('양수만 입력가능합니다.');
        if(savePrevPrice == null){
            set.value = 0;
        }else{
            set.value = savePrevPrice;
            return false;
        }
    }

    if(set.value > 1000000000){
        alert('가격은 1,000,000,000을 초과 할 수 없습니다.');
        if(savePrevPrice == null){
            set.value = 0;
        }else{
            set.value = savePrevPrice;
            return false;
        }
    }

    let isvalPrice = Number(set.value);
    var str = savePrevPrice.toString();
    str = str.slice(0,-1);
    if( str == isvalPrice){
        return;
    }
    if(isNaN(isvalPrice)){
        set.value = savePrevPrice;
    }else{
        set.value = isvalPrice;
        savePrevPrice = isvalPrice;
    }
}

// 할인율, 문자 제한
function set100(set){
    if(set.value > 90){
        alert('최대 90까지 입력가능합니다.');
        set.value = savePrevDiscount;
    }
    if(set.value < 0){
        alert('최소 0까지 입력가능합니다.');
        set.value = savePrevDiscount;
    }

    let isvalDiscount = Number(set.value);
    var str = savePrevDiscount.toString();
    str = str.slice(0,-1);
    if( str == isvalDiscount){
        return;
    }
    if(isNaN(isvalDiscount)){
        set.value = savePrevDiscount;
    }else{
        set.value = isvalDiscount;
        savePrevDiscount = isvalDiscount;
    }
}

//재고수량, 문자제한
function Slimit(stock){
    if(stock.value > 99999){
        alert('재고수량은 99,999를 초과 할 수 없습니다.')
        stock.value = savePrevStock;
        return;
    }

    let isvalStock = Number(stock.value);
    var str = savePrevStock.toString();
    str = str.slice(0,-1);
    if( str == isvalStock){
        return;
    }
    if(isNaN(isvalStock)){
        stock.value = savePrevStock;
    }else{
        stock.value = isvalStock;
        savePrevStock = isvalStock;
    }
}