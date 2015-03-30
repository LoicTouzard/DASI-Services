/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.UtilDAO;

import ifroutardb3325.entites.Circuit;
import ifroutardb3325.entites.Voyage;
import ifroutardb3325.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public interface CircuitDAO {

    void creerCircuit(Circuit circuit);

    List<Voyage> listerVoyages();

    Circuit modifierCircuit(Circuit circuit) throws NonexistentEntityException;

    void supprimerCircuit(Long id) throws NonexistentEntityException;

    Circuit trouverCircuit(Long id);
    
}
