const upload = document.querySelector("[name='uploadFile']");
const not_change = document.querySelector("[name='not_change']");

upload.addEventListener("input", function (){
    not_change.remove();
});