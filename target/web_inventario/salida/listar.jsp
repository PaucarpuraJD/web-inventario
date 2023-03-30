<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h2 style="text-align: center">LISTA DE HISTORIAS CLINICAS</h2>
<form action="${pageContext.request.contextPath}/salidas" method="post">
<div class="input-group mb-3 mt-4">
    <div class="col-sm-3">
        <input type="text" name="historia" class="form-control" autofocus >
    </div>
    <div class="mx-2">
        <input type="submit" value="Buscar" class="btn btn-secondary">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/salidas/form"> Crear [+] </a>
    </div>
</div>
</form>
<table class="table table-hover table-striped" style="text-align: center">
    <tr>
        <th>HISTORIA</th>
        <th>AREA</th>
        <th>ENCARGADO</th>
        <th>SALIDA</th>
        <th>MOTIVO</th>
        <th>EDITAR</th>
        <th>SALIDA</th>
    </tr>
    <c:forEach items="${salidas}" var="s">
    <tr>
        <td>${s.historia.historia}</td>
        <td>${s.area.nombre}</td>
        <td>${s.encargado}</td>
        <td>${s.fechaSalida}</td>
        <td>${s.motivo}</td>
        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/salidas/form?id=${s.id}">Entrada</a></td>
    </tr>
    </c:forEach>
</table>