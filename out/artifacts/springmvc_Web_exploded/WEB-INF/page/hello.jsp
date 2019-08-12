<%--
  Created by IntelliJ IDEA.
  User: jsl
  Date: 2019/5/29
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${message}</h1>
<h1>${user.userName}</h1>

<h1>request user ${requestScope.user}</h1>
<h1>session user${sessionScope.user}</h1>
</body>
</html>
