/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Pays;
import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Voyage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class VoyageDAOJpa implements VoyageDAO {
    
    @Override
    public void creerVoyage(Voyage voyage){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(voyage);
    }
    
    @Override
    public void supprimerVoyage(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Voyage voyage;
            try {
                voyage = em.getReference(Voyage.class, id);
                voyage.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The voyage with id " + id + " no longer exists.", e);
            }
        em.remove(voyage);
    }
    
    @Override
    public Voyage modifierVoyage(Voyage voyage) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            voyage = em.merge(voyage);
        } catch(Exception e){
             Long id = voyage.getId();
                if (trouverVoyage(id) == null) {
                    throw new NonexistentEntityException("The voyage with id " + id + " no longer exists.");
                }
        }
        return voyage;
    }
    
    @Override
    public Voyage trouverVoyage(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Voyage.class, id);
    }
    
    @Override
    public List<Voyage> listerVoyages(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT c FROM Voyage c");
        List<Voyage> listeVoyage=query.getResultList();
        return listeVoyage;
    }
    
    @Override
    public List<Voyage> listerVoyageComporantPays(List<Pays> listePays){ //Fonctionne
        return listerVoyageComporantPays(listePays, true, true);
    }
    
    
    @Override
    public List<Voyage> listerVoyageComporantPays(List<Pays> listePays, boolean circuit, boolean sejour){ //Fonctionne
        EntityManager em = JpaUtil.obtenirEntityManager();
        if(listePays.isEmpty()  || (!circuit && !sejour))
        {
            return new ArrayList<Voyage>();
        }
        List<Voyage> resultRequete;
        //creation de la string query
        if(circuit && sejour){
            String queryS = "SELECT v FROM Voyage v WHERE ";
            for(int i=0; i<listePays.size(); i++){ //Pour chaque pays
                if(i==listePays.size()-1)
                    queryS += "v.pays=:pays"+i;
                else
                   queryS += "v.pays=:pays"+i+" AND ";
            }
            Query query=em.createQuery(queryS);
            System.out.println(queryS);
            //affectation des parametres query
            for(int i=0; i<listePays.size(); i++)
            {
                String param = "pays"+i;
                query.setParameter(param, listePays.get(i));
            }
            resultRequete=query.getResultList();
        }
        else if(circuit && !sejour){
            String queryS = "SELECT c FROM Circuit c WHERE ";
            for(int i=0; i<listePays.size(); i++){ //Pour chaque pays
                if(i==listePays.size()-1)
                    queryS += "c.pays=:pays"+i;
                else
                   queryS += "c.pays=:pays"+i+" AND ";
            }
            Query query=em.createQuery(queryS);
            System.out.println(queryS);
            //affectation des parametres query
            for(int i=0; i<listePays.size(); i++)
            {
                String param = "pays"+i;
                query.setParameter(param, listePays.get(i));
            }
            resultRequete=query.getResultList();
        }
        else {
            String queryS = "SELECT s FROM Sejour s WHERE ";
            for(int i=0; i<listePays.size(); i++){ //Pour chaque pays
                if(i==listePays.size()-1)
                    queryS += "s.pays=:pays"+i;
                else
                   queryS += "s.pays=:pays"+i+" AND ";
            }
            Query query=em.createQuery(queryS);
            System.out.println(queryS);
            //affectation des parametres query
            for(int i=0; i<listePays.size(); i++)
            {
                String param = "pays"+i;
                query.setParameter(param, listePays.get(i));
            }
            resultRequete=query.getResultList();
        }
        return resultRequete; 
    }
    
}
