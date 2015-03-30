/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Conseiller;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface ConseillerDAO {

    void creerConseiller(Conseiller conseiller);

    Conseiller getConseillerLibre();

    List<Conseiller> listerConseiller();

    Conseiller modifierConseiller(Conseiller conseiller) throws NonexistentEntityException;

    void supprimerConseiller(Long id) throws NonexistentEntityException;

    Conseiller trouverConseiller(Long id);
    
}
