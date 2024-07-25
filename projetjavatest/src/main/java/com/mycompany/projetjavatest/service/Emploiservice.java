/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.service;

import com.mycompany.projetjavatest.DAO.EmploisDAO;
import com.mycompany.projetjavatest.domain.Emplois;
import java.util.ArrayList;
import java.util.List;

/**
 * *Structure du projet
*domain : contient la classe Emplois.
*DAO : contient la classe EmploisDAO.
*service : contient la classe EmploisService.
*utils : contient la classe FileUtils pour la lecture du fichier.
 * @author shan
 */
public class Emploiservice {
     private EmploisDAO emploisDAO = new EmploisDAO();

    /**
     * Récupère un objet `Emplois` en fonction de l'identifiant et du mot de passe.
     *
     * @param empId l'identifiant de l'employé.
     * @param mdp le mot de passe de l'employé.
     * @return l'objet `Emplois` correspondant ou null s'il n'existe pas.
     */
    public Emplois getEmploisbyIDandMDP(String empId, String mdp) {
        try {
            // Lire les lignes du fichier texte
            List<String> emploisStringList = emploisDAO.readFromTxt("emplois.txt");
            // Parser les lignes en objets Emplois
            List<Emplois> emploisList = emploisDAO.parseData(emploisStringList);

            // Rechercher l'emploi correspondant à l'empId et mdp
            for (Emplois emploi : emploisList) {
                if (emploi.getEmpId().equals(empId) && emploi.getMdp().equals(mdp)) {
                    return emploi;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Si aucun emploi correspondant n'est trouvé
    }
    
    // Méthode pour écrire la liste des emplois dans un fichier
    public void saveAllEmploisToFile(List<Emplois> emploisList, String filePath) {
        List<String> emploisStringList = new ArrayList<>();
        for (Emplois emploi : emploisList) {
            String emploiStr = emploi.getId() + "," +
                                emploi.getEmpId() + "," +
                                emploi.getMdp() + "," +
                                emploi.getNom() + "," +
                                emploi.getJob();
            emploisStringList.add(emploiStr);
        }
        
        try {
            filePath="emplois.txt";
            emploisDAO.writeToTxt(filePath, emploisStringList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}