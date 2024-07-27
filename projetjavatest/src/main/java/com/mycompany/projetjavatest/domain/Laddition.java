/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.util.Date;

public class Laddition {
    private String additionId; // le numero de l'addition creer par default 
    private int menuId; // le id of plate of menu
    private int nums; // quatite de plate
    private float prix;//prix pour chquae addition
    private int tableId;//id of table
    private Date additiondate;//date de l'addition
    private String status; // status de l'addition :1. payé 2. non payement 

    public Laddition() {
    }

    public Laddition(String additionId, int menuId, int nums, float prix, int tableId, Date additiondate, String status) {
        this.additionId = additionId;
        this.menuId = menuId;
        this.nums = nums;
        this.prix = prix;
        this.tableId = tableId;
        this.additiondate = additiondate;
        this.status = status;
    }

    // Getters
    public String getAdditionId() {
        return additionId;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getNums() {
        return nums;
    }

    public float getPrix() {
        return prix;
    }

    public int getTableId() {
        return tableId;
    }

    public Date getAdditiondate() {
        return additiondate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setAdditionId(String additionId) {
        this.additionId = additionId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setAdditiondate(Date additiondate) {
        this.additiondate = additiondate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString
    @Override
    public String toString() {
        return "<html>L'addition : " +
                "<br>addition : " + additionId + 
                "<br>menuId : " + menuId +
                "<br>nums : " + nums +
                "<br>prix : " + prix +
                "<br>tableId : " + tableId +
                "<br>additiondate : " + additiondate +
                "<br>status : " + status + "</html>";
    }
}
