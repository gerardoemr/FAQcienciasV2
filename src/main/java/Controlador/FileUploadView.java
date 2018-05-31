package Controlador;

/**
 *
 * @author gerardo
 */
import java.io.*;
import java.util.Base64;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class FileUploadView implements Serializable {  
    
    private String correo;
    
    
    public void handleFileUpload(FileUploadEvent event) {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
       
        UsuarioDAO udao = new UsuarioDAO();
        byte[] archivo = Base64.getEncoder().encode(event.getFile().getContents());
        //System.out.println(new String(archivo));
        //System.out.println(event.getFile().getContentType());
        u.setImagen(new String(archivo));
        u.setFormato(event.getFile().getContentType());
        udao.actualiza(u);
        try{
            recarga(message);
        }catch(IOException e){    
        }
    }
     
     /**
     * Método que actualiza la vista verUsuario.xhtml
     * @throws IOException 
     */
    public void recarga(FacesMessage message) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
}  