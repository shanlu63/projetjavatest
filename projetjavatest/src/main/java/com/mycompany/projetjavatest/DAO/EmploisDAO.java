/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;

import com.mycompany.projetjavatest.domain.Emplois;
import java.util.ArrayList;
import java.util.List;

public class EmploisDAO extends BasicDAO<Emplois> {

    /**
     * Parse les données en objets de type `Emplois`.
     *
     * @param data la liste de chaînes représentant les lignes du fichier.
     * @return une liste d'objets `Emplois`.
     * @throws Exception si une erreur survient lors du parsing des données.
     */
    public List<Emplois> parseData(List<String> data) throws Exception {
        List<Emplois> result = new ArrayList<>();
        for (String line : data) {
            String[] fields = line.split(",");
            if (fields.length == 5) {
                Emplois emploi = new Emplois(
                    Integer.parseInt(fields[0]), // id
                    fields[1],                   // empId
                    fields[2],                   // mdp
                    fields[3],                   // nom
                    fields[4]                    // job
                );
                result.add(emploi);
            }
        }
        return result;
    }
}