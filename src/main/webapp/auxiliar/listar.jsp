<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h2 style="text-align: center">Listado de Auxiliar</h2>
<a class="btn btn-primary my2" href="${pageContext.request.contextPath}/auxiliares/form"> Crear [+] </a>
<table class="table table-hover table-striped mt-4">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Descripcion</th>
        <th>Estado</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    <c:forEach items="${auxiliares}" var="a">
    <tr>
        <td>${a.id}</td>
        <td>${a.nombre}</td>
        <td>${a.descripcion}</td>
        <td>${a.estado == true ? "Activo" : "Inactivo"}</td>
        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/auxiliares/form?id=${a.id}">Editar</a></td>
        <td><a class="btn btn-danger"
        href="${pageContext.request.contextPath}/auxiliares/estado?id=${a.id}">${a.estado == true ? "Desactivar" : "Activar"}</a></td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="../layout/footer.jsp" />
