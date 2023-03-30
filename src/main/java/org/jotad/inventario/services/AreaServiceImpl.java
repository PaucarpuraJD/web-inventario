package org.jotad.inventario.services;

import org.jotad.inventario.models.Area;
import org.jotad.inventario.repository.AreaRepositoryImpl;
import org.jotad.inventario.repository.CrudRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AreaServiceImpl implements AreaService{

    private CrudRepository<Area> repository;

    public AreaServiceImpl(Connection conn) {
        this.repository = new AreaRepositoryImpl(conn);
    }

    @Override
    public List<Area> listar(String texto) {
        try {
            return repository.listar(texto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Area> porId(Long id) {
        return Optional.empty();
    }

    @Override
    public void guardar(Area Area) {

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
}
