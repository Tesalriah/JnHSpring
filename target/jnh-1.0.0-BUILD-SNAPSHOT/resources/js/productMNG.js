const productPrice = document.getElementsByName('product_price');
const productDiscount = document.getElementsByName('product_discount');
const productStock = document.getElementsByName('product_stock');
let savePrevPrice = null;
let savePrevstocktity = null;
let savePrevDiscount = null;


for(let i=0; i<productPrice.length; i++){
    productPrice[i].addEventListener('input', function(){
        cantminus(productPrice[i]);
        savePrevPrice = productPrice[i].value;
    })
}

for(let i=0; i<productDiscount.length; i++){
    productDiscount[i].addEventListener('input',function(){
        set100(productDiscount[i]);
        savePrevDiscount = productDiscount[i].value;
    })
}

for(let i=0; i<productStock.length; i++){
    productStock[i].addEventListener('input',function(){
        Slimit(productStock[i]);
        savePrevstocktity = productStock[i].value;
    })
}
// 가격, 문자제한
function cantminus(set){
    let isval = Number(set.value);

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
        alert('가격은 1000000000을 초과 할 수 없습니다.');
        if(savePrevPrice == null){
            set.value = 0;
        }else{
            set.value = savePrevPrice;
            return false;
        }
    }

    if(isNaN(isval)){
        set.value = savePrevPrice;
        return false;
    }else{
        set.value = isval;
    }

    savePrevPrice = set.value;
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
    let isval = Number(set.value);
    if(isNaN(isval)){
        set.value = savePrevDiscount;
    }else{
        set.value = isval;
    }
}

//재고수량, 문자제한
function Slimit(stock){
    if(stock.value > 99999){
        alert('재고수량은 99999를 초과 할 수 없습니다.')
        stock.value = savePrevstocktity;
        return false;
    }

    let isval = Number(stock.value);
    if(isNaN(isval)){
        stock.value = savePrevstocktity;
    }else{
        stock.value = isval;
    }
}