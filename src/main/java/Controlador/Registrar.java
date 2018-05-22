/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.util.Date;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

@ManagedBean
@RequestScoped
public class Registrar implements Serializable{

     private int idusuario;
     private String nombre;
     private Date fechanac;
     private String correo;
     private Character rol;
     private String contrasena;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        Usuario u;
        UsuarioDAO ud = new UsuarioDAO();
        u = ud.busca(correo);
   
      
        if(u==null){
            Boolean administrador = false;
            Boolean aceptado = false;
            u = new Usuario(nombre,fechanac,correo,administrador,contrasena,aceptado);     
            ud.agrega(u);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", u);          
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario nuevo", "Has registrado un usuario"));
            String subject = "Registro en FAQciencias";
            String mensaje = "Te has registrado con \n " +correo+"confirmar en el link \n" +"http://localhost:8084/user/Confirma.xhtml?correo="+u.getCorreo();
            Mail mail = new Mail();
            mail.sendMail(subject,mensaje,this.correo);
            this.msg = u.getCorreo();
            this.contrasena = "";
            this.nombre="";            
            this.correo = "";            
            return "VerificacionCorreo";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario Repetido", "Ya existe un usuario con ese correo"));
            this.msg = u.getCorreo();
            this.contrasena = "";
            this.nombre="";
            this.correo = "";
            return "";
        }
        
        
    }
        
}
