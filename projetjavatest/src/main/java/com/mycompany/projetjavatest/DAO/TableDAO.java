/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;

import com.mycompany.projetjavatest.domain.Emplois;
import com.mycompany.projetjavatest.domain.Table;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shan
 */
public class TableDAO extends BasicDAO<Emplois>{
    private String filePath;

    public TableDAO(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initialise la liste des tables à partir du fichier spécifié.
     *
     * @return une liste de tables
     */
    public List<Table> initializeTableList() {
        List<Table> tableList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Lire chaque ligne du fichier
            while ((line = br.readLine()) != null) {
                // Ignorer la première ligne si elle contient des en-têtes
                if (line.startsWith("idtable")) continue;

                // Diviser la ligne en parties en fonction des virgules
                String[] parts = line.split(",");

                // Vérifier que la ligne contient le bon nombre de parties
                if (parts.length == 6) {
                    try {
                        int idtable = Integer.parseInt(parts[0].trim());
                        int status = Integer.parseInt(parts[1].trim());
                        int seat = Integer.parseInt(parts[2].trim());

                        // Si la table n'a pas de réservation, créer l'objet Table sans réservation
                        if (parts[3].trim().isEmpty() && parts[4].trim().isEmpty() && parts[5].trim().isEmpty()) {
                            Table table = new Table(idtable, status, seat);
                            tableList.add(table);
                        } else {
                            String orderName = parts[3].trim();
                            String orderTel = parts[4].trim();
                            String horaire = parts[5].trim();
                            Table table = new Table(idtable, status, seat, orderName, orderTel, horaire);
                            tableList.add(table);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format de nombre dans le fichier : " + line);
                    }
                } else if (parts.length == 3) {
                    try {
                        int idtable = Integer.parseInt(parts[0].trim());
                        int status = Integer.parseInt(parts[1].trim());
                        int seat = Integer.parseInt(parts[2].trim());
                        Table table = new Table(idtable, status, seat);
                        tableList.add(table);
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format de nombre dans le fichier : " + line);
                    }
                } else {
                    System.err.println("Format incorrect dans le fichier : " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }

        return tableList;
    }
}