let submitBtn = document.querySelector("#submit");
let loginFix = document.querySelector(".loading_fix")

submitBtn.addEventListener("click", function (){
    submitBtn.style.pointerEvents = "none";
    loginFix.style.display = 'block';
})