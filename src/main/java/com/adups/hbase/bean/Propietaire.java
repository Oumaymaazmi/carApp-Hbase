package com.adups.hbase.bean;

import org.springframework.stereotype.Component;

@Component

public class Propietaire {

    private String nom;
    private String prenom;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
