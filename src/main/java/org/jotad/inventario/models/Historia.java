package org.jotad.inventario.models;

public class Historia {
    private Long id;
    private String historia;
    private String serie;
    private String duplicidad;
    private String descripcion;

    private String estado;
    private Caja caja;

    public Historia() {
    }

    public Historia(Long id, String historia, String serie, String duplicidad, String descripcion, String estado) {
        this.id = id;
        this.historia = historia;
        this.serie = serie;
        this.duplicidad = duplicidad;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDuplicidad() {
        return duplicidad;
    }

    public void setDuplicidad(String duplicidad) {
        this.duplicidad = duplicidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }
}
