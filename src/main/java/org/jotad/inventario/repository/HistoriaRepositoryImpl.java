package org.jotad.inventario.repository;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Caja;
import org.jotad.inventario.models.Historia;
import org.jotad.inventario.models.Ubicacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaRepositoryImpl implements CrudRepositoryPaginable<Historia>{

    private Connection conn;
    private boolean estado;

    public HistoriaRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Historia> listar(String texto, int numPagina, int totalPorPagina) throws SQLException {
        List<Historia> historias = new ArrayList<>();
        String sql = "select h.*, c.numero_caja as caja " +
                ",u.pasillo, u.columna, u.nivel from historias as h "
                + "inner join cajas as c on h.caja_id=c.id "
                + "inner join ubicaciones as u on c.ubicacion_id=u.id where h.historia LIKE ? ORDER BY h.id ASC LIMIT ?, ?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1,  "%" + texto + "%");
            psmt.setInt(2, (numPagina -1) * totalPorPagina);
            psmt.setInt(3, totalPorPagina);
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Historia h = getHistoria(rs);
                    historias.add(h);
                }
            }
        }

        return historias;
    }

    @Override
    public Historia porId(Long id) throws SQLException {
        Historia historia = null;
        List<Historia> historias = new ArrayList<>();
        String sql = "select h.*, c.numero_caja as caja ,u.pasillo, u.columna, u.nivel from historias as h "
                + "inner join cajas as c on h.caja_id=c.id "
                + "inner join ubicaciones as u on c.ubicacion_id=u.id where h.historia LIKE ?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if(rs.next()){
                    historia = getHistoria(rs);
                }
            }
        }
        return historia;
    }

    @Override
    public void guardar(Historia historia) throws SQLException {
        String sql;
        if(historia.getId() != null && historia.getId() > 0){
            sql = "UPDATE historias SET historia=?, caja_id=?, serie=?, duplicidad=?, descripcion=? WHERE id=?";
        }else {
            sql = "INSERT INTO historias (historia, caja_id, serie, duplicidad, descripcion) VALUES (?,?,?,?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, historia.getHistoria());
            psmt.setLong(2, historia.getCaja().getId());
            psmt.setString(3, historia.getSerie());
            psmt.setString(4, historia.getDuplicidad());
            psmt.setString(5, historia.getDescripcion());

            if (historia.getId() != null && historia.getId() > 0){
                psmt.setLong(6, historia.getId());
            }
            psmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM historias WHERE id=?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setLong(1, id);
            psmt.executeUpdate();
        }
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE historias SET estado=1 WHERE id=?")){
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
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE historias SET estado=0 WHERE id=?")){
            psmt.setLong(1, id);
            if (psmt.executeUpdate() > 0){
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public boolean existe(String texto) throws SQLException {
        return false;
    }

    @Override
    public int total() throws SQLException{
        int totalRegistros = 0;
        try (Statement psmt = conn.createStatement();
             ResultSet rs = psmt.executeQuery("SELECT COUNT(id) FROM historias")){
            while (rs.next()) {
                totalRegistros =  rs.getInt( "COUNT(id)");
            }
        }
        return totalRegistros;
    }

    private static Historia getHistoria(ResultSet rs) throws SQLException {
        Historia h = new Historia();
        h.setId(rs.getLong("id"));
        h.setHistoria(rs.getString("historia"));
        Caja c = new Caja();
        c.setId(rs.getLong("caja_id"));
        c.setNumeroCaja(rs.getInt("caja"));
        Ubicacion u = new Ubicacion();
        u.setId(rs.getLong("id"));
        u.setPasillo(rs.getString("pasillo"));
        u.setColumna(rs.getString("columna"));
        u.setNivel(rs.getString("nivel"));
        c.setUbicacion(u);
        h.setCaja(c);
        h.setSerie(rs.getString("serie"));
        h.setDuplicidad(rs.getString("duplicidad"));
        h.setDescripcion(rs.getString("descripcion"));
        h.setEstado(rs.getString("estado"));
        return h;
    }
}
