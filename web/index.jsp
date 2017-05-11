<%--
  Created by IntelliJ IDEA.
  User: zhangbaoning
  Date: 2017/5/9
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script>
        function reload() {
            var date = new Date().getTime();
            //添加时间参数使每个链接不一样，否则图片不会刷新
            document.getElementById("address").src = "imageServlet?" + date;

        }
    </script>
</head>
<body>
<form method="post" action="/checkServlet">
    <input type="text" name="check_code">
    <img id="address" src="imageServlet" alt="验证码">
    <a href=javascript:reload()>看不清楚</a>
    <input type="submit" value="验证">
</form>
</body>
</html>
