/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

/**
 * Classe représentant un menu.
 * 
 * Cette classe contient les détails d'un menu, y compris l'ID du menu,
 * le nom du plat, le type de plat, le prix du plat et la quantité commandée.
 * 
 */
public class Menu {
    private int menuId; // ID du menu pour commander
    private String nameplate; // Nom du plat
    private String type; // Type de plat
    private float prix; // Prix du plat
    private int quantite; // Quantité du plat

    /**
     * Constructeur par défaut.
     */
    public Menu() {
    }

    /**
     * Constructeur avec paramètres.
     * 
     * @param menuId l'ID du menu
     * @param nameplate le nom du plat
     * @param type le type de plat
     * @param prix le prix du plat
     * @param quantite la quantité du plat
     */
    public Menu(int menuId, String nameplate, String type, float prix, int quantite) {
        this.menuId = menuId;
        this.nameplate = nameplate;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
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

    public int getQuantite() {
        return quantite;
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

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
   

    // toString
    @Override
    public String toString() {
   
         return  ""+menuId +"   "+ nameplate+ "   " + type+ "   " +prix+ "   €  " + quantite ;
               
    }

    public Object getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}