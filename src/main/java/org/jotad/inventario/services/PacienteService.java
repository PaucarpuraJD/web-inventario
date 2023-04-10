package org.jotad.inventario.services;

import org.jotad.inventario.models.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> listar(int numPagina, int totalPorPagina);
    List<Paciente> listar2(String texto, String texto2, String texto3, int numPagina, int totalPorPagina);
    Optional<Paciente> porId (Long id);
    void guardar (Paciente paciente);
    void eliminar (Long id);
    boolean activar(Long id);
    boolean desactivar(Long id);
    boolean existe(String texto);
    int total();
}
