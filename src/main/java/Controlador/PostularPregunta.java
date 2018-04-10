/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import modelo.Usuario;

/**
 *
 * @author Oxium
 */

@ManagedBean
@RequestScoped
public class PostularPregunta {
     private int idpregunta;
     private Usuario usuario;
     private String titulo;
     private String detalles;
     private Date fecha;
     private Integer vistas;
     private char activa;

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

    public char getActiva() {
        return activa;
    }

    public void setActiva(char activa) {
        this.activa = activa;
    }
     
    /**
     * Metodo que permite la interaccion entre la vista 
     * "VistaPostularPregunta" y el modelo "Pregunta".
     * @return el direccionanmiento de la vista.
     */
    public String postulaPregunta() {
         usuario =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
         
         activa = '1';
         fecha = new Date();
         Pregunta pregunta = new Pregunta(usuario,titulo,detalles,fecha,activa);
         if (verificaTitulo(titulo)) {
            //Falta una verificacion de los detalles
            PreguntaDAO pd = new PreguntaDAO();
            pd.insert(pregunta);
         }
         else {
            return "VistaPostularPregunta";
         }
         return "VerificacionDelSistema";
     }
        
    /**
     * Metodo que nos regresa a la paguina de inicio 
     * @return la direccion de la paguina de inicio
     */
    public String regreso() {
        return "InicioIH";
    }
     
    private boolean verificaTitulo(String p) {
        return !(p == null || p.length() < 3);
    }
}
