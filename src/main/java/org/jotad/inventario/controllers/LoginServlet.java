package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jotad.inventario.models.Usuario;
import org.jotad.inventario.services.LoginService;
import org.jotad.inventario.services.LoginServiceImpl;
import org.jotad.inventario.services.UsuarioService;
import org.jotad.inventario.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login" ,  "/index.html"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceImpl();
        Optional<String> usuOptional = auth.getUsername(req);
        if (usuOptional.isPresent()){
            resp.sendRedirect(req.getContextPath() + "/historias");
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsuarioService service = new UsuarioServiceImpl(conn);
        Optional<Usuario> usuarioOptional = service.login(username, password);
        if (usuarioOptional.isPresent()){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/index.html");

        }else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para entrar a esta pagina");
        }


    }
}
