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
 * @author Oxium
 */

@ManagedBean
@RequestScoped
public class PostularRespuesta {
     private int idRespuesta;
     private Pregunta pregunta;
     private Usuario usuario;
     private String titulo;
     private String detalles;
     private Date fecha;
    
    public PostularRespuesta() {
        pregunta =(Pregunta) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pregunta");
    }
     
    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
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
     
    /**
     * Permite la interaccion entre la vista
     * "VistaPostularRespuesta" y el modelo "Respuesta".
     * @return - el direccionamiento de la vista.
     */
    public String postulaRespuesta() {
         boolean respuestaValida;
         FacesMessage message;
         usuario =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

         fecha = new Date();
         
         Respuesta respuesta = new Respuesta(pregunta,usuario,titulo,detalles,fecha);
         if (verificaTitulo(titulo)) {
            respuestaValida = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Respuesta valida", "");
            RespuestaDAO pd = new RespuestaDAO();
            pd.insert(respuesta);
         } else{
            respuestaValida = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Respuesta invalida", "No puede haber menos de 4 caracteres en una respuesta");
         }
         
         
         FacesContext.getCurrentInstance().addMessage(null, message);
         PrimeFaces.current().ajax().addCallbackParam("respuestaValida", respuestaValida);
         if (respuestaValida)
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pregunta");
         String s =  (respuestaValida) ? "/user/VerificacionDelSistema": null;
         return s;
     }
    
    public String regreso() {
        pregunta =(Pregunta) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pregunta");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pregunta");
        return "InicioIH";
    }
     
    private boolean verificaTitulo(String r) {
        return !(r == null || r.length() < 4);
    }
}