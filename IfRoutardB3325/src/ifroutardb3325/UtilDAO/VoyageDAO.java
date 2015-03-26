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
import java.util.Hashtable;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class VoyageDAO {
    
    public static void creerVoyage(Voyage voyage){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(voyage);
    }
    
    public static void supprimerVoyage(Long id) throws NonexistentEntityException{
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
    
    public static Voyage modifierVoyage(Voyage voyage) throws NonexistentEntityException{
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
    
    public static Voyage trouverVoyage(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Voyage.class, id);
    }
    
    public static List<Voyage> listerVoyages(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT c FROM Voyage c");
        List<Voyage> listeVoyage=query.getResultList();
        return listeVoyage;
    }
    
    public static List<Voyage> listerVoyageComporantPays(List<Pays> listePays){ //Fonctionne pas
        EntityManager em = JpaUtil.obtenirEntityManager();
        if(listePays.isEmpty())
        {
            return new ArrayList<Voyage>();
        }
        /*
        //construction de la requete
        String queryString = "SELECT v FROM Voyage v WHERE v.pays ";
        int i =0;
        for(i=0; i< listePays.size() -1 ; i++){
            queryString+="IN(:arg"+i+") AND";
        }
        queryString+="IN(:arg"+i+");";
        
        Query query=em.createQuery(queryString); //Faire le if date > ....
        
        //ajout des parametres
        for(i=0; i < listePays.size(); i++){
            query.setParameter("arg"+Integer.toString(i), listePays.get(i).getId());
        }
        List<Voyage> listeVoyage=query.getResultList();*/
        List<Voyage> listeVoyage = new ArrayList<Voyage>();
        
        Hashtable<Long, Integer> voyagesOccurences= new Hashtable<Long, Integer>();
        
        for(int i=0; i<listePays.size(); i++){
            List<Voyage> listeVoyagePays=listePays.get(i).getListeVoyage();
            for(int j=0; j<listeVoyagePays.size(); j++){
                if(voyagesOccurences.contains(listeVoyagePays.get(j).getId())){
                    voyagesOccurences.put(listeVoyagePays.get(j).getId() ,voyagesOccurences.get(listeVoyagePays.get(j).getId())+1);
                }
                else{
                    voyagesOccurences.put(listeVoyagePays.get(j).getId() , 1);
                }
            }
        }
        
        for(int j=0; j<voyagesOccurences.size(); j++){
            if(voyagesOccurences.keys().){
                
            }
        }
        
        
        
        
        return listeVoyage;
    }
    
}
