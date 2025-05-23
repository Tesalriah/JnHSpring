<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
        <script src="<c:url value="/resources/js/address.js" />"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/user-info.css"/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="contents">
                <h2>회원 정보 확인</h2>
                <form:form modelAttribute="changePwd" method="post">
                    <table class="user_info">
                        <tr>
                            <td>아이디</td>
                            <td>${user.user_id}<input name="user_id" type="hidden" value=""></td>
                        </tr>
                        <tr>
                            <td>이름</td>
                            <td>${user.user_name}<input name="user_name" type="hidden" value=""></td>
                        </tr>
                        <tr>
                            <td>휴대폰 번호</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(user.phone) == 11}">
                                        ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,7)}-${fn:substring(user.phone,7,11)}
                                    </c:when>
                                    <c:otherwise>
                                        ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,6)}-${fn:substring(user.phone,6,10)}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호 변경</td>
                            <td class="edit_password">
                                <div>
                                    <div>현재 비밀번호</div>
                                    <div><input name="user_pwd" type="password"></div>
                                </div>
                                <div>
                                    <div>새 비밀번호</div>
                                    <div><input name="newPwd" type="password"><div class="error"><form:errors path="newPwd"/></div></div>
                                </div>
                                <div>
                                    <div>새 비밀번호 확인</div>
                                    <div><input name="checkNewPwd" type="password"><div class="error"><form:errors path="checkNewPwd"/></div></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>주소</td>
                            <td>
                                <div>
                                    <input type="text" class="address" name="address" value="${user.address}" readonly><button type="button" class="find_address" id="change_address">주소찾기</button>
                                </div>
                                <div style="display:none; margin-top: 15px;"><input type="text" class="address_detail" name="address2" placeholder="상세주소"><button type="submit" id="change_address2" formaction="<c:url value="/mypage/user/change-address"/> ">주소변경</button></div>
                            </td>
                        </tr>
                    </table>
                    <div class="right">회원탈퇴를 원하시면 우측의 회원탈퇴 버튼을 눌러주세요<button id="withdrawal" type="submit" formaction="<c:url value="/mypage/user/del-account"/>">회원탈퇴</button></div>
                    <div class="edit_button">
                        <button type="submit" id="change_pwd" formaction="<c:url value="/mypage/user/change-pwd"/>">수정</button>
                    </div>
                </form:form>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
    <script>
        const withdrawal = document.getElementById('withdrawal');

        withdrawal.addEventListener("click", function(e){
            const isConfirmed = confirm("정말로 탈퇴하시겠습니까?");

            if(!isConfirmed){
                e.preventDefault();
            }
        });
    </script>
</body>
</html>