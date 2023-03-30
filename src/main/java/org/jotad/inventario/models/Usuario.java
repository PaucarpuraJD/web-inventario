package org.jotad.inventario.models;

public class Usuario {

    private Long id;
    private Rol rol;
    private String username;
    private String password;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(Long id, String username, String password, boolean estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
