/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.RespuestaDAO;
import modelo.Usuario;
import org.primefaces.PrimeFaces;
/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class ActualizarRespuesta {
    private int idrespuesta;
     private Pregunta pregunta;
     private Usuario usuario;
     private String titulo;
     private String detalles;
     private Date fecha;
    
    public ActualizarRespuesta() {
        pregunta =(Pregunta) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pregunta");
    }
     
    public int getIdRespuesta() {
        return idrespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idrespuesta = idRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String editarRespuesta(Respuesta respuesta) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idrespuesta", respuesta.getIdrespuesta());
        this.titulo = respuesta.getTitulo();
        this.detalles = respuesta.getDetalles();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fecha", respuesta.getFecha());
       
        return "VistaActualizarRespuesta";
    }
    
    public String actualizaRespuesta() {
         boolean respuestaValida;
         FacesMessage message;
         idrespuesta = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idrespuesta");
         usuario =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
         fecha = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fecha");
         
         Respuesta respuesta = new Respuesta(idrespuesta,pregunta,usuario,titulo,detalles,fecha);
         if (verificaTitulo(titulo)) {
            respuestaValida = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta valida", "");
            RespuestaDAO pd = new RespuestaDAO();
            pd.update(respuesta);
         } else{
            respuestaValida = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Respuesta invalida", "No puede haber menos de 4 caracteres en una respuesta");
         }
         
         
         FacesContext.getCurrentInstance().addMessage(null, message);
         PrimeFaces.current().ajax().addCallbackParam("respuestaValida", respuestaValida);
         if (respuestaValida)
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pregunta");
         String s =  (respuestaValida) ? "/user/VerificacionDelSistema": null;
         clean();
         return s;
     }
    
    public boolean esDelUsuario(Respuesta r) {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return r.getUsuario().getIdusuario() == usuario.getIdusuario();
    }
     
    private boolean verificaTitulo(String r) {
        return !(r == null || r.length() < 4);
    }
    
    private void clean(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("idrespuesta");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("fecha");
    }
}
