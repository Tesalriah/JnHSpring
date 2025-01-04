function deleteConfirm() {
    if (confirm("정말 삭제하시겠습니까?")) {
        // 확인 버튼을 눌렀을 때 실행할 동작
        // alert("삭제되었습니다.");
        return true;
    } else {
        // 취소 버튼을 눌렀을 때 실행할 동작
        alert("삭제가 취소되었습니다.");
        return false;
    }
}