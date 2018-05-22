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
import javax.faces.context.FacesContext;
import modelo.PreguntaDAO;
import modelo.UsuarioDAO;
import modelo.Usuario;
import modelo.Respuesta;
/**
 *
 * @author Admin
 */

@ManagedBean
@RequestScoped
public class VerUsuario {
    private Usuario usuario;
    public Usuario getUsuario() {
        return this.usuario;
    }


    
    
    public String darUsuario(Usuario u){
    this.usuario=u;
    return "verusuarioajeno";
    }
}