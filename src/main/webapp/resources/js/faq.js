const qestionDiv = document.querySelectorAll('.question');
const answerDiv = document.querySelectorAll('.answer');

for(let i=0; i<qestionDiv.length; i++){
    qestionDiv[i].addEventListener('click', function(){
        answerDiv[i].classList.toggle('answer_display');
        for(let j=0; j<answerDiv.length; j++){
            if(answerDiv[i] != answerDiv[j]){
                answerDiv[j].classList.remove('answer_display');
            }
        }
    })
}