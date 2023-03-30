<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h3>Listado de Roles</h3>
<table class="table table-hover table-striped">
    <tr>
        <th>Id</th>
        <th>Nombre</th>
        <th>Descripcion</th>
    </tr>
    <c:forEach items="${roles}" var="r">
    <tr>
        <td>${r.id}</td>
        <td>${r.nombre}</td>
        <td>${r.descripcion}</td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="../layout/footer.jsp" />