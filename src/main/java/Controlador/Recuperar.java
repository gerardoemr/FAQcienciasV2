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
 * @author gerardo
 */

@ManagedBean
@ViewScoped
public class Recuperar implements Serializable{
        
    private String correo;
    private Usuario usuario;
    private Boolean desactivado;

    public Boolean getDesactivado() {
        return desactivado;
    }

    public void setDesactivado(Boolean desactivado) {
        this.desactivado = desactivado;
    }
    
    public void desactivarBoton(){
        if (usuario != null){
            desactivado = true;
        }else{
            desactivado = false;
        }
    }

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
   
    public void recuperar(){
        if(correo ==null)
            return;
       
        UsuarioDAO udao = new UsuarioDAO();
        usuario = udao.busca(correo);
       
        if(usuario == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no registrado", "Registrate para confirmar tu correo"));
            return;
        }
        
        if(usuario!=null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correo enviado", "Hemos enviado la contraseña a tu correo"));
            String asunto = "FAQciencias";
            String mensaje = "Tu contraseña para el foro es:\n"+usuario.getContrasena();
            Mail mail = new Mail();
            mail.sendMail(asunto, mensaje, usuario.getCorreo());
            desactivado = false;
        }
    }
    
}