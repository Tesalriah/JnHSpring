<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/add-product.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/product-add.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/category.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/stock.js'/>" defer></script>
        <title>J&H 상품 추가</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/admin.png'/>"/>
                </div>
                <div style="font-family: 'Raleway', sans-serif;">${empty modify ? "Add " : ""}Product${modify == 1 ? " Modify" : ""}</div>
            </div>
            <div class="nav" style="display: block;">
                <div class="content">
                    <form action="" method="post" enctype="multipart/form-data">
                        <div class="table_box">
                            <div class="add_table">
                                <div class="product_name">
                                    <div>상품명</div>
                                    <div><input type="text" name="product_name" autocomplete="off" value="${list[0].product_name}"></div>
                                    <c:if test="${not empty modify}"><input type="hidden" name="product_id" value="${list[0].product_id}">  </c:if>
                                </div>
                                <div class="category">
                                    <div>카테고리</div>
                                    <div>
                                        <div id="category_menu">
                                            <div>
                                                <div class="set_gender" id="set_men" style="${list[0].gender eq "MEN" ? "background:#f5f5f5;": ""}"><span>MEN</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                                <div id="set_women" style="${list[0].gender eq "WOMEN" ? "background:#f5f5f5;": ""}"><span>WOMEN</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                            </div>
                                            <div class="emptied" style="${not empty list ? "display: none;" : ""}"></div>
                                            <div class="display_category" style="${empty list ? "display: none;" : ""}">
                                                <div class="set_category" id="set_tops" style="${list[0].category eq "TOPS" ? "background:#f5f5f5;": ""}"><span>TOPS</span><span><i class="fa-solid fa-angle-right" style="${list[0].category eq "TOPS" ? "background:#f5f5f5;": ""}"></i></span></div>
                                                <div class="set_category" id="set_bottom" style="${list[0].category eq "BOTTOM" ? "background:#f5f5f5;": ""}"><span>BOTTOM</span><span><i class="fa-solid fa-angle-right" style="${list[0].category eq "BOTTOM" ? "background:#f5f5f5;": ""}"></i></span></div>
                                                <div class="set_category" id="set_outer" style="${list[0].category eq "OUTER" ? "background:#f5f5f5;": ""}"><span>OUTER</span><span><i class="fa-solid fa-angle-right" style="${list[0].category eq "OUTER" ? "background:#f5f5f5;": ""}"></i></span></div>
                                            </div>
                                        </div>
                                        <div class="selected_category">
                                            선택한 카테고리 : <span id="gender_span"></span><span id="arrow" style="display: none;">&nbsp;<i class="fa-solid fa-angle-right"></i>&nbsp;</span><span id="category_span"></span>
                                            <input type="hidden" name="gender" value="${list[0].gender}"><input type="hidden" name="category" value="${list[0].category}">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div>가격</div>
                                    <div style="border-right: 1px solid #dddddd;"><input type="text" name="price" placeholder="(단위:원)" autocomplete="off" value="${list[0].price}"></div>
                                    <div>할인율</div>
                                    <div><input type="text" name="discount" placeholder="(단위:%)" autocomplete="off" value="${list[0].discount}"></div>
                                </div>
                                <div>
                                    <div class="set_height">색상</div>
                                    <div class="set_height" style="border-right: 1px solid #dddddd;"><input type="text" name="color" autocomplete="off" value="${list[0].color}"></div>
                                    <div>이미지</div>
                                    <div id="img_height">
                                        <c:if test="${not empty modify}">
                                            <div id="ex_img" style="margin-bottom: 5px;">
                                                기존이미지 :<br><br>
                                                <img id="product_img" src='<c:url value="/resources/img/upload/product-img/${list[0].product_id}/${list[0].image}"/>' >
                                                <input type="hidden" name="not_change" value="${list[0].image}">
                                            </div>
                                            변경할 파일 선택 :
                                        </c:if>
                                        <input id="image" type="file" name="uploadFile">
                                    </div>
                                </div>
                                <div class="stock">
                                    <div>재고</div>
                                    <div>
                                        <c:choose>
                                            <c:when test="${empty modify}"><button type="button" id="add_stock">추가</button></c:when>
                                            <c:otherwise><div style="margin-bottom:10px;">사이즈와 재고는 추가, 삭제 할 수 없습니다. 변경은 가능합니다.</div></c:otherwise>
                                        </c:choose>
                                        <div>
                                            <table id="insert_here">
                                                <tr style="border-bottom: 2px solid #999999;">
                                                    <td>사이즈</td>
                                                    <td>재고</td>
                                                    <td>삭제</td>
                                                </tr>
                                                <tr class="display_set" style="${not empty list ? "display:none;" : "table-row;"}">
                                                    <td colspan="3" style="height: 50px;">
                                                        데이터가 없습니다.
                                                    </td>
                                                </tr>
                                                <c:forEach items="${list}" var="product">
                                                    <tr>
                                                        <td><input type="text" name="size" autocomplete="off" value="${product.size}"></td>
                                                        <td><input type="text" name="stock" oninput="Slimit(this);" autocomplete="off" value="${product.stock}"}></td>
                                                        <td><c:if test="${empty modify}"><button type="button" class="del_table" onclick="removeCell(this);">삭제</button></c:if> </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="submit_button">
                                <c:choose>
                                    <c:when test="${empty modify}"><button type="submit" id="submit" formaction="<c:url value="/admin/product-add"/>">등록</button></c:when>
                                    <c:otherwise><button type="submit" id="submit" formaction="<c:url value="/admin/product-modify"/>${sc.queryString}">수정</button></c:otherwise>
                                </c:choose>
                                &nbsp;&nbsp;<button type="button" onclick="history.back();">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
    <script>
        window.onload = function(){
            var checkImg = document.querySelector('input[name="not_change"]');
            if(checkImg.value != null){
                document.querySelectorAll('.set_height').forEach(function (element){
                    element.style.height = (document.querySelector('#img_height').clientHeight - 30) + "px";
                    element.style.display = 'flex';
                    element.style.alignItems = 'center';
                    element.style.justifyContent = 'center';
                })
            }
        }
    </script>
</body>
</html>