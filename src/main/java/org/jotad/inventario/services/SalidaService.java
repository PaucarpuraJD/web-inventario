package org.jotad.inventario.services;

import org.jotad.inventario.models.Auxiliar;
import org.jotad.inventario.models.Salida;

import java.util.List;
import java.util.Optional;

public interface SalidaService {
    List<Salida> listar(String texto);
    Optional<Salida> porId (Long id);
    void guardar (Salida salida);
}
