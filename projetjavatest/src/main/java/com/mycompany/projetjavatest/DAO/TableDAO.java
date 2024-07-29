/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;

import com.mycompany.projetjavatest.domain.Table;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shan
 */
public class TableDAO {
   private String filePath;
    private List<Table> tableList;

    public TableDAO(String filePath) {
        this.filePath = filePath;
        this.tableList = initializeTableList();
    }

    /**
     * Initialise la liste des tables à partir du fichier spécifié.
     *
     * @return une liste de tables
     */
    public List<Table> initializeTableList() {
        List<Table> tables = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Lire chaque ligne du fichier
            while ((line = br.readLine()) != null) {
                // Ignorer la première ligne si elle contient des en-têtes
                if (line.startsWith("idtable")) continue;

                // Diviser la ligne en parties en fonction des virgules
                String[] parts = line.split(",");

                // Vérifier que la ligne contient le bon nombre de parties
                switch (parts.length) {
                    case 6:
                        try {
                            int idtable = Integer.parseInt(parts[0].trim());
                            int status = Integer.parseInt(parts[1].trim());
                            int seat = Integer.parseInt(parts[2].trim());
                            
                            // Si la table n'a pas de réservation, créer l'objet Table sans réservation
                            if (parts[3].trim().isEmpty() && parts[4].trim().isEmpty() && parts[5].trim().isEmpty()) {
                                Table table = new Table(idtable, status, seat);
                                tables.add(table);
                            } else {
                                String orderName = parts[3].trim();
                                String orderTel = parts[4].trim();
                                String horaire = parts[5].trim();
                                Table table = new Table(idtable, status, seat, orderName, orderTel, horaire);
                                tables.add(table);
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Erreur de format de nombre dans le fichier : " + line);
                        }   break;
                    case 3:
                        try {
                            int idtable = Integer.parseInt(parts[0].trim());
                            int status = Integer.parseInt(parts[1].trim());
                            int seat = Integer.parseInt(parts[2].trim());
                            Table table = new Table(idtable, status, seat);
                            tables.add(table);
                        } catch (NumberFormatException e) {
                            System.err.println("Erreur de format de nombre dans le fichier : " + line);
                        }   break;
                    default:
                        System.err.println("Format incorrect dans le fichier : " + line);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }

        return tables;
    }

    /**
     * Trouve une table par son ID.
     *
     * @param tableId l'ID de la table
     * @return la table correspondante, ou null si elle n'est pas trouvée
     */
    public Table findTableById(int tableId) {
        if (tableList != null) {
            for (Table table : tableList) {
                if (table.getIdtable() == tableId) {
                    return table;
                }
            }
        }
        return null; // Retourner null si la table n'a pas été trouvée
    }

    /**
     * Met à jour la liste des tables.
     *
     * @param tables la nouvelle liste des tables
     */
    public void setTableList(List<Table> tables) {
        this.tableList = tables;
    }

    /**
     * Obtient la liste des tables.
     *
     * @return la liste des tables
     */
    public List<Table> getTableList() {
        return tableList;
    }
    
    /**
     * Met à jour une table par son ID.
     *
     * @param tableId l'ID de la table à mettre à jour
     * @param status  le nouveau statut de la table
     * @param seat
     * @param orderName  le nom de la réservation : NomRDV
     * @param orderTel le téléphone de la réservation ： teleRDV 
     * @param horaire    la date de la réservation : String datastring
     * @return true si la table a été mise à jour, sinon false
     */
    public boolean updateTable(int tableId, int status, int seat, String orderName, String orderTel, String horaire) {
        Table tableToUpdate = findTableById(tableId);//trouver le table id dans le txt 
        if (tableToUpdate != null) {
            tableToUpdate.setStatus(2);// status 2 est reserve 
            tableToUpdate.setReservationDetails(orderName, orderTel, horaire);
            saveTableList(); // save to table.txt
            return true;
        }
        return false;
    }
    private void saveTableList() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.newLine();//nest ligne
            for (Table table : tableList) {
                bw.write(table.toCSVString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }
    public void reloadTableList() {//mis en jour si on modifier le table
        this.tableList = initializeTableList();
    }
    
}