package org.jotad.inventario.repository;

import org.jotad.inventario.models.Area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaRepositoryImpl implements CrudRepository<Area> {

    private Connection conn;

    public AreaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Area> listar(String texto) throws SQLException {
        List<Area> areas = new ArrayList<>();
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM areas WHERE nombre LIKE ?")){
            psmt.setString(1, "%" + texto + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Area area = getArea(rs);
                    areas.add(area);
                }
            }
        }
        return areas;
    }

    @Override
    public Area porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Area area) throws SQLException {

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

    private static Area getArea(ResultSet rs) throws SQLException {
        Area area = new Area();
        area.setId(rs.getLong("id"));
        area.setNombre(rs.getString("nombre"));
        return area;
    }
}
