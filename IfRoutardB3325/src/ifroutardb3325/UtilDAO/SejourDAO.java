/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Sejour;
import ifroutardb3325.entites.Voyage;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface SejourDAO {

    void creerSejour(Sejour sejour);

    List<Voyage> listerVoyages();

    Sejour modifierSejour(Sejour sejour) throws NonexistentEntityException;

    void supprimerSejour(Long id) throws NonexistentEntityException;

    Sejour trouverSejour(Long id);
    
}
