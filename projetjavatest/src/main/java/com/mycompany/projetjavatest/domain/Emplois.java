/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetjavatest.domain;

/**
 *
 * @author shan
 */
public class Emplois {
    private int id;// index uniaue
    private String empId;// id pour se connecter 
    private String mdp; // mdp pour se connecter 
    private String nom; // nom de emplois
    private String job; // mananger, serveur ou directeur
    
    //construction sans argument
    public Emplois(){
    }
   
    //construction 
    public Emplois(int id, String empId, String mdp, String nom, String job){
        this.id = id;
        this.empId = empId;
        this.mdp = mdp;
        this.nom = nom;
        this.job= job;
    }
    //creer setter et getter
    // Getter et Setter pour id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter et Setter pour empId
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    // Getter et Setter pour mdp
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    // Getter et Setter pour nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour job
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
