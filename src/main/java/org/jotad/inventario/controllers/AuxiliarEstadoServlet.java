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
import java.util.Optional;

@WebServlet("/auxiliares/estado")
public class AuxiliarEstadoServlet extends HttpServlet {
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

        if (id != 0) {
            Optional<Auxiliar> o = service.porId(id).filter(s -> s.isEstado());
            if (o.isPresent()) {
                service.desactivar(id);
                resp.sendRedirect(req.getContextPath() + "/auxiliares");
            } else {
                service.activar(id);
                resp.sendRedirect(req.getContextPath() + "/auxiliares");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id no puede ser null, tiene que ser enviado en el parametro del request");
        }
    }
}
