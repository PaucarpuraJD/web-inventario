package org.jotad.inventario.models;

import java.time.LocalDate;

public class Salida {
    private Long id;
    private Historia historia;
    private Area area;
    private String encargado;
    private LocalDate fechaSalida;
    private String motivo;

    public Salida() {
    }

    public Salida(Long id, String encargado, LocalDate fechaSalida, String motivo) {
        this.id = id;
        this.encargado = encargado;
        this.fechaSalida = fechaSalida;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Historia getHistoria() {
        return historia;
    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
