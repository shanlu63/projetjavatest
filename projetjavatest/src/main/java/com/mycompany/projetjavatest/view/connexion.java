/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projetjavatest.view;

import com.mycompany.projetjavatest.DAO.BasicDAO;
import com.mycompany.projetjavatest.domain.Emplois;
import com.mycompany.projetjavatest.service.Emploiservice;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author shan
 */
public class connexion extends javax.swing.JFrame {
    
    
    /**
     * Creates new form connexion
     */
    private List<Emplois> employes;//creer une liste de emplois

     public connexion() throws Exception {
        initComponents();
        // Initialize the list of employes
        employes = new ArrayList<>();
        
        setLocationRelativeTo(null);
        
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelBienvenue = new javax.swing.JLabel();
        BoutonConnexion = new javax.swing.JButton();
        labelID = new javax.swing.JLabel();
        labelmtp = new javax.swing.JLabel();
        jTextFieldid = new javax.swing.JTextField();
        jPasswordFieldMTP1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LabelBienvenue.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        LabelBienvenue.setText("Bienvenue à Bistro S&K");

        BoutonConnexion.setText("Se Connecter");
        BoutonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoutonConnexionActionPerformed(evt);
            }
        });

        labelID.setText("Votre ID: ");

        labelmtp.setText("Mot de passe:");

        jTextFieldid.setText("Saisir votre id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(LabelBienvenue))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(BoutonConnexion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelmtp)
                            .addComponent(labelID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldid, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jPasswordFieldMTP1))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(LabelBienvenue)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(jTextFieldid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelmtp)
                    .addComponent(jPasswordFieldMTP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(BoutonConnexion)
                .addGap(43, 43, 43))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BoutonConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoutonConnexionActionPerformed
        // TODO add your handling code here:
        //connexion avec le id et le mot de passe
        //utilser le method etEmploisbyIDandMDP
        // Création d'une instance d'Emploisevice pour utiliser ses méthodes
        Emploiservice emploisevice = new Emploiservice(); // Assurez-vous que le constructeur par défaut est présent dans Emploisevice
    
        String idinput = jTextFieldid.getText().trim(); // Saisir ID
        char[] passwordChars = jPasswordFieldMTP1.getPassword();
        String mtpinput = new String(passwordChars);// Saisir mot de passe
        String job;
       
    
        Emplois employe = emploisevice.getEmploisbyIDandMDP(idinput, mtpinput);
        
        // Vérifier si l'emploi a été trouvé
        if (employe != null) {
            JOptionPane.showMessageDialog(null, "Bienvenue " + employe.getNom());
            job = employe.getJob();
            // Aller à la page choix de l'interface
            choixInterface secondPage = new choixInterface(job);
            secondPage.setVisible(true);
            
            // Masquer la fenêtre actuelle
            this.setVisible(false);
        } else {
            // Afficher un message d'erreur si l'emploi n'a pas été trouvé
            JOptionPane.showMessageDialog(this, "ID ou mot de passe incorrect");
            jTextFieldid.setText("");
            jPasswordFieldMTP1.setText("");
        }
        
    }//GEN-LAST:event_BoutonConnexionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new connexion().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BoutonConnexion;
    private javax.swing.JLabel LabelBienvenue;
    private javax.swing.JPasswordField jPasswordFieldMTP1;
    private javax.swing.JTextField jTextFieldid;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelmtp;
    // End of variables declaration//GEN-END:variables
}
