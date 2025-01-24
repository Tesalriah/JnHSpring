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
    <script type="text/javascript" src="<c:url value="/resources/js/review-write.js"/>" defer></script>
    <c:if test="${not empty modify}">
        <script type="text/javascript" src="<c:url value="/resources/js/review-modify.js"/>" defer></script>
    </c:if>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/review-write.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>리뷰 ${empty modify ? "작성" : "수정"}</h2>
                    <form action="<c:url value="/mypage/review/write"/>" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="rno" value="${review.rno}">
                        <input type="hidden" name="reg_date" value="<fmt:formatDate value="${review.reg_date}" pattern="yyyy/MM/dd"/>">
                        <div class="write_review">
                            <div class="review_product">
                                <div class="review_product_img">
                                    <img src="<c:url value="/resources/img/upload/product-img/${review.product_id}/${review.order.product.image}"/>">
                                </div>
                                <div class="review_product_detail">
                                    <div class="product_info">${review.order.product.product_name} / ${review.order.color} / ${review.order.size} / ${review.order.quantity}개</div>
                                    <div>
                                        <div class="rating">
                                            <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                            <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                            <input id="rating" name="rating" type="range" value="5" min="1" max="5" step="0.5">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="review_content">
                                <div >내용</div>
                                <div>
                                    <textarea name="contents" rows="8" placeholder="상품에 대한 솔직한 리뷰를 남겨주세요.">${review.contents}</textarea>
                                </div>
                            </div>
                            <div class="review_image">
                                <div>사진</div>
                                <div>
                                    <c:if test="${not empty review.image}">
                                        <div id="ex_img" style="margin-bottom: 5px;">
                                            기존이미지 :<br><br>
                                            <img id="review_img" src='<c:url value="/resources/img/upload/review-img/${review.rno}/${review.image}"/>' >
                                            <input type="hidden" name="not_change" value="${review.image}">
                                        </div>
                                        변경할 파일 선택 :
                                    </c:if>
                                    <input type="file" name="uploadFile">
                                    <c:if test="${not empty review.image}">
                                        (파일 미선택시 기존 이미지 사용)
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="review_button">
                            <button type="submit">등록하기</button><button type="button" onclick="location.href='<c:url value="/mypage/review/${empty modify ? 'able' : 'wrote'}"/>'">취소하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>