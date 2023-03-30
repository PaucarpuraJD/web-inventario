package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Historia;
import org.jotad.inventario.services.HistoriaService;
import org.jotad.inventario.services.HistoriaServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/eliminar/historias")
public class HistoriaEliminarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HistoriaService service = new HistoriaServiceImpl(conn);

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        if (id != 0){
            Optional<Historia> o = service.porId(id);
            if (o.isPresent()){
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/historias");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id no puede ser null, tiene que ser enviado en el parametro del request");
        }
    }
}
