package com.mycompany.login.logica;

import java.io.Serializable;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Usuario implements Serializable {

    //si se utiliza el valor el ID generado automaticamente hay que editar
    //en  los metodos de crear usuario
    
    //Atributos 
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombreUsuario;
    private String contra;
    @ManyToOne
    @JoinColumn(name = "fk_rol")
    private Rol unRol;

    //Constructor
    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String contra, Rol unRol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.contra = contra;
        this.unRol = unRol;
    }


    //get y set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public Rol getUnRol() {
        return unRol;
    }

    public void setUnRol(Rol unRol) {
        this.unRol = unRol;
    }

}
