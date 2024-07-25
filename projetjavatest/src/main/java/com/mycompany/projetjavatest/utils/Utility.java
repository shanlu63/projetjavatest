/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.utils;

/**
 *
 * @author shan
 */

/**
 * La classe utilitaire sert à :
 * Traiter diverses situations de saisie utilisateur et permettre aux programmeurs d'obtenir les entrées de la console de l'utilisateur selon leurs besoins.
 */

import java.util.*;

public class Utility {
    // Attributs statiques
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Fonction : Lire une option de menu saisie au clavier, valeur : entre 1 et 5
     * @return 1 à 5
     */
    public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false); // Inclut une chaîne de caractères de longueur 1
            c = str.charAt(0); // Convertit la chaîne en caractère de type char
            if (c != '1' && c != '2' && 
                c != '3' && c != '4' && c != '5') {
                System.out.print("Choix incorrect, veuillez réessayer : ");
            } else break;
        }
        return c;
    }

    /**
     * Fonction : Lire un caractère saisi au clavier
     * @return un caractère
     */
    public static char readChar() {
        String str = readKeyBoard(1, false); // Une seule chaîne de caractères
        return str.charAt(0);
    }

    /**
     * Fonction : Lire un caractère saisi au clavier, ou retourne une valeur par défaut si l'entrée est vide
     * @param defaultValue Valeur par défaut spécifiée
     * @return Valeur par défaut ou caractère saisi
     */
    public static char readChar(char defaultValue) {
        String str = readKeyBoard(1, true); // Une chaîne vide ou un seul caractère
        return (str.length() == 0) ? defaultValue : str.charAt(0);
    }

    /**
     * Fonction : Lire un entier saisi au clavier, de longueur inférieure à 2 chiffres
     * @return un entier
     */
    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false); // Un entier de longueur <= 2 chiffres
            try {
                n = Integer.parseInt(str); // Convertit la chaîne en entier
                break;
            } catch (NumberFormatException e) {
                System.out.print("Erreur de saisie, veuillez réessayer : ");
            }
        }
        return n;
    }

    /**
     * Fonction : Lire un entier saisi au clavier ou retourner une valeur par défaut, si l'entrée est vide
     * @param defaultValue Valeur par défaut spécifiée
     * @return Entier ou valeur par défaut
     */
    public static int readInt(int defaultValue) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(10, true);
            if (str.equals("")) {
                return defaultValue;
            }

            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("Erreur de saisie, veuillez réessayer : ");
            }
        }
        return n;
    }

    /**
     * Fonction : Lire une chaîne de caractères saisie au clavier de longueur spécifiée
     * @param limit Longueur maximale
     * @return Chaîne de caractères de longueur spécifiée
     */
    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }

    /**
     * Fonction : Lire une chaîne de caractères saisie au clavier de longueur spécifiée ou retourner une valeur par défaut, si l'entrée est vide
     * @param limit Longueur maximale
     * @param defaultValue Valeur par défaut spécifiée
     * @return Chaîne de caractères de longueur spécifiée ou valeur par défaut
     */
    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("") ? defaultValue : str;
    }

    /**
     * Fonction : Lire une option de confirmation saisie au clavier, Y ou N
     * @return Y ou N
     */
    public static char readConfirmSelection() {
        System.out.print("Confirmer la réservation (Y/N) : ");
        char c;
        for (; ; ) { // Boucle infinie
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Choix incorrect, veuillez réessayer : ");
            }
        }
        return c;
    }

    /**
     * Fonction : Lire une chaîne de caractères
     * @param limit Longueur maximale
     * @param blankReturn Si true, accepte une chaîne vide. Si false, n'accepte pas de chaîne vide.
     * @return Chaîne de caractères
     */
    private static String readKeyBoard(int limit, boolean blankReturn) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.length() == 0) {
                if (blankReturn) return line; // Si blankReturn=true, peut retourner une chaîne vide
                else continue; // Si blankReturn=false, ne pas accepter de chaîne vide
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.print("Longueur incorrecte (ne peut pas dépasser " + limit + "), veuillez réessayer : ");
                continue;
            }
            break;
        }

        return line;
    }
}
