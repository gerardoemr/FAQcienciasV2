<<<<<<< HEAD
package Controlador;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

@ManagedBean
@ViewScoped
public class Registrar {

     private int idusuario;
     private String nombre;
     private Date fechanac;
     private String correo;
     private Character rol;
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

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    public String agregaUsuario(){
        rol='0';
        Usuario u = new Usuario(nombre,fechanac,correo,rol,contrasena);     
        UsuarioDAO ud = new UsuarioDAO();
        ud.agrega(u);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
        return "InicioIH";
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

@ManagedBean
@ViewScoped
public class Registrar {

     private int idusuario;
     private String nombre;
     private Date fechanac;
     private String correo;
     private Character rol;
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

    public Character getRol() {
        return rol;
    }

    public void setRol(Character rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    public String agregaUsuario(){
        rol='0';
        Usuario u = new Usuario(nombre,fechanac,correo,rol,contrasena);     
        UsuarioDAO ud = new UsuarioDAO();
        ud.agrega(u);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);
        return "user/InicioIH";
    }
}
>>>>>>> 809636fb8c3f0ced8bf583118e6b7518c0f6140a
