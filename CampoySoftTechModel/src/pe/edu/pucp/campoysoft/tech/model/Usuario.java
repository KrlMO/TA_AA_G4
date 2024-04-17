package pe.edu.pucp.campoysoft.tech.model;

import pe.edu.pucp.campoysoft.rrhh.model.Persona;



public class Usuario {
    private int idusuario;
    private String username;
    private String password;
    private Persona persona;
    private boolean activo;

    public Usuario(int idusuario, String username, String password, Persona persona, boolean activo) {
        this.idusuario = idusuario;
        this.username = username;
        this.password = password;
        this.persona = persona;
        this.activo = activo;
    }


    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
