package org.jotad.inventario.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jotad.inventario.models.Area;
import org.jotad.inventario.models.Historia;
import org.jotad.inventario.models.Salida;
import org.jotad.inventario.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@WebServlet("/salidas/form")
public class SalidaFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        HistoriaService service = new HistoriaServiceImpl(conn);
        AreaService areaService = new AreaServiceImpl(conn);
        SalidaService salidaService = new SalidaServiceImpl(conn);

        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));

        }catch (NumberFormatException e){
            id = 0L;
        }

        Historia historia = new Historia();
        if (id > 0){
            Optional<Historia> o = service.porId(id);
            if (o.isPresent()){
                historia = o.get();
            }
        }
        Salida salida = new Salida();
        salida.setHistoria(historia);
        req.setAttribute("salida", salida);
        req.setAttribute("areas", areaService.listar(""));
        req.setAttribute("historias", service.listar("",1,4));
        getServletContext().getRequestDispatcher("/salida/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        SalidaService service = new SalidaServiceImpl(conn);
        Long id;
        try {
            id = Long.valueOf(req.getParameter("id"));

        }catch (NumberFormatException e){
            id = 0L;
        }
        Long historiaId;
        try {
            historiaId = Long.valueOf(req.getParameter("historia"));

        }catch (NumberFormatException e){
            historiaId = 0L;
        }

        Long areaId;
        try {
            areaId = Long.valueOf(req.getParameter("area"));
        }catch (NumberFormatException e){
            areaId = 0L;
        }

        String encargado = req.getParameter("encargado");
        String motivo = req.getParameter("motivo");
        String fechaStr = req.getParameter("fechaSalida");
        LocalDate fecha1;
        try {
            fecha1= LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (DateTimeException e){
            fecha1 = null;
        }

        Salida salida = new Salida();
        salida.setId(id);
        Historia historia = new Historia();
        historia.setId(historiaId);
        historia.setHistoria(historia.getHistoria());
        salida.setHistoria(historia);
        Area area = new Area();
        area.setId(areaId);
        area.setNombre(area.getNombre());
        salida.setArea(area);
        salida.setEncargado(encargado);
        salida.setFechaSalida(fecha1);
        salida.setMotivo(motivo);

        service.guardar(salida);
        resp.sendRedirect(req.getContextPath() + "/historias");

    }
}
