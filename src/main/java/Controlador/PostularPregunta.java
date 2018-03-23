/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Date;
import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import modelo.Usuario;

/**
 *
 * @author Oxium
 */

//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@ViewScoped
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
     
     public void postulaPregunta() {
         Pregunta pregunta = new Pregunta(idpregunta,usuario,titulo,fecha,activa);
         PreguntaDAO pd = new PreguntaDAO();
         pd.insert(pregunta);
     }
     
     private boolean verifica(Pregunta p) {
         return false;
     }
}
