package Controlador;

/**
 *
 * @author gerardo
 */
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class FileUploadView implements Serializable {  

    private UploadedFile foto;
    private String msg;

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public void validaFoto(Usuario u){
        UsuarioDAO udao = new UsuarioDAO();
        if(this.foto != null ){
                String fileName = foto.getFileName();
                //Verifico la extension
                if(fileName.contains(".")){
                    String[] extension = fileName.split("\\.",2);
                    System.out.println(fileName+extension.length+extension[1]);
                    //Si la extension es correcta la guardamos
                    if(extension[1].compareTo("png")==0||extension[1].compareTo("jpg")==0||extension[1].compareTo("jpge")==0){
                        u.setImagen("images/"+u.getNombre()+fileName);
                        this.subeFoto(u);
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imagen no permtida", "extensión inválida"+extension[1]));
                        this.msg = fileName;
                        this.foto =null;
                    }
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Imagen no permtida", "la extensión "));
                    this.msg = fileName;
                    this.foto =null;
                }
            
            }else{
                u.setImagen("images/usuario.png");
                System.out.println("No se cargó la foto");
            }
        udao.actualiza(u);
    }
    
    public void subeFoto(Usuario u){
        String fileName = u.getNombre()+foto.getFileName();
        //String contentType = foto.getContentType();
        
        try {
            InputStream entrada = foto.getInputstream();
            //obtenemos la ruta de la aplicacion
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destination = (servletContext.getRealPath("/"))+"images/";
            System.out.println(destination);
            OutputStream out = new FileOutputStream(new File(destination + fileName));
            int read = 0; 
            byte[] bytes = new byte[1024]; 
            while ((read = entrada.read(bytes)) != -1) {
                System.out.println(read);
                out.write(bytes, 0, read);
            }
            entrada.close();
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
   
}  