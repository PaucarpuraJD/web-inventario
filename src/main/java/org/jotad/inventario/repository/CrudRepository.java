package org.jotad.inventario.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> listar(String texto) throws SQLException;
    T porId (Long id) throws SQLException;
    void guardar (T t) throws SQLException;
    boolean activar(Long id) throws SQLException;
    boolean desactivar(Long id) throws SQLException;
    boolean existe(String texto) throws SQLException;
}
