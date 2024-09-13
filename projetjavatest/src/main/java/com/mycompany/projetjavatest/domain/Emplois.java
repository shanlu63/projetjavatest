/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;


import java.util.HashSet;
import java.util.Set;
/**
 *Structure du projet
*domain : contient la classe Emplois.
*DAO : contient la classe EmploisDAO.
*service : contient la classe EmploisService.
*utils : contient la classe FileUtils pour la lecture du fichier.
 * @author shan
 */
public class Emplois {
private static int nextId = 1;
    private int id; // Index unique
    private String empId; // Identifiant pour se connecter
    private String mdp; // Mot de passe pour se connecter
    private String nom; // Nom de l'employé
    private String job; // Manager, serveur ou directeur
    
    public Emplois(int id,String empId, String mdp, String nom, String job) throws Exception {
        this.id = id;
        this.empId =empId;
        this.mdp = mdp;
        this.nom = nom;
        this.job = job;
        nextId = this.id ;
    }

    public Emplois(String mdp, String nom, String job) throws Exception {
      nextId++;
        this.id =nextId ;
        this.empId = "emp00"+this.id;
        this.mdp = mdp;
        this.nom = nom;
        this.job = job;
        
    }

    // Getters et setters
    public int getId() { return id; }
    public String getEmpId() { return empId; }
    public String getMdp() { return mdp; }
    public String getNom() { return nom; }
    public String getJob() { return job; }

    public void setId(int id) { this.id = id; }
    public void setEmpId(String empId){
        
     
        this.empId = empId;
        
    }
    
    public void setMdp(String mdp) { this.mdp = mdp; }
    public void setNom(String nom) { this.nom = nom; }
    public void setJob(String job) { this.job = job; }
}