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
import java.util.List;

@WebServlet("/historias")
public class HistoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection)req.getAttribute("conn");
        HistoriaService service = new HistoriaServiceImpl(conn);
        List<Historia> historias = service.listar("",1,8);
        req.setAttribute("historias", historias);
        getServletContext().getRequestDispatcher("/historia/listar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HistoriaService service = new HistoriaServiceImpl(conn);
        String historia = req.getParameter("historia");
        List<Historia> historias = service.listar(historia,1,8);
        req.setAttribute("historias", historias);
        getServletContext().getRequestDispatcher("/historia/listar.jsp").forward(req,resp);
    }
}
