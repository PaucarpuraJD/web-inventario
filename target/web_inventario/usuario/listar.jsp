<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h3>Listado de Usuarios</h3>
<a class="btn btn-primary my-2" href="${pageContext.request.contextPath}/usuarios/form"> Crear [+] </a>
<form action="${pageContext.request.contextPath}/usuarios" method="post">
<div class="input-group mb-3">
    <div class="col-sm-3">
        <input type="text" name="nombre" class="form-control">
    </div>
    <div>
        <input type="submit" value="Buscar" class="btn btn-secondary">
    </div>
</div>
</form>
<table class="table table-hover table-striped">
    <tr>
        <th>Id</th>
        <th>Rol</th>
        <th>Username</th>
        <th>Estado</th>
        <th>Editar</th>
        <th>Eliminar</th>
    </tr>
    <c:forEach items="${usuarios}" var="u">
    <tr>
        <td>${u.id}</td>
        <td>${u.rol.nombre}</td>
        <td>${u.username}</td>
        <td>${u.estado == true ? "Activo" : "Inactivo"}</td>
        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/usuarios/form?id=${u.id}">editar</a></td>
        <td><a class="btn btn-danger" href="${pageContext.request.contextPath}/usuarios/estado?id=${u.id}">${u.estado == true ? "Desactivar" : "Activar"}</a></td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="../layout/footer.jsp" />