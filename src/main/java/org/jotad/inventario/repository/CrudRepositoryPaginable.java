package org.jotad.inventario.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepositoryPaginable<T> {
    List<T> listar(String texto, int numPagina, int totalPagina) throws SQLException;
    T porId (Long id) throws SQLException;
    void guardar (T t) throws SQLException;
    void eliminar (Long id) throws SQLException;
    boolean activar(Long id) throws SQLException;
    boolean desactivar(Long id) throws SQLException;
    boolean existe(String texto) throws SQLException;
    int total() throws SQLException;
}
