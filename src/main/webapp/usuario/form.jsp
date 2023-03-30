<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h3>Crear de Usuario</h3>
<form action="${pageContext.request.contextPath}/usuarios/form" method="post">

<div class="row mb-2">
    <label for="rol" class="col-form-label col-sm-2">Rol</label>
    <div class="col-sm-4">
        <select  name="rol" id="rol" class="form-select" class="col-form-label col-sm-2">
            <option value=""> --- selecionar ---</option>
            <c:forEach items="${roles}" var="r">
            <option value="${r.id}" ${r.id.equals(usuario.rol.id) ? "selected" : ""}>${r.nombre}</option>
            </c:forEach>
        </select>
    </div>
    <c:if test="${errores != null && not empty errores.rol}">
        <div style="color:red;">${errores.rol}</div>
    </c:if>
</div>
<div class="row mb-2">
    <label for="username" class="col-form-label col-sm-2">Username</label>
    <div class="col-sm-4">
        <input class="form-control" type="text" name="username" id="username" value="${usuario.username}">
    </div>
        <c:if test="${errores != null && not empty errores.username}">
        <div style="color:red;">${errores.username}</div>
        </c:if>
</div>
<div class="row mb-2">
    <label for="password" class="col-form-label col-sm-2">Password</label>
    <div class="col-sm-4">
        <input class="form-control" type="password" name="password" id="password" value="${usuario.password}">
    </div>
        <c:if test="${errores != null && not empty errores.password}">
        <div style="color:red;">${errores.password}</div>
        </c:if>
</div>
<div class="row mb-2">
    <div>
        <input class="btn btn-primary my-2" type="submit" value="${usuario.id != null && usuario.id > 0 ? "Editar" : "Crear"}">
        <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/usuarios">Cancelar</a>
    </div>
</div>
<input type="hidden" name="id" value="${usuario.id}">
</form>
<jsp:include page="../layout/footer.jsp" />