package modelo;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * UsuarioDAO.java
 * 
 * Clase que permite realizar consultas sobre la tabla Usuario en la base de datos.
 * 
 * @author gerardo
 */
public class UsuarioDAO {
     private SessionFactory sessionFactory;
    
    public UsuarioDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
   
   /**
    * Método que regresa una lista con todos los usuarios registrados en la base de datos.
    * @return List<Usuario>
    */
   public List<Usuario> usuarios() {
        List<Usuario> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transacción
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Usuario";
            Query query = session.createQuery(hql);
            result = (List<Usuario>)query.list();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
   
    /**
     * Regresa el Usuario que tiene el correo recibido como parámetro.
     * @return Usuario
     */
    public Usuario busca(String correo) {
        Usuario result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = " from Usuario where correo like '%"+correo+"%'";
            Query query = session.createQuery(hql);
            //query.setParameter("correo", correo);
            result = (Usuario)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
            //si hay un problema regresamos la base aun estado antes de la consulta
            if (tx!=null){
                tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
            //cerramos la session
            session.close();
        }
        return result;
    }
   
    public void agrega(Usuario em){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
           session.persist(em);
           
           tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
    }
    
    /**
     * Método que elimina un usuario de la base de datos.
     * Las preguntas y respuestas hechas por el usuario que se va a eliminar se le asignan a admin.
     * @param id del usuario que se desea eliminar
     */
    public void elimina(Usuario u){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String id = String.valueOf(u.getIdusuario());
            String hql = "from Usuario where idusuario = " + id; 
            
            Query query = session.createQuery(hql);
            
            Query admin = session.createQuery("from Usuario where idusuario = 3");
            
            Query preguntas = session.createQuery("from Pregunta where idusuario = " + id);
            Query respuestas = session.createQuery("from Respuesta where idusuario = " + id);
            
            List<Usuario> l = query.list(); //lista que contiene el usuario a eliminar
            List<Usuario> a = admin.list(); //lista que contiene al administrador
            
            List<Pregunta> p = preguntas.list(); //lista que contiene las preguntas hechas por el usuario.
            List<Respuesta> r = respuestas.list(); // lista que contiene las respuestas hechas por el usuario.

            Usuario nuevo = a.get(0);//objeto que representa al administrador
            
            for(Pregunta x: p){
                x.setUsuario(nuevo);//asigna las preguntas del usuario al administrador
            }
            
            for(Respuesta x: r){
                x.setUsuario(nuevo);//asigna las respuestas del usuario al administrador
            }
            
            Usuario em = l.get(0);//objeto que representa el usuario a eliminar
            
           
            /*
            tr = session.getTransaction();
            tr.begin();
            updateQuery.executeUpdate();
            tr.commit;
            */
            
            
            session.delete(em);
            
            tx.commit();
            
        }catch(Exception e){
            if (tx!=null)
            {
                tx.rollback();
            }
           e.printStackTrace(); 
        }finally{
            //cerramos la session
            session.close();
        }
    }
    
    /**
     * Método por ser implementado en la segunda iteración.
     * Actualiza los atributos de un Usuario en la base de datos.
     * @param id
     * @param n
     * @param of
     * @param fun
     * @param empl
     * @param con
     * @param pres 
     */
    public void actualiza(String id, String n, String of, Date fun, Short empl, String con, String pres){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String hql = "from Usuario where idE = " + id;
            
            

            Query query = session.createQuery(hql);

            List<Usuario> l = query.list();

            Usuario em = l.get(0);
            
            //Implementar actualización de atributos
           
           session.update(em);
          
           tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }  
    }
    
}