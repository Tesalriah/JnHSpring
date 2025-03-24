<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/add-product.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/product-mng.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/category.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/stock.js'/>" defer></script>
        <title>J&H 상품 추가</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <script>
            msg = "${msg}";
            if(!!msg){alert(msg);}
        </script>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/admin.png'/>"/>
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Add Produck</div>
            </div>
            <div class="nav" style="display: block;">
                <div class="content">
                    <form action="<c:url value="/add-product"/>" method="post" enctype="multipart/form-data">
                        <div class="table_box">
                            <div class="add_table">
                                <div class="product_name">
                                    <div>상품명</div>
                                    <div><input type="text" name="product_name" autocomplete="off" value="${product_name}"></div>
                                </div>
                                <div class="category">
                                    <div>카테고리</div>
                                    <div>
                                        <div id="category_menu">
                                            <div>
                                                <div id="set_men"><span>MEN</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                                <div id="set_women"><span>WOMEN</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                            </div>
                                            <div class="emptied"></div>
                                            <div class="display_category" style="display: none;">
                                                <div id="set_tops"><span>TOPS</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                                <div id="set_bottom"><span>BOTTOM</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                                <div id="set_outer"><span>OUTER</span><span><i class="fa-solid fa-angle-right"></i></span></div>
                                            </div>
                                        </div>
                                        <div class="selected_category">
                                            선택한 카테고리 : <span id="gender_span"></span><span id="arrow" style="display: none;">&nbsp;<i class="fa-solid fa-angle-right"></i>&nbsp;</span><span id="category_span"></span>
                                            <input type="hidden" name="gender" value="">
                                            <input type="hidden" name="category" value="">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div>가격</div>
                                    <div style="border-right: 1px solid #dddddd;"><input type="text" name="price" placeholder="(단위:원)" autocomplete="off" value="${price}"></div>
                                    <div>할인율</div>
                                    <div><input type="text" name="discount" placeholder="(단위:%)" autocomplete="off" value="${discount}"></div>
                                </div>
                                <div>
                                    <div>색상</div>
                                    <div style="border-right: 1px solid #dddddd;"><input type="text" name="color" autocomplete="off" value="${color}"></div>
                                    <div>이미지</div>
                                    <div>
                                        <input id="image" type="file" name="uploadFile">
                                    </div>
                                </div>
                                <div class="stock">
                                    <div>재고</div>
                                    <div>
                                        <button type="button" id="add_stock">추가</button>
                                        <div>
                                            <table id="insert_here">
                                                <tr style="border-bottom: 2px solid #999999;">
                                                    <td>사이즈</td>
                                                    <td>재고</td>
                                                    <td>삭제</td>
                                                </tr>
                                                <tr class="display_set">
                                                    <td colspan="3" style="height: 50px;">
                                                        데이터가 없습니다.
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="submit_button">
                                <button type="submit" id="submit">등록</button>&nbsp;&nbsp;<button type="button">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>