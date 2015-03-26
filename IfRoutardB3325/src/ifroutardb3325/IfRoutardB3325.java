/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325;

import ifroutardb3325.UtilDAO.PaysDAO;
import ifroutardb3325.entites.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ltouzard
 */
public class IfRoutardB3325 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //DEMO 1 : Inscription interactive de clients
          // Client c1 = new Client("M", "Patate", "Robert", "robert.patate@patatat.fr", "laposte", "0322065488");
            //Services.creerClient(c1);
            //Client c1=Services.creerClientIteractif();
            //Client c2=Services.creerClientIteractif();
        
        
        //DEMO 2 : Affichage de la liste de clients inscrits
        /*
            List<Client> liste=Services.listerClient();
            for(int i=0; i<liste.size(); i++){
                System.out.println("ID : "+liste.get(i).getId());
                System.out.println("Prenom : "+liste.get(i).getPrenom());
                System.out.println("Nom : "+liste.get(i).getNom());
            }
        */
        
        //DEMO 3 : Création interactive d'un devis
          /*Services.creerConseiller(new Conseiller("Michel-le-conseiller", "Jean-le-conseillé", "j.michel@yahoo.fr"));
            
            Sejour sej=new Sejour( "CODDD", "description de ouff", 5L, "lenom", "hoteldeluxe");
            Services.creerVoyage(sej);
            
            Pays p=new Pays("lePaysDeOuf", "LaCapDeOuf", "LPDO", 2, 100, "le patois AYyYYYE", "leSUD");
            p.addVoyage(sej);
            sej.addPays(p);
            Services.creerPays(p);
            
            Depart d=new Depart(new Date(), 300.0f, "CAMION", "WeshCity");
           
            d.setVoyage(sej);
            sej.addDepart(d);
            Services.creerDepart(d);
            Services.modifierVoyage(sej);
            
            Devis devisCree=Services.creerDevisIteractif(c1,d);
        
        
        //DEMO 4 : Affichage du devis crée
        
            System.out.println("DATE : "+devisCree.getDate());
            System.out.println("nbParticipants : "+devisCree.getNbPersonnes());
            System.out.println("Conseiller : "+devisCree.getConseiller().getNom()+" "+devisCree.getConseiller().getPrenom());
            System.out.println("Depart : "+devisCree.getDate());
            System.out.println("Client : "+devisCree.getClient().getCivilite()+". "+devisCree.getClient().getNom());
            System.out.println("Voyage : "+devisCree.getVoyage().getNom());
            for(int i=0; i<devisCree.getVoyage().getListePays().size(); i++){
                System.out.println("Pays : "+devisCree.getVoyage().getListePays().get(i).getNom());
            }
            System.out.println("Depart : "+devisCree.getDepart().getVille()+" : "+devisCree.getDepart().getJour());
        
        
        */
        //AUTRES TRUCS
            
            //Test trouver voyage en fonction de la liste de pays
            List<Pays> listePays=new ArrayList<Pays>();
            listePays.add(Services.trouverPays(136L));
            listePays.add(Services.trouverPays(44L));
            
            List<Voyage> lv=Services.trouverVoyageParPays(listePays);
        
            for(int i=0; i<lv.size(); i++){
                System.out.println(lv.get(i).getNom());
            }
        
        
        
            //PEUPLEMENT DE LA TABLE CLIENT
        
            //Services.init();

        
            //LISTER LES DEPARTS IMMINENTS
        
            /*String format="yyyy-MM-dd";
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            
            try {
                Services.creerDepart(new Depart(formatter.parse("2015-03-28"), 300.0f, "PAST", "lavilleVille"));
                Services.creerDepart(new Depart(formatter.parse("2015-04-29"), 300.0f, "BIRTHDAY", "lavilleVille"));
                Services.creerDepart(new Depart(formatter.parse("2015-04-25"), 300.0f, "BIENTOT", "lavilleVille"));
                Services.creerDepart(new Depart(formatter.parse("2013-07-28"), 300.0f, "BIRTHDAY_LOIC", "lavilleVille"));
                Services.creerDepart(new Depart(formatter.parse("2015-02-27"), 300.0f, "NOEL", "lavilleVille"));
            } catch (ParseException ex) {
                Logger.getLogger(IfRoutardB3325.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            List<Depart> d=Services.listerProchainsDeparts(1);
            for(int i=0; i<d.size(); i++){
                System.out.println(d.get(i).getJour());
            }*/
        
            /*Services.creerConseiller(new Conseiller("Michel-le-conseiller", "Jean-le-conseillé", "j.michel@yahoo.fr"));
            Services.creerPays(new Pays("lePaysDeOuf", "LaCapDeOuf", "LPDO", 2, 100, "le patois AYyYYYE", "leSUD"));
            Services.creerDepart(new Depart(new Date(), 300.0f, "CAMION", "lavilleVille"));
            Services.creerVoyage(new Sejour( "CODDD", "description de ouff", 5L, "lenom", "hoteldeluxe"));
            Services.creerVoyage(new Circuit("FR007", "Description",3L, "Voyage Voyage !", 200L, "voiture et camion"));
        */
    }   
}
