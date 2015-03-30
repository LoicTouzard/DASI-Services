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
import java.util.List;

/**
 *
 * @author ltouzard
 */
public class Services {
    
    static final int SEJOUR=0;
    static final int CIRCUIT=1;
    
    //Init
    public static void init() throws Exception{
        for(int i=0; i<ClientData.CLIENTS.size(); i++){
            String[] clientString=ClientData.CLIENTS.get(i);
            Client c=new Client(clientString[0], clientString[1], clientString[2] ,clientString[4] ,clientString[5] ,clientString[6]);
            creerClient(c);
        }
    }
    
    
    //Client
    
     /**
    * Ajoute un client dans la base de données
    * @param c Client à ajouter dans la base
    * @Exception Exception si l'adresse mail est déjà associée à un client dans la base
    */
    public static void creerClient(Client c) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        ClientDAO.creerClient(c);
        try{
            JpaUtil.validerTransaction();
        }catch(Exception e){
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw e;
        }
        JpaUtil.fermerEntityManager();
    }

    /**
   * Supprime un client de la base de données
   * @param id Identifiant du client à supprimer
   * @exception NonexistentEntityException si l'id passé en paramètre ne correspond à aucun client
   */
    public static void supprimerClient(Long id) throws NonexistentEntityException{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ClientDAO.supprimerClient(id);
            JpaUtil.validerTransaction();
        } catch (NonexistentEntityException ex) {
             JpaUtil.annulerTransaction();
             JpaUtil.fermerEntityManager();
             throw ex;
        }

        JpaUtil.fermerEntityManager();
    }
     
     /**
    * Trouve un client dans la base de données
    * @param id Identifiant du client à trouver
    * @return Client possédant l'identifiant passé en paramètre, null sinon
    */
    public static Client trouverClient(Long id){
        JpaUtil.creerEntityManager();
        
        Client c=ClientDAO.trouverClient(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }

    /**
    * Modifie un client dans la base de données
    * @param client Client que l'on souhaite mettre à jour dans la base
    * @return Client mis à jour
    * @exception NonexistentEntityException si le client passé en paramètre n'existe pas dans la base
    */
    public static Client modifierClient(Client client) throws NonexistentEntityException{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
       
        try {
            client=ClientDAO.modifierClient(client);
            JpaUtil.validerTransaction();
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.fermerEntityManager();
        return client;
    }
   
    /**
   * Liste tous les clients dans la base de données
   * @return liste de tous les clients présents dans la base
   */
    
    /**
     * 
     * @return 
     */
    public static List<Client> listerClient(){
        JpaUtil.creerEntityManager();
        
        List<Client> listeClients=ClientDAO.listerClient();
        
        JpaUtil.fermerEntityManager();
        return listeClients;
    }
    
    /**
    * Création d'un client de manière interactive en console
    * @return Client que l'on vient de créer grace aux informations fournies par l'utilisateur
    * @exception Exception si la création interactive à échoué (principalement car le mail existe déjà)
    */
    public static Client creerClientIteractif() throws Exception{
        String civilite=Saisie.lireChaine("Civilite (M, Mme, Mlle) :\n");
        String nom=Saisie.lireChaine("Nom :\n");
        String prenom=Saisie.lireChaine("Prénom :\n");
        String mail=Saisie.lireChaine("Mail :\n");
        String adresse=Saisie.lireChaine("Adresse postale :\n");
        String tel=Saisie.lireChaine("Telephone :\n");
        
        Client c=new Client(civilite, nom, prenom, mail, adresse, tel);
        
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
         try{
            ClientDAO.creerClient(c);
        }catch(Exception e){
           JpaUtil.annulerTransaction();
           Client clientExisteDeja=ClientDAO.trouverClientByMail(c.getMail());
           if(clientExisteDeja==null){
               JpaUtil.fermerEntityManager();
               throw e;
            }
           else
           {
               JpaUtil.fermerEntityManager();
               throw new Exception("Exception levée dans Services.enregistrerClient() : l'email "+c.getMail()+" existe déjà pour un autre Client");
               
           }
        }
         
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        
        System.out.println("Client créé\n");
        return c;

    }
    
   /**
   * Vérifie si le mot de passe est correct pour un identifiant donné
   * @param mail Adresse e-mail du client qui souhaite se connecter (son identifiant)
   * @param password Mot de passe du client qui souhaite se connecter (son id par défaut)
   * @return Client possédant cet adresse mail et ce mot de passe si il existe, null sinon
   */
    public static Client verifierMotDePasse(String mail, Long password){
        Client cMail=ClientDAO.trouverClientByMail(mail);
        if(cMail.getId()==password){
           return cMail;
        }
        return null;
    }
    
    /**
    * Simule l'envoi du mail d'inscription (en cas de succès ou d'echec)
    * @param c client que l'on souhaite inscrire (enregistrer dans la base de données)
    * @return le client passé en paramètre
    * @Exception Exception si la création a échoué
    */
    public static Client enregistrerClient(Client c) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();

        try{
            ClientDAO.creerClient(c);
            System.out.println("Bienvenue : "+c.getPrenom()+
                   ".\n Nous vous confirmons votre inscription à l'agence IF'Routard. Votre numéro de client est : "+
                   c.getId()+".\n Votre mot de passe est : "+c.getId()+"(Pensez à modifier votre mot de passe)");
        }catch(Exception e){
           JpaUtil.annulerTransaction();
           Client clientExisteDeja=ClientDAO.trouverClientByMail(c.getMail());
           System.out.println("Bienvenue : "+clientExisteDeja.getPrenom()+
                   ".\n Votre inscription à l'agence IF'Routard a échouée. Merci de recommencer ultérieurement");
           if(clientExisteDeja==null){
               JpaUtil.fermerEntityManager();
               throw e;
            }
           else
           {
               JpaUtil.fermerEntityManager();
               throw new Exception("Exception levée dans Services.enregistrerClient() : l'email "+c.getMail()+" existe déjà pour un autre Client");
           }
           
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    
    //Conseiller
    
    /**
    * Ajoute un conseiller dans la base de données
    * @param conseiller Conseiller à ajouter dans la base
    */
    public static void creerConseiller(Conseiller conseiller) {
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        
        ConseillerDAO.creerConseiller(conseiller);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
   /**
   * Supprime un conseiller de la base de données
   * @param id Identifiant du conseiller à supprimer
   * @exception Exception si l'id passé en paramètre ne correspond à aucun conseiller
   */
    public static void supprimerConseiller(Long id) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            ConseillerDAO.supprimerConseiller(id);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
     /**
    * Trouve un conseiller dans la base de données
    * @param id Identifiant du conseiller à supprimer
    * @return Conseiller possédant l'identifiant passé en paramètre, null s'il n'est pas trouvé
    */
    public static Conseiller trouverConseiller(Long id){
        JpaUtil.creerEntityManager();
        
        Conseiller c=ConseillerDAO.trouverConseiller(id);
        
        JpaUtil.fermerEntityManager();
        return c;
    }
 
    /**
    * Modifie un conseiller dans la base de données
    * @param conseiller Conseiller que l'on souhaite mettre à jour dans la base
    * @return Conseiller mis à jour
    * @exception Exception si le conseiller passé en paramètre n'existe pas dans la base
    */
    public static Conseiller modifierConseiller(Conseiller conseiller)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            conseiller=ConseillerDAO.modifierConseiller(conseiller);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return conseiller;
    }
     
     
    //Voyage
    
     /**
    * Ajoute un voyage dans la base de données
    * @param v Voyage à ajouter dans la base
    */
    public static void creerVoyage(Voyage v){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        VoyageDAO.creerVoyage(v);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
 
    /**
    * Supprime un voyage de la base de données
    * @param id Identifiant du voyage à supprimer
    * @exception Exception si l'id passé en paramètre ne correspond à aucun voyage
    */
    public static void supprimerVoyage(Long id)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            VoyageDAO.supprimerVoyage(id);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
   
     /**
    * Trouve un voyage dans la base de données
    * @param id Identifiant du voyage à supprimer
    * @return Voyage possédant l'identifiant passé en paramètre, null s'il n'est pas trouvé
    */
    public static Voyage trouverVoyage(Long id){
        JpaUtil.creerEntityManager();
        
        Voyage v=VoyageDAO.trouverVoyage(id);
        
        JpaUtil.fermerEntityManager();
        return v;
    }
     
     /**
    * Modifie un voyage dans la base de données
    * @param voyage Voyage que l'on souhaite mettre à jour dans la base
    * @return Voyage mis à jour
    * @exception Exception si le voyage passé en paramètre n'existe pas
    */
    public static Voyage modifierVoyage(Voyage voyage) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            voyage=VoyageDAO.modifierVoyage(voyage);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return voyage;
    }
    
    /**
    * Trouve tous les voyages qui se déroulent dans le pays passé en paramètre
    * @param p pays dont on souhaite connaitre tous les voyages s'y déroulant
    * @return Liste des voyages passant par ce pays, null sinon
    */
    public static List<Voyage> trouverVoyageDuPays(Pays p){
        JpaUtil.creerEntityManager();
        
        List<Voyage> listeDeVoyage=p.getListeVoyage();
        
        JpaUtil.fermerEntityManager();
        return listeDeVoyage;
    }
    
    
    /**
    * Trouve tous les voyages dans la base de données qui passent par tous les pays de la liste passée en paramètre
    * @param listePays Liste de pays dont ou souhaite connaitre les voyages associés.
    * @return Liste des voyages qui passent par tous les pays de la liste de pays
    */
    public static List<Voyage> trouverVoyageParPays(List<Pays> listePays){
        List<Voyage> listeVoyage=new ArrayList<>();
        JpaUtil.creerEntityManager();
        
        listeVoyage=VoyageDAO.listerVoyageComporantPays(listePays);
        
        JpaUtil.fermerEntityManager();
        return listeVoyage;
    }
    
    
    /**
    * Trouve tous les voyages dans la base de données qui passent par tous les pays de la liste passée en paramètre.
    * On peut sélectionner les voyages selon leur type
    * @param listePays Liste de pays dont ou souhaite connaitre les voyages associés.
    * @param circuit true si on veut réupérérer les circuits, false sinon
    * @param sejour true si on veut réupérérer les séjours, false sinon
    * @return Liste des voyages qui passent par tous les pays de la liste de pays et qui sont du(des) type(s) demandé(s)
    */ 
    public static List<Voyage> trouverVoyageParPaysEtType(List<Pays> listePays, boolean circuit, boolean sejour){
        List<Voyage> listeVoyage=new ArrayList<>();
        JpaUtil.creerEntityManager();
        
        listeVoyage=VoyageDAO.listerVoyageComporantPays(listePays, circuit, sejour);
        
        JpaUtil.fermerEntityManager();
        return listeVoyage;
    }

    /**
    * Liste tous les voyages de type TypeVoyage
    * @param typeVoyage Entier correspondant au type de voyage (Circuit=0, Séjour=1)
    * @return Liste des voyages du même type (Circuit ou Séjour). Renvoi une liste nulle si un autre paramètre différent est passé ou si aucun voyage n'est de ce type.
    */
    public static List<Voyage> trouverVoyageParType(int typeVoyage){
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
    
    
    //Sejour
    
     /**
    * Ajoute un Sejour dans la base de données
    * @param s Sejour à ajouter dans la base
    */
    public static void creerSejour(Sejour s){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        SejourDAO.creerSejour(s);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
   
    //Circuit
    
     /**
    * Ajoute un Circuit dans la base de données
    * @param c Circuit à ajouter dans la base
    */
    public static void creerCircuit(Circuit c){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        CircuitDAO.creerCircuit(c);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    
    
    //Depart
    
     /**
    * Ajoute un Depart dans la base de données
    * @param d Depart à ajouter dans la base
    */
    public static void creerDepart(Depart d){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        DepartDAO.creerDepart(d);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
     /**
    * Supprime un depart de la base de données
    * @param id Identifiant du depart à supprimer
    * @exception Exception si l'id passé en paramètre ne correspond à aucun depart
    */
    public static void supprimerDepart(Long id)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DepartDAO.supprimerDepart(id);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
     
     /**
    * Trouve un depart dans la base de données
    * @param id Identifiant du depart à supprimer
    * @return Depart possédant l'identifiant passé en paramètre, null s'il n'est pas trouvé
    */
    public static Depart trouverDepart(Long id){
        JpaUtil.creerEntityManager();
        
        Depart d=DepartDAO.trouverDepart(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }
     
     /**
    * Modifie un depart dans la base de données
    * @param d Depart que l'on souhaite mettre à jour dans la base
    * @return Depart mis à jour
    * @exception Exception si le depart passé en paramètre n'existe pas dans la base
    */
    public static Depart modifierDepart(Depart d)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DepartDAO.modifierDepart(d);
        } catch (NonexistentEntityException ex) {
             JpaUtil.annulerTransaction();
             JpaUtil.fermerEntityManager();
             throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

     /**
    * Liste tous les clients dans la base de données
    * @param n Nombre de départs contenus dans la liste à retourner
    * @return Liste des departs ayant une date supérieure à la date d'aujourd'hui et triée par date (de la plus proche à la plus lointaine)
    */
    public static List<Depart> listerProchainsDeparts(int n){
        JpaUtil.creerEntityManager();
        
        List<Depart> listeTotale=DepartDAO.listerProchainsDeparts();
        List<Depart> listeVoulue=new ArrayList<>();
        for(int i=0; i<listeTotale.size() && i<n; i++){
            listeVoulue.add(listeTotale.get(i));
        }
        JpaUtil.fermerEntityManager();
        return listeVoulue;
    }
    
    
    //Devis
    
     /**
    * Ajoute un Devis dans la base de données et simule l'envoi du mail contenant toutes les informations relatives à ce devis
    * @param c Client associé au Devis
    * @param dp Depart associé au Devis
    * @param nbPersonnes Nombre de participants du voyage
    * @return Devis que l'on vient de créer
    * @exception Exception levée si une erreur s'est produite
    */
    public static Devis creerDevis(Client c, Depart dp, int nbPersonnes) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        Conseiller conseillerLibre=ConseillerDAO.getConseillerLibre();
        Devis d=new Devis(nbPersonnes);
        d.setDepart(dp);
        d.setClient(c);
        d.setConseiller(conseillerLibre);
        
        DevisDAO.creerDevis(d);

        //mise a jour Client
        c.addDevis(d);
        try {
            c=ClientDAO.modifierClient(c);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        //mise a jour conseiller
        conseillerLibre.addDevis(d);
        try {
            conseillerLibre=ConseillerDAO.modifierConseiller(conseillerLibre);
        } catch (NonexistentEntityException ex) {
             JpaUtil.annulerTransaction();
             JpaUtil.fermerEntityManager();
             throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();

        Voyage v=d.getVoyage();

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
        
        return d;
    }
    
    /**
    * Supprime un devis de la base de données
    * @param id Identifiant du depart à supprimer
    * @exception Exception si l'id passé en paramètre ne correspond à aucun devis
    */
    public static void supprimerDevis(Long id)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            DevisDAO.supprimerDevis(id);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }

     /**
    * Trouve un devis dans la base de données
    * @param id Identifiant du devis à supprimer
    * @return Devis possédant l'identifiant passé en paramètre, null s'il n'est pas trouvé
    */
    public static Devis trouverDevis(Long id){
        JpaUtil.creerEntityManager();
        
        Devis d=DevisDAO.trouverDevis(id);
        
        JpaUtil.fermerEntityManager();
        return d;
    }
     
     /**
    * Modifie un devis dans la base de données
    * @param d Devis que l'on souhaite mettre à jour dans la base
    * @return Devis mis à jour
    * @exception Exception si le devis passé en paramètre n'existe pas dans la base
    */
    public static Devis modifierDevis(Devis d)throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        try {
            d=DevisDAO.modifierDevis(d);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }

    /**
    * Liste tous les devis
    * @return Liste de tous les devis
    */
    public static List<Devis> listerDevis(){
        JpaUtil.creerEntityManager();
         
        List<Devis> listeDevis=DevisDAO.listerDevis();
        
        JpaUtil.fermerEntityManager();
        return listeDevis;
    }
    
     /**
    * Création d'un devis de manière interactive en console
    * @param c Client créant le devis
    * @param dp Depart associé au Devis
    * @return Devis que l'on vient de créer grace aux informations fournies par l'utilisateur
    * @exception Exception si une erreur s'est produite
    */
    public static Devis creerDevisIteractif(Client c, Depart dp) throws Exception{
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        //nombre de participants
        int nbParticipants=0;
        while( (nbParticipants=Saisie.lireInteger("Nombre de participants :\n")) <=0){
            System.out.println("Le nombre de partcipants doit être positif");
        }
        
        Conseiller conseillerLibre=ConseillerDAO.getConseillerLibre();
        Devis d=new Devis(nbParticipants);
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
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        //mise a jour conseiller
        conseillerLibre.addDevis(d);
        try {
            conseillerLibre=ConseillerDAO.modifierConseiller(conseillerLibre);
        } catch (NonexistentEntityException ex) {
            JpaUtil.annulerTransaction();
            JpaUtil.fermerEntityManager();
            throw ex;
        }
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return d;
    }
    
    
    //Pays
    
     /**
    * Ajoute un Pays dans la base de données
    * @param p Pays à ajouter dans la base
    */
    public static void creerPays(Pays p){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        PaysDAO.creerPays(p);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
     /**
    * Liste tous les pays que l'on peut visiter (dont au moins un voyage s'y déroule)
    * @return Liste des pays que l'on peut aller visiter
    */
    public List<Pays> listerDestinations(){
        List<Pays> listeDestinations=new ArrayList<>();
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
    * Liste tous les pays (y compris ce qui n'ont pas de voyage associé)
    * @return Liste de tous les pays de la base
    */
    public static List<Pays> listerAllPays(){
        JpaUtil.creerEntityManager();
        
        List<Pays> listePays=PaysDAO.listerPays();
        
        JpaUtil.fermerEntityManager();
        return listePays;
    }

     /**
    * Trouve un pays dans la base de données
    * @param id Identifiant du pays à supprimer
    * @return Pays possédant l'identifiant passé en paramètre, null s'il n'est pas trouvé
    */
    public static Pays trouverPays(Long id){
        JpaUtil.creerEntityManager();
        
        Pays p=PaysDAO.trouverPays(id);
        
        JpaUtil.fermerEntityManager();
        return p;
    }

}
