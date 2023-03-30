package org.jotad.inventario.services;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Rol;
import org.jotad.inventario.models.Usuario;
import org.jotad.inventario.repository.AuxiliarRepositoryImpl;
import org.jotad.inventario.repository.RolRepositoryImpl;
import org.jotad.inventario.repository.UsuarioRepository;
import org.jotad.inventario.repository.UsuarioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository repository;
    private RolRepositoryImpl rolRepository;
    private AuxiliarRepositoryImpl auxiliarRepository;

    public UsuarioServiceImpl(Connection conn) {
        this.repository = new UsuarioRepositoryImpl(conn);
        this.rolRepository = new RolRepositoryImpl(conn);
        this.auxiliarRepository = new AuxiliarRepositoryImpl(conn);
    }

    @Override
    public List<Usuario> listar(String username) {
        try {
            return repository.listar(username);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            repository.guardar(usuario);
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
    public boolean existe(String nombre) {
        try {
            return repository.existe(nombre);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Rol> listarRol() {
        try {
            return rolRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Auxiliar> listarAuxiliar(String texto) {
        try {
            return auxiliarRepository.listar(texto);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(repository.porUsername(username)).filter(u -> u.getPassword().equals(password) && u.isEstado());
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
