package org.jotad.inventario.repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudFiltroRepository<T> {
    List<T> listar(int numPagina, int totalPorPagina) throws SQLException;
    List<T> listar2(String texto, String texto2, String texto3, int numPagina, int totalPorPagina) throws SQLException;
    T porId (Long id) throws SQLException;
    void guardar (T t) throws SQLException;
    void eliminar (Long id) throws SQLException;
    boolean activar(Long id) throws SQLException;
    boolean desactivar(Long id) throws SQLException;
    boolean existe(String texto) throws SQLException;
    int total() throws SQLException;
}
