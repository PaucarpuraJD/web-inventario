<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp" />
<h2 style="text-align: center">Listado de Pacientes</h2>
<form action="${pageContext.request.contextPath}/pacientes" method="post">
  <div class="input-group mb-3 mt-4">
    <div class="mx-2">
      <input type="text" name="historia" class="form-control" placeholder="Historia" autofocus>
    </div>
    <div class="mx-2">
      <input type="text" name="nombre" class="form-control" placeholder="Nombre">
    </div>
    <div class="mx-2">
      <input type="text" name="dni" class="form-control" placeholder="DNI">
    </div>
    <div class="mx-2">
      <input type="submit" value="Buscar" class="btn btn-secondary">
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/pacientes/form"> Crear [+] </a>
    </div>
  </div>
</form>

<table class="table table-hover table-striped mt-4">
    <tr>
        <th>ID</th>
        <th>Historia</th>
        <th>Nombre</th>
        <th>DNI</th>
        <th>Editar</th>
    </tr>
    <c:forEach items="${pacientes}" var="p">
    <tr>
        <td>${p.id}</td>
        <td>${p.history}</td>
        <td>${p.nombre}</td>
        <td>${p.dni}</td>
        <td><a class="btn btn-success" href="${pageContext.request.contextPath}/pacientes/form?id=${p.id}">Editar</a></td>
    </tr>
    </c:forEach>
</table>
<jsp:include page="../layout/footer.jsp" />