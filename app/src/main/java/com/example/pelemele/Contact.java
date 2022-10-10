package com.example.pelemele;

import java.io.Serializable;

public class Contact implements Serializable {
    private String Nom;
    private String phoneNumber;


    public Contact(String userName, String phoneNumber)  {
        this.Nom = userName;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }


    @Override
    public String toString() {
        return this.Nom +" ("+ this.phoneNumber +")";
    }
}
