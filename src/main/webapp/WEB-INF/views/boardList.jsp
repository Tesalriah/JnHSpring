<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>fastcampus</title>
  <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
      font-family: "Noto Sans KR", sans-serif;
    }

    a {
      text-decoration: none;
      color: black;
    }
    button,
    input {
      border: none;
      outline: none;
    }

    .board-container {
      width: 60%;
      height: 1200px;
      margin: 0 auto;
      /* border: 1px solid black; */
    }
    .search-container {
      background-color: rgb(253, 253, 250);
      width: 100%;
      height: 110px;
      border: 1px solid #ddd;
      margin-top : 10px;
      margin-bottom: 30px;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .search-form {
      height: 37px;
      display: flex;
    }
    .search-option {
      width: 100px;
      height: 100%;
      outline: none;
      margin-right: 5px;
      border: 1px solid #ccc;
      color: gray;
    }

    .search-option > option {
      text-align: center;
    }

    .search-input {
      color: gray;
      background-color: white;
      border: 1px solid #ccc;
      height: 100%;
      width: 300px;
      font-size: 15px;
      padding: 5px 7px;
    }
    .search-input::placeholder {
      color: gray;
    }

    .search-button {
      /* 메뉴바의 검색 버튼 아이콘  */
      width: 20%;
      height: 100%;
      background-color: rgb(22, 22, 22);
      color: rgb(209, 209, 209);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 15px;
    }
    .search-button:hover {
      color: rgb(165, 165, 165);
    }

    table {
      border-collapse: collapse;
      width: 100%;
      border-top: 2px solid rgb(39, 39, 39);
    }

    tr:nth-child(even) {
      background-color: #f0f0f070;
    }

    th,
    td {
      width:300px;
      text-align: center;
      padding: 10px 12px;
      border-bottom: 1px solid #ddd;
    }

    td {
      color: rgb(53, 53, 53);
    }

    .no      { width:150px;}
    .title   { width:50%;  }

    td.title   { text-align: left;  }
    td.writer  { text-align: left;  }
    td.viewcnt { text-align: right; }

    td.title:hover {
      text-decoration: underline;
    }

    .paging {
      color: black;
      width: 100%;
      align-items: center;
    }

    .page {
      color: black;
      padding: 6px;
      margin-right: 10px;
    }
    .paging-active {
      background-color: rgb(216, 216, 216);
      border-radius: 5px;
      color: rgb(24, 24, 24);
    }

    .paging-container {
      width:100%;
      height: 70px;
      display: flex;
      margin-top: 50px;
      margin : auto;
    }
    .btn-write {
      background-color: rgb(236, 236, 236); /* Blue background */
      border: none; /* Remove borders */
      color: black; /* White text */
      padding: 6px 12px; /* Some padding */
      font-size: 16px; /* Set a font size */
      cursor: pointer; /* Mouse pointer on hover */
      border-radius: 5px;
      margin-left: 30px;
    }

    .btn-write:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div id="menu">
  <ul>
    <li id="logo">fastcampus</li>
    <li><a href="<c:url value='/'/>">Home</a></li>
    <li><a href="<c:url value='/board/list'/>">Board</a></li>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
    <li><a href=""><i class="fa fa-search"></i></a></li>
  </ul>
</div>
<script>
  let msg = "${msg}";
  if(msg=="LIST_ERR")  alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
  if(msg=="READ_ERR")  alert("삭제되었거나 없는 게시물입니다.");
  if(msg=="DEL_ERR")   alert("삭제되었거나 없는 게시물입니다.");

  if(msg=="DEL_OK")    alert("성공적으로 삭제되었습니다.");
  if(msg=="WRT_OK")    alert("성공적으로 등록되었습니다.");
  if(msg=="MOD_OK")    alert("성공적으로 수정되었습니다.");
</script>
<div style="text-align:center">
    <div class="board-container">
        <div class="search-container">
            <form action="<c:url value="/board/list"/>" class="search-form" method="get">
              <select class="search-option" name="option">
                <option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : ""}>제목+내용</option>
                <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목만</option>
                <option value="W" ${ph.sc.option=='W' ? "selected" : ""}>작성자</option>
              </select>

              <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
              <input type="submit" class="search-button" value="검색">
            </form>
            <button id="writeBtn" class="btn-write" onclick="location.href='<c:url value="/board/write"/>'"><i class="fa fa-pencil"></i> 글쓰기</button>
        </div>

        <table id="table">

        </table>
        <br>
      <c:if test="${totalCnt==null || totalCnt==0}">
        <div> 게시물이 없습니다. </div>
      </c:if>
      <div class="paging-container">
          <div class="paging" id ="paging"></div>
        </div>
    </div>
</div>
<script type="text/javascript" <%--src="<c:url value='/resources/js/boardList.js'/>"--%>>
    let defaultpage = 1;
    let pageSize = null;

    let option = "";
    let keyword = "";
    let showList = function (page){
        if(option == null){
            option="";
        }
        if(keyword == null){
            keyword = "";
        }
        $.ajax({
            type: 'GET',       // 요청 메서드
            url: '/ch4/board/paging?page='+page/*+"&pageSize="+pageSize*/+'&option='+option+'&keyword='+keyword,  // 요청 URI
            success: function (result) {
                $("#paging").html(toHtml(result));
                defaultpage = result.sc.page;
                pageSize = result.sc.page;
            },
            error: function () {
                alert("error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }

    let toList = function (list){
        let tmp = "<tr>" +
            "<th class='no'>번호</th>" +
            "<th class='title'>제목</th>" +
            "<th class='writer'>이름</th>" +
            "<th class='regdate'>등록일</th>" +
            " <th class='viewcnt'>조회수</th>" +
            "</tr>";


        list.forEach(function(listEach){
            tmp += "<tr>"
            tmp += "<td class='no'>"+listEach.bno+"</td>";
            tmp += "<td class='title'><a href='/ch4/board/read?bno="+listEach.bno+"&page="+defaultpage+"&option="+option+"&keyword="+keyword+"'>" + listEach.title + "</a>";
            tmp += "<td class='writer'>"+listEach.writer+"</td>";
            tmp += "<td class='regdate'>" + listEach.reg_date + "</td>";
            tmp += "<td class='viewcnt'>"+ listEach.view_cnt +"</td>";
            tmp += "</tr>";
        });

        return tmp;
    }

    let toHtml = function (page){
        let tmp = "";

        if(page.showPrev) {
            tmp += "<a class='page' href='javascript:void(0)' data-page=" + (page.beginPage-1) + " id='prev' href='javascript:void(0)'>&lt;</a>";
        }
        for(let i = page.beginPage; i <= page.endPage; i++){
            tmp += "<a href='javascript:void(0)' data-page="
            tmp += i
            tmp += " class='page"
            tmp += (i == page.sc.page) ? " paging-active" : "" ;
            tmp += "'>"+ i + "</a>"
        }
        if(page.showNext)
            tmp += "<a class='page' href='javascript:void(0)' data-page=" + (page.endPage+1) + " id='prev' href='javascript:void(0)'>&gt;</a>";

        return tmp;
    }

    let loadList = function (page){
        $.ajax({
            type:'POST',       // 요청 메서드
            url: '/ch4/board/paging',  // 요청 URI
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({page:page, option:option, keyword:keyword}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(result){
                $("#table").empty();
                $("#table").html(toList(result));
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }

    $(document).ready(function() {
        const strurl = window.location.search;
        function searchParam(urltemp, key){
            return new URLSearchParams(urltemp).get(key);
        }
        if(!(!searchParam(strurl, "page"))){
            defaultpage = searchParam(strurl, "page");
        }
        option = searchParam(strurl, "option");
        keyword = searchParam(strurl, "keyword");

        showList(defaultpage);
        loadList(defaultpage);

        $("#paging").on("click", ".page", function () {
            defaultpage = $(this).attr("data-page");
            loadList(defaultpage);
            showList(defaultpage);
        });


    })
</script>
</body>
</html>