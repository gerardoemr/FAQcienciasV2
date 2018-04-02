package Controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author gerardo
 */
@ManagedBean
@SessionScoped
public class Login {
    
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
    
    public String login() {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.busca(correo);
        FacesContext context = FacesContext.getCurrentInstance();

        if (u == null) {
            context.addMessage(null, new FacesMessage("El correo que ingresó no está registrado, intente de nuevo"));
            correo = null;
            contrasena = null;
            return null;
        } else if(u.getContrasena().equals(this.contrasena)){
            context.getExternalContext().getSessionMap().put("user", u);
            
            return "InicioIH?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage("Contraseña incorrecta"));
            correo = null;
            contrasena = null;
            return null; 
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}