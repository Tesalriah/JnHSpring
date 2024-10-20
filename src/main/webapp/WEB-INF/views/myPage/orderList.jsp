<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/sideMenu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/orderList.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <div class="left_menu">
                    <div><a href="orderList.html">주문목록</a></div>
                    <div><a href="">취소/반품/교환</a></div>
                    <div><a href="">찜리스트</a></div>
                    <br>
                    <div><a href="">개인정보 확인/수정</a></div>
                    <div><a href="">신고내역확인</a></div>
                    <br>
                    <div><a href="">리뷰관리</a></div>
                    <br>
                    <div><a href="">문의하기</a></div>
                    <div><a href="">문의내역확인</a></div>
                </div>
                <div class="contents">
                    <h2>주문목록</h2>
                    <div class="order_list">
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div><a href="orderDetail.html">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="<c:url value="/resources/img/best.jpg"/>"></div>
                                <div class="order_status">
                                    <div>배송중</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                </div>
                                <div class="order_button">
                                    <div><button type="button">재구매</button></div>
                                    <div><button type="button">교환, 반품신청</button></div>
                                    <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                        </div>
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div><a href="">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="<c:url value="/resources/img/main1.jpg"/>"><img src="<c:url value="/resources/img/weekly.jpg"/>"></div>
                                <div class="order_status">
                                    <div>배송완료</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                    <div>배송중</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                </div>
                                <div class="order_button">
                                    <div><button type="button">재구매</button></div>
                                    <div><button type="button">교환, 반품신청</button></div>
                                    <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                        </div>
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div><a href="">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="<c:url value="/resources/img/women.jpg"/>"></div>
                                <div class="order_status">
                                    <div>배송완료</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                </div>
                                <div class="order_button">
                                    <div><button type="button">재구매</button></div>
                                    <div><button type="button">교환, 반품신청</button></div>
                                    <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                        </div>
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div><a href="">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="<c:url value="/resources/img/men.jpg"/>"></div>
                                <div class="order_status">
                                    <div>배송완료</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                </div>
                                <div class="order_button">
                                    <div><button type="button">재구매</button></div>
                                    <div><button type="button">교환, 반품신청</button></div>
                                    <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                        </div>
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div><a href="">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="<c:url value="/resources/img/weekly.jpg"/>"></div>
                                <div class="order_status">
                                    <div>배송완료</div>
                                    <div><a href="">상품명</a></div>
                                    <div>150,000원 / 1개</div>
                                </div>
                                <div class="order_button">
                                    <div><button type="button">재구매</button></div>
                                    <div><button type="button">교환, 반품신청</button></div>
                                    <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="paging">
                        <a href=""><i class="fa-solid fa-angle-left"></i></a>
                        <a href="">1</a>
                        <a href="">2</a>
                        <a href="">3</a>
                        <a href="">4</a>
                        <a href="">5</a>
                        <a href=""><i class="fa-solid fa-angle-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>