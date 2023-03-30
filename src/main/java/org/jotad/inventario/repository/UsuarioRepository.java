package org.jotad.inventario.repository;

import org.jotad.inventario.models.Usuario;

import java.sql.SQLException;

public interface UsuarioRepository extends CrudRepository<Usuario>{
    Usuario porUsername(String username) throws SQLException;
}
