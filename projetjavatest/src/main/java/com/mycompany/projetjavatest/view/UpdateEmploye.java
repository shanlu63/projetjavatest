/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projetjavatest.view;

import com.mycompany.projetjavatest.domain.Cuisine;
import com.mycompany.projetjavatest.domain.Emplois;
import com.mycompany.projetjavatest.domain.Menu;
import static com.mycompany.projetjavatest.view.ModificationMenue.loadMenuData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kayiba
 */
public class UpdateEmploye extends javax.swing.JFrame {

    /**
     * Creates new form UpdateEmploye
     */
   private int id;
   private Emplois EmployeTrouver;
   String jobt;
    public UpdateEmploye(int id,String job) throws Exception {
    this.id = id;
    this.jobt=job;// Vous assignez l'id passé en paramètre
    initComponents(); // Initialisation de l'interface utilisateur
     setLocationRelativeTo(null);
    
    // Remplacer l'exception ou l'initialisation problématique par quelque chose de concret
    List<Emplois> ListEmplois = getListEmploye("emplois.txt"); // Assurez-vous que ce fichier existe
    this.EmployeTrouver = trouverEmploye(ListEmplois, this.id);
    
    
    if (EmployeTrouver != null) {
        // Mettre à jour les labels avec les informations trouvées
        jLabel2.setText("Vous avez choisi l'employé au poste de " + EmployeTrouver.getJob() + " : " + EmployeTrouver.getNom());
        jLabel4.setText(EmployeTrouver.getNom());
        jLabel7.setText(EmployeTrouver.getMdp());
        jLabel13.setText(EmployeTrouver.getJob());
    } else {
        jLabel1.setText("Employé non trouvé !");
    }
}

    private UpdateEmploye(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
   
    
     private void modifierNom() throws Exception {
        String nouveauNom = jTextField1.getText();
        EmployeTrouver.setNom(nouveauNom);
        enregistrerEmploye();
    }
     
     private void modifierMDP() throws Exception {
        String nouveauNom = jTextField2.getText();
        EmployeTrouver.setMdp(nouveauNom);
        enregistrerEmploye();
    }
     
    
     
    private void modifierJob() throws Exception {
        
        EmployeTrouver.setJob(jComboBox1.getSelectedItem().toString());
        enregistrerEmploye();
    }
     
     
    
    public List<Emplois> getListEmploye(String fileName) 
      throws Exception {
        List<Emplois> EmploisList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by semicolon and get the first and second elements (index 0 and 1)
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int idl = Integer.parseInt(parts[0].trim());
          
                    String login = parts[1].trim();
                    String MDP = parts[2].trim();
                    String name = parts[3].trim();
                    String job = parts[4].trim(); // Get the ID of the menu
                    
                    Emplois emplois = new Emplois(idl,login,MDP,name,job);
                    EmploisList.add(emplois);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return EmploisList;
    }
    
    public static Emplois trouverEmploye(List<Emplois> EmploisList, int menuId) {
        for (Emplois emploi : EmploisList) {
            System.out.println("Recherche Menu ID: " + emploi.getEmpId()); // Debugging
            if (emploi.getId() == menuId) 
            {
                return emploi;
            }
        }
        return null;
    }
    
 private void enregistrerEmploye() throws Exception {
    List<Emplois> listEmploye = getListEmploye("emplois.txt");

    // Vérification de l'unicité de l'identifiant en excluant l'employé actuel
    for (Emplois emploi : listEmploye) {
        // Ne pas comparer avec l'employé actuel (basé sur son ID unique)
        if (emploi.getId() != EmployeTrouver.getId() && emploi.getEmpId().equals(EmployeTrouver.getEmpId())) {
            throw new Exception("L'identifiant " + emploi.getEmpId() + " est déjà utilisé. Veuillez en choisir un autre.");
        }
    }

    // Mise à jour des informations de l'employé en question
    for (Emplois emploi : listEmploye) {
        if (emploi.getId() == EmployeTrouver.getId()) {
            emploi.setId(EmployeTrouver.getId());
            emploi.setEmpId(EmployeTrouver.getEmpId());
            emploi.setMdp(EmployeTrouver.getMdp());
            emploi.setNom(EmployeTrouver.getNom());
            emploi.setJob(EmployeTrouver.getJob());
        }
    }

    enregistrerEmploie(listEmploye); // Sauvegarde des modifications
}
    
 private void enregistrerEmploie(List<Emplois> listEmploye) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("emplois.txt"))) {
        for (Emplois emploi : listEmploye) {
            StringBuilder line = new StringBuilder();
            line.append(emploi.getId()).append(",")
                .append(emploi.getEmpId()).append(",")
                .append(emploi.getMdp()).append(",")
                .append(emploi.getNom()).append(",")
                .append(emploi.getJob());
            bw.write(line.toString());
            bw.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
 }
    
     private void supprimerEmploye() 
     {
    int confirmation = javax.swing.JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cet employé ?", "Confirmation", javax.swing.JOptionPane.YES_NO_OPTION);
    if (confirmation == javax.swing.JOptionPane.YES_OPTION) {
        List<Emplois> listMenu = null;
        try {
            listMenu = getListEmploye("emplois.txt");
        } catch (Exception ex) {
            Logger.getLogger(UpdateEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Utilisation de l'itérateur pour supprimer en toute sécurité
        Iterator<Emplois> iterator = listMenu.iterator();
        while (iterator.hasNext()) {
            Emplois menu = iterator.next();
            if (menu.getId() == EmployeTrouver.getId()) {
                iterator.remove(); // Supprimer le menu trouvé
                break;  // Nous avons trouvé et supprimé le menu, on peut arrêter la boucle
            }
        }

        // Enregistrer la liste mise à jour
        enregistrerEmploie(listMenu);
        jLabel1.setText("Le menu a été supprimé.");
    }
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Modification Employe");

        jLabel2.setText("e");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nom actuel");

        jLabel4.setText("jLabel4");

        jLabel5.setText("Nouveau nom");

        jTextField1.setText("...");

        jButton2.setText("Modifier ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(159, 159, 159))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Mot de passe actuel");

        jLabel7.setText("jLabel7");

        jLabel8.setText("Nouveau mot de passe");

        jTextField2.setText("...");

        jButton3.setText("Modifier ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(16, 16, 16))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Poste actuel");

        jLabel13.setText("jLabel13");

        jLabel14.setText("Nouveau poste");

        jButton5.setText("Modifier ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "serveur", "manager", "directeur" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel13))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jButton5)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jButton6.setBackground(new java.awt.Color(255, 0, 0));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Supprimer ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       if(jTextField1.getText().isEmpty()|| jTextField1.getText().equals("...")){
                   javax.swing.JOptionPane.showMessageDialog(this, "Entrez une valeur valide ", "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);

       }
       else{
        try {
            // TODO add your handling code here:
            modifierNom();
            javax.swing.JOptionPane.showMessageDialog(this, "Le nom de l'employé a bien été modifié", "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ModifierEmploye ee =new ModifierEmploye(jobt);
            ee.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(UpdateEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(jTextField2.getText().isEmpty()|| jTextField2.getText().equals("...")){
                   javax.swing.JOptionPane.showMessageDialog(this, "Entrez une valeur valide ", "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);

       }
       else{
        try {
            // TODO add your handling code here:
            modifierMDP();
            javax.swing.JOptionPane.showMessageDialog(this, "Le mot de passe de l'employé a bien été modifié", "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ModifierEmploye ee =new ModifierEmploye(jobt);
            ee.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(UpdateEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        try {
           // TODO add your handling code here:
           
           // TODO add your handling code here:
           modifierJob();
       } catch (Exception ex) {
           Logger.getLogger(UpdateEmploye.class.getName()).log(Level.SEVERE, null, ex);
       }
            javax.swing.JOptionPane.showMessageDialog(this, "Le Statut de l'employé a bien été modifié", "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            ModifierEmploye ee =new ModifierEmploye(jobt);
            ee.setVisible(true);
            this.dispose();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        supprimerEmploye();
        ModifierEmploye ee =new ModifierEmploye(jobt);
            ee.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ModifierEmploye ee =new ModifierEmploye(jobt);
            ee.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateEmploye.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateEmploye.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateEmploye.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateEmploye.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            try {
                // Définir un ID d'employé pour le test ou selon votre logique
                int id = 1; // Exemple d'ID
                new UpdateEmploye(id).setVisible(true); // Appeler le constructeur avec l'ID
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
}

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

   
}
