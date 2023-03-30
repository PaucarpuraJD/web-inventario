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

@WebServlet("/historias/paginable")
public class HistoriaPaginableServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HistoriaService service = new HistoriaServiceImpl(conn);

        int numPagina;
        try {
            numPagina = Integer.valueOf(req.getParameter("numPagina"));
        }catch (NumberFormatException e){
            numPagina = 1;
        }
        int totalPorPagina;
        try {
            totalPorPagina = Integer.valueOf(req.getParameter("totalPorPagina"));
        }catch (NumberFormatException e){
            totalPorPagina = 8;
        }

        List<Historia> historias = service.listar("",numPagina,totalPorPagina);
        req.setAttribute("historias", historias);
        getServletContext().getRequestDispatcher("/historia/listar.jsp").forward(req, resp);

    }

}
