/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

/**
 *
 * @author shan
 */
public class Menu {
    private int menuId;//id de menu pour commander
    private String nameplate;//nom de plate
    private String type;//type de plate
    private float prix;//prix pour le plate

    public Menu () {//CONSTRUCTEUR
    }

    public Menu(int menuId, String nameplate, String type, float prix) {
        this.menuId = menuId;
        this.nameplate = nameplate;
        this.type = type;
        this.prix = prix;
    }
     // Getters
    public int getMenuId() {
        return menuId;
    }

    public String getNameplate() {
        return nameplate;
    }

    public String getType() {
        return type;
    }

    public float getPrix() {
        return prix;
    }

    // Setters
    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    // toString
    @Override
    public String toString() {
        return "<html>Menu" +
                "<br>menuId : " + menuId +
                "<br>nameplate : " + nameplate + 
                "<br>type : " + type + 
                "<br>prix : " + prix +"</html>";
    }
}