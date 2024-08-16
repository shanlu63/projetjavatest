/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Facture {
    private static int nextIdFacture = 202400001; // le 1 e  facture ID 
    private int idFacture;
    private List<Command> commands; // Command
    private float prixTotalHT;
    private float prixTVA;
    private float prixTotalTTC;
    private Date date;
    private String payement; // payement :  en espece ou carte bleue

    // constructeur 
    public Facture(Command command, String payement) {
        this.idFacture = nextIdFacture++; // 
        this.commands = new ArrayList<>(); // 
        this.commands.add(command); // 
        this.payement = payement;
        this.date = new Date(); // 
        calculateTotals(); // 
    }

    // calculateTotals HT、TVA TTC
    private void calculateTotals() {
        this.prixTotalHT = 0;
        this.prixTVA = 0;
        for (Command command : commands) {
            this.prixTotalHT += command.getPrixTotalHT();
            this.prixTVA += command.getPrixTVA();
        }
        this.prixTotalTTC = this.prixTotalHT + this.prixTVA;
    }

    // Getter  Setter 
    public int getIdFacture() {
        return idFacture;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
        calculateTotals(); // mise en jour 
    }

    public float getPrixTotalHT() {
        return prixTotalHT;
    }

    public float getPrixTVA() {
        return prixTVA;
    }

    public float getPrixTotalTTC() {
        return prixTotalTTC;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayement() {
        return payement;
    }

    public void setPayement(String payement) {
        this.payement = payement;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", commands=" + commands +
                ", prixTotalHT=" + prixTotalHT +
                ", prixTVA=" + prixTVA +
                ", prixTotalTTC=" + prixTotalTTC +
                ", date=" + date +
                ", payement='" + payement + '\'' +
                '}';
    }
}

