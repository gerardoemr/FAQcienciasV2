package modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Oxium
 */
public class RespuestaDAO {
    private SessionFactory sessionFactory;
    
    public RespuestaDAO () {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * Inserta una nueva resuesta a la base de datos.
     * @param p - la respuesta a insertar.
     */
    public void insert(Respuesta p){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
           session.persist(p);
           
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

    public List<Respuesta> respuestas(Pregunta p) {
        List<Respuesta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        String a=String.valueOf(p.getIdpregunta());
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "from Respuesta where idpregunta="+a;
            Query query = session.createQuery(hql);
            result = (List<Respuesta>)query.list();
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
    
    public List<Pregunta> buscar(String busqueda){
        List<Pregunta> result = null;
        // arbrimos la sesion son sessionFactory 
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            //iniciamos la transaccion, la consulta a realizar
            tx = session.beginTransaction();
            //Escribimos la consulta en HQL
            String hql = "select p from Pregunta as p"
                    + " where p.idpregunta in ( "
                    + " select r.pregunta.idpregunta from Respuesta as  r"
                    + " where r.titulo like '%"+ busqueda +"%'"
                    + " or r.detalles like '%"+ busqueda +"%')";
            Query query = session.createQuery(hql);
            result = (List<Pregunta>)query.list();
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
}
