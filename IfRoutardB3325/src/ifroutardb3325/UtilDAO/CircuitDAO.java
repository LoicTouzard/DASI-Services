/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Circuit;
import ifroutardb3325.entites.Voyage;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard & ggouzi
 */
public class CircuitDAO {
    
    public static void creerCircuit(Circuit circuit){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(circuit);
    }
    
    public static void supprimerCircuit(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Circuit circuit;
            try {
                circuit = em.getReference(Circuit.class, id);
                circuit.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The circuit with id " + id + " no longer exists.", e);
            }
        em.remove(circuit);
    }
    
    public static Circuit modifierCircuit(Circuit circuit) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            circuit = em.merge(circuit);
        } catch(Exception e){
             Long id = circuit.getId();
                if (trouverCircuit(id) == null) {
                    throw new NonexistentEntityException("The circuit with id " + id + " no longer exists.");
                }
        }
        return circuit;
    }
    
    public static Circuit trouverCircuit(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Circuit.class, id);
    }
    
    public static List<Voyage> listerVoyages(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT c FROM Circuit c");
        List<Voyage> listeCircuit=query.getResultList();
        return listeCircuit;
    }
}
