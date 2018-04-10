package Controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author gerardo
 */


/**
 * Login.java:
 * 
 *  Controlador para el caso de uso  
 * 
 * @author gerardo
 * @version 1.0, 10/04/2018
 * @see documento "Especificaci√≥n de Dise√±o de Software"
 * @since jdk 7.0 
 */
@ManagedBean
@SessionScoped
public class Login {
    
    /**
     * Cadena que almacena el correo de la sesi√≥n actual.
     */
    private String correo;
    
    /**
     * Cadena para validar con la contrase√±a registrada.
     */
    private String contrasena;
<<<<<<< HEAD
    public static boolean login = false;
    
=======

    /**
     * Getter del atributo correo
     * @return cadena del correo 
     */
>>>>>>> f84c65066bc2a9926f7fdeae62dae8ed8c76d3ff
    public String getCorreo() {
        return correo;
    }
    
    /**
     * Setter del atributo correo.
     * @param correo cadena que se guarda como correo. 
     */
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
        Login.login = true;
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.busca(correo);
        FacesContext context = FacesContext.getCurrentInstance();

        if (u == null) {
            context.addMessage(null, new FacesMessage("El correo que ingresÛ no est· registrado, intente de nuevo"));
            correo = null;
            contrasena = null;
            return null;
        } else if(u.getContrasena().equals(this.contrasena)){
            context.getExternalContext().getSessionMap().put("user", u);
            
            return "InicioIH?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage("ContraseÒa incorrecta"));
            correo = null;
            contrasena = null;
            return null; 
        }
    }

    public String logout() {
        Login.login = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
    
    public String regreso() {
        return "index";
    }
}