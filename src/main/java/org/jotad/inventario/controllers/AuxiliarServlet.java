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
import java.util.List;

@WebServlet({"/auxiliares"})
public class AuxiliarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection)req.getAttribute("conn");
        AuxiliarService service = new AuxiliarServiceImpl(conn);
        List<Auxiliar> auxiliares = service.listar("");

        req.setAttribute("auxiliares", auxiliares);
        getServletContext().getRequestDispatcher("/auxiliar/listar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        AuxiliarService service = new AuxiliarServiceImpl(conn);
    }
}
