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
class Entree extends Cuisine {
    /**
     * Constructeur avec paramètres pour une entrée.
     * 
     * @param prix le prix de l'entrée
     * @param composants la liste des composants de l'entrée
     * @param nom le nom de l'entrée
     */
    public Entree(float prix, List<String> composants, String nom) {
        super(prix, composants, nom, 1); // Quantité par défaut à 1, ajustez si nécessaire
    }

    @Override
    public String toString() {
        return "Entrée: " + super.toString();
    }
}
