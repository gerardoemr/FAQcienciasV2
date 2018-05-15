/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

@ManagedBean
@ViewScoped
public class Registrar implements Serializable {

     private int idusuario;
     private String nombre;
     private Date fechanac;
     private String correo;
     private Boolean administrador;
     private Boolean aceptado;
     private String contrasena;

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

    public Boolean getAceptado() {
        return aceptado;
    }

    public void setAceptado(Boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String agregaUsuario(){
        administrador = false;
        aceptado = false;
        Usuario u = new Usuario(nombre,fechanac,correo,administrador,contrasena,aceptado);     
        UsuarioDAO ud = new UsuarioDAO();
        ud.agrega(u);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
        return "VerificacionCorreo";
    }
}