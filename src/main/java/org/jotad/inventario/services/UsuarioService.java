package org.jotad.inventario.services;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Rol;
import org.jotad.inventario.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar(String username);

    Optional<Usuario> porId(Long id);

    void guardar(Usuario usuario);

    boolean activar(Long id);

    boolean desactivar(Long id);

    boolean existe(String username);

    List<Rol> listarRol();
    List<Auxiliar> listarAuxiliar(String text);

    Optional<Usuario> login(String username, String password);

}
