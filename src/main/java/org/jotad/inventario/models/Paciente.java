package org.jotad.inventario.models;

public class Paciente {
    private Integer id;
    private String history;
    private String nombre;
    private String dni;

    public Paciente() {
    }

    public Paciente(Integer id, String history, String nombre, String dni) {
        this.id = id;
        this.history = history;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
