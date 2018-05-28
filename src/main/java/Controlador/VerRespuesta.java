/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Pregunta;
import modelo.Respuesta;
import modelo.RespuestaDAO;
import javax.faces.context.FacesContext;
import modelo.PreguntaDAO;
import modelo.Usuario;
/**
 *
 * @author Admin
 */

@ManagedBean
@RequestScoped
public class VerRespuesta {
    private List<Respuesta> respuestas;
    private Pregunta pregunta;

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }
    
    public List<Respuesta> respuestas(){
        pregunta =(Pregunta) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pregunta");
        PreguntaDAO a=new PreguntaDAO();
        RespuestaDAO lib = new RespuestaDAO();
        return lib.respuestas(pregunta);
    }
    
    public String listarRespuestas(Pregunta p){
    this.pregunta=p;
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getSessionMap().put("pregunta", p);
    PreguntaDAO a=new PreguntaDAO();
    a.aumentarVista(pregunta);
    RespuestaDAO lib = new RespuestaDAO();
    respuestas= lib.respuestas(pregunta);
    
    return "verrespuestas";
    }
    
    public List<Pregunta> respuestasUsuario(int idusuario) {
        RespuestaDAO rd = new RespuestaDAO();
        return rd.preguntas(idusuario);
    }
}