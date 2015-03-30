/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Depart;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface DepartDAO {

    void creerDepart(Depart depart);

    List<Depart> listerProchainsDeparts();

    Depart modifierDepart(Depart depart) throws NonexistentEntityException;

    void supprimerDepart(Long id) throws NonexistentEntityException;

    Depart trouverDepart(Long id);
    
}
