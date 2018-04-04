package Controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import modelo.Usuario;
import modelo.UsuarioDAO;

/**
 *
 * @author gerardo
 */
@ManagedBean
@ViewScoped
public class VerUsuario implements Serializable{
     private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    @PostConstruct
    public void ver() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarios = dao.usuarios();
    }
}
