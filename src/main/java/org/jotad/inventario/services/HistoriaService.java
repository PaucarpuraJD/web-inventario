package org.jotad.inventario.services;

import org.jotad.inventario.models.Historia;

import java.util.List;
import java.util.Optional;

public interface HistoriaService {
    List<Historia> listar(String texto, int numPagina, int totalPorPagina);
    Optional<Historia> porId (Long id);
    void guardar (Historia historia);
    void eliminar (Long id);
    boolean activar(Long id);
    boolean desactivar(Long id);
    boolean existe(String texto);
    int total();
}
