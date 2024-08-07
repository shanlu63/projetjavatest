/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;




import java.util.Objects;


import java.util.Date;
import java.util.List;

/**
 * Classe représentant une commande.
 * 
 * Cette classe contient les détails d'une commande, y compris l'ID de la commande,
 * l'ID de la table, le menu commandé, la date de la commande, le prix total hors taxe
 * et le prix de la TVA.
 * 
 * @author shan
 */
public class Command {
    private int idCommand;
    private int idTable;
    private String plate;
    private int quantity;
    private float prixTotalHT;
    private float prixTVA;
    private Date date;
    private boolean payement;// true daja payer, false pas peyer  
/**
     * Constructeur par défaut.
     */
    public Command() {
    }

    /**
     * Constructeur avec paramètres.
     * 
     * @param idCommand l'ID de la commande
     * @param idTable l'ID de la table
     * @param quantity nombre de plate
     * @param plate le plate de commandés
     * @param date la date de la commande
     * @param prixTotalHT le prix total hors taxe
     * @param prixTVA le prix de la TVA
     * @param payement status de payement
     */
    public Command(int idCommand, int idTable,String plate, int quantity, Date date, float prixTotalHT, float prixTVA,boolean payement) {
        this.idCommand = idCommand;
        this.idTable = idTable;
        this.plate = plate;
        this.quantity=quantity;
        this.prixTotalHT = prixTotalHT;
        this.prixTVA = prixTVA;
        this.date = date;
        this.payement =payement;
    }

    // Getters et Setters
    public int getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(int idCommand) {
        this.idCommand = idCommand;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrixTotalHT() {
        return prixTotalHT;
    }

    public void setPrixTotalHT(float prixTotalHT) {
        this.prixTotalHT = prixTotalHT;
    }

    public float getPrixTVA() {
        return prixTVA;
    }

    public void setPrixTVA(float prixTVA) {
        this.prixTVA = prixTVA;
    }
     public boolean getPayement() {
        return payement;
    }

    public void setPayement(boolean payement) {
        this.payement = payement;
    }

    // Méthodes utilitaires
    @Override
    public String toString() {
        return "Command{" +
                "idCommand :" + idCommand +
                ", idTable :" + idTable +
                ", payement :" + payement +
                ", date :" + date +
                ", prixTotalHT :" + prixTotalHT +
                ", prixTVA : " + prixTVA +
                ", payement : " + payement +
                '}';
    }

    public boolean isPayement() {
        return payement;
    }

   

   
}