package org.jotad.inventario.services;

import org.jotad.inventario.models.Rol;
import org.jotad.inventario.repository.RolRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RolServiceImpl {

    private RolRepositoryImpl rolRepository;

    public RolServiceImpl(Connection conn) {
        rolRepository = new RolRepositoryImpl(conn);
    }

    public List<Rol> listar(){
        try {
            return rolRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    public int total(){
        try {
            return rolRepository.total();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
