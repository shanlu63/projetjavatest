/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.projetjavatest.view;

import com.mycompany.projetjavatest.DAO.TableDAO;
import com.mycompany.projetjavatest.domain.Table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ButtonUI;

/**
 *
 * @author shan
 */
public class GestionService1 extends javax.swing.JFrame {
     // Liste pour stocker les objets Table
    // Liste pour stocker les objets Table
    private List<Table> tableList;

    // Les composants de l'interface utilisateur
    private JTextArea statusArea;

    /**
     * Creates new form GestionService1
     */
    /**
     * Creates new form GestionService1
     */
    public GestionService1() {
          // Initialize tableList first
        TableDAO tableDAO = new TableDAO("table.txt"); // Créer une instance de TableDAO avec le chemin du fichier
        tableList = tableDAO.initializeTableList();

        initComponents();
       // displayLabels(); // Call the method to display labels
       jPaneltest.setVisible(false);
      
       

        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanelPlan = new javax.swing.JPanel();
        jButtonDeconnexion = new javax.swing.JButton();
        jLabelPlandurestarant = new javax.swing.JLabel();
        jPanelMenudugestion = new javax.swing.JPanel();
        jButtonQuitter = new javax.swing.JButton();
        jButtonCommender = new javax.swing.JButton();
        jButtonAddtion = new javax.swing.JButton();
        jButtonStatus = new javax.swing.JButton();
        jLabelMenu = new javax.swing.JLabel();
        jButtonAddtion1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPaneltest = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(800, 600));

        jPanelPlan.setBackground(new java.awt.Color(255, 204, 204));
        jPanelPlan.setMaximumSize(new java.awt.Dimension(500, 486));
        jPanelPlan.setMinimumSize(new java.awt.Dimension(500, 486));
        jPanelPlan.setPreferredSize(new java.awt.Dimension(588, 486));

        javax.swing.GroupLayout jPanelPlanLayout = new javax.swing.GroupLayout(jPanelPlan);
        jPanelPlan.setLayout(jPanelPlanLayout);
        jPanelPlanLayout.setHorizontalGroup(
            jPanelPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        jPanelPlanLayout.setVerticalGroup(
            jPanelPlanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jButtonDeconnexion.setText("Déconnexion");

        jLabelPlandurestarant.setText("Plan du restarant");

        jPanelMenudugestion.setBackground(new java.awt.Color(204, 255, 255));

        jButtonQuitter.setText("Quitter");
        jButtonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuitterActionPerformed(evt);
            }
        });

        jButtonCommender.setText("Commander");

        jButtonAddtion.setText("l'addition");

        jButtonStatus.setText("Status de table");
        jButtonStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatusActionPerformed(evt);
            }
        });

        jLabelMenu.setText("Menu du gestion");

        jButtonAddtion1.setText("Réservation");

        javax.swing.GroupLayout jPanelMenudugestionLayout = new javax.swing.GroupLayout(jPanelMenudugestion);
        jPanelMenudugestion.setLayout(jPanelMenudugestionLayout);
        jPanelMenudugestionLayout.setHorizontalGroup(
            jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenudugestionLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabelMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelMenudugestionLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonQuitter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMenudugestionLayout.createSequentialGroup()
                        .addGroup(jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCommender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAddtion, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jButtonAddtion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))))
                .addGap(300, 300, 300))
        );
        jPanelMenudugestionLayout.setVerticalGroup(
            jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenudugestionLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabelMenu)
                .addGap(18, 18, 18)
                .addGroup(jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddtion1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMenudugestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCommender, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddtion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        jPanel5.setBackground(java.awt.Color.cyan);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jLabel1.setText("diponible");

        jPanel6.setBackground(java.awt.Color.yellow);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        jLabel2.setText("réservé");

        jPanel7.setBackground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        jLabel3.setText("occupé");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPaneltest.setBackground(new java.awt.Color(255, 255, 51));
        jPaneltest.setMaximumSize(new java.awt.Dimension(300, 200));
        jPaneltest.setMinimumSize(new java.awt.Dimension(300, 200));
        jPaneltest.setPreferredSize(new java.awt.Dimension(300, 200));

        javax.swing.GroupLayout jPaneltestLayout = new javax.swing.GroupLayout(jPaneltest);
        jPaneltest.setLayout(jPaneltestLayout);
        jPaneltestLayout.setHorizontalGroup(
            jPaneltestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPaneltestLayout.setVerticalGroup(
            jPaneltestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabelPlandurestarant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 900, Short.MAX_VALUE)
                .addComponent(jButtonDeconnexion)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanelPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanelMenudugestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPaneltest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDeconnexion)
                    .addComponent(jLabelPlandurestarant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelMenudugestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPaneltest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed
        // TODO add your handling code here:
        //affiche la status des table apres cliquer le boutton status de table
         // Affiche le status des tables
            displayLabels();
            jPaneltest.setVisible(true);
           

        }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionService1().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonStatusActionPerformed

    private void jButtonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuitterActionPerformed
        // TODO add your handling code here:
        this.dispose();
        choixInterface choixInterface = new choixInterface();
        choixInterface.setVisible(true);
       
    }//GEN-LAST:event_jButtonQuitterActionPerformed
    private void displayLabels() {
        // Définir la disposition de jPanel1 en tant que GridBagLayout
        //
        jPanelPlan.removeAll();
        jPanelPlan.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        int columns = 3; // Number of columns to display labels
        
      
        // Créer et ajouter des étiquettes (JLabels) à jPanel1 en fonction de la liste des tables
        for (Table table : tableList) {
        // Créer un nouveau JLabel avec le texte "Table i", le texte est centré horizontalement
            JLabel label = new JLabel(table.getStatusText(), SwingConstants.CENTER);

            // Définir la taille fixe de l'étiquette à 50x50 pixels
            label.setPreferredSize(new Dimension(100, 100));
            label.setMinimumSize(new Dimension(100, 100));
            label.setMaximumSize(new Dimension(100, 100));
          
            // Définir la couleur de fond de l'étiquette en fonction du statut de la table
            label.setOpaque(true);
            switch (table.getStatus()) {
                case 1: // Disponible
                    label.setBackground(Color.CYAN);
                    break;
                case 2: // Réservé
                    label.setBackground(Color.YELLOW);
                    break;
                case 3: // Occupé
                    label.setBackground(Color.RED);
                    break;
                default:
                    label.setBackground(Color.GRAY); // Couleur par défaut pour statut inconnu
                    break;
        }

        // Ajouter une bordure bleue à l'étiquette
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          // Ajouter l'étiquette à jPanel1
            jPanelPlan.add(label, gbc);

          // Ajouter un MouseListener pour gérer les clics
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Appeler une méthode pour afficher les détails dans panelStatusPourChequeTable
                    showDetailsInPanel(table);//afficher les details des que clic un table 
                }
            });

            // Mettre à jour les contraintes de disposition pour la prochaine étiquette
             gbc.gridx++;
            if (gbc.gridx == columns) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

            // Revalider jPanel1 pour appliquer la nouvelle disposition des composants
            jPanelPlan.revalidate();
            // Repeindre jPanel1 pour s'assurer qu'il affiche les composants mis à jour
            jPanelPlan.repaint();
        }
    
     private void showDetailsInPanel(Table table) {
          jPaneltest.setLayout(new BorderLayout());
          Font font = new Font("Arial", Font.BOLD, 14);
           //System.out.println("Updating panel with details for: " + table.getStatusText()); // Debug line

            jPaneltest.removeAll();
            
            JLabel detailsLabel = new JLabel( table.getStatusTextdetail(), SwingConstants.CENTER);
            detailsLabel.setFont(font);

            jPaneltest.add(detailsLabel, BorderLayout.CENTER);

            jPaneltest.revalidate();
            jPaneltest.repaint();
        }
    /**
     * @param args the command line arguments
     */
    /*
        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
/*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionService1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionService1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionService1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionService1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
 /* 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionService1().setVisible(true);
            }
        });*/
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddtion;
    private javax.swing.JButton jButtonAddtion1;
    private javax.swing.JButton jButtonCommender;
    private javax.swing.JButton jButtonDeconnexion;
    private javax.swing.JButton jButtonQuitter;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JLabel jLabelPlandurestarant;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelMenudugestion;
    private javax.swing.JPanel jPanelPlan;
    private javax.swing.JPanel jPaneltest;
    // End of variables declaration//GEN-END:variables
}
