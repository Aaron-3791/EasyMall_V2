<%--
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
    <title>欢迎注册EasyMall V2</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>
    <script type="text/javascript" src="js/jquery-1.4.2.js">
    </script>


    <script type="text/javascript">
        //文档就绪事件
        //离开焦点事件检查
        $(function () {


            $("#img_click").click(function () {
                $(this).attr("src", "${pageContext.request.contextPath}/ValidImgServlet?Time=" + new Date().getTime());
            });

            $("input[name='username']").blur(function () {
                if (formObj.checkNull("username", "用户名称不能为空")) {
                    //ajax检查用户是否存在
                    var username = $("input[name='username']").val();
                    $("#username_msg").load("${pageContext.request.contextPath}/AjaxCheckUserNameServlet", {"username": username});
                }
            });
            $("input[name='password']").blur(function () {
                formObj.checkNull("password", "密码不能为空");
            });
            $("input[name='password2']").blur(function () {
                formObj.checkNull("password2", "确认密码不能为空");
                formObj.checkPasswd();
            });
            $("input[name='nickname']").blur(function () {
                formObj.checkNull("nickname", "昵称不能为空");
            });
            $("input[name='email']").blur(function () {
                if (formObj.checkNull("email", "邮箱不能为空")) {
                    if (formObj.checkMail()) {
                        var email = $("input[name='email']").val();
                        $("#email_msg").load("${pageContext.request.contextPath}/AjaxCheckEmailServlet", {"email": email});
                    }
                }
            });
            $("input[name='valistr']").blur(function () {


                formObj.checkNull("valistr", "验证码不能为空")
            });
        });

        //前端校验
        var formObj = {
            checkForm: function () {
                var flagNull = true;
                flagNull = this.checkNull("username", "用户名称不能为空！") && flagNull;
                flagNull = this.checkNull("password", "密码不能为空！") && flagNull;
                flagNull = this.checkNull("password2", "确认密码不能为空！") && flagNull;
                flagNull = this.checkNull("nickname", "昵称不能为空！") && flagNull;
                flagNull = this.checkNull("email", "邮箱不能为空！") && flagNull;
                flagNull = this.checkNull("valistr", "验证码不能为空！") && flagNull;
                flagNull = this.checkMail() && flagNull;
                flagNull = this.checkPasswd() && flagNull;

                return flagNull;
            },
            //邮箱格式校验
            checkMail: function () {
                var mailReg = /^\w+@\w+(\.\w+)+$/;
                var val = $("input[name='email']").val();
                if (val != "" && !mailReg.test(val)) {
                    this.setMsg("email", "邮箱格式不正确！");
                    return false;
                }
                return true;
            },

            //密码一致性校验
            checkPasswd: function () {
                var p1 = $("input[name='password']").val();
                var p2 = $("input[name='password2']").val();
                if (p1 != "" && p2 != "" & p1 != p2) {
                    this.setMsg("password2", "两次密码不一致！");
                    return false;
                }
                return true;
            },
            //非空校验
            checkNull: function (name, msg) {
                var val = $("input[name='" + name + "']").val();
                //提示消息清空
                // $("input[name='"+name+"']").nextAll("span").text("");
                this.setMsg(name, "");
                if ($.trim(val) == "") {
                    this.setMsg(name, msg);
                    return false;
                }
                return true;
            },
            //设置span消息
            setMsg: function (name, msg) {
                $("input[name='" + name + "']").nextAll("span").text(msg).css("color", "red");
            }
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/RegistServlet" method="POST" onsubmit="return formObj.checkForm()">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr>
            <td class="tds" colspan="2" style="color: red;text-align: center">
                ${requestScope.msg}
            </td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username"
                value="${param.username}"/>
                <span id="username_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2"/><span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname"
<%--                       value="<%=request.getParameter("nickname")==null?"":request.getParameter("nickname")%>"/><span></span>--%>
                value="${param.nickname}"
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email"
                value="${param.email}"/>
                <span id="email_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr"/>
                <img id="img_click" src="${pageContext.request.contextPath}/ValidImgServlet" width="" height=""
                     alt="验证码生成错误"/><span></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
