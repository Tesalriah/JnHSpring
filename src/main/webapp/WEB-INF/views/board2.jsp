<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div><div style="text-align:center">
    <script>
        let msg = "${msg}";

        if(msg == "REMOVED") alert("삭제되었거나 없는 게시글입니다.");
        if(msg == "MOD_ERR") alert("게시물 수정에 실패했습니다. 다시 등록해주세요.");
        if(msg == "WRT_ERR") alert("게시물 등록에 실패했습니다. 다시 등록해주세요.");
    </script>
    <h2>게시물 <c:choose>
        <c:when test="${mode.equals('new')}">쓰기</c:when>
        <c:when test="${mode.equals('mod')}">수정</c:when>
        <c:otherwise>읽기</c:otherwise></c:choose></h2>
    <form action="" id="form" name="board">
        <input type="hidden" name="bno" value="${boardDto.bno}"  readonly>
        <input type="text" name="title" value="${boardDto.title}" ${empty mode ? "readonly" : ""}>
        <textarea name="content" id="" cols="30" rows="10" ${empty mode ? "readonly" : ""}>${boardDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn" >글쓰기</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
    </form>
</div>
<script>
    window.onload=function(){
        document.querySelector('#listBtn').addEventListener("click",function(){
            location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
        });

        document.querySelector('#modifyBtn').addEventListener("click",function(){
            let form = document.forms.board;
            let isReadOnly = document.querySelector("input[name=title]").readOnly;


            if(isReadOnly == true){
                document.querySelector("input[name='title']").readOnly = false;
                document.querySelector("textArea").readOnly = false;
                document.querySelector("#modifyBtn").innerHTML = "등록";
                document.querySelector("h2").innerHTML = "게시물 수정";
                return;
            }
            form.method = "post";
            form.action = "<c:url value='/board/modify'/> ";
            form.submit();
        });

        document.querySelector('#removeBtn').addEventListener("click",function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;

            let form = document.forms.board;
            form.method = "post";
            form.action = "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize} ";
            form.submit();
        });

        document.querySelector('#writeBtn').addEventListener("click",function(){
            let form = document.forms.board;
            form.method = "post";
            form.action = "<c:url value='/board/write'/> ";
            form.submit();
        });
    };
</script>
</body>
</html>