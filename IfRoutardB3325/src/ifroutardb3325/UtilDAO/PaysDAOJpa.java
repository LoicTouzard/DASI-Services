/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Pays;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class PaysDAOJpa implements PaysDAO {
    
    @Override
    public void creerPays(Pays pays){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(pays);
    }
    
    @Override
    public void supprimerPays(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Pays pays;
            try {
                pays = em.getReference(Pays.class, id);
                pays.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The pays with id " + id + " no longer exists.", e);
            }
        em.remove(pays);
    }
    
    @Override
    public Pays modifierPays(Pays pays) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            pays = em.merge(pays);
        } catch(Exception e){
             Long id = pays.getId();
                if (trouverPays(id) == null) {
                    throw new NonexistentEntityException("The pays with id " + id + " no longer exists.");
                }
        }
        return pays;
    }
    
    @Override
    public Pays trouverPays(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Pays.class, id);
    }
    
    @Override
    public List<Pays> listerPays(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT p FROM Pays p");
        List<Pays> listePays=query.getResultList();
        return listePays;
    }
}
