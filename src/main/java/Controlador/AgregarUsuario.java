
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import modelo.Usuario;
import modelo.UsuarioDAO;

@ManagedBean
@ViewScoped
public class AgregarUsuario {

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
        Usuario u = new Usuario();
        u.setIdusuario(idusuario);        
        u.setNombre(nombre);
        u.setFechanac(fechanac);
        u.setCorreo(correo);
        u.setRol(rol);
        u.setContrasena(contrasena);
        UsuarioDAO ud = new UsuarioDAO();
        ud.agrega(u);
        return "/user/InicioIH?faces-redirect=true";
    }
}
