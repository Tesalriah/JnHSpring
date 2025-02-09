<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/product-mng.css"/>">
    <title>J&H</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/admin.png"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Admin Page</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>상품관리</h2>
                    <div class="MNG_nav">
                        <div class="nav_each">
                            <a href="">MEN</a>
                            <div class="sub_MNG_nav">
                                <div><a href="">TOPS</a></div>
                                <div><a href="">BOTTOM</a></div>
                                <div><a href="">OUTER</a></div>
                            </div>
                        </div>
                        <div class="nav_each">
                            <a href="">WOMEN</a>
                            <div class="sub_MNG_nav">
                                <div><a href="">TOPS</a></div>
                                <div><a href="">BOTTOM</a></div>
                                <div><a href="">OUTER</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="point">MEN <i class="fa-solid fa-angle-right"></i> TOPS</div>
                    <div class="MNG_box">
                        <table class="MNG_table">
                            <tr>
                                <td style="width: 15%;">품번</td>
                                <td style="width: 15%;">상품명</td>
                                <td style="width: 15%;">가격</td>
                                <td style="width: 15%;">할인율(%)</td>
                                <td style="width: 15%;">재고</td>
                                <td style="width: 8%;">상세수정</td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>6</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>7</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>8</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>9</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>10</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>11</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>12</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>13</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>14</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>15</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>16</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>17</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>18</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>19</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                            <tr>
                                <td>20</td>
                                <td>PRODUCT NAME</td>
                                <td><input type="text" name="product_price" value="160000"><button type="button">OK</button></td>
                                <td><input type="text" name="product_discount" value="30"><button type="button">OK</button></td>
                                <td><input type="text" name="product_stock" value="100"><button type="button">OK</button></td>
                                <td><a href="">이동</a></td>
                            </tr>
                        </table>
                        <div class="paging">
                            <a href=""><i class="fa-solid fa-angle-left"></i></a>
                            <a href="">1</a>
                            <a href="">2</a>
                            <a href="">3</a>
                            <a href="">4</a>
                            <a href="">5</a>
                            <a href="">6</a>
                            <a href="">7</a>
                            <a href="">8</a>
                            <a href="">9</a>
                            <a href="">10</a>
                            <a href=""><i class="fa-solid fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
