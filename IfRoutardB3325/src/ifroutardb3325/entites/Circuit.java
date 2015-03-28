/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.entites;

import javax.persistence.Entity;

/**
 *
 * @author ltouzard & ggouzi
 */
@Entity
public class Circuit extends Voyage{

    private Long kilometrage;
    private String transport;
    
    public Circuit() {
        super();
    }

    public Circuit(String code, String description, Long duree, String nom, Long kilometrage, String transport) {
        super(code, description, duree, nom);
        this.kilometrage = kilometrage;
        this.transport = transport;
    }

    public Circuit(Long id, String code, String description, Long duree, String nom, Long kilometrage, String transport) {
        super(id, code, description, duree, nom);
        this.kilometrage = kilometrage;
        this.transport = transport;
    }
    
    public String getType(){
        return "Circuit";
    }

    public Long getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Long kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

}
