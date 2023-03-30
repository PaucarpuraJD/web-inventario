package org.jotad.inventario.repository;

import org.jotad.inventario.models.Rol;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RolRepositoryImpl {

    private Connection conn;

    public RolRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Rol> listar() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM roles")){
            while (rs.next()){
                Rol rol = new Rol();
                rol.setId(rs.getLong("id"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
                roles.add(rol);
            }
        }
        return roles;
    }

    public int total() throws SQLException {
        int totalRegistro = 0;
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id) FROM roles")){
            while (rs.next()){
                totalRegistro = rs.getInt("COUNT(id)");
            }
        }
        return totalRegistro;
    }
}
