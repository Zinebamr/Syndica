package com.FSTM.syndica.Model;

import java.time.LocalDate;
import java.util.Date;

public class Reunion {
    public int id;
    public LocalDate date;
    public String titre;
    public String ordre;
    public int Syndic_id;
    public String decission_final;
    public int Corpropriete_id;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public int getSyndic_id() {
        return Syndic_id;
    }

    public void setSyndic_id(int syndic_id) {
        Syndic_id = syndic_id;
    }

    public String getDecission_final() {
        return decission_final;
    }

    public void setDecission_final(String decission_final) {
        this.decission_final = decission_final;
    }

    public int getCorpropriete_id() {
        return Corpropriete_id;
    }

    public void setCorpropriete_id(int corpropriete_id) {
        Corpropriete_id = corpropriete_id;
    }
}
