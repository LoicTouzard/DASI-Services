/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Devis;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class DevisDAO {
    
    public static void creerDevis(Devis devis){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(devis);
    }
    
    public static void supprimerDevis(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Devis devis;
            try {
                devis = em.getReference(Devis.class, id);
                devis.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The devis with id " + id + " no longer exists.", e);
            }
        em.remove(devis);
    }
    
    public static Devis modifierDevis(Devis devis) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            devis = em.merge(devis);
        } catch(Exception e){
             Long id = devis.getId();
                if (trouverDevis(id) == null) {
                    throw new NonexistentEntityException("The devis with id " + id + " no longer exists.");
                }
        }
        return devis;
    }
    
    public static Devis trouverDevis(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Devis.class, id);
    }
    
    public static List<Devis> listerDevis(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT d FROM Devis d");
        List<Devis> listeDevis=query.getResultList();
        return listeDevis;
    }
}
