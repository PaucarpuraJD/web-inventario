package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.services.AuxiliarService;
import org.jotad.inventario.services.AuxiliarServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/auxiliares/form")
public class AuxiliarFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        AuxiliarService service = new AuxiliarServiceImpl(conn);
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Auxiliar auxiliar = new Auxiliar();
        if (id > 0) {
            Optional<Auxiliar> o = service.porId(id);
            if (o.isPresent()) {
                auxiliar = o.get();
            }
        }

        req.setAttribute("auxiliar", auxiliar);
        getServletContext().getRequestDispatcher("/auxiliar/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        AuxiliarService service = new AuxiliarServiceImpl(conn);

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "La nombre es requerido");
        }
        if (service.existe(nombre) && id.equals(0L)) {
            errores.put("nombre", "El nombre ya existe");
        }
        Auxiliar auxiliar = new Auxiliar();
        auxiliar.setId(id);
        auxiliar.setNombre(nombre);
        auxiliar.setDescripcion(descripcion);
        if (errores.isEmpty()) {
            service.guardar(auxiliar);
            resp.sendRedirect(req.getContextPath() + "/auxiliares");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("auxiliar", auxiliar);
            getServletContext().getRequestDispatcher("/auxiliar/form.jsp").forward(req, resp);
        }
    }
}