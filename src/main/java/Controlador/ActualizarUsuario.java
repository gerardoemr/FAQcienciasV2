/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.RespuestaDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.primefaces.PrimeFaces;
/**
 *
 * @author JJKid
 */
@ManagedBean
@RequestScoped

public class ActualizarUsuario {
    
    private int idusuario;
     private String nombre;
     private Date fechanac;
     private String correo;
     private Boolean administrador;
     private String contrasena;
     private Boolean aceptado;
     private String imagen;
     
    public ActualizarUsuario(){
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        this.nombre = user.getNombre();
        this.fechanac = user.getFechanac();
        this.contrasena = user.getContrasena();
        this.correo = user.getCorreo();
        this.imagen = user.getImagen();
    }
     
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
     
     public String actualizarUsuario(Usuario user) {                
         Usuario usuario = new Usuario (user.getIdusuario(), nombre, fechanac, user.getCorreo(), false, contrasena, true, imagen, user.getFormato());
         UsuarioDAO ud = new UsuarioDAO();
         ud.actualiza(usuario);
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
        return "InicioIH";
     }
    
}
