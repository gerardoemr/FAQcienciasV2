/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Respuesta;
import modelo.RespuestaDAO;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class EliminarRespuesta {
     
     public void eliminaRespuesta(Respuesta respuesta) throws IOException {
        RespuestaDAO rd = new RespuestaDAO();
        rd.delete(respuesta);
        recarga();
     }
     
    public void recarga() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}