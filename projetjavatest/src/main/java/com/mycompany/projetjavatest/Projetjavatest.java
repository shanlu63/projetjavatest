/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetjavatest;

import com.mycompany.projetjavatest.view.connexion;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author shan
 */
public class Projetjavatest {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("c'est le projet java test par Shan LU et kayiba");
        System.out.println("c'est le projet java test par Shan LU et kayiba");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
                new connexion().setVisible(true);
            }
        });
        
    }
}
