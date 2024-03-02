<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/signUp.css'/>">
    <script type="text/javascript">
        // opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
        //document.domain = "abc.go.kr";

        function goPopup(){
            // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
            var pop = window.open("<c:url value='/jusoPopup'/>","pop","width=570,height=420, scrollbars=yes, resizable=yes");

            // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
            //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
        }
        /** API 서비스 제공항목 확대 (2017.02) **/
        function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
            , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
            // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
            document.form.roadAddrPart1.value = roadAddrPart1;
            document.form.roadAddrPart2.value = roadAddrPart2;
            document.form.addrDetail.value = addrDetail;
            document.form.zipNo.value = zipNo;
        }
    </script>
    <title>회원가입</title>
</head>
<body>
<div class="container">
    <div class="logo"><a href="<c:url value='/'/>">J&H</a></div>
    <form class="user" method="post" action="/signUp">
        <div class="input"><i class="fa-regular fa-id-card"></i><input  type="text" name="id" placeholder="아이디" autocomplete="off"></div>
        <div class="input"><i class="fa-solid fa-lock"></i><input type="password" name="pwd" placeholder="비밀번호"></div>
        <div class="input"><i class="fa-solid fa-check"></i><input type="password" name="pwd_check" placeholder="비밀번호 확인"></div>
        <div class="input"><i class="fa-solid fa-user"></i><input type="text" name="name" placeholder="이름" autocomplete="off"></div>
        <div class="input"><i class="fa-solid fa-envelope"></i><input type="email" name="email" placeholder="이메일" autocomplete="off"></div>
        <div class="input"><i class="fa-solid fa-mobile-screen"></i><input type="text" name="phone" placeholder="휴대폰 번호" autocomplete="off"></div>
        <div class="input"><i class="fa-solid fa-location-dot"></i><input class="address" type="text" name="address" placeholder="주소" readonly><button class="find_address" type=button onclick="goPopup();">주소찾기</button></div>
        <div class="input"><i class="fa-solid fa-venus-mars"></i><select name="gender"><option value="" disabled selected>성별</option><option value="남성">남성</option><option value="여성">여성</option></select></div>
        <div class="input"><i class="fa-solid fa-cake-candles"></i><input type="date" name="birthday" placeholder="생년월일 ex)19990101" autocomplete="off"></div>
        <div class="button_menu">
            <input type="submit" value="가입하기">
        </div>
        <tbody>
        <tr>
            <th>우편번호</th>
            <td>
                <input type="hidden" id="confmKey" name="confmKey" value=""  >
                <input type="text" id="zipNo" name="zipNo" readonly style="width:100px">
                <input type="button"  value="주소검색" onclick="goPopup();">
            </td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><input type="text" id="roadAddrPart1" style="width:85%"></td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td>
                <input type="text" id="addrDetail" style="width:40%" value="">
                <input type="text" id="roadAddrPart2"  style="width:40%" value="">
            </td>
        </tr>
        </tbody>
    </form>
</div>
</body>
</html>
