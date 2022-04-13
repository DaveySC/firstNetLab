<%@ page import="com.example.test.entity.AccountingRecords" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideBar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/findBar.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/clientScript.js"></script>
    <title>Получить книгу</title>
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

<% List<AccountingRecords> accountingRecordsList = (List<AccountingRecords>) request.getSession().getAttribute("accountingRecordsList"); %>

<div class="main">
    <table>
        <tr>
            <th>№ Записи</th>
            <th>№ Клиента</th>
            <th>Имя клиента</th>
            <th>№ Книги</th>
            <th>Название книги</th>
            <th>Дата получения</th>
            <th>Дата возвращения</th>
            <th>Дополнительно</th>
        </tr>
        <% for (AccountingRecords ac: accountingRecordsList) {%>
        <tr>
            <td><%=ac.getId()%></td>
            <td><%=ac.getClient().getId()%></td>
            <td><%=ac.getClient().getFirstName() + " " + ac.getClient().getSecondName()%></td>
            <td><%=ac.getBook().getId()%></td>
            <td><%=ac.getBook().getName()%></td>
            <td><%=ac.getReceiptDate()%></td>
            <td><%=ac.getReturnDate()%></td>
            <td>
                <button class="changeButton" onclick="updateData(this)">изменить</button>
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
            <h1 class="modal__title">Добавить запись</h1>
            <form action = "${pageContext.request.contextPath}/takeBook" method="post">
                Id клиента <input type = "text" name = "clientId" autocomplete="off"/>
                <br />
                Id книги <input type = "text" name = "bookId" autocomplete="off"/>
                <br/>
                Дата возврата <input type="date" name="returnDate" autocomplete="off">
                <br/>
                Status: <input type="text" name="status" value="add" readonly>
                <input type = "submit" value = "Submit" class="readyButton" autocomplete="off"/>
            </form>
        </div>
    </div>



    <div class="modal1">
        <div class="modal__content1">
            <button class="modal__close-button1">X</button>
            <!-- Контент модального окна -->
            <h1 class="modal__title1">Изменить данные о записи</h1>
            <form>
                <fieldset>
                    <legend>Данные</legend>
                    ID:  <input type = "text" name = "id" autocomplete="off"/>
                    <label>Id клиента<input type="text" name="clientId" required autocomplete="off"></label>
                    <label>Id книги<input required autocomplete="off" name="bookId" ></label>
                    <label>Дата возвращения <input type="date" name="returnDate" autocomplete="off" required></label>
                    <label>Статус<input type="text" name="status" value="edit" readonly autocomplete="off"/></label>
                </fieldset>
                <button class="readyButton1" type="submit" formmethod="post">Готово</button>
            </form>

        </div>
    </div>


    <div class="modal2">
        <div class="modal__content2">
            <button class="modal__close-button2">X</button>
            <!-- Контент модального окна -->
            <h1 class="modal__title2">Удалить данные</h1>
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
