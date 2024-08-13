/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.util.List;

/**
 *
 * @author shan
 */
public class Dessert extends Cuisine {
 /**
     * Constructeur avec paramètres pour un dessert.
     * 
     * @param prix le prix du dessert
     * @param composants la liste des composants du dessert
     * @param nom le nom du dessert
     */
    public Dessert(float prix, List<String> composants, String nom) {
        super(prix, composants, nom, 1); // Quantité par défaut à 1, ajustez si nécessaire
    }

    @Override
    public String toString() {
        // Utilise la méthode toString() de la classe parente pour afficher les détails du dessert
        return super.toString() + " (Dessert)";
    }
 }
