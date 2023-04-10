package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Paciente;
import org.jotad.inventario.services.PacienteService;
import org.jotad.inventario.services.PacienteServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/pacientes")
public class PacienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PacienteService service = new PacienteServiceImpl(conn);
        List<Paciente> pacientes = service.listar(1, 8);
        req.setAttribute("pacientes", pacientes);
        getServletContext().getRequestDispatcher("/paciente/listar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        PacienteService service = new PacienteServiceImpl(conn);
        String historia = req.getParameter("historia");
        String nombre = req.getParameter("nombre");
        String dni = req.getParameter("dni");
        List<Paciente> pacientes = service.listar2(historia,nombre,dni, 1, 8);
        req.setAttribute("pacientes", pacientes);
        getServletContext().getRequestDispatcher("/paciente/listar.jsp").forward(req,resp);
    }
}
