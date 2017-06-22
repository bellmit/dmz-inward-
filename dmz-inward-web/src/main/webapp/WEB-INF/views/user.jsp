<%--
  Created by IntelliJ IDEA.
  User: dmz
  Date: 2016/3/30
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--UserName: ${requestScope.User.userID } <br />--%>

<%--Age: ${requestScope.User.userName }--%>

UserName: ${user.userID } <br />

Age: ${user.userName }
</body>
</html>
