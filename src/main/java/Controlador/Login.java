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
 * Login.java:
 * 
 *  Controlador para el caso de uso de iniciar y cerrar sesión
 * 
 * @author gerardo
 * @version 2.1, 8/05/2018
 * @see documento "Especificación de Diseño de Software"
 * @since jdk 7.0 
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{
    
    /**
     * Cadena que almacena el correo de la sesión actual.
     */
    private String correo;
    
    /**
     * Cadena para validar con la contraseña registrada.
     */
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
    
    /**
     * Método principal para iniciar sesión
     * @return cadena que redirige al usuario a la vista InicioIH si la autenticación fue exitosa.
     * En otro caso se informa el error que haya ocurrido (credenciales inválidas o el usuario debe verificar correo.)
     * @throws IOException 
     */
    public String login() throws IOException {
        UsuarioDAO dao = new UsuarioDAO(); 
        Usuario u = dao.busca(correo);//en esta línea se busca se verifica si el correo es de un usuario registrado.
        FacesMessage message; //se crea este mensaje para mandar alertas en la vista.
        FacesContext context = FacesContext.getCurrentInstance();
        boolean loggedIn = false;//bandera para determinar si el inicio de sesión fue exitoso.
        String s = null;
         
        if(u!= null){ 
            if(u.getAceptado()){
                if(contrasena.equals(u.getContrasena())){ //credenciales válidas
                    loggedIn = true;
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", u.getNombre());//mensaje de bienvenida
                    context.getExternalContext().getSessionMap().put("user", u);//se agrega la información del usuario al contexto.
                    s = "/user/InicioIH?facesRedirect=true";//se redirige al usuario a la página InicioIH
                }
                else{//credenciales inválidas
                    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Credenciales inválidas");//mensaje de error
                }
            }
            else{//El usuario no ha verificado su cuenta
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Verifique su cuenta");
            }
        }
        else{//El usuario no está registrado en la base de datos
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Correo no registrado");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);     
        return s;
    }
    
    /**
     * Método para cerrar sesión.
     * @return cadena que cierra la sesión del usuario actual y lo redirige a la página index.
     * @throws IOException 
     */
    public String logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();//cierra la sesión
        return "/index?facesRedirect=true";//se redirige al usuario a la página principal (index.xhtml)
    }
}