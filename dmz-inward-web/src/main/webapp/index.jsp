<%--
  Created by IntelliJ IDEA.
  User: dmz
  Date: 2016/3/7
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>dmz</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
  </head>
  <body>
  <h1>Please upload a file</h1>
  <form method="post" action="/fileUpload" enctype="multipart/form-data">
    <input type="text" name="name"/>
    <input type="file" name="file"/>
    <input type="submit"/>
  </form>
  <form method="post" action="/LoginFront/commentLogin">
    <div>
      <input type="button" value="我是按钮" onclick="report()" ID="Button1" NAME="Button1">
    </div>
    <input type="submit"/>
    </form>
  <form method="post" action="/LoginFront/login">
    <div class="row">
      <label for="email">邮箱</label> <input type="text" id="email"
                                          name="email" value="geetest@126.com" />
    </div>
    <div class="row">
      <label for="passwd">密码</label> <input type="password" id="passwd"
                                            name="passwd" value="gggggggg" />
    </div>
    <input type="submit"/>
  <%--<script>--%>
    <%--$.ajax({--%>
      <%--// 获取id，challenge，success（是否启用failback）--%>
      <%--url: "/LoginFront/captcha",--%>
      <%--type: "get",--%>
      <%--success: function(data) {--%>
        <%--console.log(data);--%>
      <%--}--%>
    <%--});--%>
  <%--</script>--%>
  <div id="captcha"></div>
  <script src="http://static.geetest.com/static/tools/gt.js"></script>
  <script>
    function report(){
      $.ajax({
        url: "/LoginFront/submitcomments",
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        data: '{"RID":"123"}',
        success: function (data) {

        }
      });
    }


//    var handler = function (captchaObj) {
//      // 将验证码加到id为captcha的元素里
//      captchaObj.appendTo("#captcha");
//      captchaObj.onSuccess(function () {
//          alert("SUCCESS");
//      });
//
//      var a = captchaObj.getValidate();
//      console.log(a);
//    };
//    $.ajax({
//      // 获取id，challenge，success（是否启用failback）
//      url: "/LoginFront/captcha",
//      type: "get",
//      dataType: "json", // 使用jsonp格式
//      success: function (data) {
//        // 使用initGeetest接口
//        // 参数1：配置参数，与创建Geetest实例时接受的参数一致
//        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
//        initGeetest({
//          gt: data.gt,
//          challenge: data.challenge,
//          product: "embed", // 产品形式
//          offline: !data.success
//        }, handler);
//      }
//    });
  </script>
    </form>
  </body>
</html>
