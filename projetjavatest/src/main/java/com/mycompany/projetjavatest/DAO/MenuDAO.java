package com.mycompany.projetjavatest.dao;

import com.mycompany.projetjavatest.domain.Cuisine;
import com.mycompany.projetjavatest.domain.Menu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.io.IOException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MenuDAO {
    private JPanel commandPanel;
    private JScrollPane jScrollPaneCommand;
    private NumberFormat numberFormat;

    // Constructeur
    public MenuDAO(JPanel commandPanel, JScrollPane jScrollPaneCommand) {
        this.commandPanel = commandPanel;
        this.jScrollPaneCommand = jScrollPaneCommand;
        this.numberFormat = NumberFormat.getInstance(Locale.FRANCE);
    }

    // Lire les menus à partir d'un fichier
    public List<Menu> readMenusFromFile(String fileName) {
        List<Menu> menus = new ArrayList<>();
       
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Ligne lue: " + line); // Debug line
                String[] parts = line.split(";"); // Split par point-virgule

                if (parts.length == 6) { // Correction de la longueur pour 6 parties
                    try {
                        int menuId = Integer.parseInt(parts[0].trim());
                        String nom = parts[1].trim();
                        String[] composantsArray = parts[2].split("\\|"); // Séparer les composants par |
                        List<String> composants = new ArrayList<>();
                        for (String composant : composantsArray) {
                            composants.add(composant.trim());
                        }

                        String type = parts[3].trim(); // Type (entrée, plat principal, dessert)
                        float prix = numberFormat.parse(parts[4].trim()).floatValue(); // Prix
                        int quantite = Integer.parseInt(parts[5].trim()); // Quantité

                        List<Cuisine> platList = new ArrayList<>();
                        platList.add(new Cuisine(prix, composants, nom, quantite));

                        menus.add(new Menu(menuId,nom,platList, type, prix, quantite));
                    } catch (ParseException | NumberFormatException e) {
                        System.err.println("Erreur lors du parsing des données: " + e.getMessage());
                    }
                } else {
                    System.err.println("Format de ligne incorrect: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menus;
    }

    // Catégoriser les menus par type
    public Map<String, List<Menu>> categorizeMenus(List<Menu> menus) {
        Map<String, List<Menu>> categorizedMenus = new HashMap<>();
        for (Menu menu : menus) {
            String type = menu.getType();
            categorizedMenus.putIfAbsent(type, new ArrayList<>());
            categorizedMenus.get(type).add(menu);
        }
        return categorizedMenus;
    }

    // Créer un panneau de menus
    public JPanel createMenuPanel(String fileName) {
         JPanel menuPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Espacements

    // Lire et catégoriser les menus
    List<Menu> menus = readMenusFromFile(fileName);
    Map<String, List<Menu>> categorizedMenus = categorizeMenus(menus);

    int row = 0;
    // Afficher les plats
    for (Map.Entry<String, List<Menu>> entry : categorizedMenus.entrySet()) {
        String type = entry.getKey(); // Get type
        List<Menu> menuList = entry.getValue();

        JLabel typeLabel = new JLabel("<html><h2>" + type + "</h2></html>");
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        menuPanel.add(typeLabel, gbc);

        for (Menu menu : menuList) {
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.gridwidth = 1;

            JLabel idLabel = new JLabel(String.valueOf(menu.getMenuId()));//afficher id
            idLabel.setFont(new Font("Arial", Font.BOLD, 15));//grand taille
            menuPanel.add(idLabel, gbc);

            gbc.gridx = 1;
            JLabel nameLabel = new JLabel(menu.getPlat().get(0).getNom()); // Afficher le nom du plat
            nameLabel.setFont(new Font("Arial", Font.BOLD, 15));//grand taille
            nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            nameLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addToCommandPanel(menu);
                }
            });
            menuPanel.add(nameLabel, gbc);

            gbc.gridx = 2;
            JLabel priceLabel = new JLabel(String.format("%.2f €(HT)", menu.getPrix()));
            priceLabel.setFont(new Font("Arial", Font.BOLD, 15));//grand taille
            menuPanel.add(priceLabel, gbc);

            row++;

            // Ajouter les composants du plat sur la ligne suivante
            gbc.gridx = 1;
            gbc.gridy = row;
            gbc.gridwidth = 2;
            JLabel componentsLabel = new JLabel("(Composants:"+menu.getPlat().get(0).obtenircomposants().toString().replace("[", "").replace("]", "").replace(", ", " , ")+")");
            menuPanel.add(componentsLabel, gbc);

            row++;

            // Ajouter un espacement entre les plats
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 4;
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        }
    }

        return menuPanel;
    }

    // Ajouter un plat au commandPanel
    private void addToCommandPanel(Menu menu) {
            if (commandPanel == null) {
           return; // Si erreur
       }

       SwingUtilities.invokeLater(() -> {
           boolean menuExists = false;
           for (Component component : commandPanel.getComponents()) {
               if (component instanceof JPanel) {
                   JPanel panel = (JPanel) component;
                   for (Component innerComponent : panel.getComponents()) {
                       if (innerComponent instanceof JLabel) {
                           JLabel label = (JLabel) innerComponent;

                           // Vérifier si le menu est déjà présent
                           if (label.getText().startsWith(menu.getPlat().get(0).getNom())) {
                               // Trouver le champ de texte pour la quantité et augmenter la quantité
                               for (Component c : panel.getComponents()) {
                                   if (c instanceof JTextField) {
                                       JTextField quantityField = (JTextField) c;
                                       try {
                                           int quantity = Integer.parseInt(quantityField.getText());
                                           quantityField.setText(String.valueOf(quantity + 1));
                                       } catch (NumberFormatException e) {
                                           quantityField.setText("1");
                                       }
                                       break;
                                   }
                               }
                               menuExists = true;
                               break;
                           }
                       }
                   }
                   if (menuExists) break;
               }
           }

           if (!menuExists) {
               GridBagConstraints gbc = new GridBagConstraints();
               gbc.insets = new Insets(5, 5, 5, 5);
               gbc.anchor = GridBagConstraints.WEST; // Align to left
               gbc.gridx = 0;
               gbc.gridy = commandPanel.getComponentCount(); // Ajouter la commande dans la dernière ligne

               JPanel itemPanel = new JPanel(new GridBagLayout());
               GridBagConstraints itemGbc = new GridBagConstraints();
               itemGbc.insets = new Insets(3, 3, 3, 3);
               itemGbc.gridx = 0;
               itemGbc.gridy = 0;

               // Modifier ici pour ne pas inclure les composants
               JLabel commandLabel = new JLabel(menu.getPlat().get(0).getNom() + " - " + String.format("%.2f €", menu.getPrix()));
               itemPanel.add(commandLabel, itemGbc);

               // Ajouter un champ de texte pour la quantité
               JTextField quantityField = new JTextField("1", 3);
               quantityField.setHorizontalAlignment(JTextField.RIGHT);
               itemGbc.gridx = 1;
               itemPanel.add(quantityField, itemGbc);

               JButton deleteButton = new JButton("Effacer");
               itemGbc.gridx = 2;
               itemPanel.add(deleteButton, itemGbc);

               gbc.gridx = 0;
               gbc.gridy = commandPanel.getComponentCount();
               commandPanel.add(itemPanel, gbc);

               deleteButton.addActionListener(e -> {
                   commandPanel.remove(itemPanel);
                   updateTotalLine();
                   commandPanel.revalidate();
                   commandPanel.repaint();
               });

               // Ajouter un DocumentListener pour mettre à jour le total lorsqu'une quantité change
               quantityField.getDocument().addDocumentListener(new DocumentListener() {
                   @Override
                   public void insertUpdate(DocumentEvent e) {
                       updateTotalLine();
                   }

                   @Override
                   public void removeUpdate(DocumentEvent e) {
                       updateTotalLine();
                   }

                   @Override
                   public void changedUpdate(DocumentEvent e) {
                       updateTotalLine();
                   }
               });

               // Mise à jour total
               updateTotalLine();
           } else {
               updateTotalLine();
           }

           // test，print commandPanel
           for (Component component : commandPanel.getComponents()) {
               System.out.println(component);
           }
       });
    }

    // Mettre à jour la ligne de total
    private void updateTotalLine() {
        float total = 0;
    
       
    // Calculer le montant total
    for (Component component : commandPanel.getComponents()) {
        if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            for (Component innerComponent : panel.getComponents()) {
                if (innerComponent instanceof JLabel) {
                    JLabel label = (JLabel) innerComponent;
                    String text = label.getText();
                    try {
                        // Extraire la partie prix
                        int priceStartIndex = text.lastIndexOf(" - ") + 3;
                        int priceEndIndex = text.lastIndexOf(" €");
                        if (priceStartIndex > 0 && priceEndIndex > priceStartIndex) {
                            String priceStr = text.substring(priceStartIndex, priceEndIndex).trim();
                            Number number = numberFormat.parse(priceStr);
                            float price = number.floatValue();

                            // Chercher le champ de texte pour la quantité
                            for (Component c : panel.getComponents()) {
                                if (c instanceof JTextField) {
                                    JTextField quantityField = (JTextField) c;
                                    try {
                                        int quantity = Integer.parseInt(quantityField.getText());
                                        total += price * quantity;
                                    } catch (NumberFormatException e) {
                                        // Si le texte n'est pas un nombre valide, traiter comme quantité 0
                                    }
                                }
                            }
                        }
                    } catch (ParseException e) {
                        System.err.println("Error parsing price: " + e.getMessage());
                    }
                }
            }
        }
    }

     // Supprimer l'ancienne ligne de total (si elle existe)
    for (Component component : commandPanel.getComponents()) {
        if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Total:")) {
            commandPanel.remove(component);
        }
    }

    // Ajouter une nouvelle ligne de total
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 3, 5, 3);
    gbc.anchor = GridBagConstraints.WEST; // Align to left
    gbc.gridx = 0;
    gbc.gridy = commandPanel.getComponentCount()+2; // Add total line at the end
    gbc.weighty = 0.0; // Reset weighty

    JLabel totalLabel = new JLabel("Total: " + String.format("%.2f €", total));
    commandPanel.add(totalLabel, gbc);

   

    commandPanel.revalidate();
    commandPanel.repaint();
    }
    
}
