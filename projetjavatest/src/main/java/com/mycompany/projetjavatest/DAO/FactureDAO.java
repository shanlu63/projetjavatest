/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.DAO;

import com.mycompany.projetjavatest.domain.Facture;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
/**
 *
 * @author shan
 */
public class FactureDAO {
    private static final String FILE_NAME = "facture.txt";

    // Method to save a Facture object to a file
    public void saveFacture(Facture facture) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME, true), StandardCharsets.UTF_8))) {
            writer.write(facture.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving facture: " + e.getMessage());
        }
    }

    
    


    // Additional methods like reading from the file, updating, deleting can be added here

    public Facture readLastFacture() {
        Facture lastFacture = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read line: " + line);  // test
                lastLine = line;  // test lastLine 
            }
            if (lastLine != null) {
                lastFacture = parseFacture(lastLine);
                System.out.println("Parsed last facture: " + lastFacture);  // test
            }
        } catch (IOException e) {
            System.err.println("Error reading factures: " + e.getMessage());
        }
        return lastFacture;
    }


    private Facture parseFacture(String factureString) {
            try {
            System.out.println("Parsing facture string: " + factureString);  //test

            // split Facture info
            String[] parts = factureString.split(";");

            // Facture numéro:202400001
            String idPart = parts[0].split(":")[1].trim();
            int idFacture = Integer.parseInt(idPart);

            // Prix HT en total :4,58 €
            String prixHTPart = parts[1].split(":")[1].replace("€", "").trim();
            float totalPrixHT = parseFloat(prixHTPart);

            // Prix TVA :0,92 €
            String tvaPart = parts[2].split(":")[1].replace("€", "").trim();
            float totalTVA = parseFloat(tvaPart);

            // Prix TTC en total :5,50 €
            String prixTTCPart = parts[3].split(":")[1].replace("€", "").trim();
            float totalPrixTTC = parseFloat(prixTTCPart);

            // Date :13/09/2024-10:27
            String datePart = parts[4].split(":", 2)[1].trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            Date date = dateFormat.parse(datePart);

            // Payement méthod :carte bleue
            String paymentMethod = parts[5].split(":")[1].trim();

            // create Facture objetif 
            Facture facture = new Facture();
            facture.setIdFacture(idFacture);
            facture.setTotalPrixHT(totalPrixHT);
            facture.setTotalTVA(totalTVA);
            facture.setTotalPrixTTC(totalPrixTTC);
            facture.setDate(date);
            facture.setPaymentMethod(paymentMethod);

            return facture;

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            e.printStackTrace();
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
