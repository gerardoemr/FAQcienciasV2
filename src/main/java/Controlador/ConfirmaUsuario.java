/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author Juan Jose Arroyo
 */
@ManagedBean
@ViewScoped
public class ConfirmaUsuario implements Serializable{
    
    private String correo;
    private Usuario usuario;
    
    
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void obtenUsuario(){
        if(correo ==null)
            return;
        
        UsuarioDAO udao = new UsuarioDAO();
        usuario = udao.busca(correo);
        if(usuario ==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no registrado", "Registrate para confirmar tu correo"));
        }
        if(usuario.getAceptado())
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario ya confirmado", "Ya existe un usuario confirmado con ese correo"));
    }
    
    
    
    public String confirmar(){
        UsuarioDAO udao = new UsuarioDAO();
        if(usuario!=null){
            usuario.setAceptado(true);
            udao.actualiza(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario ya confirmado", "Has confirmado"));
            String asunto = "Confirmado";
            String mensaje = "Ya confirmaste el correo\n"+usuario.getCorreo();
            Mail mail = new Mail();
            mail.sendMail(asunto, mensaje, usuario.getCorreo());
        }
        return "index?faces-redirect=true";
    
    }
    
}