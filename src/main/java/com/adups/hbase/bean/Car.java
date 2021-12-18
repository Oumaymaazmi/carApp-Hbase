package com.adups.hbase.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class Car implements Serializable {
    private String rowKey;
    private Caracteristique caracteristique;
    private Model model;
    private Propietaire propietaire;


    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Caracteristique getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(Caracteristique caracteristique) {
        this.caracteristique = caracteristique;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Propietaire getPropietaire() {
        return propietaire;
    }

    public void setPropietaire(Propietaire propietaire) {
        this.propietaire = propietaire;
    }
}
