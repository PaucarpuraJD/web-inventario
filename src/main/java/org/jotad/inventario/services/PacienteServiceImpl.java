package org.jotad.inventario.services;

import org.jotad.inventario.models.Paciente;
import org.jotad.inventario.repository.CrudFiltroRepository;
import org.jotad.inventario.repository.PacienteRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PacienteServiceImpl implements PacienteService{

    private CrudFiltroRepository<Paciente> repository;

    public PacienteServiceImpl(Connection conn) {
        this.repository = new PacienteRepositoryImpl(conn);
    }

    @Override
    public List<Paciente> listar(int numPagina, int totalPorPagina) {
        try {
            return repository.listar(numPagina,totalPorPagina);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Paciente> listar2(String texto, String texto2, String texto3, int numPagina, int totalPorPagina) {
        try {
            return repository.listar2(texto, texto2,texto3,numPagina,totalPorPagina);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Paciente> porId(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Paciente paciente) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public boolean activar(Long id) {
        return false;
    }

    @Override
    public boolean desactivar(Long id) {
        return false;
    }

    @Override
    public boolean existe(String texto) {
        return false;
    }

    @Override
    public int total() {
        return 0;
    }
}
