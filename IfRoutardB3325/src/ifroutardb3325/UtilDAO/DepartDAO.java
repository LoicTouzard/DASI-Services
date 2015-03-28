/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Depart;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard & ggouzi
 */
public class DepartDAO {
    
    public static void creerDepart(Depart depart){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(depart);
    }
    
    public static void supprimerDepart(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Depart depart;
            try {
                depart = em.getReference(Depart.class, id);
                depart.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The depart with id " + id + " no longer exists.", e);
            }
        em.remove(depart);
    }
    
    public static Depart modifierDepart(Depart depart) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            depart = em.merge(depart);
        } catch(Exception e){
             Long id = depart.getId();
                if (trouverDepart(id) == null) {
                    throw new NonexistentEntityException("The depart with id " + id + " no longer exists.");
                }
        }
        return depart;
    }
    
    public static Depart trouverDepart(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Depart.class, id);
    }
    
    public static List<Depart> listerProchainsDeparts() {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query;
        query = em.createQuery("SELECT DISTINCT d FROM Depart d WHERE d.jour>CURRENT_DATE ORDER BY d.jour");
        List<Depart> listeProchainsDeparts=query.getResultList();
        return listeProchainsDeparts;
    }
   
}
