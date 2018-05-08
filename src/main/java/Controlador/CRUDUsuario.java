package Controlador;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author gerardo
 */

@ManagedBean
@RequestScoped
public class CRUDUsuario {
    
    private List<Usuario> usuarios;
    

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
   
    @PostConstruct
    public void ver() {
        UsuarioDAO lib = new UsuarioDAO();
        usuarios = lib.usuarios();
    }
    
    /**
     * Método que elimina al usuario u de la base de datos.
     * @param u
     * @throws IOException 
     */
    public void elimina(Usuario u) throws IOException {
        UsuarioDAO dao = new UsuarioDAO();
        dao.elimina(u);
        recarga();
    }
    
    /**
     * Método que actualiza la vista verUsuario.xhtml
     * @throws IOException 
     */
    public void recarga() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
