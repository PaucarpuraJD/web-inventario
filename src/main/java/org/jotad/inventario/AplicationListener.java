package org.jotad.inventario;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.jotad.inventario.models.Historia;
import org.jotad.inventario.models.Usuario;

public class AplicationListener implements  ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("inicializando la session");
        Historia historia = new Historia();
        Usuario usuario = new Usuario();
        HttpSession session = se.getSession();
        session.setAttribute("historia", historia);
        session.setAttribute("usuario", usuario);

    }
}
