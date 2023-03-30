<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h3>Formulario de Categoria</h3>
<form action="${pageContext.request.contextPath}/salidas/form" method="post">
<div class="row mb-2">
    <label for="historia" class="col-form-label col-sm-2">Historia</label>
    <div class="col-sm-4">
        <select name="historia" id="historia" class="form-select" class="col-form-label col-sm-2">
            <option value="">--- seleccionar ---</option>
            <c:forEach items="${historias}" var="h">
            <option value="${h.id}" ${h.id.equals(salida.historia.id) ? "selected" : ""}>${h.historia}</option>
            </c:forEach>
        </select>
    </div>
</div>
<div class="row mb-2">
    <label for="area" class="col-form-label col-sm-2">Area</label>
    <div class="col-sm-4">
        <select name="area" id="area" class="form-select" class="col-form-label col-sm-2">
            <option value="">--- seleccionar ---</option>
            <c:forEach items="${areas}" var="a">
            <option value="${a.id}" ${a.id.equals(salida.area.id) ? "selected" : ""}>${a.nombre}</option>
            </c:forEach>
        </select>
    </div>
</div>

    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="encargado">Solicitante</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="encargado" id="encargado" value="${salida.encargado}">
        </div>
    </div>
    <div class="row mb-2">
        <label for="fechaSalida" class="col-form-label col-sm-2">Fecha Ingreso</label>
            <div class="col-sm-4">
                <input class="form-control" type="date" name="fechaSalida" id="fechaSalida" value="${salida.fechaSalida !=null ? salida.fechaSalida.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
            </div>
    </div>
    <div class="row mb-2">
        <label class="col-form-label col-sm-2" for="motivo">Motivo</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="motivo" id="motivo" value="${salida.motivo}">
        </div>
    </div>
    <div class="row mb-2">
        <div>
            <input class="btn btn-primary my-2" type="submit" value="${salida.id != null && salida.id > 0 ? "Editar" : "Crear"}">
            <a class="btn btn-success my-2" href="${pageContext.request.contextPath}/historias">Cancelar</a>
        </div>
    </div>
    <input type="hidden" name="id" value="${salida.id}">
</form>
<jsp:include page="../layout/footer.jsp" />