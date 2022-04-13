<%@ page import="com.example.test.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.test.entity.Author" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideBar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findBar.css">
    <title>Поиск книги</title>
</head>
<body>
<form action="" ></form>
<div class="sidenav">
    <a href= "${pageContext.request.contextPath}/clients">Пользователи</a>
    <a href= "${pageContext.request.contextPath}/catalog">Каталог книги</a>
    <a href= "${pageContext.request.contextPath}/authors">Каталог авторов</a>
    <a href= "${pageContext.request.contextPath}/findBook">Поиск книги</a>
    <a href= "${pageContext.request.contextPath}/takeBook">Получить книгу</a>
</div>

<% List<Book> bookList = (List<Book>) request.getSession().getAttribute("bookList"); %>
<% if (bookList == null) { bookList = new ArrayList<>();}%>
<div class="main">
    <div class="box">
        <form class="container-1" method="post" action="${pageContext.request.contextPath}/findBook">
            <span class="icon"><i class="fa fa-search"></i></span>
            <input type="search" id="search" placeholder="Search..." name="searchValue" autocomplete="off"/>
            <input type="submit" value="submit" autocomplete="off"/>
        </form>
    </div>

    <table>
        <tr>
            <th>№ </th>
            <th>Название</th>
            <th>Категория</th>
            <th>Авторы</th>
            <th>Дополнительно</th>
        </tr>
        <% for (Book book: bookList) {%>
        <tr>
            <td><%=book.getId()%></td>
            <td><%=book.getName()%></td>
            <td><%=book.getCategory()%></td>
            <td>
                <%
                    List<Author> authorList = book.getAuthors();
                    if (book.getAuthors() == null) {
                        authorList = new ArrayList<>();
                    }
                %>
                <% for (int i = 0; i < authorList.size(); i++) {%>
                <%=authorList.get(i).getFirstname()%> <%=authorList.get(i).getLastname()%><br>
                <% }%>
            </td>
            <td>
                <button class="changeButton" onclick="updateData(this)">изменить</button>
                <button class="deleteButton">удалить</button>
            </td>
        </tr>
        <% }%>
    </table>
</div>

</body>
</html>
