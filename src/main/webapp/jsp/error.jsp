<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
    <% System.out.println(request.getParameter("errorMessage"));%>
    <h1><%=request.getSession().getAttribute("errorMessage")%></h1>
    <a href="${pageContext.request.contextPath}/clients">Вернуть на страницу клиентов</a>
</body>
</html>
