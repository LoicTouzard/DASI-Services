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
 * @author ltouzard & ggouzi
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
    
    /**
     * Ajoute un client dans la base de donn�es
     * @param client Client � ajouter dans la base
     */
    public static void creerClient(Client client){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ClientDAO.creerClient(client);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Supprime un client de la base de donn�es
     * @param id Identifiant du client � supprimer
     * @exception Exception si l'id pass� en param�tre ne correspond � aucun client
     */
    public static void supprimerClient(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ClientDAO.supprimerClient(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levee dans Services.java : Services.supprimerClient");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Trouve un client dans la base de donn�es
     * @param id Identifiant du client � trouver
     * @return Client poss�dant l'identifiant pass� en param�tre
     */
    public static Client trouverClient(Long id){
        JpaUtil.creerEntityManager();
        
        Client c=ClientDAO.trouverClient(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }


    /**
     * Modifie un client dans la base de donn�es
     * @param client Client que l'on souhaite mettre � jour dans la base
     * @return Client mis � jour
     * @exception Exception si le client pass� en param�tre n'existe pas dans la base
     */
    public static Client modifierClient(Client client){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
       
        try {
            client=ClientDAO.modifierClient(client);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception levee dans Services.java : modifierClient");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return client;
    }
   
    /**
     * Liste tous les clients dans la base de donn�es
     * @return List<Client> liste de tous les clients pr�sents dans la base
     */
    public static List<Client> listerClient(){
        JpaUtil.creerEntityManager();
        
        List<Client> listeClients=ClientDAO.listerClient();
        
        JpaUtil.fermerEntityManager();
        return listeClients;
    }
    
    /**
     * Cr�ation d'un client de mani�re interactive en console
     * @return Client que l'on vient de cr�er grace aux informations fournies par l'utilisateur
     */
    public static Client creerClientIteractif(){
        String civilite=Saisie.lireChaine("Civilite (M, Mme, Mlle) :\n");
        String nom=Saisie.lireChaine("Nom :\n");
        String prenom=Saisie.lireChaine("Prenom :\n");
        String mail=Saisie.lireChaine("Mail :\n");
        String adresse=Saisie.lireChaine("Adresse postale :\n");
        String tel=Saisie.lireChaine("Telephone :\n");
        
        Client c=new Client(civilite, nom, prenom, mail, adresse, tel);
        
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ClientDAO.creerClient(c);
      
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        
        System.out.println("Client cree\n");
        return c;

    }
    
    /**
     * V�rifie si le mot de passe est correct pour un identifiant donn�
     * @param mail Adresse e-mail du client qui souhaite se connecter (son identifiant)
     * @param password Mot de passe du client qui souhaite se connecter (son id par d�faut)
     * @return Client poss�dant cet adresse mail et ce mot de passe si il existe, renvoie null sinon
     */
    public static Client verifierMotDePasse(String mail, Long password){
        Client cMail=ClientDAO.trouverClientByMail(mail);
        if(cMail.getId()==password){
           return cMail;
        }
        return null;
    }
    
    /** 
     * Simule l'envoi du mail d'inscription (en cas de succ�s ou d'echec)
     * @param Client client que l'on souhaite inscrire (enregistrer dans la base de donn�es)
     * @return Client ce m�me client que celui pass� en param�tre
     */
    public static Client enregistrerClient(Client client){
        JpaUtil.creerEntityManager();
         
        Client clientExisteDeja=ClientDAO.trouverClientByMail(client.getMail());
        if(clientExisteDeja==null){
           System.out.println("Bienvenue : "+client.getPrenom()+
                   ".\n Nous vous confirmons votre inscription � l'agence IF'Routard. Votre num�ro de client est : "+
                   client.getId()+".\n Votre mot de passe est : "+client.getId()+"(Pensez � modifier votre mot de passe)");

        }
        else{
            System.out.println("Bienvenue : "+client.getPrenom()+
                   ".\n Votre inscription � l'agence IF'Routard a �chou�e. Merci de recommencer ult�rieurement");
        }
        
        JpaUtil.fermerEntityManager();
        return client;
    }
    
    
    //Conseiller
    /**
     * Ajoute un conseiller dans la base de donn�es
     * @param conseiller Conseiller � ajouter dans la base
     */
    public static void creerConseiller(Conseiller conseiller) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ConseillerDAO.creerConseiller(conseiller);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Supprime un conseiller de la base de donn�es
     * @param id Identifiant du conseiller � supprimer
     * @exception Exception si l'id pass� en param�tre ne correspond � aucun conseiller
     */
    public static void supprimerConseiller(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ConseillerDAO.supprimerConseiller(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.supprimerConseiller");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Trouve un conseiller dans la base de donn�es
     * @param id Identifiant du conseiller � supprimer
     * @return Conseiller poss�dant l'identifiant pass� en param�tre
     */
    public static Conseiller trouverConseiller(Long id){
        JpaUtil.creerEntityManager();
        
        Conseiller c=ConseillerDAO.trouverConseiller(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }
     
    /**
     * Modifie un conseiller dans la base de donn�es
     * @param conseiller Conseiller que l'on souhaite mettre � jour dans la base
     * @return Conseiller mis � jour
     * @exception Exception si le conseiller pass� en param�tre n'existe pas dans la base
     */
    public static Conseiller modifierConseiller(Conseiller conseiller){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            conseiller=ConseillerDAO.modifierConseiller(conseiller);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.modifierConseiller");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }
     
     
    //Voyage
    /**
     * Ajoute un voyage dans la base de donn�es
     * @param voyage Voyage � ajouter dans la base
     */
    public static void creerVoyage(Voyage voyage){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        VoyageDAO.creerVoyage(voyage);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
    /**
     * Supprime un voyage de la base de donn�es
     * @param id Identifiant du voyage � supprimer
     * @exception Exception si l'id pass� en param�tre ne correspond � aucun voyage
     */
    public static void supprimerVoyage(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            VoyageDAO.supprimerVoyage(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.supprimerVoyage");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }     
    
    /**
     * Trouve un voyage dans la base de donn�es
     * @param id Identifiant du voyage � supprimer
     * @return Voyage poss�dant l'identifiant pass� en param�tre
     */
    public static Voyage trouverVoyage(Long id){
        JpaUtil.creerEntityManager();
        
        Voyage v=VoyageDAO.trouverVoyage(id);
        
        JpaUtil.fermerEntityManager();
        return v;
    }
     
    
    /**
     * Modifie un voyage dans la base de donn�es
     * @param voyage Voyage que l'on souhaite mettre � jour dans la base
     * @return Voyage mis � jour
     * @exception Exception si le voyage pass� en param�tre n'existe pas dans la base
     */
    public static Voyage modifierVoyage(Voyage voyage){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            voyage=VoyageDAO.modifierVoyage(voyage);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.modifierVoyage");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }
      
   
    /**
     * Trouve tous les voyages dans la base de donn�es qui passent par tous les pays de la liste pass�e en param�tre
     * @param listePays Liste de pays dont ou souhaite connaitre les voyages associ�s.
     * @return List<Voyage> Liste des voyages qui passent par tous les pays de la liste de pays
     */
    public static List<Voyage> trouverVoyageParPays(List<Pays> listePays){
        List<Voyage> listeVoyage=new ArrayList<Voyage>();
        JpaUtil.creerEntityManager();
        
        VoyageDAO.listerVoyageComporantPays(listePays);
        
        JpaUtil.fermerEntityManager();
        return listeVoyage;
    }
    
    /**
     * Liste tous les voyages de type TypeVoyage
     * @param typeVoyage Entier correspondant au type de voyage (Circuit ou S�jour)
     * @return List<Voyage> liste des voyages du m�me type (Circuit ou S�jour). Renvoi une liste nulle si un autre param�tre diff�rent est pass�.
     */
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
    
    /**
     * Ajoute un Sejour dans la base de donn�es
     * @param sejour Sejour � ajouter dans la base
     */
    public static void creerSejour(Sejour sejour){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        SejourDAO.creerSejour(sejour);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
   
    /**
     * Ajoute un Circuit dans la base de donn�es
     * @param circuit Circuit � ajouter dans la base
     */
    
    
    //Circuit
    public static void creerCircuit(Circuit circuit){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        CircuitDAO.creerCircuit(circuit);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    
    //Devis
    
    
    
  	//Depart
    /**
     * Ajoute un Depart dans la base de donn�es
     * @param depart Depart � ajouter dans la base
     */
    public static void creerDepart(Depart depart){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        DepartDAO.creerDepart(depart);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

    /**
     * Supprime un depart de la base de donn�es
     * @param id Identifiant du depart � supprimer
     * @exception Exception si l'id pass� en param�tre ne correspond � aucun depart
     */
    public static void supprimerDepart(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DepartDAO.supprimerDepart(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.supprimerDepart");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Trouve un depart dans la base de donn�es
     * @param id Identifiant du depart � supprimer
     * @return Depart poss�dant l'identifiant pass� en param�tre
     */
    public static Depart trouverDepart(Long id){
        JpaUtil.creerEntityManager();
        
        Depart d=DepartDAO.trouverDepart(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }

    /**
     * Modifie un depart dans la base de donn�es
     * @param depart Depart que l'on souhaite mettre � jour dans la base
     * @return Depart mis � jour
     * @exception Exception si le depart pass� en param�tre n'existe pas dans la base
     */
    public static Depart modifierDepart(Depart depart){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DepartDAO.modifierDepart(depart);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.modifierDepart");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return depart;
    }

    /**
     * Liste tous les clients dans la base de donn�es
     * @param n Nombre de d�parts contenus dans la liste � retourner
     * @return List<Depart> liste des departs ayant une date sup�rieure � la date d'aujourd'hui et tri�e par date (de la plus proche � la plus lointaine)
     */
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

    /**
     * Ajoute un Devis dans la base de donn�es et simule l'envoi du mail contenant toutes les informations relatives � ce devis
     * @param devis Devis � ajouter dans la base
     */
    public static void creerDevis(Devis devis){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        DevisDAO.creerDevis(devis);
        Voyage v=devis.getVoyage();
        Conseiller c=devis.getConseiller();

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
        
        System.out.println("D�part : le "+devis.getDate().getYear()+1900+"/"+devis.getDate().getMonth()+1+"/"+devis.getDate().getDate()+" de "+devis.getDepart().getVille());
        System.out.println("Transport : "+devis.getDepart().getTransport());
        System.out.println(devis.getVoyage().getDescription()+"\n");
        System.out.println("----------------------");
        System.out.println("Nombre de personnes : "+devis.getNbPersonnes());
        System.out.println("Tarif par personne : "+devis.getDepart().getPrix());
        System.out.println("TOTAL : "+devis.getPrixTotal());
                
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
   
    /**
     * Supprime un devis de la base de donn�es
     * @param id Identifiant du depart � supprimer
     * @exception Exception si l'id pass� en param�tre ne correspond � aucun devis
     */
    public static void supprimerDevis(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DevisDAO.supprimerDevis(id);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.supprimerDevis");
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Trouve un devis dans la base de donn�es
     * @param id Identifiant du devis � supprimer
     * @return Devis poss�dant l'identifiant pass� en param�tre
     */
     
    public static Devis trouverDevis(Long id){
        JpaUtil.creerEntityManager();
        
        Devis d=DevisDAO.trouverDevis(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }
     
    
    /**
     * Modifie un devis dans la base de donn�es
     * @param devis Devis que l'on souhaite mettre � jour dans la base
     * @return Devis mis � jour
     * @exception Exception si le devis pass� en param�tre n'existe pas dans la base
     */
    public static Devis modifierDevis(Devis d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DevisDAO.modifierDevis(d);
        } catch (NonexistentEntityException ex) {
             System.err.println("Exception lev�e dans Services.java : Services.modifierDevis");
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

    
    /**
     * Liste tous les devis
     * @return List<Devis> liste de tous les devis 
     */
    public static List<Devis> listerDevis(){
        JpaUtil.creerEntityManager();
         
        List<Devis> listeDevis=DevisDAO.listerDevis();
        
        JpaUtil.fermerEntityManager();
        return listeDevis;
    }
    
    /**
     * Cr�ation d'un devis de mani�re interactive en console
     * @return Devis que l'on vient de cr�er grace aux informations fournies par l'utilisateur
     */
    
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
    
    //PAYS
    /**
     * Ajoute un Pays dans la base de donn�es
     * @param pays Pays � ajouter dans la base
     */
    public static void creerPays(Pays pays){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        PaysDAO.creerPays(pays);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    /**
     * Liste tous les voyages qui se d�roulent dans le pays pass� en param�tre
     * @param Pays pays dont on souhaite connaitre tous les voyages s'y d�roulant
     * @return List<Voyage> liste des voyages passant par ce pays.
     */

    public static List<Voyage> listerVoyage(Pays pays){
        JpaUtil.creerEntityManager();
        
        List<Voyage> listeDeVoyage=pays.getListeVoyage();
        
        JpaUtil.fermerEntityManager();
        return listeDeVoyage;
    }
    
    /**
     * Liste tous les pays que l'on peut visiter (dont au moins un voyage s'y d�roule)
     * @return List<Pays> liste des pays que l'on peut aller visiter
     */
    
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
    
    /**
     * Liste tous les pays (y compris ce qui n'ont pas de voyage associ�)
     * @return List<Pays> liste de tous les pays de la base
     */
    
    public static List<Pays> listerAllPays(){
        JpaUtil.creerEntityManager();
        
        List<Pays> listePays=PaysDAO.listerPays();
        
        JpaUtil.fermerEntityManager();
        return listePays;
    }
    
    /**
     * Trouve un pays dans la base de donn�es
     * @param id Identifiant du pays � supprimer
     * @return Pays poss�dant l'identifiant pass� en param�tre
     */

    public static Pays trouverPays(Long id){
        JpaUtil.creerEntityManager();
        
        Pays p=PaysDAO.trouverPays(id);
        
        JpaUtil.fermerEntityManager();
        return p;
    }
}
