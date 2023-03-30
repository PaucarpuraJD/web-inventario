package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Rol;
import org.jotad.inventario.models.Usuario;
import org.jotad.inventario.services.UsuarioService;
import org.jotad.inventario.services.UsuarioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/usuarios/form")
public class UsuarioFormServlet extends HttpServlet {
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
        Usuario usuario = new Usuario();
        usuario.setRol(new Rol());

        if (id > 0){
            Optional<Usuario> o = service.porId(id);
            if (o.isPresent()){
                usuario = o.get();
            }
        }

        req.setAttribute("usuario", usuario);
        req.setAttribute("auxiliares", service.listarAuxiliar(""));
        req.setAttribute("roles", service.listarRol());
        getServletContext().getRequestDispatcher("/usuario/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceImpl(conn);

        Long auxId;
        try {
            auxId = Long.valueOf(req.getParameter("auxiliar"));
        }catch (NumberFormatException e){
            auxId = 0L;
        }
        Long rolId;
        try {
            rolId = Long.valueOf(req.getParameter("rol"));
        }catch (NumberFormatException e){
            rolId = 0L;
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }

        Map<String,String> errores = new HashMap<>();
        if (rolId.equals(0L)){
            errores.put("rol", "El rol es requerido");
        }
        if (auxId.equals(0L)){
            errores.put("auxiliar", "El auxiliar es requerido");
        }
        if (username == null || username.isBlank()){
            errores.put("username", "El username es requerido");
        }
        if (password == null || password.isBlank()){
            errores.put("password", "El password es requerido");
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);
        Rol rol = new Rol();
        rol.setId(rolId);
        usuario.setRol(rol);
        usuario.setUsername(username);
        usuario.setPassword(password);

        if (errores.isEmpty()){
            service.guardar(usuario);
            resp.sendRedirect(req.getContextPath() + "/usuarios");
        }else {
            req.setAttribute("errores", errores);
            req.setAttribute("roles", service.listarRol());
            req.setAttribute("usuario", usuario);
            getServletContext().getRequestDispatcher("/usuario/form.jsp").forward(req,resp);
        }
    }
}
