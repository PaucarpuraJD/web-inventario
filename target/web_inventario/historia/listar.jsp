<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h2 style="text-align: center">LISTA DE HISTORIAS CLINICAS</h2>
<form action="${pageContext.request.contextPath}/historias" method="post">
<div class="input-group mb-3 mt-4">
    <div class="col-sm-3">
        <input type="text" name="historia" class="form-control" autofocus >
    </div>
    <div class="mx-2">
        <input type="submit" value="Buscar" class="btn btn-secondary">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/historias/form"> Crear [+] </a>
    </div>
</div>
</form>
<table class="table table-hover table-striped" style="text-align: center">
    <tr>
        <th>HISTORIA</th>
        <th>CAJA</th>
        <th>DUPLICIDAD</th>
        <th>OBSERVACION</th>
        <th>UBICACION</th>
        <th>SALIDA</th>
    </tr>
    <c:forEach items="${historias}" var="h">
    <tr>
        <td>${h.historia}</td>
        <td>${h.caja.numeroCaja}</td>
        <td>${h.duplicidad}</td>
        <td>${h.descripcion}</td>
        <td>${h.caja.ubicacion.pasillo}-${h.caja.ubicacion.columna}-${h.caja.ubicacion.nivel}</td>
        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/salidas/form?id=${h.id}">Dar Salida</a></td>
    </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/historias/paginable" method="post">
    <div class="row mb-2" >
        <div class="col-sm-3">
            <select name="numPagina" class="form-select" >
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            </select>
        </div>
        <div class="col-sm-3">
            <select name="totalPorPagina" class="form-select" >
            <option value="10">10</option>
            <option value="15">15</option>
            <option value="20">20</option>
            <option value="100">100</option>
            <option value="200">200</option>
            </select>
        </div>
        <div class="col-sm-3">
            <input class="btn btn-primary" type="submit" value="Siguiente">
        </div>
    </div>
</form>
<jsp:include page="../layout/footer.jsp" />
