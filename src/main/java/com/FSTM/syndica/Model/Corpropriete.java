package com.FSTM.syndica.Model;

public class Corpropriete {

    private int id;
    private String nom;
    private String ville;
    private int codePostal;
    private int nbrUnite;
    private double montantCotis;
    private double montantDispo;
    private String adresse;
    private int syndicId;
    private int typeCorproprieteId;

    public Corpropriete() {
    }

    public String getShowedId(){
        return id+"_"+nom;
    }
    public Corpropriete(int id, String nom, String ville, int codePostal, int nbrUnite, double montantCotis, double montantDispo, String adresse, int syndicId, int typeCorproprieteId) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.codePostal = codePostal;
        this.nbrUnite = nbrUnite;
        this.montantCotis = montantCotis;
        this.montantDispo = montantDispo;
        this.adresse = adresse;
        this.syndicId = syndicId;
        this.typeCorproprieteId = typeCorproprieteId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public int getNbrUnite() {
        return nbrUnite;
    }

    public void setNbrUnite(int nbrUnite) {
        this.nbrUnite = nbrUnite;
    }

    public double getMontantCotis() {
        return montantCotis;
    }

    public void setMontantCotis(double montantCotis) {
        this.montantCotis = montantCotis;
    }

    public double getMontantDispo() {
        return montantDispo;
    }

    public void setMontantDispo(double montantDispo) {
        this.montantDispo = montantDispo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getSyndicId() {
        return syndicId;
    }

    public void setSyndicId(int syndicId) {
        this.syndicId = syndicId;
    }

    public int getTypeCorproprieteId() {
        return typeCorproprieteId;
    }

    public void setTypeCorproprieteId(int typeCorproprieteId) {
        this.typeCorproprieteId = typeCorproprieteId;
    }
}
