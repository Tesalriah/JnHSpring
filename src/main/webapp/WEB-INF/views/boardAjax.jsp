<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Title</title>
      <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
  </head>
  <body>
    boardAjax
  </body>
  <script>
      $(document).ready(function(){

          $(".paging").on("click", ".page" ,function(){
              $.ajax({
                  type:'GET',       // 요청 메서드
                  url: '/ch4/board/list?page='+page+"&pageSize="+pageSize+"&option="+option+"&keyword="+keyword,  // 요청 URI
                  headers : { "content-type": "application/json"}, // 요청 헤더
                  success : function(result){

                  },
                  error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
              }); // $.ajax()

              alert("the request is sent")
          });

          let toHtml = function (paging){
              let tmp ="";

              paging.forEach(function(page){
                  tmp += "<a class='page' data-page='"+page.page"'>"
              })
          }

      });
  </script>
</html>
