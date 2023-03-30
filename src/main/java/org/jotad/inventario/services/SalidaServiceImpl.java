package org.jotad.inventario.services;

import org.jotad.inventario.models.Salida;
import org.jotad.inventario.repository.CrudRepository;
import org.jotad.inventario.repository.SalidaRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SalidaServiceImpl implements SalidaService{
    private CrudRepository<Salida> repository;

    public SalidaServiceImpl(Connection conn) {
        this.repository = new SalidaRepositoryImpl(conn);
    }

    @Override
    public List<Salida> listar(String texto) {
        try {
            return repository.listar("");
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Salida> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Salida salida) {
        try {
            repository.guardar(salida);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
