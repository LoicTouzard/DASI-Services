/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Pays;
import ifroutardb3325.entites.Voyage;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface VoyageDAO {

    void creerVoyage(Voyage voyage);

    List<Voyage> listerVoyageComporantPays(List<Pays> listePays);

    List<Voyage> listerVoyageComporantPays(List<Pays> listePays, boolean circuit, boolean sejour);

    List<Voyage> listerVoyages();

    Voyage modifierVoyage(Voyage voyage) throws NonexistentEntityException;

    void supprimerVoyage(Long id) throws NonexistentEntityException;

    Voyage trouverVoyage(Long id);
    
}
