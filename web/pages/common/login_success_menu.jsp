<%--
  Created by IntelliJ IDEA.
  User: Patrick
  Date: 2021/8/6
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书香阁</span>
<%--    <a href="pages/order/order.jsp">我的订单</a>--%>
    <a href="userServlet?action=loginout">注销</a>
    <a href="index.jsp">返回</a>
</div>
