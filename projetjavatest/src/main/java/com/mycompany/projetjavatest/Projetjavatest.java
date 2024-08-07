/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projetjavatest;

import com.mycompany.projetjavatest.dao.CommandDAO;
import com.mycompany.projetjavatest.view.connexion;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

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
                
                
                try {
                    new connexion().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Projetjavatest.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Create a dummy JTable for testing
        /*String[] columnNames = {"ID", "Name", "Price"};
        Object[][] data = {
            {1, "Pizza", 9.99},
            {2, "Burger", 5.49},
            {3, "Pasta", 7.99}
        };

        JTable table = new JTable(data, columnNames);
        String filePath = "command.txt";

        CommandDAO dao = new CommandDAO();
        dao.writeCommandsToFile(table, filePath);*/
                }
            });

        }
}
