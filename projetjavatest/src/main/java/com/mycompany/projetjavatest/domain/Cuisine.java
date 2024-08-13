/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un plat de cuisine.
 * 
 * Cette classe contient les détails d'un plat, y compris l'ID unique du plat,
 * le prix, les composants (ingrédients), le nom du plat et la quantité.
 */
public class Cuisine {
    private static int nextId = 1;  // Variable statique pour générer des IDs uniques
    private int id;  // Identifiant unique de chaque plat
    private float prix;  // Prix du plat
    private List<String> composants;  // Liste des composants du plat
    private String nom;  // Nom du plat
    private int quantite;  // Quantité du plat

    /**
     * Constructeur avec paramètres.
     * 
     * @param prix le prix du plat
     * @param composants la liste des composants du plat
     * @param nom le nom du plat
     * @param quantite la quantité du plat
     */
    public Cuisine(float prix, List<String> composants, String nom, int quantite) {
        this.id = nextId++;  // Attribution automatique d'un ID unique
        this.prix = prix;
        this.composants = (composants != null) ? composants : new ArrayList<>(); // Initialiser la liste si elle est null
        this.nom = nom;
        this.quantite = quantite;
    }

    // Getters
    public int obtenirid() {
        return id;
    }

    public float obtenirprix() {
        return prix;
    }

    public List<String> obtenircomposants() {
        return composants;
    }

    public String getNom() {
        return nom;
    }

    public int obtenirQuantite() {
        return quantite;
    }

    // Setters
    public void modifierPrix(float prix) {
        this.prix = prix;
    }

    public void modifierComposants(List<String> composants) {
        this.composants = (composants != null) ? composants : new ArrayList<>();
    }

    public void ajouterComposant(String composant) {
        if (this.composants == null) {
            this.composants = new ArrayList<>();
        }
        this.composants.add(composant);
    }

    public void modifierNom(String nom) {
        this.nom = nom;
    }

    public void modifierQuantite(int quantite) {
        this.quantite = quantite;
    }

     // Méthode pour afficher les informations du plat
    @Override
        public String toString() {
         StringBuilder composantsString = new StringBuilder();
         for (int i = 0; i < composants.size(); i++) {
             composantsString.append(composants.get(i)); // Append the string component

             if (i < composants.size() - 1) {
                 composantsString.append(" , "); // Separate components with a comma
             }
         }
         return nom + " (" + composantsString.toString() + ")"; // Return formatted string
     }
     // Méthode pour afficher les informations de l'objet
   
   // Méthode pour afficher les informations de l'objet
    public String toStringInfo() {
        return "Cuisine{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", composants=" + composants +
                ", quantite=" + quantite +
                '}';
    }

    public static String formatCuisineList(List<Cuisine> cuisines) {
     StringBuilder result = new StringBuilder();
     for (int i = 0; i < cuisines.size(); i++) {
         result.append(cuisines.get(i).toString());
         if (i < cuisines.size() - 1) {
             result.append(", "); // Separate each Cuisine object with a comma
         }
     }
     return result.toString();
 }
    
}
