package com.FSTM.syndica.Model;

public class Syndic {
    public int id;
    public String nom;
    public String prenom;
    public String adresse;



    public Syndic(int id, String nom, String prenom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;

    }

    public int getSyndicId() {
        return id;
    }

    public void setSyndicId(int syndicId) {
        this.id = syndicId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
