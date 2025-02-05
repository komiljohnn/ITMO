<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.Model" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lab2</title>

</head>
<body>
<header>
    <h1>Бердибоев Комилжон Фарход угли (373264) P3214 </h1>
</header>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f4f8;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    header {
        font-family: monospace;
        font-size: 18px;
        color: white;
        background-color: #4CAF50;
        padding: 10px;
        text-align: center;
        margin-bottom: 20px;
    }

    .main {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        max-width: 1300px;
        width: 100%;
        padding: 20px;
    }

    .container, .table {
        background-color: #fff;
        border-radius: 6px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        padding: 20px;
        flex: 1;
    }

    .table table {
        width: 100%;
        border-collapse: collapse;
    }

    .table th, .table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    .table th {
        background-color: #4CAF50;
        color: white;
    }

    #clear-storage {
        background-color: #e74c3c;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 6px;
        cursor: pointer;
        margin-top: 20px;
    }

    h1, h2 {
        font-weight: bold;
        margin: 10px 0;
    }

    .form-group {
        margin-bottom: 15px;
    }

    input[type="text"]:focus {
        border-color: #4CAF50;
    }

    input[type="text"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }

    .message-success{
        color:#4CAF50;
        font-weight: bold;
    }

    .message-fail{
        color:#e74c3c;
        font-weight: bold;
    }
</style>
<div class="main">
    <div class="container">
        <form id="form" action="controller" method="GET">
            <div class="form-group">
                <h4>Изменение X:</h4>
                <div class="radio">
                    <label><input type="radio" name="x" value="-5"/> -5</label>
                    <label><input type="radio" name="x" value="-4"/> -4</label>
                    <label><input type="radio" name="x" value="-3"/> -3</label>
                    <label><input type="radio" name="x" value="-2"/> -2</label>
                    <label><input type="radio" name="x" value="-1"/> -1</label>
                    <label><input type="radio" name="x" value="0"/> 0</label>
                    <label><input type="radio" name="x" value="1"/> 1</label>
                    <label><input type="radio" name="x" value="2"/> 2</label>
                    <label><input type="radio" name="x" value="3"/> 3</label>
                </div>
            </div>
            <div class="form-group">
                <h4>Изменение Y:</h4>
                <label><input id="y" type="text" placeholder="{-5 ... 3}"></label>
            </div>
            <div class="form-group">
                <h4>Изменение R:</h4>
                <label><input type="checkbox" name="r" value="1" /> 1</label>
                <label><input type="checkbox" name="r" value="2" /> 2</label>
                <label><input type="checkbox" name="r" value="3" /> 3</label>
                <label><input type="checkbox" name="r" value="4" /> 4</label>
                <label><input type="checkbox" name="r" value="5" /> 5</label>
            </div>

            <div class="form-group">
                <button id="check" type="submit">Проверка</button>
            </div>

        </form>

        <svg id="svgGraph" height="300" width="300">
            <!-- Оси -->
            <line stroke="black" x1="150" x2="150" y1="300" y2="0"></line>
            <text x="160" y="12">Y</text>
            <line stroke="black" x1="0" x2="300" y1="150" y2="150"></line>
            <text x="286" y="135">X</text>

            <!-- Метки интервалов на оси Y -->
            <line stroke="black" x1="147" x2="153" y1="30" y2="30"></line>
            <text x="160" y="35">R</text>
            <line stroke="black" x1="147" x2="153" y1="90" y2="90"></line>
            <text x="160" y="95">R/2</text>
            <line stroke="black" x1="147" x2="153" y1="210" y2="210"></line>
            <text x="160" y="215">-R/2</text>
            <line stroke="black" x1="147" x2="153" y1="270" y2="270"></line>
            <text x="160" y="275">-R</text>

            <!-- Метки интервалов на оси X -->
            <line stroke="black" x1="30" x2="30" y1="147" y2="153"></line>
            <text x="23" y="140">-R</text>
            <line stroke="black" x1="90" x2="90" y1="147" y2="153"></line>
            <text x="83" y="140">-R/2</text>
            <line stroke="black" x1="210" x2="210" y1="147" y2="153"></line>
            <text x="202" y="140">R/2</text>
            <line stroke="black" x1="270" x2="270" y1="147" y2="153"></line>
            <text x="264" y="140">R</text>

            <!-- Стрелки на осях -->
            <line stroke="black" x1="300" x2="290" y1="150" y2="146"></line>
            <line stroke="black" x1="300" x2="290" y1="150" y2="154"></line>
            <line stroke="black" x1="150" x2="146" y1="0" y2="10"></line>
            <line stroke="black" x1="150" x2="154" y1="0" y2="10"></line>

            <!-- Центральная точка -->
            <circle id="point" cx="150" cy="150" r="5" fill="green"></circle>

            <!-- Затененные области -->
            <!-- Прямоугольная область в верхнем левом углу -->
            <polygon fill="dodgerblue" fill-opacity="0.5" points="210,150 210,30 150,30 150,150"></polygon>
            <!-- Треугольная область в нижней правой четверти -->
            <polygon fill="dodgerblue" fill-opacity="0.5" points="150,150 270,150 150,270"></polygon>

            <!-- Сектор окружности в верхней правой четверти -->

            <path fill="dodgerblue" fill-opacity="0.5" d="M150,150 L150,30 A120,120 0 0,0 30,150 Z"></path>

        </svg>
        <p class="message"></p>
    </div>

    <div class="table">
        <table id="tab">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Время запроса</th>
                <th>Время выполнения</th>
            </tr>
            </thead>
            <tbody>
                <%
                    List<Model> models = (List<Model>) application.getAttribute("results");
                    if (models != null) {
                        for (Model model : models) {
                %>
                <tr>
                    <td><%= model.getX() %></td>
                    <td><%= model.getY() %></td>
                    <td><%= model.getR() %></td>
                    <td><%= model.isHit() ? "Попадание" : "Промах" %></td>
                    <td><%= model.getCurrentTime() %></td>
                    <td><%= model.getExecutionTime() %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <%
                    }
                %>
            </tbody>
        </table>

    </div>
    <script src="./script.js"></script>
</div>
</body>
</html>