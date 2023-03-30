package org.jotad.inventario.repository;

import org.jotad.inventario.models.Auxiliar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuxiliarRepositoryImpl implements CrudRepository<Auxiliar>{

    private Connection conn;
    private boolean estado;

    public AuxiliarRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Auxiliar> listar(String texto) throws SQLException {
        List<Auxiliar> auxiliares = new ArrayList<>();
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM auxiliares WHERE nombre LIKE ?")){
            psmt.setString(1, "%" + texto + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()) {
                    Auxiliar auxiliar = getSerie(rs);
                    auxiliares.add(auxiliar);
                }
            }
        }
        return auxiliares;
    }

    @Override
    public Auxiliar porId(Long id) throws SQLException {
        Auxiliar auxiliar = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM auxiliares WHERE id=?")){
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    auxiliar = getSerie(rs);
                }
            }
        }
        return auxiliar;
    }

    @Override
    public void guardar(Auxiliar auxiliar) throws SQLException {
        String sql;
        if (auxiliar.getId() != null && auxiliar.getId() > 0){
            sql = "UPDATE auxiliares set nombre=?, descripcion=? WHERE id=?";
        }else {
            sql = "INSERT INTO auxiliares (nombre,descripcion) VALUES (?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, auxiliar.getNombre());
            psmt.setString(2, auxiliar.getDescripcion());
            if (auxiliar.getId() != null && auxiliar.getId() > 0){
                psmt.setLong(3,auxiliar.getId());
            }
            psmt.executeUpdate();
        }
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE auxiliares SET estado=1 WHERE id=?")){
            psmt.setLong(1, id);
            if (psmt.executeUpdate() > 0){
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public boolean desactivar(Long id) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE auxiliares SET estado=0 WHERE id=?")){
            psmt.setLong(1, id);
            if (psmt.executeUpdate() > 0){
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public boolean existe(String texto) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM auxiliares WHERE nombre=?")){
            psmt.setString(1,texto);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    estado = true;
                }
            }
        }
        return estado;
    }

    private static Auxiliar getSerie(ResultSet rs) throws SQLException {
        Auxiliar auxiliar = new Auxiliar();
        auxiliar.setId(rs.getLong("id"));
        auxiliar.setNombre(rs.getString("nombre"));
        auxiliar.setDescripcion(rs.getString("descripcion"));
        auxiliar.setEstado(rs.getBoolean("estado"));
        return auxiliar;
    }
}
