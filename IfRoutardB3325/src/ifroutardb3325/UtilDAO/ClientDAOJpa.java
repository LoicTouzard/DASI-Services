/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import ifroutardb3325.entites.Client;
import ifroutardb3325.entites.Devis;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author ltouzard
 */
public class ClientDAOJpa implements ClientDAO {
    
    @Override
    public void creerClient(Client client){
        EntityManager em = JpaUtil.obtenirEntityManager();
        em.persist(client);
    }
    
    @Override
    public void supprimerClient(Long id) throws NonexistentEntityException{
        EntityManager em = JpaUtil.obtenirEntityManager();
        Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            }catch(EntityNotFoundException e){
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", e);
            }
        em.remove(client);
    }
    
    @Override
    public Client modifierClient(Client client) throws NonexistentEntityException{
        try{
            EntityManager em = JpaUtil.obtenirEntityManager();
            client = em.merge(client);
        } catch(Exception e){
             Long id = client.getId();
                if (trouverClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
        }
        return client;
    }
    
    @Override
    public Client trouverClient(Long id){
        EntityManager em = JpaUtil.obtenirEntityManager();
        return em.find(Client.class, id);
    }
    
    @Override
    public Client trouverClientByMail(String mail){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query;
        query = em.createQuery("SELECT c FROM Client c WHERE c.mail=:mail");
        query.setParameter("mail", mail);
        Client clientTrouve=(Client)query.getResultList();
        return clientTrouve;
    }
    
    @Override
    public List<Client> listerClient(){
        EntityManager em = JpaUtil.obtenirEntityManager();
        Query query=em.createQuery("SELECT c FROM Client c");
        List<Client> listeClients=query.getResultList();
        return listeClients;
    }
}
