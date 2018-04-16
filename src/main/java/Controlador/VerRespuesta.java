<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Pregunta;
import modelo.PreguntaDAO;
import modelo.Respuesta;
import modelo.RespuestaDAO;
import javax.faces.context.FacesContext;
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
    
    public String listarRespuestas(Pregunta p){
    this.pregunta=p;
    FacesContext context = FacesContext.getCurrentInstance();
     context.getExternalContext().getSessionMap().put("pregunta", p);
    RespuestaDAO lib = new RespuestaDAO();
    respuestas= lib.respuestas(pregunta);
    return "verrespuestas";
    }

}

=======
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
}
>>>>>>> 809636fb8c3f0ced8bf583118e6b7518c0f6140a
