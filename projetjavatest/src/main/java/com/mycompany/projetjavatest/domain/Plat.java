/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.util.List;


public class Plat extends Cuisine {

    /**
     * Constructeur avec paramètres pour un plat.
     * 
     * @param prix le prix du plat
     * @param composants la liste des composants du plat
     * @param nom le nom du plat
     */
    public Plat(float prix, List<String> composants, String nom) {
        super(prix, composants, nom, 1); // Quantité par défaut à 1, ajustez si nécessaire
    }
  
    @Override
    public String toString() {
        // Utilise la méthode toString() de la classe parente pour afficher les détails du plat
        return super.toString() + " (Plat)";
    }
}