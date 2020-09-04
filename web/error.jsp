<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Aaron
  Date: 2020-08-12
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
    <title>页面错误了</title>
</head>
<img>
<h1>哦，页面出错了！</h1>
<table align="center">
    <tr>
        <td class="tds" colspan="2" style="color: red;text-align: center">
            <img src="${pageContext.request.contextPath}/img/Error.jpg" align="middle" />
        </td>
    </tr>
</table>
</body>
</html>
