document.querySelector('.questions_contents').addEventListener('click', function(event){
    if(event.target.classList.contains('answer_btn')){
        document.querySelectorAll('.answer_each').forEach(el => el.remove());
        event.target.scrollIntoView({
            block: 'center'
        });
        var str = ' <div class="answer_each">';
        str += '<form method="post" action='+ document.querySelector('.questions_contents').dataset.url +'>';
        str += '<input type="hidden" name="qno" value="' + event.target.dataset.qno +'">';
        str += '<input type="hidden" name="product_id" value="' + event.target.dataset.product_id +'">';
        str += '<div class="text_area">';
        str += '<textarea name="contents" rows="5" placeholder="답변을 입력해주세요"></textarea><button type="submit" class="write_brn">작성</button>';
        str += '</div>';
        str += '</form>';
        str += '</div>';

        event.target.parentElement.parentElement.insertAdjacentHTML("afterend", str);
    }

    if(event.target.classList.contains('write_brn')){
        if(document.querySelector('[name="contents"]').value.trim() == ''){
            alert('내용을 입력해주세요.');
            event.preventDefault();
            return false;
        }
        if (!confirm("정말 작성하시겠습니까?")) {
            event.preventDefault(); // 제출 취소
        }
    }
});
