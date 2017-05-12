<%@ page import="com.google.code.kaptcha.Constants" %><%--
  Created by IntelliJ IDEA.
  User: zhangbaoning
  Date: 2017/5/12
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    session = request.getSession();
    String kaptcha = (
            String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
    String check_code = request.getParameter("check_code");
    out.println("生成的验证码"+kaptcha);
    out.println("输入的验证码"+check_code);
    if (kaptcha.equals(check_code)){
        out.println("验证成功");
    }else {
        out.println("验证失败");
    }
%>
</body>
</html>
