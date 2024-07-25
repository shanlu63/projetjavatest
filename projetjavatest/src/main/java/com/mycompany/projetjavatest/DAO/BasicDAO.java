/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;
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

    /**
     * Parse une liste de chaînes en une liste d'objets de type T.
     *
     * @param data la liste de chaînes à parser.
     * @param clazz la classe des objets à créer.
     * @return une liste d'objets de type T.
     * @throws Exception si une erreur survient lors du parsing des données.
     */
    public List<T> parseData(List<String> data, Class<T> clazz) throws Exception {
        List<T> result = new ArrayList<>();
        for (String line : data) {
            String[] fields = line.split(",");
            if (clazz.equals(Emplois.class)) {
                Emplois emploi = new Emplois(
                    Integer.parseInt(fields[0]), // id
                    fields[1],                   // empId
                    fields[2],                   // mdp
                    fields[3],                   // nom
                    fields[4]                    // job
                );
                result.add((T) emploi);
            }
            // Si d'autres types d'objets doivent être traités, ajouter plus de logique de parsing ici
        }
        return result;
    }
    
    
}
