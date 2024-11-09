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
        console.log(defaultpage+" "+option+" "+keyword)
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
            showList(page);
        },
        error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
    }); // $.ajax()
}

$(document).ready(function() {
    const strurl = window.location.search;
    function searchParam(urltemp, key){
        return new URLSearchParams(urltemp).get(key);
    }
    option = searchParam(strurl, "option");
    keyword = searchParam(strurl, "keyword");

    showList(defaultpage);
    loadList(defaultpage);

    $("#paging").on("click", ".page", function () {
        let page = $(this).attr("data-page");
        showList(page);
        loadList(page);
    });


})