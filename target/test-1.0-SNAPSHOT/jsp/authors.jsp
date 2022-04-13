<%@ page import="com.example.test.entity.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.test.entity.Book" %>
<%@ page import="com.example.test.entity.Author" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideBar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/clientScript.js"></script>

    <title>Каталог авторов</title>
    <style >
        #addButton {
            color: white;
            background-color: #0C2A44;
            position: relative;
            left: 50%;
            transform: translate(-50%, 0);
            margin-top: 20px;
            border-radius: 4px;
            width: 200px;
            height: 40px;
            border-color: white;
        }
        #addButton:hover{
            background-color: #06345e;
        }
        #addButton:active{
            background-color: #084176;
        }
    </style>
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



<% List<Author> authorList = (List<Author>) request.getSession().getAttribute("authorList"); %>


<div class="main">
    <table>
        <tr>
            <th>№ </th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Книги</th>
        </tr>
        <% for (Author author: authorList) {%>
        <tr>
            <td><%=author.getId()%></td>
            <td><%=author.getFirstname()%></td>
            <td><%=author.getLastname()%></td>
            <td>
                <button class="changeButton" onclick="updateData(this)" >изменить</button>
                <button class="deleteButton">удалить</button>
            </td>
        </tr>
        <% }%>
    </table>

    <button id="addButton">добавить</button>
    <div class="modal">
        <div class="modal__content">
            <button class="modal__close-button">X</button>
            <!-- Контент модального окна -->
            <h1 class="modal__title">Добавить автора</h1>
            <form action = "${pageContext.request.contextPath}/authors" method="post">
                Имя: <input type = "text" name = "name" autocomplete="off" />
                <br/>
                Фамилия: <input type = "text" name = "secondName" autocomplete="off"/>
                <br/>
                <label>Статус<input type="text" name="status" value="add" readonly/></label>
                <input type = "submit" value = "Submit" class="readyButton" autocomplete="off"/>
            </form>
        </div>
    </div>



    <div class="modal1">
        <div class="modal__content1">
            <button class="modal__close-button1">X</button>
            <!-- Контент модального окна -->
            <h1 class="modal__title1">Изменить данные о пользователе</h1>

            <form>
                <fieldset>
                    <legend>Данные</legend>
                    <label>ID<input type="text" name="id" required autocomplete="off"></label>
                    <label>Имя<input type="text" name = "name" required autocomplete="off"></label>
                    <label>Фамилия<input type="text" name="secondName" required autocomplete="off"></label>
                </fieldset>
                <label>Статус<input type="text" name="status" value="edit" readonly/></label>
                <button class="readyButton1" type="submit" formmethod="post">Готово</button>
            </form>

        </div>
    </div>

    <div class="modal2">
        <div class="modal__content2">
            <button class="modal__close-button2">X</button>
            <!-- Контент модального окна -->
            <h1 class="modal__title2">Удалить данные о книге</h1>
            <form>
                <fieldset>
                    <legend>Данные</legend>
                    ID:  <input type = "text" name = "id" autocomplete="off"/>
                    <label>Статус<input type="text" name="status" value="delete" readonly/></label>
                </fieldset>
                <button class="readyButton2" type="submit" formmethod="post">Готово</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
