<<<<<<< HEAD
package Controlador;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FilterSession.java:
 * 
 *  Controlador para el caso de uso iniciar y cerrar sesión.  
 *  Protege las vistas a las que sólo puede accedes un usario registrado. 
 *  Dichas vistas están ubicadas en la carpeta /user
 * 
 * @author gerardo
 * @version 1.0, 10/04/2018
 * @see documento "Especificación de Diseño de Software"
 * @since jdk 7.0 
 */
@WebFilter("/user/*")
public class FilterSession implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, 
                         ServletResponse res, 
                         FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        
        //cache
        if (!request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
        } 
        
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.xhtml");// No logged-in user found, so redirect to login page.
        }
        else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
=======
package Controlador;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FilterSession.java:
 * 
 *  Controlador para el caso de uso iniciar y cerrar sesión.  
 *  Protege las vistas a las que sólo puede accedes un usario registrado. 
 *  Dichas vistas están ubicadas en la carpeta /user
 * 
 * @author gerardo
 * @version 1.0, 10/04/2018
 * @see documento "Especificación de Diseño de Software"
 * @since jdk 7.0 
 */
@WebFilter("/user/*")
public class FilterSession implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, 
                         ServletResponse res, 
                         FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        
        //cache
        if (!request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
        } 
        
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.xhtml");// No logged-in user found, so redirect to login page.
        }
        else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }
>>>>>>> 809636fb8c3f0ced8bf583118e6b7518c0f6140a
}