/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Facture { // Define the file name
    // Define the file name
    private static int nextIdFacture = 202400001; // Initial facture ID
    private int idFacture;
    private float totalPrixHT;
    private float totalTVA;
    private float totalPrixTTC;
    private Date date;
    private String paymentMethod;

    // Default constructor
    public Facture() {
        this.idFacture = nextIdFacture++;
        this.date = new Date(); // Set to current date
    }
    
    // Parameterized constructor
    public Facture(float totalPrixHT, float totalTVA, float totalPrixTTC, Date date, String paymentMethod) {
        this.idFacture = nextIdFacture++;
        this.totalPrixHT = totalPrixHT;
        this.totalTVA = totalTVA;
        this.totalPrixTTC = totalPrixTTC;
        this.date = (date != null) ? date : new Date(); // Use provided date or current date
        validatePayment(paymentMethod);
        this.paymentMethod = paymentMethod;
    }

    private void validatePayment(String paymentMethod) {
        // Validate payment method
        if (!"en espèces".equals(paymentMethod) && !"carte bleue".equals(paymentMethod)) {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }

    // Getters and setters
    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public float getTotalPrixHT() {
        return totalPrixHT;
    }

    public void setTotalPrixHT(float totalPrixHT) {
        this.totalPrixHT = totalPrixHT;
    }

    public float getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(float totalTVA) {
        this.totalTVA = totalTVA;
    }

    public float getTotalPrixTTC() {
        return totalPrixTTC;
    }

    public void setTotalPrixTTC(float totalPrixTTC) {
        this.totalPrixTTC = totalPrixTTC;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        validatePayment(paymentMethod);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        return  "Facture numéro:"+idFacture + ";\n" +
               "Prix HT en total :" + String.format("%.2f", totalPrixHT) + " € ;\n" + 
               "Prix TVA :" +String.format("%.2f", totalTVA) + " € ;\n" +
               "Prix TTC en total :" +String.format("%.2f", totalPrixTTC) + " € ;\n" +
               "Date :" +dateFormat.format(date) + ";\n" +
               "Payement méthod :" + paymentMethod;
    }


}

