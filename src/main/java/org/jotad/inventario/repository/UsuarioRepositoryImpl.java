package org.jotad.inventario.repository;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Rol;
import org.jotad.inventario.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository{

    private Connection conn;
    private boolean estado;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar(String username) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement psmt = conn.prepareStatement("SELECT u.*, r.nombre as rol FROM usuarios AS u " +
                "INNER JOIN roles AS r ON (u.rol_id=r.id) " +
                "WHERE u.username LIKE ? ")){
            psmt.setString(1, "%" + username + "%");
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Usuario usuario = getUsuario(rs);
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario u = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT u.*, r.nombre as rol FROM usuarios AS u " +
                "INNER JOIN roles AS r ON (u.rol_id=r.id) WHERE u.id=?")){
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()) {
                    u = getUsuario(rs);
                }
            }
        }
        return u;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() >  0){
            sql = "UPDATE usuarios SET rol_id=?, username=?, password=? WHERE id=?";
        }else {
            sql = "INSERT INTO usuarios ( rol_id, username, password) VALUES (?,?,?)";
        }
        try (PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setLong(1, usuario.getRol().getId());
            psmt.setString(2, usuario.getUsername());
            psmt.setString(3, usuario.getPassword());

            if (usuario.getId() != null && usuario.getId() > 0){
                psmt.setLong(4, usuario.getId());
            }
            psmt.executeUpdate();
        }
    }

    @Override
    public boolean activar(Long id) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE usuarios SET estado=1 WHERE id=?")){
            psmt.setLong(1,id);
            if (psmt.executeUpdate() > 0){
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public boolean desactivar(Long id) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("UPDATE usuarios SET estado=0 WHERE id=?")){
            psmt.setLong(1,id);
            if (psmt.executeUpdate() > 0){
                estado = true;
            }
        }
        return estado;
    }

    @Override
    public boolean existe(String nombre) throws SQLException {
        estado = false;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT * FROM usuarios WHERE username=?")){
            psmt.setString(1, nombre);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    estado = true;
                }
            }
        }
        return estado;
    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement psmt = conn.prepareStatement("SELECT u.*, r.nombre as rol FROM usuarios AS u " +
                "INNER JOIN roles AS r ON (u.rol_id=r.id) WHERE u.username=?")){
            psmt.setString(1, username);
            try (ResultSet rs = psmt.executeQuery()){
                if (rs.next()){
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        Rol r = new Rol();
        r.setId(rs.getLong("rol_id"));
        r.setNombre(rs.getString("rol"));
        usuario.setRol(r);
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEstado(rs.getBoolean("estado"));
        return usuario;
    }
}
