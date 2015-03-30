/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Pays;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface PaysDAO {

    void creerPays(Pays pays);

    List<Pays> listerPays();

    Pays modifierPays(Pays pays) throws NonexistentEntityException;

    void supprimerPays(Long id) throws NonexistentEntityException;

    Pays trouverPays(Long id);
    
}
