<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<form action="${pageContext.request.contextPath}/auxiliares/form" method="post">
<div class="row mb-2">
    <label class="col-form-label col-sm-2" for="nombre">Nombre</label>
    <div class="col-sm-4">
        <input type="text" name="nombre" id="nombre" value="${auxiliar.nombre}" class="form-control">
    </div>
    <c:if test="${errores != null && not empty errores.nombre}">
    <div style="color:red;">${errores.nombre}</div>
    </c:if>
</div>
<div class="row mb-2">
    <label class="col-form-label col-sm-2" for="descripcion">Descripcion</label>
    <div class="col-sm-4">
        <input type="text" name="descripcion" id="descripcion" value="${auxiliar.descripcion}" class="form-control">
    </div>
</div>
<div class="row mb-2">
    <div>
        <input class="btn btn-primary my-2" type="submit" value="${auxiliar.id != null && auxiliar.id > 0 ? "Editar" : "Crear"}">
            <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/auxiliares">Cancelar</a>
        </div>
    </div>
<input type="hidden" name="id" value="${auxiliar.id}">
</form>
<jsp:include page="../layout/footer.jsp" />