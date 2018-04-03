/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Oxium
 */

@ManagedBean
@SessionScoped
public class InicioController {
    public String loginIH () {
        return "LoginIH?faces-redirect=true";
    }
    
    public String postularEnIndex () {
        return "LoginIH?faces-redirect=true";
    }
    
    public String postular() {
        return "VistaPostularPregunta";
    }
}
