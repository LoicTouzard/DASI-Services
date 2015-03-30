/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Devis;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface DevisDAO {

    void creerDevis(Devis devis);

    List<Devis> listerDevis();

    Devis modifierDevis(Devis devis) throws NonexistentEntityException;

    void supprimerDevis(Long id) throws NonexistentEntityException;

    Devis trouverDevis(Long id);
    
}
