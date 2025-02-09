<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/terms.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/js/terms.js'/>" defer></script>
    <title>J&H 회원가입</title>
</head>
<body>
<div class="container">
    <div class="logo"><a href="<c:url value='/'/>">J&H</a></div>
    <h4 style="display: flex; margin-top:30px;"><input id='agree_all' class="check_box" type="checkbox" style="margin:10px;"><label for="agree_all">이용약관 동의, 전자 금융거래 이용약관 동의, 개인정보 수집 및 이용 동의에 모두동의합니다.</label></h4>
    <div class="seller_check">
        <input id='agree_1' class="check_box" value="serviceservice" type="checkbox"><label for='agree_1'>&nbsp;이용약관 동의</label>
    </div>
    <div class="terms">
        제 1장 총칙<br>
        제 1 조 (목적)<br>
        이 이용약관은 “J&H (이하 "당 사이트")”에서 제공하는 인터넷 서비스(이하 '서비스')의 가입조건, 당 사이트와 이용자의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다.<br>
        <br>
        제 2 조 (용어의 정의)<br>
        1. "이용자"라 함은 당 사이트에 접속하여 이 약관에 따라 당 사이트가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.<br>
        2. “J&H”에서 제공하는 국가표준, 인증제도, 기술기준, 인증지원 정보를 말합니다.<br>
        3. "회원"이라 함은 서비스를 이용하기 위하여 당 사이트에 개인정보를 제공하여 아이디(ID)와 비밀번호를 부여 받은 자를 말합니다.<br>
        4. “비회원”이하 함은 회원으로 가입하지 않고 " J&H"에서 제공하는 서비스를 이용하는 자를 말합니다.<br>
        5. "회원 아이디(ID)"라 함은 회원의 식별 및 서비스 이용을 위하여 자신이 선정한 문자 및 숫자의 조합을 말합니다.<br>
        6. "비밀번호"라 함은 회원이 자신의 개인정보 및 직접 작성한 비공개 콘텐츠의 보호를 위하여 선정한 문자, 숫자 및 특수문자의 조합을 말합니다.<br>
        <br>
        제 3 조 (이용약관의 효력 및 변경)<br>
        1. 당 사이트는 이 약관의 내용을 회원이 알 수 있도록 당 사이트의 초기 서비스화면에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다.<br>
        2. 당 사이트는 이 약관을 개정할 경우에 적용일자 및 개정사유를 명시하여 현행 약관과 함께 당 사이트의 초기화면 또는 초기화면과의 연결화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 회원에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 당 사이트는 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다.<br>
        3. 당 사이트가 전항에 따라 개정약관을 공지하면서 “개정일자 적용 이전까지 회원이 명시적으로 거부의 의사표시를 하지 않는 경우 회원이 개정약관에 동의한 것으로 봅니다. ”라는 취지를 명확하게 공지하였음에도 회원이 명시적으로 거부의 의사표시를 하지 않은 경우에는 개정약관에 동의한 것으로 봅니다. 회원이 개정약관에 동의하지 않는 경우 당 사이트 이용계약을 해지할 수 있습니다.<br>
        <br>
        제 4 조(약관 외 준칙)<br>
        1. 이 약관은 당 사이트가 제공하는 서비스에 관한 이용안내와 함께 적용됩니다.<br>
        2. 이 약관에 명시되지 아니한 사항은 관계법령의 규정이 적용됩니다.<br>
        <br>
        제 2 장 이용계약의 체결<br>
        제 5 조 (이용계약의 성립 등)<br>
        이용계약은 이용고객이 당 사이트가 정한 약관에 「동의합니다」를 선택하고, 당 사이트가 정한 회원가입양식을 작성하여 서비스 이용을 신청한 후, 당 사이트가 이를 승낙함으로써 성립합니다.<br>
        <br>
        제 6 조 (회원가입)<br>
        서비스를 이용하고자 하는 고객은 당 사이트에서 정한 회원가입양식에 개인정보를 기재하여 가입을 하여야 합니다.<br>
        <br>
        제 7 조 (개인정보의 보호 및 사용)<br>
        당 사이트는 관계법령이 정하는 바에 따라 회원 등록정보를 포함한 회원의 개인정보를 보호하기 위해 노력합니다. 회원 개인정보의 보호 및 사용에 대해서는 관련법령 및 당 사이트의 개인정보 보호정책이 적용됩니다. 다만, 당 사이트 이외에 링크된 사이트에서는 당 사이트의 개인정보 보호정책이 적용되지 않습니다.<br>
        <br>
        제 8 조 (이용 신청의 승낙과 제한)<br>
        1. 당 사이트는 제6조의 규정에 의한 이용신청고객에 대하여 약관에 정하는 바에 따라 서비스 이용을 승낙합니다.<br>
        2. 당 사이트는 아래사항에 해당하는 경우에 대해서 회원가입을 승낙하지 아니하거나 이후 사전 통보 없이 취소할 수 있습니다.<br>
        - 회원가입 신청 시 내용을 허위로 기재한 경우<br>
        - 기타 규정한 제반사항을 위반하며 신청하는 경우<br>
        - 다른 사람의 당 사이트 이용을 방해하거나 그 정보를 도용하는 등의 행위를 하였을 경우<br>
        - 당 사이트를 이용하여 법령과 본 약관이 금지하는 행위를 하는 경우<br>
        <br>
        제 9 조 (회원 아이디 부여 및 변경 등)<br>
        1. 당 사이트는 이용고객에 대하여 약관에 정하는 바에 따라 자신이 선정한 회원 아이디를 부여합니다.<br>
        2. 회원 아이디는 원칙적으로 변경이 불가하며 부득이한 사유로 인하여 변경 하고자 하는 경우에는 해당 아이디를 해지하고 재가입해야 합니다.<br>
        3. 회원은 회원가입 시 기재한 개인정보가 변경되었을 경우 온라인으로 직접 수정할 수 있습니다. 이때 변경하지 않은 정보로 인해 발생되는 문제에 대한 책임은 회원에게 있습니다.<br>
        <br>
        제 3 장 계약 당사자의 의무<br>
        제 10 조 ("J&H"의 의무)<br>
        1. 당 사이트는 이용고객이 희망한 서비스 제공 개시일에 특별한 사정이 없는 한 서비스를 이용할 수 있도록 하여야 합니다.<br>
        2. 당 사이트는 개인정보 보호를 위해 보안시스템을 구축하며 개인정보 보호정책을 공시하고 준수합니다.<br>
        3. 당 사이트는 회원으로부터 제기되는 의견이 합당하다고 판단될 경우에는, 적절한 조치를 취하여야 합니다.<br>
        4. 당 사이트는 전시, 사변, 천재지변, 비상사태, 현재의 기술로는 해결이 불가능한 기술적 결함 기타 불가항력적 사유 및 이용자의 귀책사유로 인하여 발생한 이용자의 손해, 손실, 기타 모든 불이익에 대하여 어떠한 책임도 지지 않습니다.<br>
        <br>
        제 11 조 (회원의 의무)<br>
        1. 이용자는 회원가입 신청 또는 회원정보 변경 시 실명으로 모든 사항을 사실에 근거하여 작성하여야 하며, 허위 또는 타인의 정보를 등록할 경우 일체의 권리를 주장할 수 없습니다.<br>
        2. 당 사이트가 관계법령 및 개인정보 보호정책에 의거하여 그 책임을 지는 경우를 제외하고, 회원에게 부여된 아이디의 비밀번호 관리소홀, 부정사용 등에 의하여 발생하는 모든 결과에 대한 책임은 회원에게 있습니다.<br>
        3. 회원은 당 사이트 및 제 3자의 지식재산권을 침해해서는 안 됩니다.<br>
        4. 이용자는 당 사이트의 운영자, 직원, 기타 관계자를 사칭하는 행위를 하여서는 안 됩니다.<br>
        5. 이용자는 바이러스, 악성코드 등을 제작, 배포, 이용하여서는 아니 되고, 당 사이트의 승인 없이 광고하는 행위를 하여서는 안 됩니다.<br>
        6. 이용자는 당 사이트 및 제 3자의 명예를 훼손하거나 업무를 방해하거나, 외설적이거나, 폭력적이거나 기타 공서양속에 반하는 게시물, 쪽지, 메일 등을 게시, 전송, 배포하여서는 안 됩니다.<br>
        <br>
        제 4 장 서비스의 이용<br>
        제 12 조 (서비스 이용 시간)<br>
        1. 회원의 이용신청을 승낙한 때부터 서비스를 개시합니다. 단, 일부 서비스의 경우에는 지정된 일자부터 서비스를 개시합니다.<br>
        2. 업무상 또는 기술상의 장애로 인하여 서비스를 개시하지 못하는 경우에는 사이트에 공시하거나 회원에게 이를 통지합니다.<br>
        3. 서비스의 이용은 연중무휴, 1일 24시간을 원칙으로 하며, 서비스 응대 및 처리 시간은 법정 근무일 근무시간(09:00~18:00, 법정공휴일 및 주말 제외)으로 합니다. 다만, 당 사이트의 업무상 또는 기술상의 이유로 서비스가 일시 중지 될 수 있습니다. 이러한 경우 당 사이트는 사전 또는 사후에 이를 공지합니다.<br>
        4. 회원으로 가입한 이후라도 일부 서비스 이용 시 서비스 제공자의 요구에 따라 특정회원에게만 서비스를 제공할 수 있습니다.<br>
        5. 서비스를 일정범위로 분할하여 각 범위별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 그 내용을 사전에 공개합니다.<br>
        <br>
        제 13 조 (홈페이지 저작권)<br>
        1. 당 사이트가 게시한 본 홈페이지의 모든 콘텐트에 대한 저작권은 당 사이트에 있습니다. 다만, 게시물의 원저작자가 별도로 있는 경우 그 출처를 명시하며 해당 게시물의 저작권은 원저작자에게 있습니다.<br>
        2. 회원이 직접 게시한 저작물의 저작권은 회원에게 있습니다. 다만, 회원은 당 사이트에 무료로 이용할 수 있는 권리를 허락한 것으로 봅니다.<br>
        3. 당 사이트 소유의 콘텐트에 대하여 제3자가 허락 없이 다른 홈페이지에 사용 또는 인용하는 것을 금지합니다.<br>
        <br>
        제 14 조 (서비스의 변경, 중단)<br>
        1. 당 사이트는 기술상•운영상의 필요에 의해 제공하는 서비스의 일부 또는 전부를 변경하거나 중단할 수 있습니다. 당 사이트의 서비스를 중단하는 경우에는 30일 전에 홈페이지에 이를 공지하되, 다만 사전에 통지할 수 없는 부득이한 사정이 있는 경우는 사후에 통지를 할 수 있습니다.<br>
        2. 제1항의 경우에 당 사이트가 제공하는 서비스의 이용과 관련하여, 당 사이트는 이용자에게 발생한 어떠한 손해에 대해서도 책임을 지지 않습니다. 다만 당 사이트의 고의 또는 중대한 과실로 인하여 발생한 손해의 경우는 제외합니다.<br>
        <br>
        제 5 장 계약 해지 및 이용 제한<br>
        제 15 조 (계약 해지)<br>
        1. 회원은 언제든지 마이페이지 메뉴 등을 통하여 이용계약 해지 신청을 할 수 있으며, 당 사이트는 관련법 등이 정하는 바에 따라 이를 즉시 처리하여야 합니다.<br>
        2. 회원이 계약을 해지할 경우, 관련법령 및 개인정보처리방침에 따라 당 사이트가 회원정보를 보유하는 경우를 제외하고는 해지 즉시 회원의 모든 데이터는 소멸됩니다.<br>
        3. 회원이 계약을 해지하는 경우, 회원이 작성한 게시물(공용게시판에 등록된 게시물 등)은 삭제되지 아니합니다.<br>
        <br>
        제 16 조 (서비스 이용제한)<br>
        1. 당 사이트는 회원이 서비스 이용에 있어서 본 약관 및 관련 법령을 위반하거나, 다음 각 호에 해당하는 경우 서비스 이용을 제한할 수 있습니다.<br>
        - 2년 이상 서비스를 이용한 적이 없는 경우<br>
        - 기타 정상적인 서비스 운영에 방해가 될 경우<br>
        2. 상기 이용제한 규정에 따라 서비스를 이용하는 회원에게 사전 통지 후 서비스 이용을 일시정지 등 제한하거나 이용계약을 해지 할 수 있습니다. 단, 불가피한 사유로 사전 통지가 불가능한 경우에는 그러하지 아니합니다.<br>
        <br>
        제 6 장 손해배상 및 기타사항<br>
        제 17 조 (손해배상)<br>
        당 사이트는 무료로 제공되는 서비스와 관련하여 회원에게 어떠한 손해가 발생하더라도 당 사이트가 고의 또는 과실로 인한 손해발생을 제외하고는 이에 대하여 책임을 부담하지 아니합니다.<br>
        <br>
        제 18 조 (관할 법원)<br>
        서비스 이용으로 발생한 분쟁에 대해 소송이 제기되는 경우 민사 소송법상의 관할 법원에 제기합니다.<br>
        <br>
        제 19 조 (서비스별 이용자 사전 동의 사항과 의무)<br>
        당 사이트에 ‘기술정보를 제공하는 이용자는 자신의 기술정보에 대한 권리(특허권, 실용신안권, 디자인권, 상표권 등)를 법적으로 보호받기 위해서 필요한 조치를 스스로 취하여야 합니다. 당 사이트는 이용자의 권리 보장이나 취득 등을 위한 어떠한 명목의 의무나 책임도 부담하지 않고, 이를 보장하지 않으며, 이용자 개인의 행위(당 사이트의 서비스 이용 행위 일체를 포함)로 인한 어떠한 분쟁이나 어떠한 명목의 손실, 손해에 대해서도 법적 책임을 지지 아니합니다.<br>
        <br>
        제 7 장 " J&H" 게시물 운영정책<br>
        제 20 조 (운영정책)<br>
        "J&H" 각종 게시물에 대한 운영정책은 방송통신심의위원회의 정보통신에 관한 심의규정에 기반하며 이를 위반할 경우, "J&H" 운영정책에 의해 관련 게시물은 예고 없이 삭제, 이동될 수 있으며, 게시자(회원)는 아이디 이용제한 등 "J&H" 이용에 제한을 받을 수 있습니다.<br>
        <br>
        제 21 조 (게시물 등록)<br>
        게시물은 실명을 통해 등록합니다. 악성 글 등을 업로드 하실 경우 게시물 삭제 또는 게시자(회원)의 이용제한 등의 경고 조치가 가능합니다.<br>
        1. 주민등록번호 도용 타인의 개인정보를 이용한 활동이 발견될 경우, 해당 회원은 이용에 제한을 받을 수 있으며, 해당 계정은 본인인증 후에 정상적인 이용이 가능합니다. 또한 여러 개의 아이디를 생성하여 허위신고를 하거나, 타인 사칭을 통한 문제 발생 시, 그에 따른 이용 제한을 받을 수 있습니다.<br>
        <br>
        제 22 조 (게시물의 저작권)<br>
        1. 게시물은 회원이 서비스를 이용하면서 게재한 글, 사진, 파일, 관련 링크 및 댓글 등을 말합니다.<br>
        2. 회원이 서비스에 등록하는 게시물로 인하여 본인 또는 타인에게 손해 및 기타 문제가 발생하는 경우, 회원은 이에 대한 책임을 질수 있으며 또한 명예훼손이나 개인정보 유출, 저작권과 같은 법률에 위배되는 게시물 및 댓글은 관련 법안에 따라 민형사상 처벌을 받을 수 있으니 이 점 유의하여 주시기 바랍니다.<br>
        <br>
        제 23 조 (게시물 제한규정(삭제 및 이동)<br>
        1. 욕설/비속어 및 분란을 조장하는 게시물<br>
        - 욕설 및 비속어가 담겨있거나, 연상시키는 내용<br>
        - 이유 없이 회원 간의 분란을 발생시킬 여지가 있는 내용의 게시물 또는 댓글<br>
        2. 게시판 및 자료실과 관련 없는 게시물<br>
        - 각 주제 분야별로 나뉘어 있는 게시판 및 자료실의 주제와 관련 없는 내용<br>
        3. 상업성 광고 및 홍보 글에 관한 게시물<br>
        4. 개인정보의 유포에 관한 게시물<br>
        - 타인, 혹은 본인의 메일주소/실명/사진/전화번호/주민등록번호 등의 개인정보 또는 해당 정보가 담겨 있는 내용<br>
        5. 확인되지 않은 소문의 유포에 관한 게시물<br>
        - 공개되었을 경우, 당사자의 권리침해가 우려되는 내용<br>
        6. 정치적 견해 차이 및 인종/성별/지역/종교에 대한 차별, 비하하는 게시물<br>
        - 인종/성별/지역/종교에 대한 차별적 발언 또는 비하하는 내용<br>
        - 상이한 정치적 견해를 비하하거나 폄하하는 내용<br>
        7. 타인을 사칭하거나 범죄행위에 관한 게시물<br>
        - 공인이나 특정이슈와 관련한 당사자 또는 지인, 주변인 등을 사칭하여 게시물을 작성하거나, "J&H" 운영자를 사칭하여 작성된 내용<br>
        - 범죄와 관련 있거나 범죄를 유도하는 행위를 포함하는 내용<br>
        8. 저작권 위반에 관한 게시물<br>
        - 기사, 사진, 동영상, 음원, 영상물 등 저작권에 의해 보호받는 콘텐츠와 관련된 내용 뉴스의 경우, 기사 내용의 전부 혹은 일부를 게시하는 경우에는 저작권에 저촉될 수 있기 때문에 링크(URL)만을 허용합니다.<br>
        - 음원, 사진, 동영상 등 해당 자료에 대한 불법공유를 목적으로 작성한 내용 공유를 목적으로 이메일을 수집하는 행위도 동일하게 처리됩니다.<br>
        9. 악성코드/스파이웨어/혐오감 조성에 관한 게시물<br>
        - 악성코드 및 스파이웨어의 유포 및 유도 관련 게시물은 사전경고 없이 제재를 받을 수 있습니다.<br>
        - 시각 및 청각적으로 타인에게 혐오감을 줄 수 있는 게시물은 사전경고 없이 제재를 받을 수 있습니다.<br>
        10. 기타 서비스 운영에 지장을 초래할 수 있는 게시물<br>
        <br>
        제 24 조 (이용제한)<br>
        1. 게시물 제한규정(3조)에 해당하는 내용을 게재하는 경우<br>
        2. 다량의 게시물 등록을 목적으로 의미 없는 제목을 사용하거나, 반복되는 제목을 사용하여 게재하는 경우<br>
        3. 비정상적인 방법으로 게시물을 등록, 조회 및 추천하는 경우 등<br>
        <br>
        제 25 조 (게시중단 요청 서비스)<br>
        다른 회원의 게시물로 인하여 명예훼손, 저작권 침해 등의 피해를 입었을 경우, 운영담당자를 통해 해당 게시물에 대한 게시중단을 요청하실 수 있습니다.<br>
    </div>
    <div class="seller_check">
        <input id="agree_2" class="check_box" type="checkbox"><label for='agree_2'>&nbsp;전자 금융거래 이용약관 동의</label>
    </div>
    <div class="terms">
        제1장 총칙<br>
        제 1조 (목적)<br>
        본 약관은 J&H(이하 "회사"라 합니다)가 제공하는 전자지급결제대행서비스, 선불전자지급수단의 발행 및 관리서비스, 직불전자지급수단의 발행 및 관리서비스, 결제대금예치서비스, 전자고지결제서비스(이하 통칭하여 "전자금융거래서비스"라 합니다)를 "회원"이 이용함에 있어, "회사"와 "회원" 간 권리, 의무 및 "회원"의 서비스 이용절차 등에 관한 사항을 규정하는 것을 그 목적으로 합니다.<br>
        <br>
        제2조 (정의)<br>
        (1) 본 약관에서 정한 용어의 정의는 아래와 같습니다.<br>
        ① "전자금융거래"라 함은 "회사"가 "전자적 장치"를 통하여 전자금융업무를 제공하고, "회원"이 "회사"의 종사자와 직접 대면하거나 의사소통을 하지 아니하고 자동화된 방식으로 이를 이용하는 거래를 말합니다.<br>
        ② "전자지급거래"라 함은 자금을 주는 자(이하 "지급인"이라 합니다)가 "회사"로 하여금 전자지급수단을 이용하여 자금을 받는 자(이하 "수취인"이라 합니다)에게 자금을 이동하게 하는 "전자금융거래"를 말합니다.<br>
        ③ "전자적 장치"라 함은 "전자금융거래" 정보를 전자적 방법으로 전송하거나 처리하는데 이용되는 장치로써 현금자동지급기, 자동입출금기, 지급용단말기, 컴퓨터, 전화기 그 밖에 전자적 방법으로 정보를 전송하거나 처리하는 장치를 말합니다.<br>
        ④ "접근매체"라 함은 "전자금융거래"에 있어서 "거래지시"를 하거나 이용자 및 거래내용의 진실성과 정확성을 확보하기 위하여 사용되는 수단 또는 정보로서 "전자금융거래서비스"를 이용하기 위하여 "회사"에 등록된 아이디 및 비밀번호, 기타 "회사"가 지정한 수단을 말합니다.<br>
        ⑤ "아이디"란 "회원"의 식별과 서비스 이용을 위하여 "회원"이 설정하고 "회사"가 승인한 숫자와 문자의 조합을 말합니다.<br>
        ⑥ "비밀번호"라 함은 "회원"의 동일성 식별과 "회원" 정보의 보호를 위하여 "회원"이 설정하고 "회사"가 승인한 숫자와 문자의 조합을 말합니다.<br>
        ⑦ "회원"이라 함은 본 약관에 동의하고 본 약관에 따라 "회사"가 제공하는 "전자금융거래서비스"를 이용하는 자를 말합니다.<br>
        ⑧ "판매자"라 함은 "전자금융거래서비스"를 통하여 "회원"에게 재화 또는 용역(이하 "재화 등"이라 합니다)을 판매하는 자를 말합니다.<br>
        ⑨ "거래지시"라 함은 "회원"이 본 약관에 따라 "회사"에게 "전자금융거래"의 처리를 지시하는 것을 말합니다.<br>
        ⑩ "오류"라 함은 "회원"의 고의 또는 과실 없이 "전자금융거래"가 본 약관 또는 "회원"의 "거래지시"에 따라 이행되지 아니한 경우를 말합니다.<br>
        (2) 본 조 및 본 약관의 다른 조항에서 정의한 것을 제외하고는 전자금융거래법 등 관련 법령에 정한 바에 따릅니다.<br>
        <br>
        제3조 (약관의 명시 및 변경)<br>
        (1) "회사"는 "회원"이 "전자금융거래"를 하기 전에 본 약관을 서비스 페이지에 게시하고 본 약관의 중요한 내용을 확인할 수 있도록 합니다.<br>
        (2) "회사"는 "회원"의 요청이 있는 경우 전자문서의 전송(이하 전자우편을 이용한 전송을 포함합니다), 모사전송, 우편 또는 직접 교부의 방식에 의하여 본 약관의 사본을 "회원"에게 교부합니다.<br>
        (3) "회사"가 본 약관을 변경하는 때에는 그 시행일 1월 전에 변경되는 약관을 금융거래정보 입력화면 또는 서비스 홈페이지에 게시함으로써 "회원"에게 공지합니다. 다만, 법령의 개정으로 인하여 긴급하게 약관을 변경하는 때에는 변경된 약관을 서비스 홈페이지에 1개월 이상 게시하고 이용자에게 통지합니다.<br>
        (4) "회사"는 제(3)항의 공지나 통지를 할 경우, "이용자가 변경에 동의하지 아니한 경우 공지나 통지를 받은 날로부터 30일 이내에 계약을 해지할 수 있으며, 계약해지의 의사표시를 하지 아니한 경우에는 변경에 동의한 것으로 본다."라는 취지의 내용을 공지합니다.<br>
        <br>
        제4조 (거래내용의 확인)<br>
        (1) "회사"는 서비스 페이지를 통하여 "회원"의 거래내용("회원"의 "오류" 정정 요구사실 및 처리결과에 관한 사항을 포함합니다)을 확인할 수 있도록 하며, "회원"이 거래내용에 대해 서면 교부를 요청하는 경우에는 요청을 받은 날로부터 2주 이내에 모사전송, 우편 또는 직접 교부의 방법으로 거래내용에 관한 서면을 교부합니다.<br>
        (2) "회사"는 제(1)항에 따른 "회원"의 거래내용 서면 교부 요청을 받은 경우 "전자적 장치"의 운영장애, 그 밖의 사유로 거래내용을 제공할 수 없는 때에는 즉시 "회원"에게 전자문서 전송의 방법으로 그러한 사유를 알려야 하며, "전자적 장치"의 운영장애 등의 사유로 거래내용을 제공할 수 없는 기간은 제(1)항의 거래내용에 관한 서면의 교부기간에 산입하지 아니합니다.<br>
        (3) 제(1)항의 대상이 되는 거래내용 중 대상기간이 5년인 것은 다음 각 호와 같습니다.<br>
        ① 거래계좌의 명칭 또는 번호<br>
        ② "전자금융거래"의 종류 및 금액<br>
        ③ "전자금융거래"의 상대방에 관한 정보<br>
        ④ "전자금융거래"의 거래일시<br>
        ⑤ "전자적 장치"의 종류 및 "전자적 장치"를 식별할 수 있는 정보<br>
        ⑥ "회사"가 "전자금융거래"의 대가로 받은 수수료<br>
        ⑦ "회원"의 출금 동의에 관한 사항<br>
        ⑧ "전자금융거래"의 신청 및 조건의 변경에 관한 사항<br>
        (4) 제(1)항의 대상이 되는 거래내용 중 대상기간이 1년인 것은 다음 각 호와 같습니다.<br>
        ① "회원"의 "오류" 정정 요구사실 및 처리결과에 관한 사항<br>
        ② 기타 금융위원회가 고시로 정한 사항<br>
        (5) "회원"이 제(1)항에서 정한 서면 교부를 요청하고자 할 경우 다음의 주소 및 전화번호로 요청할 수 있습니다.<br>
        ① 주소: <br>
        ② 이메일 주소: help@J&H.com<br>
        ③ 전화번호: 000-0000<br>
        <br>
        제5조 ("거래지시"의 철회 등)<br>
        (1) "회원"이 "회사"의 "전자금융거래서비스"를 이용하여 "전자지급거래"를 한 경우, "회원"은 지급의 효력이 발생하기 전까지 본 약관에서 정한 바에 따라 제4조 제⑤항에 기재된 연락처로 전자문서의 전송 또는 서비스 페이지 내 철회에 의한 방법으로 "거래지시"를 철회할 수 있습니다. 단, 각 서비스 별 "거래지시" 철회의 효력 발생시기는 본 약관 제17조, 제27조, 제32조, 제36조, 제38조에서 정한 바에 따릅니다.<br>
        (2) "회원"은 전자지급의 효력이 발생한 경우에 전자상거래 등에서의 소비자보호에 관한 법률 등 관련 법령상 청약 철회의 방법에 따라 결제대금을 반환 받을 수 있습니다.<br>
        <br>
        제6조 (추심이체의 출금 동의 및 철회)<br>
        (1) "회사"는 "회원"의 요청이 있는 경우 "회원"의 계좌가 개설되어 있는 금융회사 등이 추심이체를 실행할 수 있도록 금융회사 등을 대신하여 전자금융거래법령 등 관련 법령에 따른 방법으로 출금에 대한 동의를 진행합니다.<br>
        (2) "회사"는 전 항에 따른 "회원"의 동의 사항을 추심 이체를 실행하는 해당 금융회사 등에 제출합니다.<br>
        (3) "회원"은 "회원"의 계좌의 원장에 출금기록이 끝나기 전까지 "회사" 또는 해당 금융회사 등에 제1항의 규정에 따른 동의의 철회를 요청할 수 있습니다.<br>
        (4) 전 항에도 불구하고 "회사" 또는 금융회사 등은 대량으로 처리하는 거래 또는 예약에 따른 거래 등의 경우에는 미리 "회원"과 정한 약정에 따라 동의의 철회시기를 달리 정할 수 있습니다.<br>
        (5) "회원"이 제3항에 따라 출금 동의 철회를 요청한 경우에도 "회원"은 동의 철회에 대한 의사표시 이전에 발생한 출금에 대해서는 이의를 제기할 수 없습니다.<br>
        <br>
        제7조 ("오류"의 정정 등)<br>
        (1) "회원"은 "전자금융거래서비스"를 이용함에 있어 "오류"가 있음을 안 때에는 "회사"에 대하여 그 정정을 요구할 수 있습니다.<br>
        (2) "회사"는 전 항의 규정에 따른 "오류"의 정정 요구를 받은 때 또는 스스로 "전자금융거래"에 "오류"가 있음을 안 때에는 이를 즉시 조사하여 처리한 후 정정 요구를 받은 날 또는 "오류"가 있음을 안 날부터 2주 이내에 그 결과를 문서, 전화 또는 전자우편으로 "회원"에게 알려 드립니다. 다만, "회원"이 문서로 알려줄 것을 요청하는 경우에는 문서로 알려 드립니다.<br>
        <br>
        제8조 ("전자금융거래" 기록의 생성 및 보존)<br>
        (1) "회사"는 "회원"이 이용한 "전자금융거래"의 내용을 추적, 검색하거나 그 내용에 "오류"가 발생한 경우에 이를 확인하거나 정정할 수 있는 기록을 보존합니다.<br>
        (2) 전 항의 규정에 따라 "회사"가 보존하여야 하는 기록의 종류 및 보존기간은 다음 각 호와 같습니다.<br>
        ① 다음 각 목의 거래기록은 5년간 보존하여야 합니다.<br>
        가. 제4조 제(3)항 제①호 내지 제⑧호에 관한 사항<br>
        나. 해당 "전자금융거래"와 관련한 "전자적 장치"의 접속기록<br>
        다. 건당 거래금액이 1만원을 초과하는 "전자금융거래"에 관한 기록<br>
        ② 다음 각 목의 거래기록은 1년간 보존하여야 합니다.<br>
        가. 제4조 제(4)항 제①호에 관한 사항<br>
        나. 건당 거래금액이 1만원 이하인 "전자금융거래"에 관한 기록<br>
        다. 전자지급수단 이용과 관련된 거래승인에 관한 기록<br>
        라. 기타 금융위원회가 고시로 정한 사항<br>
        <br>
        제9조 ("전자금융거래"정보의 제공금지)<br>
        (1) "회사"는 "전자금융거래서비스"를 제공함에 있어서 취득한 "회원"의 인적 사항, "회원"의 계좌, "접근매체" 및 "전자금융거래"의 내용과 실적에 관한 정보 또는 자료를 법령에 의하거나 "회원"의 동의를 얻지 아니하고 제3자에게 제공, 누설하거나 업무상 목적 외에 사용하지 아니합니다.<br>
        (2) "회사"는 "회원"이 안전하게 "전자금융거래서비스"를 이용할 수 있도록 "회원"의 개인정보보호를 위하여 개인정보처리방침을 운용합니다. "회사"의 개인정보처리방침은 "회사"의 홈페이지 또는 서비스 페이지에 링크된 화면에서 확인할 수 있습니다.<br>
        <br>
        제10조 ("접근매체"의 관리)<br>
        (1) "회사"는 "전자금융거래서비스" 제공시 "접근매체"를 선정하여 "회원"의 신원, 권한 및 "거래지시"의 내용 등을 확인합니다.<br>
        (2) "회원"은 "접근매체"를 사용함에 있어서 다른 법률에 특별한 규정이 없는 한 다음 각 호의 행위를 하여서는 아니됩니다.<br>
        ① "접근매체"를 양도하거나 양수하는 행위<br>
        ② 대가를 수수•요구 또는 약속하면서 "접근매체"를 대여받거나 대여하는 행위 또는 보관•전달•유통하는 행위<br>
        ③ 범죄에 이용할 목적으로 또는 범죄에 이용될 것을 알면서 "접근매체"를 대여받거나 대여하는 행위 또는 보관•전달•유통하는 행위<br>
        ④ "접근매체"를 질권의 목적으로 하는 행위<br>
        ⑤ 제①호부터 제④호까지의 행위를 알선하는 행위<br>
        (3) "회원"은 자신의 "접근매체"를 제3자에게 누설 또는 노출하거나 방치하여서는 안되며, "접근매체"의 도용이나 위조 또는 변조를 방지하기 위하여 충분한 주의를 기울여야 합니다.<br>
        (4) "회사"는 "회원"으로부터 "접근매체"의 분실이나 도난 등의 통지를 받은 때에는 그때부터 제3자가 그 "접근매체"를 사용함으로 인하여 "회원"에게 발생한 손해를 배상할 책임이 있습니다.<br>
        <br>
        제11조 ("회사"의 책임)<br>
        (1) "회사"는 다음 각 호의 어느 하나에 해당하는 사고로 인하여 "회원"에게 손해가 발생한 경우에는 그 손해를 배상할 책임을 집니다.<br>
        ① "접근매체"의 위조나 변조로 발생한 사고(단, "회사"가 "접근매체"의 발급 주체이거나 사용, 관리 주체인 경우로 한정합니다)<br>
        ② 계약체결 또는 "거래지시"의 전자적 전송이나 처리 과정에서 발생한 사고<br>
        ③ 전자금융거래를 위한 "전자적 장치" 또는 정보통신망 이용촉진 및 정보보호 등에 관한 법률 제2조 제1항 제1호에 따른 정보통신망에 침입하여 거짓이나 그 밖의 부정한 방법으로 획득한 "접근매체"의 이용으로 발생한 사고<br>
        (2) "회사"는 제(1)항에도 불구하고 다음 각 호의 어느 하나에 해당하는 경우에는 그 책임의 전부 또는 일부를 "회원"이 부담하게 할 수 있습니다.<br>
        ① "회원"이 "접근매체"를 제3자에게 대여하거나 그 사용을 위임한 경우 또는 양도나 담보의 목적으로 제공한 경우<br>
        ② "회원"이 제3자가 권한 없이 "회원"의 "접근매체"를 이용하여 "전자금융거래"를 할 수 있음을 알았거나 쉽게 알 수 있었음에도 불구하고 자신의 "접근매체"를 누설하거나 노출 또는 방치한 경우<br>
        ③ "회사"가 보안강화를 위하여 "전자금융거래"시 요구하는 추가적인 보안조치를 "회원"이 정당한 사유 없이 거부하여 제(1)항 제③호에 따른 사고가 발생한 경우<br>
        ④ "회원"이 제③호에 따른 추가적인 보안조치에 사용되는 매체•수단 또는 정보에 대하여 누설•노출 또는 방치하거나 제3자에게 대여하거나 그 사용을 위임한 행위 또는 양도나 담보의 목적으로 제공하는 행위를 하여 제(1)항 제③호에 따른 사고가 발생한 경우<br>
        ⑤ 법인(중소기업기본법 제2조 제2항에 의한 소기업을 제외합니다)인 "회원"에게 손해가 발생한 경우로서 "회사"가 사고를 방지하기 위하여 보안절차를 수립하고 이를 철저히 준수하는 등 합리적으로 요구되는 충분한 주의의무를 다한 경우<br>
        (3) "회사"는 컴퓨터 등 정보통신설비의 보수점검, 교체의 사유가 발생하여 "전자금융거래서비스"의 제공을 일시적으로 중단할 경우에는 "회사"의 홈페이지 또는 서비스 페이지를 통하여 "회원"에게 중단 일정 및 중단 사유를 사전에 공지합니다.<br>
        <br>
        제12조 (분쟁처리 및 분쟁조정)<br>
        (1) "회원"은 "회사"의 서비스 페이지 하단에 게시된 분쟁처리 담당자에 대하여 "전자금융거래"와 관련한 의견 및 불만의 제기, 손해배상의 청구 등의 분쟁처리를 요구할 수 있습니다.<br>
        (2) "회원"이 "회사"에 대하여 분쟁처리를 신청한 경우에는 "회사"는 15일 이내에 이에 대한 조사 또는 처리 결과를 "회원"에게 안내합니다.<br>
        (3) "회원"은 "전자금융거래"의 처리에 관하여 이의가 있을 때에는 금융위원회의 설치 등에 관한 법률에 따른 금융감독원의 금융분쟁조정위원회 또는 소비자기본법에 따른 한국소비자원의 소비자분쟁조정위원회에 분쟁조정을 신청할 수 있습니다.<br>
        <br>
        제13조 ("회사"의 안정성 확보 의무)<br>
        "회사"는 "전자금융거래"가 안전하게 처리될 수 있도록 선량한 관리자로서의 주의를 다하며, "전자금융거래"의 안전성과 신뢰성을 확보할 수 있도록 "전자금융거래"의 종류 별로 전자적 전송이나 처리를 위한 인력, 시설, "전자적 장치" 등의 정보기술부문 및 전자금융업무에 관하여 금융위원회가 정하는 기준을 준수합니다.<br>
        <br>
        제14조 (약관 외 준칙)<br>
        (1) "회사"와 "회원" 사이에 개별적으로 합의한 사항이 이 약관에 정한 사항과 다를 때에는 그 합의사항을 이 약관에 우선하여 적용합니다.<br>
        (2) 본 약관에서 정하지 아니한 사항에 대하여는 J&H 서비스약관, J&H 회원약관, 이 밖에 개별약관에서 정한 바에 따릅니다.<br>
        (3) 본 약관 및 전 항의 약관에서 정하지 아니한 사항(용어의 정의를 포함합니다)에 대하여는 전자금융거래법, 전자상거래 등에서의 소비자보호에 관한 법률, 여신전문금융업법 등 소비자보호 관련 법령 및 개별 약관에서 정한 바에 따릅니다.<br>
        <br>
        제15조 (관할)<br>
        "회사"와 "회원" 간에 발생한 분쟁에 관한 관할은 민사소송법에서 정한 바에 따릅니다.<br>
        <br>
        제2장 전자지급결제대행서비스<br>
        제16조 (정의)<br>
        본 장에서 사용하는 용어의 정의는 다음과 같습니다.<br>
        ① "전자지급결제대행서비스"라 함은 전자적 방법으로 재화 등의 구매에 있어서 지급결제정보를 송신하거나 수신하는 것 또는 그 대가의 정산을 대행하거나 매개하는 서비스를 말합니다.<br>
        제17조 ("거래지시"의 철회)<br>
        (1) "회원"이 "전자지급결제대행서비스"를 이용한 경우, "회원"은 "거래지시"된 금액의 정보에 대하여 수취인의 계좌가 개설되어 있는 금융회사 또는 "회사"의 계좌의 원장에 입금기록이 끝나거나 "전자적 장치"에 입력이 끝나기 전까지 "거래지시"를 철회할 수 있습니다.<br>
        (2) "회사"는 "회원"의 "거래지시"의 철회에 따라 지급거래가 이루어지지 않은 경우 수령한 자금을 "회원"에게 반환하여야 합니다.<br>
        <br>
        제18조 (한도 등)<br>
        "회사"의 정책 및 결제업체(이동통신사, 카드사 등)의 기준에 따라 "회원"의 결제수단별 월 누적 결제액 및 결제한도가 제한될 수 있습니다.<br>
        <br>
        제3장 선불전자지급수단의 발행 및 관리서비스<br>
        제19조 (정의)<br>
        본 장에서 사용하는 용어의 정의는 다음과 같습니다.<br>
        <br>
        (1) "선불전자지급수단"이라 함은 J&H 포인트 등 "회사"가 발행 당시 미리 "회원"에게 공지한 전자금융거래법상 선불전자지급수단을 말합니다.<br>
        (2) "유상 선불전자지급수단"이라 함은 "선불전자지급수단" 중 "회원"이 "회사"에 직접 대가를 지급하고 구매하여 "회원"이 완전한 소유권, 처분권 등을 보유한 충전 포인트 말합니다.<br>
        (3) "무상 선불전자지급수단"이라 함은 "선불전자지급수단" 중 "유상 선불전자지급수단" 이외의 것을 말합니다.<br>
        <br>
        제20조 (적용 범위)<br>
        "회원"이 "회사"가 발행한 "선불전자지급수단"으로 다른 형태의 상품권[공정거래위원회의 신유형 상품권 표준약관 제2조 제1항 각 호의 유형(전자형, 모바일, 온라인 상품권)이 아닌 것에 한함]을 구매한 경우 해당 상품권의 사용 및 환불 등에 관해서는 "회사"의 명시적인 표시가 없는 한 이 약관의 적용을 받지 않습니다.<br>
        <br>
        제21조 ("접근매체"의 관리)<br>
        (1) "회사"는 "선불전자지급수단"의 구매나 사용 등이 가능하도록 설정된 유·무형의 카드로서 "회사"가 승인한 고유한 카드번호가 기재되어 있는 J&H카드 등의 "접근매체"를 발급할 수 있습니다.<br>
        (2) "회사"는 "회원"으로부터 "접근매체"의 분실 또는 도난 등의 통지를 받기 전에 발생하는 "선불전자지급수단"에 저장된 금액에 대한 손해에 대하여는 책임지지 않습니다.<br>
        <br>
        제22조 (발행)<br>
        "회사"는 "선불전자지급수단"의 발행 시 소요되는 제반비용을 부담합니다. 다만, "무상 선불전자지급수단"은 본 조의 적용을 받지 않습니다.<br>
        <br>
        제23조("선불전자지급수단"의 보유)<br>
        "회원"은 "회사"가 정한 방법과 절차에 따라 "유상 선불전자지급수단"을 충전하거나, "무상 선불전자지급수단"을 서비스 등에서의 활동으로 적립 받는 등의 방법으로 "선불전자지급수단"을 보유할 수 있으며, "회사"는 이에 관한 구체적인 사항을 본 약관 또는 "선불전자지급수단" 관련 서비스 페이지를 통해 공지합니다.<br>
        <br>
        제24조 ("선불전자지급수단"의 사용 등)<br>
        (1) "회원"은 "회사" 또는 "회사"의 가맹점에서 "선불전자지급수단"을 사용할 수 있으며, 이에 관한 구체적인 사항을 본 약관 또는 "선불전자지급수단" 관련 서비스 페이지를 통해 공지합니다.<br>
        (2) "회원"은 잔액 범위 내에서 사용 횟수에 제한 없이 "선불전자지급수단"을 사용 할 수 있으며, 사용 시 사용 금액만큼 "선불전자지급수단"이 차감됩니다.<br>
        <br>
        제25조 ("선불전자지급수단"의 양도 등)<br>
        (1) "회원"은 "회사"가 공지한 방법과 절차에 따라 "선불전자지급수단"을 양도할 수 있습니다. 다만 "무상 선불전자지급수단"의 경우 "회사"가 양도를 제한할 수 있습니다.<br>
        (2) "회원"이 전 항에 따라 "선불전자지급수단"을 양도한 뒤에 전기통신금융사기(보이스피싱 등)를 이유로 "회사"에 피해구제를 신청한 경우 "회사"는 "선불전자지급수단"을 양도받은 "회원"의 "선불전자지급수단" 사용, 환급 등을 제한할 수 있습니다.<br>
        (3) "회사"는 전 항과 관련하여 "회원"에게 발생한 손해에 대하여는 책임지지 않습니다.<br>
        <br>
        제26조 (환급 등)<br>
        (1) "회원"은 "선불전자지급수단" 구매 후 "회사"에 청약 철회 또는 환불을 신청할 수 있으며, 이 경우 "회사"는 "선불전자지급수단"에 기록된 잔액을 "회원"에게 지급합니다. 다만, "무상 선불전자지급수단"의 경우 환불 대상에서 제외됩니다.<br>
        (2) "회사"는 "회원"이 "선불전자지급수단"의 구매 청약 철회 또는 환불을 신청할 경우 구매 금액 또는 잔액을 지급하기 위하여 필요한 비용을 수수료로 청구할 수 있으며, 이 경우 수수료를 차감 후 잔액을 지급할 수 있습니다.<br>
        (3) "회사"는 제2항에 의한 수수료가 "회원"이 유상으로 구매한 "선불전자지급수단"에 기록된 잔액보다 많을 경우에는 "회원"의 환불청구를 제한 할 수 있습니다.<br>
        (4) 다음 각 호의 어느 하나에 해당하는 사유로 인하여 "회원"이 "선불전자지급수단" 잔액의 환급을 요청 하는 경우 "회사"는 "회원"의 "선불전자지급수단" 등을 회수하고 잔액 전부를 지급합니다. 이 경우 "회사"는 "회원"에게 환급 수수료를 청구 하지 않습니다.<br>
        ① 천재지변 등의 사유로 가맹점이 물품 또는 용역을 제공하기가 곤란하여 "선불전자 지급수단"의 사용이 불가한 경우<br>
        ② "선불전자지급수단"의 결함으로 가맹점이 재화 또는 용역을 제공하지 못하게 된 경우<br>
        (5) "회사"가 "선불전자지급수단"의 소멸시효를 5년보다 짧게 정한 경우, "회원"은 소멸시효 완성 후 "선불전자지급수단" 충전일로부터 5년이 경과하기 전까지 "회사"에게 "선불전자지급수단"의 미사용 부분에 대한 반환을 청구할 수 있으며, "회사"는 잔액의 90%를 "회원"에게 지급합니다. 다만, "무상 선불전자지급수단"은 본 항의 적용을 받지 않습니다.<br>
        (6) "회사"에 환불을 요청할 수 있는 권리는 "선불전자지급수단"의 최종 소지자가 보유합니다. 다만, "선불전자지급수단" 최종 소지자가 "회사"에 환불을 요청할 수 없는 경우에 한하여 "선불전자지급수단" 구매자가 "회사"에 환불을 요청할 수 있으며 "회사"가 구매자에게 환불한 경우 "회사"는 환불에 관한 책임을 면합니다.<br>
        <br>
        제27조 ("거래지시"의 철회)<br>
        (1) "회원"이 "선불전자지급수단"을 이용하여 자금을 지급하는 경우, "회원"은 "거래지시"된 금액의 정보가 수취인이 지정한 "전자적 장치"에 도달하기 전까지 "거래지시"를 철회할 수 있습니다.<br>
        (2) "회사"는 "회원"의 "거래지시"의 철회에 따라 지급거래가 이루어지지 않은 경우 수령한 자금을 "회원"에게 반환하여야 합니다.<br>
        <br>
        제28조 ("선불전자지급수단"의 한도 등)<br>
        (1) "회사"는 "회원"의 "선불전자지급수단" 보유 한도를 관련 법령 및 "회사"의 정책에 따라 정할 수 있으며, "회원"은 "회사"가 정한 그 보유 한도 내에서만 "선불전자지급수단"을 보유할 수 있습니다.<br>
        (2) "회사"는 서비스 페이지 등을 통하여 전 항의 최대 보유 한도를 공지합니다.<br>
        <br>
        제29조 (소멸시효 등)<br>
        (1) "회사"는 "회원"에 대하여 "선불전자지급수단"에 대한 소멸시효를 설정할 수 있으며, "회원"은 "회사"가 정한 소멸시효 완성 전 까지만 "선불전자지급수단"을 사용할 수 있습니다.<br>
        (2) "회사"는 서비스 페이지 등을 통하여 전 항의 소멸시효 설정 여부 및 그 기간을 공지합니다. 단, "회사"가 소멸시효를 달리 정하지 않은 경우, 소멸시효는 5년으로 합니다.<br>
        (3) "회사"가 소멸시효를 5년보다 짧게 정한 경우, "회원"은 "회사"에게 소멸시효 완성 전까지 소멸시효의 연장을 요청할 수 있고, 요청을 받은 "회사"는 특별한 사유가 없는 한 "선불전자지급수단" 충전일로부터 5년 내에서 소멸시효를 3개월 단위로 연장합니다. 다만, "무상 선불전자지급수단"은 본 항의 적용을 받지 않습니다.<br>
        (4) "회사"는 소멸시효 완성 30일전 통지를 포함하여 3회 이상 "회원"에게 소멸시효 완성 도래, 소멸시효의 연장 가능여부와 방법, 소멸시효 완성 후 잔액의 90%를 반환받을 수 있다는 내용 등을 이메일 또는 문자메세지 등의 방법으로 통지합니다. 다만, "무상 선불전자지급수단"은 본 항의 적용을 받지 않습니다.<br>
        (5) "회사"가 유상으로 발행한 "선불전자지급수단"의 경우 충전일로부터 5년간 사용하지 않으면 소멸되며, "무상 선불전자지급수단"은 서비스 페이지 등을 통해 별도 공지합니다.<br>
        <br>
        제30조 (선불전자지급수단 충전금의 관리 및 관련 공시)<br>
        (1) "회사"는 "회원"의 "선불전자지급수단" 충전금(이하 "선불충전금"이라 합니다)을 "회사"의 고유재산과 구분하여 외부 금융기관에 신탁하거나 지급보증보험에 가입합니다.<br>
        (2) "회사"는 매 영업일 마다 "선불충전금" 총액과 신탁금 등 실제 운용중인 자금 총액의 상호 일치 여부를 점검하며, 매 분기말(분기 종료 후 10일 이내) "선불충전금" 규모 및 신탁내역, 지급보증보험 가입여부, 부보 금액 등을 홈페이지(www.J&H.com) 등에 공시합니다.<br>
        (3) "회사"는 다음 각 호의 어느 하나에 해당하는 경우 "선불충전금"을 신탁회사 및 보험회사 등을 통하여 "회원"에게 우선 지급합니다. 이 경우 1개월 이내에 그 사실과 "선불충전금"의 지급시기, 지급장소, 그 밖에 "선불충전금"의 지급과 관련된 사항을 둘 이상의 일간신문에 공고하고, 인터넷 홈페이지 등을 통하여 공시합니다.<br>
        ① 등록이 취소되거나 말소된 경우<br>
        ② 해산 또는 "선불전자지급수단" 발행 및 관리 업무를 폐지한 경우<br>
        ③ 파산선고를 받은 경우<br>
        ④ "선불전자지급수단" 발행 및 관리 업무의 정지명령을 받은 경우<br>
        ⑤ 제1호부터 제4호까지에 준하는 사유가 발생한 경우<br>
        <br>
        제31조 (선불충전금의 신탁 및 지급보증보험)<br>
        (1) "회사"는 "선불충전금" 전부를 신탁하여야 하며, 신탁업자에게 안전자산으로 운용하도록 지시합니다. 다만, "회원"의 간편송금 수요에 대응하기 위하여 전월말 기준 전체 "선불충전금"(본 조 제4항에 따라 지급보증보험에 가입한 금액은 제외)의 십분의 일(1/10)에 해당하는 금액(이하 ‘지급준비금’)까지는 보통예금 등 안전자산 중 수시입출이 가능한 형태로 신탁회사에 예치할 수 있습니다.<br>
        (2) "회사"는 신탁된 "선불충전금"(지급준비금 제외)의 수익자를 "회원"으로 지정합니다. 다만, 개별 "회원"을 수익자로 지정하기 곤란한 경우 "회사"와 이해관계가 없는 특수목적법인(SPC)를 수익자로 지정할 수 있습니다.<br>
        (3) "회사"는 당일 "회원" 및 자금의 변동 내역을 신속하게 익일까지 신탁처리하여야 합니다.<br>
        (4) 제1항에도 불구하고, "회사"가 불가피한 사유로 "선불충전금" 중 일부를 신탁하지 않고 직접 운용(지급준비금 제외)하고자 하는 경우, "회사"는 운용대상 금액 전부에 대하여 "회원"을 피보험자로 하여 지급보증보험에 가입합니다.<br>
        <br>
        제4장 직불전자지급수단의 발행 및 관리서비스<br>
        제32조 (정의)<br>
        본 장에서 사용하는 용어의 정의는 다음과 같습니다.<br>
        (1) "직불전자지급수단"이라 함은 "회원"과 "판매자" 간에 전자적 방법에 따라 금융회사의 계좌에서 자금을 이체하는 방법으로 재화 등의 제공과 그 대가의 지급을 동시에 이행할 수 있는 전자금융거래법상 직불전자지급수단을 말합니다.<br>
        <br>
        제33조 ("거래지시"의 철회)<br>
        (1) "회원"이 "직불전자지급수단"을 이용하여 자금을 지급하는 경우, "회원"은 "거래지시"된 금액의 정보에 대하여 수취인의 계좌가 개설되어 있는 금융회사의 계좌의 원장에 입금기록이 끝나기 전까지 "거래지시"를 철회할 수 있습니다.<br>
        (2) "회사"는 "회원"의 "거래지시"의 철회에 따라 지급거래가 이루어지지 않은 경우 수령한 자금을 "회원"에게 반환하여야 합니다.<br>
        <br>
        제34조 ("직불전자지급수단"의 한도 등)<br>
        (1) "회사"는 "회원"이 "직불전자지급수단"을 이용하여 재화 등을 구매할 수 있는 최대 이용한도(1회, 1일 이용한도 등)를 관련 법령 및 "회사"의 정책에 따라 정할 수 있으며, "회원"은 "회사"가 정한 그 이용한도 내에서만 "직불전자지급수단"을 사용할 수 있습니다.<br>
        (2) "회사"는 서비스 페이지 등을 통하여 전 항의 최대 이용한도를 공지합니다.<br>
        <br>
        제5장 결제대금예치서비스<br>
        제35조 (정의)<br>
        본 장에서 사용하는 용어의 정의는 다음과 같습니다.<br>
        (1) "결제대금예치서비스"라 함은 서비스 페이지에서 이루어지는 "선불식 통신판매"에 있어서, "회사"가 "회원"이 지급하는 결제대금을 예치하고, 배송이 완료되는 등 구매가 확정된 후 재화 등의 대금을 "판매자"에게 지급하는 서비스를 말합니다.<br>
        (2) "선불식 통신판매"라 함은 "회원"이 재화 등을 공급받기 전에 미리 대금의 전부 또는 일부를 지급하는 방식의 통신판매를 말합니다.<br>
        <br>
        제36조 (예치된 결제대금의 지급방법)<br>
        (1) "회원"(그 "회원"의 동의를 얻은 경우에는 재화 등을 공급받을 자를 포함합니다. 이하 제②항 및 제③항에서 동일합니다)은 재화 등을 공급받은 사실을 재화 등을 공급받은 날부터 3영업일 이내에 "회사"에 통보하여야 합니다.<br>
        (2) "회사"는 "회원"으로부터 재화 등을 공급받은 사실을 통보 받을 경우 "회사"가 정한 기일 내에 "판매자"에게 결제대금을 지급합니다.<br>
        (3) "회사"는 "회원"이 재화 등을 공급받은 날부터 3영업일이 지나도록 정당한 사유의 제시 없이 그 공급받은 사실을 "회사"에 통보하지 아니하는 경우에 "회원"의 동의 없이 "판매자"에게 결제대금을 지급할 수 있습니다.<br>
        (4) "회사"가 "판매자"에게 결제대금을 지급하기 전에 "회원"이 그 결제대금을 환급 받을 정당한 사유가 발생한 경우에는 그 결제대금을 "회원"에게 환급합니다.<br>
        <br>
        제 37조("거래지시"의 철회)<br>
        (1) "회원"이 "결제대금예치서비스"를 이용한 경우, "회원"은 "거래지시"된 금액의 정보""에 대하여 수취인의 계좌가 개설되어 있는 금융회사 또는 "회사"의 계좌의 원장에 입금기록이 끝나거나, "전자적 장치"에 입력이 끝나기 전까지 "거래지시"를 철회할 수 있습니다.<br>
        (2) "회사"는 "회원"의 "거래지시"의 철회에 따라 지급거래가 이루어지지 않은 경우 수령한 자금을 "회원"에게 반환하여야 합니다.<br>
        제6장 전자고지결제서비스<br>
        <br>
        제38조 (정의)<br>
        본 장에서 사용하는 용어의 정의는 다음과 같습니다.<br>
        (1) "전자고지결제서비스"라 함은 국세, 지방세, 공공요금, 각종 지로요금 등의 지급과 관련하여 "청구서"등의 전자적인 방법으로 자금 내역을 고지하고, 이를 수수하여 그 정산을 대행하는 업무를 제공하는 시스템 및 서비스 일체를 말합니다.<br>
        (2) "청구서"라 함은 "회사"가 수취인을 대행하여 지급인에게 전송하는 전자적인 방식의 고지방법을 말합니다.<br>
        제39조 ("거래지시"의 철회)<br>
        (1) "회원"이 "전자고지결제서비스"를 이용한 경우, "회원"은 "거래지시"된 금액의 정보에 대하여 수취인의 계좌가 개설되어 있는 금융회사 또는 "회사"의 계좌의 원장에 입금기록이 끝나거나 "전자적 장치"에 입력이 끝나기 전까지 "거래지시"를 철회할 수 있습니다.<br>
        (2) "회사"는 "회원"의 "거래지시"의 철회에 따라 지급거래가 이루어지지 않은 경우 수령한 자금을 "회원"에게 반환하여야 합니다.<br>
        <br>
        제7장 착오송금<br>
        제40조 (착오송금에 관한 사항)<br>
        (1) "회원"이 착오로 수취금융회사, 수취계좌번호 등을 잘못 기재하거나 입력하여 수취인에게 선불전자지급수단 등의 자금이 이동(이하 착오송금이라 합니다)된 경우, "회사"에 통지하여 "회사" 또는 수취 금융회사 등을 통해 수취인에게 연락하여 착오 송금액 반환을 요청할 수 있습니다.<br>
        (2) "회사"는 수취인에 대한 연락 사실, 수취인의 반환의사 유무, 수취인이 반환의사가 없는 경우 그 사유 등 "회원" 요청사항에 대한 처리결과 또는 관련 처리 진행상황을 "회원"이 전 항의 착오송금 발생사실을 "회사"에 통지한 날로부터 15일 이내에 "회원"에게 알립니다.<br>
        (3) "회사" 또는 수취 금융회사를 통한 착오송금 반환 요청에도 수취인이 반환하지 않는 경우, "회원"은 ｢예금자보호법｣ 제5장(착오송금 반환지원)에 따라 예금보험공사에 착오송금 반환지원 제도 이용을 신청할 수 있습니다(개정 ｢예금자보호법｣ 시행일인 ’21.7.6. 이후 발생한 착오송금에 대해 신청 가능). 단, 연락처를 통한 송금, SNS회원간 송금 거래 등 예금보험공사가 수취인의 실지명의를 취득할 수 없는 거래는 반환지원 신청이 제한됩니다.<br>
        (4) "회사"는 예금보험공사가 착오송금 반환지원 업무의 원활한 수행을 위해 "회사"에 착오송금 수취인의 반환불가 사유, 실지명의, 주소 및 연락처, 착오송금 발생 현황 등의 자료를 요청하는 경우 정당한 사유가 없으면 이에 따릅니다.<br>
        (5) "회원"이 예금보험공사를 통해 착오송금 반환지원을 신청한 내역이 다음 각 호에 해당하는 경우 관련 법령에 따라 예금보험공사의 지원 절차가 중단될 수 있습니다.<br>
        ① "회원"이 거짓이나 부정한 방법으로 반환지원을 신청한 경우<br>
        ② 착오송금이 아님이 객관적인 자료로 확인되는 경우<br>
        ③ 신청일 이전 반환 지원을 신청한 착오송금과 관련된 소송 등이 진행 중이거나 완료된 경우<br>
        ④ 그 밖에 예금보험위원회가 인정하는 경우<br>
    </div>
    <div class="seller_check">
        <input id='agree_3' type="checkbox"  class="check_box" ><label for='agree_3'>&nbsp;개인정보 수집 및 이용 동의</label>
    </div>
    <div class="terms">
        개인정보보호법에 따라 J&H에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.<br>
        <br>
        1. 수집하는 개인정보<br>
        이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 J&H 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, J&H는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.<br>
        <br>
        회원가입 시점에 J&H가 이용자로부터 수집하는 개인정보는 아래와 같습니다.<br>
        - 회원 가입 시에 ‘아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호’를 필수항목으로 수집합니다. 만약 이용자가 입력하는 생년월일이 만14세 미만 아동일 경우에는 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 휴대전화번호)를 추가로 수집합니다. 그리고 선택항목으로 이메일 주소를 수집합니다.<br>
        - 단체아이디로 회원가입 시 단체아이디, 비밀번호, 단체이름, 이메일주소, 휴대전화번호를 필수항목으로 수집합니다. 그리고 단체 대표자명을 선택항목으로 수집합니다.<br>
        서비스 이용 과정에서 이용자로부터 수집하는 개인정보는 아래와 같습니다.<br>
        - 회원정보 또는 개별 서비스에서 프로필 정보(별명, 프로필 사진)를 설정할 수 있습니다. 회원정보에 별명을 입력하지 않은 경우에는 마스킹 처리된 아이디가 별명으로 자동 입력됩니다.<br>
        <br>
        - J&H 내의 개별 서비스 이용, 이벤트 응모 및 경품 신청 과정에서 해당 서비스의 이용자에 한해 추가 개인정보 수집이 발생할 수 있습니다. 추가로 개인정보를 수집할 경우에는 해당 개인정보 수집 시점에서 이용자에게 ‘수집하는 개인정보 항목, 개인정보의 수집 및 이용목적, 개인정보의 보관기간’에 대해 안내 드리고 동의를 받습니다.<br>
        <br>
        서비스 이용 과정에서 IP 주소, 쿠키, 서비스 이용 기록, 기기정보, 위치정보가 생성되어 수집될 수 있습니다. 또한 이미지 및 음성을 이용한 검색 서비스 등에서 이미지나 음성이 수집될 수 있습니다.<br>
        구체적으로 1) 서비스 이용 과정에서 이용자에 관한 정보를 자동화된 방법으로 생성하여 이를 저장(수집)하거나,<br>
        2) 이용자 기기의 고유한 정보를 원래의 값을 확인하지 못 하도록 안전하게 변환하여 수집합니다. 서비스 이용 과정에서 위치정보가 수집될 수 있으며,<br>
        J&H에서 제공하는 위치기반 서비스에 대해서는 'J&H 위치정보 이용약관'에서 자세하게 규정하고 있습니다.<br>
        이와 같이 수집된 정보는 개인정보와의 연계 여부 등에 따라 개인정보에 해당할 수 있고, 개인정보에 해당하지 않을 수도 있습니다.<br>
        <br>
        2. 수집한 개인정보의 이용<br>
        J&H 및 J&H 관련 제반 서비스(모바일 웹/앱 포함)의 회원관리, 서비스 개발・제공 및 향상, 안전한 인터넷 이용환경 구축 등 아래의 목적으로만 개인정보를 이용합니다.<br>
        <br>
        - 회원 가입 의사의 확인, 연령 확인 및 법정대리인 동의 진행, 이용자 및 법정대리인의 본인 확인, 이용자 식별, 회원탈퇴 의사의 확인 등 회원관리를 위하여 개인정보를 이용합니다.<br>
        - 콘텐츠 등 기존 서비스 제공(광고 포함)에 더하여, 인구통계학적 분석, 서비스 방문 및 이용기록의 분석, 개인정보 및 관심에 기반한 이용자간 관계의 형성, 지인 및 관심사 등에 기반한 맞춤형 서비스 제공 등 신규 서비스 요소의 발굴 및 기존 서비스 개선 등을 위하여 개인정보를 이용합니다.<br>
        - 법령 및 J&H 이용약관을 위반하는 회원에 대한 이용 제한 조치, 부정 이용 행위를 포함하여 서비스의 원활한 운영에 지장을 주는 행위에 대한 방지 및 제재, 계정도용 및 부정거래 방지, 약관 개정 등의 고지사항 전달, 분쟁조정을 위한 기록 보존, 민원처리 등 이용자 보호 및 서비스 운영을 위하여 개인정보를 이용합니다.<br>
        - 유료 서비스 제공에 따르는 본인인증, 구매 및 요금 결제, 상품 및 서비스의 배송을 위하여 개인정보를 이용합니다.<br>
        - 이벤트 정보 및 참여기회 제공, 광고성 정보 제공 등 마케팅 및 프로모션 목적으로 개인정보를 이용합니다.<br>
        - 서비스 이용기록과 접속 빈도 분석, 서비스 이용에 대한 통계, 서비스 분석 및 통계에 따른 맞춤 서비스 제공 및 광고 게재 등에 개인정보를 이용합니다.<br>
        - 보안, 프라이버시, 안전 측면에서 이용자가 안심하고 이용할 수 있는 서비스 이용환경 구축을 위해 개인정보를 이용합니다.<br>
        3. 개인정보의 보관기간<br>
        회사는 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체없이 파기하고 있습니다.<br>
        단, 이용자에게 개인정보 보관기간에 대해 별도의 동의를 얻은 경우, 또는 법령에서 일정 기간 정보보관 의무를 부과하는 경우에는 해당 기간 동안 개인정보를 안전하게 보관합니다.<br>
        <br>
        이용자에게 개인정보 보관기간에 대해 회원가입 시 또는 서비스 가입 시 동의를 얻은 경우는 아래와 같습니다.<br>
        - 부정 가입 및 이용 방지<br>
        부정 이용자의 가입인증 휴대전화번호 또는 DI (만14세 미만의 경우 법정대리인DI) : 탈퇴일로부터 6개월 보관<br>
        탈퇴한 이용자의 휴대전화번호(복호화가 불가능한 일방향 암호화(해시처리)) : 탈퇴일로부터 6개월 보관<br>
        - QR코드 복구 요청 대응<br>
        QR코드 등록 정보:삭제 시점으로부터6개월 보관<br>
        - 스마트플레이스 분쟁 조정 및 고객문의 대응<br>
        휴대전화번호:등록/수정/삭제 요청 시로부터 최대1년<br>
        - J&H 플러스 멤버십 서비스 혜택 중복 제공 방지<br>
        암호화처리(해시처리)한DI :혜택 제공 종료일로부터6개월 보관<br>
        전자상거래 등에서의 소비자 보호에 관한 법률, 전자문서 및 전자거래 기본법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우는 아래와 같습니다. J&H는 이 기간 동안 법령의 규정에 따라 개인정보를 보관하며, 본 정보를 다른 목적으로는 절대 이용하지 않습니다.<br>
        - 전자상거래 등에서 소비자 보호에 관한 법률<br>
        계약 또는 청약철회 등에 관한 기록: 5년 보관<br>
        대금결제 및 재화 등의 공급에 관한 기록: 5년 보관<br>
        소비자의 불만 또는 분쟁처리에 관한 기록: 3년 보관<br>
        - 전자문서 및 전자거래 기본법<br>
        공인전자주소를 통한 전자문서 유통에 관한 기록 : 10년 보관<br>
        - 전자서명 인증 업무<br>
        인증서와 인증 업무에 관한 기록: 인증서 효력 상실일로부터 10년 보관<br>
        - 통신비밀보호법<br>
        로그인 기록: 3개월<br>
        <br>
        참고로 J&H는 ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원의 개인정보를 별도로 분리 보관하여 관리하고 있습니다.<br>
        <br>
        4. 개인정보 수집 및 이용 동의를 거부할 권리<br>
        이용자는 개인정보의 수집 및 이용 동의를 거부할 권리가 있습니다. 회원가입 시 수집하는 최소한의 개인정보, 즉, 필수 항목에 대한 수집 및 이용 동의를 거부하실 경우, 회원가입이 어려울 수 있습니다.<br>
    </div>
    <div class="button_area">
        <button type="button" class="cancel_btn">취소</button>
        <button type="button" class="ok_btn">확인</button>
    </div>
</div>
</body>
</html>