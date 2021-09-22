<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div class="row">
    <ul class="nav">
        <c:if test="${user == null}">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/auth.do'/>">Войти</a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<c:url value='/reg.do'/>">Регистрация</a>
            </li>
        </c:if>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/ad.do'/>">Объявления</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value='/add-ad.do'/>">Добавить
                объявление</a>
        </li>
        <c:if test="${user != null}">
            <li class="nav-item">
                <c:out value="${user.name}"/>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<%=request.getContextPath()%>/logout.do">Выйти</a>
            </li>
        </c:if>
    </ul>
</div>
