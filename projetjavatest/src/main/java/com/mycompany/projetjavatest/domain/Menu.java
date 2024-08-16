package com.mycompany.projetjavatest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un menu.
 * 
 * Cette classe contient les détails d'un menu, y compris l'ID du menu,
 * la liste des plats, le type de menu, le prix total et la quantité commandée.
 */
public class Menu {
   
    private int menuId; // ID du menu pour commander
    private List<Cuisine> plat; // Liste des plats
    private String type; // Type de menu
    private float prix; // Prix total du menu
    private int quantite; // Quantité commandée

    /**
     * Constructeur par défaut.
     */
    public Menu() {
    }

    /**
     * Constructeur avec paramètres.
     * 
     * @param menuId
     * @param plat la liste des plats
     * @param type le type de menu
     * @param prix le prix total du menu
     * @param quantite la quantité commandée
     */
     public Menu(int menuId, List<Cuisine> plat, String type, float prix, int quantite) {
        this.menuId = menuId;
        this.plat = new ArrayList<>(plat); // Crée une nouvelle liste pour éviter les modifications externes
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
    }

    // Getters
    public int getMenuId() {
        return menuId;
    }

    public List<Cuisine> getPlat() {
        return plat;
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
   public void setPlat(List<Cuisine> plat) {
        this.plat = new ArrayList<>(plat);
    }

    public void ajouterPlat(Cuisine plate) {
        if (this.plat == null) {
            this.plat = new ArrayList<>();
        }
        this.plat.add(plate);
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
        StringBuilder platsString = new StringBuilder();
        for (Cuisine cuisine : plat) {
            platsString.append(cuisine.getNom()).append(", ");
        }
        // Remove the last comma and space if there are any plats
        if (platsString.length() > 0) {
            platsString.setLength(platsString.length() - 2);
        }
        return menuId + "   " + platsString.toString() + "   " + type + "   " + String.format("%.2f", prix) + " €  Quantité: " + quantite;
    }
}
