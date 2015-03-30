/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Client;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface ClientDAO {

    void creerClient(Client client);

    List<Client> listerClient();

    Client modifierClient(Client client) throws NonexistentEntityException;

    void supprimerClient(Long id) throws NonexistentEntityException;

    Client trouverClient(Long id);

    Client trouverClientByMail(String mail);
    
}
