package org.jotad.inventario.repository;

import org.jotad.inventario.models.Area;
import org.jotad.inventario.models.Historia;
import org.jotad.inventario.models.Salida;
import org.jotad.inventario.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalidaRepositoryImpl implements CrudRepository<Salida> {
    private Connection conn;

    public SalidaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Salida> listar(String texto) throws SQLException {
        List<Salida> salidas = new ArrayList<>();
        try (PreparedStatement psmt = conn.prepareStatement("SELECT s.*, h.historia, a.nombre as area FROM salidas AS s " +
                "INNER JOIN historias as h ON (s.historia_id=h.id) " +
                "INNER JOIN areas as a ON (s.area_id=a.id) WHERE h.historia LIKE ?")){
            psmt.setString(1,"%" + texto + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Salida salida = getSalida(rs);
                    salidas.add(salida);
                }
            }

        }
        return salidas;
    }

    @Override
    public Salida porId(Long id) throws SQLException {
        Salida salida = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT s.*, h.historia, a.nombre as area FROM salidas AS s " +
                "INNER JOIN historias as h ON (s.historia_id=h.id) " +
                "INNER JOIN areas as a ON (s.area_id=a.id) WHERE s.id=?")){
            psmt.setLong(1,id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    salida = getSalida(rs);
                }
            }
        }
        return salida;
    }

    @Override
    public void guardar(Salida salida) throws SQLException {
        String sql;
        if (salida.getId() > 0 && salida.getId() != null){
            sql = "UPDATE salidas SET (encargado=?, fecha_salida=?, motivo=?) WHERE id=?";
        } else {
            sql = "INSERT INTO salidas (historia_id, area_id, encargado, fecha_salida, motivo) VALUES (?,?,?,?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            if (salida.getId() > 0 && salida.getId() != null){
                psmt.setString(1, salida.getEncargado());
                psmt.setDate(2, Date.valueOf(salida.getFechaSalida()));
                psmt.setString(3, salida.getMotivo());
                psmt.setLong(4, salida.getId());

            }{
                psmt.setLong(1,salida.getHistoria().getId());
                psmt.setLong(2, salida.getArea().getId());
                psmt.setString(3, salida.getEncargado());
                psmt.setDate(4, Date.valueOf(salida.getFechaSalida()));
                psmt.setString(5, salida.getMotivo());
            }

            psmt.executeUpdate();
        }
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        return false;
    }

    @Override
    public boolean desactivar(Long id) throws SQLException {
        return false;
    }

    @Override
    public boolean existe(String texto) throws SQLException {
        return false;
    }

    private static Salida getSalida(ResultSet rs) throws SQLException {
        Salida salida = new Salida();
        salida.setId(rs.getLong("id"));
        Historia historia = new Historia();
        historia.setId(rs.getLong("historia_id"));
        historia.setHistoria(rs.getString("historia"));
        salida.setHistoria(historia);
        Area area = new Area();
        area.setId(rs.getLong("area_id"));
        area.setNombre(rs.getString("area"));
        salida.setArea(area);
        salida.setEncargado(rs.getString("encargado"));
        salida.setFechaSalida(rs.getDate("fecha_salida").toLocalDate());
        salida.setMotivo(rs.getString("motivo"));
        return salida;
    }
}
