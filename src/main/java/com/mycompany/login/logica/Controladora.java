package com.mycompany.login.logica;

import com.mycompany.login.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }

    public Usuario validarUsuario(String usuario, String contra) {
        Usuario validar = null;

        List<Usuario> listaUsuario = controlPersis.traerUsuarios();

        if (!listaUsuario.isEmpty()) {
            for (Usuario user : listaUsuario) {
                if (user.getNombreUsuario().equals(usuario)) {
                    if (user.getContra().equals(contra)) {
                        validar = user;
                        return validar;
                    } else {
                        validar = null;
                        return validar;
                    }
                } else {
                    validar = null;
                }
            }
        }
        return validar;
    }

    public String validarRol(String usuario) {
        String rol = "";

        List<Usuario> listaUsuario = controlPersis.traerUsuarios();

        if (!listaUsuario.isEmpty()) {
            for (Usuario user : listaUsuario) {
                if (user.getNombreUsuario().equals(usuario)) {
                    
                    rol = user.getUnRol().getNombreRol();
                    return rol;
                } 
            }
        }
        return rol;

    }

    public List<Usuario> traerUsuarios() {
        
        return controlPersis.traerUsuarios();
        
    }

    public List<Rol> traerRoles() {    
        return controlPersis.traerRoles();
    }

    public void crearUsuario(String nombre, String contrasenia, String rolRecibido) {
        
        Usuario usuario = new Usuario();
        
        usuario.setNombreUsuario(nombre);
        usuario.setContra(contrasenia);
        
        Rol rolEncontrado = traerRol(rolRecibido);
        
        if(rolEncontrado != null){
            usuario.setUnRol(rolEncontrado);
        }
        
        int id = this.buscarUltimoIdUsuarios();
        usuario.setId(id+1);
        controlPersis.crearUsuario(usuario);
        
    }

    private Rol traerRol(String rolRecibido) {
        
        List<Rol> listaRol = controlPersis.traerRoles();
        
        if(listaRol != null){
            for(Rol rol : listaRol){
                if(rol.getNombreRol().equals(rolRecibido)){
                    return rol;
                }
            }
        }
        return null;
        
    }

    private int buscarUltimoIdUsuarios() {
        Usuario usu = null;
        int id = 0;
        List<Usuario> listaUsuario = controlPersis.traerUsuarios();
        
        if(listaUsuario!= null){
             usu = listaUsuario.get(listaUsuario.size()-1);
             id = usu.getId();
             return id;
                     
        }
        
        return id;
    }

    public void eliminarUsuario(int id) {
        controlPersis.eliminarUsuario(id);
    }

    public Usuario traerUsuario(int id) {
        return controlPersis.traerUsuario(id);
    }

    public void editarUsuario(String nombre, String contrasenia, String nombreRol, Usuario u) {
        Usuario usuario = u;
        Rol ro = u.getUnRol();
        
        usuario.setNombreUsuario(nombre);
        usuario.setContra(contrasenia);
        
        ro.setId(traerRol(nombreRol).getId());
        ro.setNombreRol(nombreRol);
        
        usuario.setUnRol(ro);
        
        controlPersis.editarUsuario(usuario);
        
    }

}
