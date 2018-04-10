package Controlador;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.primefaces.PrimeFaces;

/**
 *
 * @author gerardo
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{
    
    private String correo;
    private String contrasena;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String login() throws IOException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.busca(correo);
        FacesMessage message;
        FacesContext context = FacesContext.getCurrentInstance();
        boolean loggedIn;
         
        
        if(u != null && contrasena.equals(u.getContrasena())) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", u.getNombre());
            context.getExternalContext().getSessionMap().put("user", u);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales inválidas");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        String s =  (loggedIn) ? "/user/InicioIH?faces-redirect=true": null;
        System.out.println(s);
        return s;
    }
    
/*
    public String login() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.busca(correo);
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;

        if (u == null) {
            context.addMessage(null, new FacesMessage("El correo que ingresó no está registrado, intente de nuevo"));
            correo = null;
            contrasena = null;
            loggedIn = false;
        } else if(u.getContrasena().equals(this.contrasena)){
            context.getExternalContext().getSessionMap().put("user", u);
            loggedIn = true;
        } else {
            context.addMessage(null, new FacesMessage("Contraseña incorrecta"));
            correo = null;
            contrasena = null;
            loggedIn = false;
        }
        
        String s =  (loggedIn) ? "/user/InicioIH?faces-redirect=true": null;
        return s;
    }
*/
    public String logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        return "index.xhtml";
    }
}
