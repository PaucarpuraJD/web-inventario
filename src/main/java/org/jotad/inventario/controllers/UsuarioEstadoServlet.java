package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Usuario;
import org.jotad.inventario.services.UsuarioService;
import org.jotad.inventario.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/usuarios/estado")
public class UsuarioEstadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        if (id > 0){
            Optional<Usuario> o = service.porId(id).filter(u -> u.isEstado());
            if (o.isPresent()){
                service.desactivar(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            }else {
                service.activar(id);
                resp.sendRedirect(req.getContextPath() + "/usuarios");
            }
        }else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "El id no puede ser null, tiene que ser enviado en el parametro del request");
        }
    }
}
