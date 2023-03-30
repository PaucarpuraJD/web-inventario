package org.jotad.inventario.models;

public class Caja {
    private Long id;
    private Integer numeroCaja;
    private Ubicacion ubicacion;

    public Caja() {
    }

    public Caja(Long id, Integer numeroCaja) {
        this.id = id;
        this.numeroCaja = numeroCaja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(Integer numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
