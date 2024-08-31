/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;
import com.mycompany.projetjavatest.domain.Command;
import com.mycompany.projetjavatest.domain.Facture;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author shan
 */
public class FactureDAO {
    private static final String FILE_NAME = "facture.txt";

    // Method to save a Facture object to a file
    public void saveFacture(Facture facture) {
       
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(facture.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving facture: " + e.getMessage());
            e.printStackTrace();
        }
    
    }
    
    


    // Additional methods like reading from the file, updating, deleting can be added here

    public Facture readLastFacture() {
       Facture lastFacture = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                lastLine = line; // Keep updating lastLine until the end of the file
            }
            if (lastLine != null) {
                lastFacture = parseFacture(lastLine);
            }
        } catch (IOException e) {
            System.err.println("Error reading factures: " + e.getMessage());
            e.printStackTrace();
        }
        return lastFacture;
    }

    private Facture parseFacture(String factureString) {
        try {
            String[] parts = factureString.split(";");
            if (parts.length != 6) {
                throw new IllegalArgumentException("Invalid facture format");
            }

            int idFacture = Integer.parseInt(parts[0]);
            float totalPrixHT = parseFloat(parts[1]);
            float totalTVA = parseFloat(parts[2]);
            float totalPrixTTC = parseFloat(parts[3]);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            Date date = dateFormat.parse(parts[4]);

            String paymentMethod = parts[5];

            Facture facture = new Facture();
            facture.setIdFacture(idFacture);
            facture.setTotalPrixHT(totalPrixHT);
            facture.setTotalTVA(totalTVA);
            facture.setTotalPrixTTC(totalPrixTTC);
            facture.setDate(date);
            facture.setPaymentMethod(paymentMethod);

            return facture;
        } catch (Exception e) {
            System.err.println("Error parsing facture: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // Helper method to parse floats with locale consideration
    private float parseFloat(String value) {
        try {
            return Float.parseFloat(value.replace(',', '.'));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing float value: " + value);
            e.printStackTrace();
            return 0.0f;
        }
    }
    // Method to read all Factures from the file
    public List<Facture> readAllFactures() {
         List<Facture> factures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Facture facture = parseFacture(line);
                if (facture != null) {
                    factures.add(facture);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading factures: " + e.getMessage());
            e.printStackTrace();
        }
        return factures;
    }
    
    

}
