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
import modelo.PreguntaDAO;
import modelo.Usuario;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class ActualizarPregunta {
    private int idpregunta;
     private Usuario usuario;
     private String titulo;
     private String detalles;
     private Date fecha;
     private Integer vistas;
     private boolean activa;

    public int getIdpregunta() {
        return idpregunta;
    }

    public void setIdpregunta(int idpregunta) {
        this.idpregunta = idpregunta;
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

    public Integer getVistas() {
        return vistas;
    }

    public void setVistas(Integer vistas) {
        this.vistas = vistas;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    public String editarPregunta(Pregunta pregunta) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idpregunta", pregunta.getIdpregunta());
        this.titulo = pregunta.getTitulo();
        this.detalles = pregunta.getDetalles();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fecha", pregunta.getFecha());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("vistas", pregunta.getVistas());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("activa", pregunta.isActiva());
       
        return "VistaActualizarPregunta";
    }
    
     public String actualizaPregunta() {
         boolean preguntaValida;
         FacesMessage message;
            
         if (verificaTitulo(titulo)) {
            preguntaValida = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta valida", "");
            idpregunta = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idpregunta");
            usuario =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            fecha = (Date) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fecha");
            vistas = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("vistas");
            activa = (boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("activa");
            
            Pregunta pregunta = new Pregunta(idpregunta,usuario,titulo,detalles,fecha,vistas,activa);
            PreguntaDAO pd = new PreguntaDAO();
            pd.update(pregunta);
         }
         else {
            preguntaValida = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Pregunta invalida", "No puede haber menos de 4 caracteres en una pregunta");
         }
         
         FacesContext.getCurrentInstance().addMessage(null, message);
         PrimeFaces.current().ajax().addCallbackParam("preguntaValida", preguntaValida);
         String s =  (preguntaValida) ? "/user/VerificacionDelSistema": null;
         clean();
         return s;
     }
    
    public String regreso() {
        clean();
        return "MisPreguntas";
    }
     
    private boolean verificaTitulo(String p) {
        return !(p == null || p.length() < 4);
    }
    
    private void clean(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("idpregunta");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("fecha");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("vistas");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("activa");
    }
}
