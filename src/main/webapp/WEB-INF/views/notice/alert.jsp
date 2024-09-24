<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>alert</title>
</head>
<body>
<script>
    var msg = "<c:out value='${msg}'/>";
    var url = "<c:out value="${url}"/>";
    alert(msg);
    location.href = url;
</script>
</body>
</html>
