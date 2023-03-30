package org.jotad.inventario.services;

import org.jotad.inventario.models.Historia;
import org.jotad.inventario.repository.CrudRepositoryPaginable;
import org.jotad.inventario.repository.HistoriaRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HistoriaServiceImpl implements HistoriaService {

    private CrudRepositoryPaginable<Historia> repository;

    public HistoriaServiceImpl(Connection conn) {
        this.repository = new HistoriaRepositoryImpl(conn);
    }

    @Override
    public List<Historia> listar(String texto, int numPagina, int totalPorPagina) {
        try {
            return repository.listar(texto, numPagina, totalPorPagina);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Historia> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Historia historia) {
        try {
            repository.guardar(historia);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean activar(Long id) {
        try {
            return repository.activar(id);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean desactivar(Long id) {
        try {
            return repository.desactivar(id);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existe(String texto) {
        return false;
    }

    @Override
    public int total() {
        try {
            return repository.total();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

}
