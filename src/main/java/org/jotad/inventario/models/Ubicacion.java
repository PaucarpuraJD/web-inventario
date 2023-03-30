package org.jotad.inventario.models;

public class Ubicacion {
    private Long id;
    private String pasillo;
    private String columna;
    private String nivel;

    public Ubicacion() {
    }

    public Ubicacion(Long id, String pasillo, String columna, String nivel) {
        this.id = id;
        this.pasillo = pasillo;
        this.columna = columna;
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasillo() {
        return pasillo;
    }

    public void setPasillo(String pasillo) {
        this.pasillo = pasillo;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
