/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

/**
 *
 * @author shan
 */
public class Table {
    private int idtable;  // ID unique
    private int status;   // 1 -disponible, 2 - reservé, 3 - occupé
    private int seat;     // nombre de seats，1 represente 1 place，2represente 2 place，依此类推

    // constructeur
    public Table(int idtable, int status, int seat) {
        this.idtable = idtable;
        this.status = status;
        this.seat = seat;
    }

    // getID
    public int getIdtable() {
        return idtable;
    }

    // setID
    public void setIdtable(int idtable) {
        this.idtable = idtable;
    }

    // getStatus
    public int getStatus() {
        return status;
    }

    // setStatus
    public void setStatus(int status) {
        this.status = status;
    }

    // getSeat
    public int getSeat() {
        return seat;
    }

    // setSeat
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // return le string de status
    
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
 }