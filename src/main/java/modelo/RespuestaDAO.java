/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author daniel
 */
public class RespuestaDAO {
    private SessionFactory sessionFactory;
    
    
public RespuestaDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
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
}