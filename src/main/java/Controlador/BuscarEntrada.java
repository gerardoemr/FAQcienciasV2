/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import modelo.Respuesta;
import modelo.RespuestaDAO;

//Etiqueta para decirle a jsf que esta clase es un controlador
@ManagedBean
//Etiqueta para que viva este bean hasta que se cambie de pagina. util para jax
@SessionScoped


/**
 *
 * @author Cal
 */
public class BuscarEntrada implements Serializable {
    private List<Pregunta> preguntasResult;
     
    private List<Pregunta> respuestasResult;
    
    private Pregunta selectedPregunta;
    
    private Respuesta selectedRespuesta;
    
    @ManagedProperty("#{preguntaDAO}")
    private PreguntaDAO pdao;
    
    @ManagedProperty("#{respuestaDAO}")
    private RespuestaDAO rdao;
    
    private String busqueda;
    
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    /**
     * peticion que guarda un proyecto 
     * @return 
     */
    public String init(){
        pdao = new PreguntaDAO();
        rdao = new RespuestaDAO();
        preguntasResult = pdao.buscar(busqueda);
        respuestasResult = rdao.buscar(busqueda);
    
        for (Pregunta p : respuestasResult){
            if (!preguntasResult.contains(p)){
                preguntasResult.add(p);
            }
        }
        return "ResultadosIH?faces-redirect=true";
    }

    public List<Pregunta> getPreguntasResult() {
        return preguntasResult;
    }

    public void setPreguntasResult(List<Pregunta> preguntasResult) {
        this.preguntasResult = preguntasResult;
    }

    public List<Pregunta> getRespuestasResult() {
        return respuestasResult;
    }

    public void setRespuestasResult(List<Pregunta> respuestasResult) {
        this.respuestasResult = respuestasResult;
    }
    

 
    public void setPdao(PreguntaDAO pdao) {
        this.pdao = pdao;
    }
    
    public void setRdao(RespuestaDAO rdao) {
        this.rdao = rdao;
    }
 
    public Pregunta getSelectedPregunta() {
        return selectedPregunta;
    }
    
    public Respuesta getSelectedRespuesta() {
        return selectedRespuesta;
    }
 
    public void setSelectedPregunta(Pregunta selectedPregunta) {
        this.selectedPregunta = selectedPregunta;
    }
    
    public void setSelectedRespuesta(Respuesta selectedRespuesta) {
        this.selectedRespuesta = selectedRespuesta;
    }
    
}