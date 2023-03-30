package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Rol;
import org.jotad.inventario.services.RolServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/roles")
public class RolServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        RolServiceImpl service = new RolServiceImpl(conn);
        List<Rol> roles = service.listar();

        req.setAttribute("roles", roles);
        getServletContext().getRequestDispatcher("/rol/listar.jsp").forward(req,resp);
    }
}
