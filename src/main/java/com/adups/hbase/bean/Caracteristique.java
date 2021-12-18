package com.adups.hbase.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Caracteristique {
    private String matricule;
    private String fuel;
    private String transmission;
    private String nombrePorte;
    private String puissanceFiscale;
    private String kilometrage;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getNombrePorte() {
        return nombrePorte;
    }

    public void setNombrePorte(String nombrePorte) {
        this.nombrePorte = nombrePorte;
    }

    public String getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissanceFiscale(String puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }
}
