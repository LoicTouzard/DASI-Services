/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325;

import ifroutardb3325.exceptions.NonexistentEntityException;
import ifroutardb3325.entites.*;
import ifroutardb3325.UtilDAO.*;
import ifroutardB3325.Saisie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ltouzard
 */
public class Services {
    
    static final int SEJOUR=0;
    static final int CIRCUIT=1;
    
    //Init
    public static void init(){
        for(int i=0; i<ClientData.CLIENTS.size(); i++){
            String[] clientString=ClientData.CLIENTS.get(i);
            Client c=new Client(clientString[0], clientString[1], clientString[2] ,clientString[4] ,clientString[5] ,clientString[6]);
            creerClient(c);
        }
    }
    
    
    //Client
    public static void creerClient(Client c){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ClientDAO.creerClient(c);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void supprimerClient(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ClientDAO.supprimerClient(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.supprimerClient");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static Client trouverClient(Long id){
        JpaUtil.creerEntityManager();
        
        Client c=ClientDAO.trouverClient(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }

    public static Client modifierClient(Client client){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
       
        try {
            client=ClientDAO.modifierClient(client);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : modifierClient");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }
   
    public static List<Client> listerClient(){
        JpaUtil.creerEntityManager();
        
        List<Client> listeClients=ClientDAO.listerClient();
        
        JpaUtil.fermerEntityManager();
        return listeClients;
    }
    
    public static Client creerClientIteractif(){
        String civilite=Saisie.lireChaine("Civilite (M, Mme, Mlle) :\n");
        String nom=Saisie.lireChaine("Nom :\n");
        String prenom=Saisie.lireChaine("Prénom :\n");
        String mail=Saisie.lireChaine("Mail :\n");
        String adresse=Saisie.lireChaine("Adresse postale :\n");
        String tel=Saisie.lireChaine("Telephone :\n");
        
        Client c=new Client(civilite, nom, prenom, mail, adresse, tel);
        
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ClientDAO.creerClient(c);
      
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        
        System.out.println("Client créé\n");
        return c;

    }
    
    public static Client verifierMotDePasse(String mail, Long password){
        Client cMail=ClientDAO.trouverClientByMail(mail);
        if(cMail.getId()==password){
           return cMail;
        }
        return null;
    }
    
    public static Client enregistrerClient(Client c){
        JpaUtil.creerEntityManager();
         
        Client clientExisteDeja=ClientDAO.trouverClientByMail(c.getMail());
        if(clientExisteDeja==null){
           System.out.println("Bienvenue : "+c.getPrenom()+
                   ".\n Nous vous confirmons votre inscription à l'agence IF'Routard. Votre numéro de client est : "+
                   c.getId()+".\n Votre mot de passe est : "+c.getId()+"(Pensez à modifier votre mot de passe)");

        }
        else{
            System.out.println("Bienvenue : "+c.getPrenom()+
                   ".\n Votre inscription à l'agence IF'Routard a échouée. Merci de recommencer ultérieurement");
        }
        
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    
    //Conseiller
    public static void creerConseiller(Conseiller conseiller) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ConseillerDAO.creerConseiller(conseiller);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void supprimerConseiller(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ConseillerDAO.supprimerConseiller(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.supprimerConseiller");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static Conseiller trouverConseiller(Long id){
        JpaUtil.creerEntityManager();
        
        Conseiller c=ConseillerDAO.trouverConseiller(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }
     
    public static Conseiller modifierConseiller(Conseiller conseiller){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            conseiller=ConseillerDAO.modifierConseiller(conseiller);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierConseiller");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }
     
     
    //Voyage
    public static void creerVoyage(Voyage v){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        VoyageDAO.creerVoyage(v);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static void supprimerVoyage(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            VoyageDAO.supprimerVoyage(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.supprimerVoyage");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static Voyage trouverVoyage(Long id){
        JpaUtil.creerEntityManager();
        
        Voyage v=VoyageDAO.trouverVoyage(id);
        
        JpaUtil.fermerEntityManager();
        return v;
    }
     
    public static Voyage modifierVoyage(Voyage voyage){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            voyage=VoyageDAO.modifierVoyage(voyage);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierVoyage");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }
      
    public static List<Voyage> trouverVoyageParPays(List<Pays> listePays){
        List<Voyage> listeVoyage=new ArrayList<Voyage>();
        JpaUtil.creerEntityManager();
        
        VoyageDAO.listerVoyageComporantPays(listePays);
        
        JpaUtil.fermerEntityManager();
        return listeVoyage;
    }
    
    //Depart
    public static void creerDepart(Depart d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        DepartDAO.creerDepart(d);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static void supprimerDepart(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DepartDAO.supprimerDepart(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.supprimerDepart");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static Depart trouverDepart(Long id){
        JpaUtil.creerEntityManager();
        
        Depart d=DepartDAO.trouverDepart(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }
     
    public static Depart modifierDepart(Depart d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DepartDAO.modifierDepart(d);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierDepart");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

    public static List<Depart> listerProchainsDeparts(int n){
        JpaUtil.creerEntityManager();
        
        List<Depart> listeTotale=DepartDAO.listerProchainsDeparts();
        List<Depart> listeVoulue=new ArrayList<Depart>();
        for(int i=0; i<listeTotale.size() && i<n; i++){
            listeVoulue.add(listeTotale.get(i));
        }
        JpaUtil.fermerEntityManager();
        return listeVoulue;
    }
    
    
    //Sejour
    public static void creerSejour(Sejour s){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        SejourDAO.creerSejour(s);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static List<Voyage> listerVoyage(int typeVoyage){
        JpaUtil.creerEntityManager();
        List<Voyage> listeDeVoyage=null;
        
        if(typeVoyage==SEJOUR){
            listeDeVoyage=SejourDAO.listerVoyages();
        }
        else if(typeVoyage==CIRCUIT){
            listeDeVoyage=CircuitDAO.listerVoyages();
        }

        JpaUtil.fermerEntityManager();
        return listeDeVoyage;
    }
    
    
    //Circuit
    public static void creerCircuit(Circuit c){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        CircuitDAO.creerCircuit(c);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    
    //Devis
    public static void creerDevis(Devis d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        DevisDAO.creerDevis(d);
        Voyage v=d.getVoyage();
        Conseiller c=d.getConseiller();

        System.out.println("Votre conseiller pour le voyage : "+c.getNom()+" "+c.getPrenom()+"("+c.getMail()+")");
        System.out.println("Votre voyage :"+v.getNom()+". ");
        for(int i=0; i<v.getListePays().size(); i++){
            System.out.print(v.getListePays().get(i)+" ");
        }
        System.out.println(); //Saut de ligne
        if(v instanceof Sejour){
            Sejour s=(Sejour)v;
            System.out.println(s.getType()+"("+s.getDuree()+", "+s.getResidence()+"\n");
           
        }
        else if(v instanceof Circuit){
            Circuit cr=(Circuit)v;
            System.out.println(cr.getType()+"("+cr.getDuree()+", "+cr.getKilometrage()+", "+cr.getTransport()+"\n");
        }
        
        System.out.println("Départ : le "+d.getDate().getYear()+1900+"/"+d.getDate().getMonth()+1+"/"+d.getDate().getDate()+" de "+d.getDepart().getVille());
        System.out.println("Transport : "+d.getDepart().getTransport());
        System.out.println(d.getVoyage().getDescription()+"\n");
        System.out.println("----------------------");
        System.out.println("Nombre de personnes : "+d.getNbPersonnes());
        System.out.println("Tarif par personne : "+d.getDepart().getPrix());
        System.out.println("TOTAL : "+d.getPrixTotal());
                
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void supprimerDevis(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DevisDAO.supprimerDevis(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.supprimerDevis");
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    public static Devis trouverDevis(Long id){
        JpaUtil.creerEntityManager();
        
        Devis d=DevisDAO.trouverDevis(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }
     
    public static Devis modifierDevis(Devis d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DevisDAO.modifierDevis(d);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierDevis");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

    public static List<Devis> listerDevis(Devis d){
        JpaUtil.creerEntityManager();
         
        List<Devis> listeDevis=DevisDAO.listerDevis();
        
        JpaUtil.fermerEntityManager();
        return listeDevis;
    }
    
    public static Devis creerDevisIteractif(Client c, Depart dp){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        Date date=Saisie.lireDate("Date :\n");
        
        //nombre de participants
        int nbParticipants=0;
        while( (nbParticipants=Saisie.lireInteger("Nombre de participants :\n")) <=0){
            System.out.println("Le nombre de partcipants doit être positif");
        }
        
        Conseiller conseillerLibre=ConseillerDAO.getConseillerLibre();
        Devis d=new Devis(date, nbParticipants);
        d.setDepart(dp);
        d.setClient(c);
        d.setConseiller(conseillerLibre);
        
        DevisDAO.creerDevis(d);
        
        System.out.println("Devis créé\n");
        
        //mise a jour Client
        c.addDevis(d);
        try {
            c=ClientDAO.modifierClient(c);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierClient");
        }
        
        //mise a jour conseiller
        conseillerLibre.addDevis(d);
        try {
            conseillerLibre=ConseillerDAO.modifierConseiller(conseillerLibre);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levée dans Services.java : Services.modifierConseiller");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }
    
    
    //Pays
    public static void creerPays(Pays p){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        PaysDAO.creerPays(p);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    public static List<Voyage> listerVoyage(Pays p){
        JpaUtil.creerEntityManager();
        
        List<Voyage> listeDeVoyage=p.getListeVoyage();
        
        JpaUtil.fermerEntityManager();
        return listeDeVoyage;
    }
    
    public List<Pays> listerDestinations(){
        List<Pays> listeDestinations=new ArrayList<Pays>();
        List<Voyage> listeTousVoyages=VoyageDAO.listerVoyages();
        for(int i=0; i<listeTousVoyages.size(); i++){
            List<Pays> listePaysVoyage=listeTousVoyages.get(i).getListePays();
            for(int j=0; j<listePaysVoyage.size(); j++){
                if(!listeDestinations.contains(listePaysVoyage.get(j))){
                    listeDestinations.add(listePaysVoyage.get(j));
                }
            }
        }
        
        return listeDestinations;
    }
    
    public static List<Pays> listerAllPays(){
        JpaUtil.creerEntityManager();
        
        List<Pays> listePays=PaysDAO.listerPays();
        
        JpaUtil.fermerEntityManager();
        return listePays;
    }

    public static Pays trouverPays(Long id){
        JpaUtil.creerEntityManager();
        
        Pays p=PaysDAO.trouverPays(id);
        
        JpaUtil.fermerEntityManager();
        return p;
    }

}
