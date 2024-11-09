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
    <link rel="stylesheet" href="<c:url value="/resources/css/orderDetail.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <%@ include file="leftMenu.jsp" %>
                <div class="contents">
                    <h2>주문상세</h2>
                    <p style="padding-top:20px;">주문번호 : 0001</p>
                    <div class="order_list">
                        <div class="order">
                            <div class="order_top"><div>2024.01.01 주문</div><div></div></div>
                            <div class="order_contents">
                                <div class="order_img"><img src="img/best.jpg"></div>
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
                    </div>
                    <div class="subheading">받는사람 정보</div>
                    <div class="receiver">
                        <table>
                            <tr>
                                <td>받는사람</td>
                                <td>홍길동</td>
                            </tr>
                            <tr>
                                <td>연락처</td>
                                <td>010-0000-0000</td>
                            </tr>
                            <tr>
                                <td>받는주소</td>
                                <td>서울특별시 노원구 상계동 00-000 OO아파트 OOO호</td>
                            </tr>
                            <tr>
                                <td>배송요청사항</td>
                                <td>문 앞에 놔주세요</td>
                            </tr>
                        </table>
                    </div>
                    <div class="subheading">결제정보</div>
                    <div class="payment">
                        <div>
                            <div>
                                <div>결제수단</div>
                                <div>OO카드 / 일시불</div>
                            </div>
                            <div>
                                <div><div>총 상품가격</div><div>150,000원</div></div>
                                <div><div>배송비</div><div>3,000원</div></div>
                            </div>
                        </div>
                        <div>
                            <div></div>
                            <div>
                                <div><div>총 결제금액</div><div>153,000원</div></div>
                            </div>
                        </div>
                    </div>
                    <div class="order_detail_button">
                        <button type="button">주문목록 돌아가기</button>
                        <button type="button">주문내역 삭제</button>
                    </div>
                    <div class="guide">
                        <div>배송상품 주문상태 안내</div>
                        <div>
                            <div>
                                <div>결제완료</div>
                                <div>주문,결제,확인이 완료되었습니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>상품준비중</div>
                                <div>판매자가 발송할 상품을 준비중입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송시작</div>
                                <div>상품준비가 완료되어 곧 배송될 예정입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송중</div>
                                <div>상품이 고객님께 배송중입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송완료</div>
                                <div>상품이 주문자에게 전달완료되었습니다.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>