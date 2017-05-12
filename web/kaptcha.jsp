<%--
  Created by IntelliJ IDEA.
  User: zhangbaoning
  Date: 2017/5/12
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>谷歌验证码</title>
</head>
<body>
<%--在idea中，如果验证码不出现，将Jar包放到web-inf下面--%>
<form action="result.jsp" method="post">
    <img alt="random" src="randomcode.jpg"/>
    <input name="check_code" type="text">
    <input type="submit" value="提交">
</form>
</body>
</html>
