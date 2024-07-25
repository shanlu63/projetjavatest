/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;
import com.mycompany.projetjavatest.domain.Emplois;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author shan
 */
public class BasicDAO<T> {
    
     /**
     * Lit toutes les lignes d'un fichier TXT spécifié.
     *
     * @param filePath le chemin du fichier TXT à lire.
     * @return une liste de chaînes représentant chaque ligne du fichier.
     * @throws IOException si une erreur survient lors de la lecture du fichier.
     */
     public List<String> readFromTxt(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    /**
     * Écrit des données dans un fichier TXT spécifié. Si le fichier existe déjà, il sera écrasé.
     *
     * @param filePath le chemin du fichier TXT à écrire.
     * @param data la liste de chaînes à écrire dans le fichier.
     * @throws IOException si une erreur survient lors de l'écriture dans le fichier.
     */
    public void writeToTxt(String filePath, List<String> data) throws IOException {
        Files.write(Paths.get(filePath), data, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

   
   
    
}
