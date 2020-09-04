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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
    <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="${pageContext.request.contextPath}/LoginServlet" method="POST">
    <table>
        <%--<%
            Cookie[] cs = request.getCookies();
            Cookie ckTemp = null;
            String username = "";
            if (cs != null) {
                for (Cookie c : cs) {
                    if ("username".equals(c.getName())) {
                        ckTemp = c;
                    }
                }
            }

            if (ckTemp != null) {
                username = URLDecoder.decode(ckTemp.getValue(),"utf-8");
            }
        %>--%>
        <tr>
            <td class="tds" colspan="2" style="color: red;text-align: center">
<%--                <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>--%>
                ${requestScope.msg}
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名：</td>
            <td><input type="text" name="username" value="${cookie["username"].value}"/></td>
        </tr>
        <tr>
            <td class="tdx">密&nbsp;&nbsp; 码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <c:if test="${not empty cookie['username'].value}">
                            checked="checked"
                        </c:if>

                />记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <input type="submit" value="登 陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
