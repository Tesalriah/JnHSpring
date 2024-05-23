<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/addProduct.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/productMNG.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/category.js'/>" defer></script>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/admin.png'/>"/>
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Add Produck</div>
            </div>
            <div class="nav" style="display: block;">
                <div class="content">
                    <form action="<c:url value="/addProduct"/>" method="post">
                        <div class="table_box">
                            <div class="add_table">
                                <div class="product_name">
                                    <div>상품명</div>
                                    <div><input type="text" name="product_name" autocomplete="off"></div>
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
                                            <input type="hidden" name="product_gender" value=""><input type="hidden" name="category" value="">
                                        </div>
                                    </div>
                                </div>
                                <div class="product_price">
                                    <div>가격</div>
                                    <div style="border-right: 1px solid #dddddd;"><input type="text" name="product_price" placeholder="(단위:원)" autocomplete="off"></div>
                                    <div>할인율</div>
                                    <div><input type="text" name="product_discount" placeholder="(단위:%)" autocomplete="off"></div>
                                </div>
                                <div class="product_color">
                                    <div>색상</div>
                                    <div style="border-right: 1px solid #dddddd;"><input type="text" name="color" autocomplete="off"></div>
                                    <div>재고</div>
                                    <div><input type="text" name="product_stock" autocomplete="off"></div>
                                </div>
                                <div class="product_img">
                                    <div>이미지</div>
                                    <div style="width: 30%;"><input type="file"></div>
                                </div>
                            </div>
                            <div class="submit_button">
                                <button type="submit">등록</button>&nbsp;&nbsp;<button type="button">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>