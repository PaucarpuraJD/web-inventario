package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Salida;
import org.jotad.inventario.services.SalidaService;
import org.jotad.inventario.services.SalidaServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/salidas")
public class SalidaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        SalidaService service = new SalidaServiceImpl(conn);
        List<Salida> salidas = service.listar("");
        req.setAttribute("salidas",salidas);
        getServletContext().getRequestDispatcher("/salida/listar.jsp").forward(req,resp);
    }

}
