package org.jotad.inventario.services;

import org.jotad.inventario.models.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    List<Area> listar(String texto);
    Optional<Area> porId (Long id);
    void guardar (Area Area);
    boolean activar(Long id);
    boolean desactivar(Long id);
    boolean existe(String texto);
}
