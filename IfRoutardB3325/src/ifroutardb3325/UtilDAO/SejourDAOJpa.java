/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Client;
import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Sejour;
import ifroutardb3325.entites.Voyage;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class SejourDAOJpa implements SejourDAO {
    
    @Override
    public void creerSejour(Sejour sejour){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(sejour);
    }
    
    @Override
    public void supprimerSejour(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Sejour sejour;
            try {
                sejour = em.getReference(Sejour.class, id);
                sejour.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The sejour with id " + id + " no longer exists.", e);
            }
        em.remove(sejour);
    }
    
    @Override
    public Sejour modifierSejour(Sejour sejour) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            sejour = em.merge(sejour);
        } catch(Exception e){
             Long id = sejour.getId();
                if (trouverSejour(id) == null) {
                    throw new NonexistentEntityException("The sejour with id " + id + " no longer exists.");
                }
        }
        return sejour;
    }
    
    @Override
    public Sejour trouverSejour(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Sejour.class, id);
    }
    
    @Override
    public List<Voyage> listerVoyages(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT c FROM Sejour c");
        List<Voyage> listeSejour=query.getResultList();
        return listeSejour;
    }
}
