package org.jotad.inventario.services;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.repository.CrudRepository;
import org.jotad.inventario.repository.AuxiliarRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuxiliarServiceImpl implements AuxiliarService {

    private CrudRepository<Auxiliar> repository;

    public AuxiliarServiceImpl(Connection conn) {
        this.repository = new AuxiliarRepositoryImpl(conn);
    }

    @Override
    public List<Auxiliar> listar(String texto) {
        try {
            return repository.listar(texto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Auxiliar> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Auxiliar auxiliar) {
        try {
            repository.guardar(auxiliar);
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
        try {
            return repository.existe(texto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
