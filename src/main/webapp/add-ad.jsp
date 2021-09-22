<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Add ad</title>
</head>
<body>
<div class="container">
    <c:import url="nav-menu.jsp"/>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Добавить объявление
            </div>
            <div class="card-body">
                <form action="<c:url value='add-ad.do'/>" method="post">
                    <div class="form-group">
                        <div>
                            <label for="description-textarea">Описание</label>
                        </div>
                        <div>
                            <textarea id="description-textarea"
                                      name="description" cols="100"
                                      required></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <label for="bodyType">Тип кузова</label>
                        </div>
                        <div>
                            <select id="bodyType" class="form-control"
                                    name="bodyType" required>
                                <c:forEach items="${bodyTypes}" var="bt">
                                    <option value='<c:out value="${bt.id}"/>'>
                                        <c:out value="${bt.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div>
                            <label for="brand">Марка</label>
                        </div>
                        <div>
                            <select id="brand" class="form-control"
                                    name="brand" required>
                                <c:forEach items="${brands}" var="b">
                                    <option value='<c:out value="${b.id}"/>'>
                                        <c:out value="${b.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button id="add-ad-button" class="btn btn-primary">Add
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
