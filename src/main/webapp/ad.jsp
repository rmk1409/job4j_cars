<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Ad</title>
</head>
<body>
<div class="container">
    <c:import url="nav-menu.jsp"/>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Объявления
            </div>
            <div class="card-body">
                <table id="ad-table" class="table">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Sold</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Photo</th>
                        <th>Add photo</th>
                        <th>Body type</th>
                        <th>Brand</th>
                        <th>Author</th>
                    </tr>
                    </thead>
                    <tdoby>
                        <c:forEach var="ad" items="${advs}">
                            <tr>
                                <td><c:out value="${ad.id}"/></td>
                                <td>
                                    <input type="checkbox" name="sold"
                                           data-id="<c:out value='${ad.id}'/>"
                                           <c:if test="${ad.isSold}">checked</c:if>
                                           <c:if test="${user.name != ad.author.name}">disabled</c:if>
                                    />
                                </td>
                                <td><c:out value="${ad.created}"/></td>
                                <td><c:out value="${ad.car.description}"/></td>
                                <td><img
                                        src="<c:url value='/download?name=${ad.id}'/>"
                                        width="100" height="100"></td>

                                <td>
                                    <a href='<c:url value="/add-photo.jsp?id=${ad.id}"/>'>
                                        Загрузить фото
                                        <i class="fa fa-upload mr-3"></i>
                                    </a>
                                </td>
                                <td><c:out
                                        value="${ad.car.bodyType.name}"/></td>
                                <td><c:out value="${ad.car.brand.name}"/></td>
                                <td><c:out value="${ad.author.name}"/></td>
                            </tr>
                        </c:forEach>
                    </tdoby>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    $('#ad-table').on('change', (evt) => {
        $.ajax({
            type: 'POST',
            url: `/job4j_cars/change-sold`,
            data: {id: evt.target.dataset.id}
        });
    })
</script>
</body>
</html>
