<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/group-buying.css'/>">
</head>
<body>
    <%@ include file="header.jsp" %>
    <main>
        <div class="container">
            <div class="container">
                <div class="title">
                    <div class="image">
                        <img src="<c:url value="/resources/img/group-buying.jpg"/>">
                    </div>
                    <div style="font-family: 'Raleway', sans-serif; ">Group Buying</div>
                </div>
                <div class="group_buying">
                    <div class="group_top">
                        구매를 희망하시는 상품에 대하여 아래 담당자 메일 또는<br><br>
                        연락처로 문의 후 진행 가능합니다.<br><br>
                    </div>
                    <table>
                        <tr>
                            <td>단체구매 담당자</td>
                            <td>김OO shopjnhmall@gmail.com</td>
                        </tr>
                        <tr>
                            <td>단체구매 진행 프로세스</td>
                            <td>
                                <div class="buying_process">
                                    <div><p>단체 구매</p>
                                        <p>상품/수량 취합</p>
                                    </div>
                                    <div><i class="fa-solid fa-angle-right"></i></div>
                                    <div>
                                        <p>상품 준비</p>
                                        <p style="color:#cccccc;">2~7일 소요</p>
                                    </div>
                                    <div><i class="fa-solid fa-angle-right"></i></div>
                                    <div style="padding:56px 0; background-color: black; color:#ffffff;">
                                        <p>결제 및 출고</p>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>상품</td>
                            <td>
                                <p>ㆍ50벌 이상이고, 100만원 이상인 구매건부터 단체구매로 간주됩니다. 이하 수량/금액의 주문건은 일반 주문으로 진행하셔야 합니다.</p>
                                <p>ㆍ문의 시 진행 일정 및 구매 상품 / 수량에 대해 전달 주셔야 합니다. 품절 옵션은 구매 불가합니다.</p>
                                <p>ㆍ단체구매 시 판매가의 10% 할인 혜택을 드립니다.</p>
                                <p>ㆍ자수 작업은 필요 시 업체 연결 해드리고 있으며, 관련 내용은 업체와 별도 진행 하셔야 합니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <td>결제 및 출고</td>
                            <td>
                                <p>ㆍ상품 준비는 물류 재고 상황에 따라 2~ 7일 소요 될 수 있습니다</p>
                                <p>ㆍ상품이 모두 준비되면 결제 안내를 해드리며, 결제 완료 후 상품이 출고 됩니다. (결제방법 : 카드결제, 무통장입금/ 후불결제 불가능)</p>
                                <p>ㆍ발급 가능 서류는 견적서, 영수증, 세금계산서이며, 이외 서류는 발급 불가합니다.</p>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>
