/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.text.SimpleDateFormat;

/**
 * Représente une table avec un identifiant unique, un statut, un nombre de sièges,
 * et des informations de réservation.
 * 
 */
public class Table {
    private int idtable;    // ID unique
    private int status;     // 1 - disponible, 2 - réservé, 3 - occupé
    private int seat;       // Nombre de sièges
    private String orderName; // Nom du réservateur
    private String orderTel;  // Téléphone du réservateur
    private String horaire;   // Horaire de réservation, format mm/hh/jj-mm-yy
    
    //constructuer initialize
    public Table(){
    }
    /**
     * Constructeur pour les tables avec réservation.
     */
    public Table(int idtable, int status, int seat, String orderName, String orderTel, String horaire) {
        this.idtable = idtable;
        this.status = status;
        this.seat = seat;
        this.orderName = orderName;
        this.orderTel = orderTel;
        this.horaire = horaire;
    }
   
   
    /**
     * Constructeur pour les tables sans réservation.
     * @param idtable
     * @param status
     * @param seat
     */
    public Table(int idtable, int status, int seat) {
        this.idtable = idtable;
        this.status = status;
        this.seat = seat;
        this.orderName = null;
        this.orderTel = null;
        this.horaire = null;
    }

    // Getters et Setters

    public int getIdtable() {
        return idtable;
    }

    public void setIdtable(int idtable) {
        this.idtable = idtable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    /**
     * Retourne une représentation textuelle du statut de la table.
     * @return 
     */
    public String getStatusText() {
        String statusString;
        switch (status) {
            case 1:
                statusString = "Disponible";
                break;
            case 2:
                statusString = "Réservé";
                break;
            case 3:
                statusString = "Occupé";
                break;
            default:
                statusString = "Inconnu";
                break;
        }
        return "<html>Table " + idtable + ":<br>" + statusString + "<br>Sièges: " + seat + "</html>";
    }
    public String getStatusTextdetail() {
        String statusString;
        switch (status) {
            case 1:
                statusString = "Disponible";
                break;
            case 2:
                statusString = "Réservé";
                break;
            case 3:
                statusString = "Occupé";
                break;
            default:
                statusString = "Inconnu";
                break;
        }
        String detail = "<html>Détail de Table :<br> Table " + idtable + ": <br>Status :" + statusString + "<br>Sièges: " + seat + "<br>Nom du réservateur : " + orderName + "<br>téléphone : "+ orderTel+"<br>horaire : "+horaire+"</html>";
    
        return detail;
    }

     //set resrevation
    public void setReservationDetails(String orderName, String orderTel, String horaire) {
        
        this.orderName = orderName;
        this.orderTel = orderTel;
        this.horaire = horaire;
    }

    /**
     * Convertit la table en une chaîne CSV.
     *
     * @return une chaîne CSV en format txt représentant la table
     */
     // Method to convert table to CSV string dans le table.txt
    public String toCSVString() {
        return idtable + "," + status + "," + seat + "," + orderName + "," + orderTel + "," + horaire;
    }

}