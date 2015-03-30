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
import ifroutardb3325.entites.Conseiller;
import ifroutardb3325.entites.Devis;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class ConseillerDAOJpa implements ConseillerDAO {
    @Override
    public void creerConseiller(Conseiller conseiller){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(conseiller);
    }
    
    @Override
    public void supprimerConseiller(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Conseiller conseiller;
            try {
                conseiller = em.getReference(Conseiller.class, id);
                conseiller.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The conseiller with id " + id + " no longer exists.", e);
            }
        em.remove(conseiller);
    }
    
    @Override
    public Conseiller modifierConseiller(Conseiller conseiller) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            conseiller = em.merge(conseiller);
        } catch(Exception e){
             Long id = conseiller.getId();
                if (trouverConseiller(id) == null) {
                    throw new NonexistentEntityException("The conseiller with id " + id + " no longer exists.");
                }
        }
        return conseiller;
    }
    
    @Override
    public Conseiller trouverConseiller(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Conseiller.class, id);
    }
    
    @Override
    public List<Conseiller> listerConseiller(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query;
        query = em.createQuery("SELECT c FROM Conseiller c");
        List<Conseiller> listeConseiller=query.getResultList();
        return listeConseiller;
    }
    
    @Override
    public Conseiller getConseillerLibre(){
        Conseiller conseillerLibre=null;
        int minClient=Integer.MAX_VALUE;
        for( Conseiller leConseiller : listerConseiller() ){   //On parcours la liste de devis du ième conseiller de la listeConseiller
            HashSet <Client> listeClientsDuConseiller=new HashSet();
            for(Devis leDevis:leConseiller.getListeDevis()){
               listeClientsDuConseiller.add(leDevis.getClient());
            }
            if(listeClientsDuConseiller.size() < minClient){
                minClient=listeClientsDuConseiller.size();
                conseillerLibre=leConseiller;
            }
        }
        return conseillerLibre;
    }
}
