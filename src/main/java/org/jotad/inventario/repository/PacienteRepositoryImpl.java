package org.jotad.inventario.repository;

import org.jotad.inventario.models.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryImpl implements CrudFiltroRepository<Paciente> {

    private Connection conn;

    public PacienteRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Paciente> listar(int numPagina, int totalPorPagina) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes LIMIT ?, ?";
        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setInt(1, (numPagina -1) * totalPorPagina);
            psmt.setInt(2, totalPorPagina);
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Paciente paciente = getPaciente(rs);
                    pacientes.add(paciente);
                }
            }

        }

        return pacientes;
    }

    @Override
    public List<Paciente> listar2(String texto, String texto2, String texto3, int numPagina, int totalPorPagina) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE history=? OR nombre=? OR dni=? LIMIT ?, ?";
        try(PreparedStatement psmt = conn.prepareStatement(sql)){
            psmt.setString(1, texto);
            psmt.setString(2, texto2 );
            psmt.setString(3, texto3);
            psmt.setInt(4, (numPagina -1) * totalPorPagina);
            psmt.setInt(5, totalPorPagina);
            try (ResultSet rs = psmt.executeQuery()){
                while (rs.next()){
                    Paciente paciente = getPaciente(rs);
                    pacientes.add(paciente);
                }
            }

        }

        return pacientes;
    }

    @Override
    public Paciente porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Paciente paciente) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

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

    @Override
    public int total() throws SQLException {
        return 0;
    }

    private static Paciente getPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setId(rs.getInt("id"));
        paciente.setHistory(rs.getString("history"));
        paciente.setNombre(rs.getString("nombre"));
        paciente.setDni(rs.getString("dni"));
        return paciente;
    }
}
