package org.jotad.inventario.services;

import org.jotad.inventario.models.Auxiliar;

import java.util.List;
import java.util.Optional;

public interface AuxiliarService {
    List<Auxiliar> listar(String texto);
    Optional<Auxiliar> porId (Long id);
    void guardar (Auxiliar auxiliar);
    boolean activar(Long id);
    boolean desactivar(Long id);
    boolean existe(String texto);
}
