package com.mycompany.projetjavatest.dao;

import com.mycompany.projetjavatest.domain.Menu;
import com.mycompany.projetjavatest.view.GestionService1;

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
                String[] parts = line.split(";"); // Split par point-virgule
                if (parts.length == 4) {
                    int menuId = Integer.parseInt(parts[0].trim());
                    String nameplate = parts[1].trim();
                    String type = parts[2].trim();
                    float prix = numberFormat.parse(parts[3].trim()).floatValue();
                    menus.add(new Menu(menuId, nameplate, type, prix));
                }
            }
        } catch (IOException | ParseException e) {
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
                JLabel idLabel = new JLabel(String.valueOf(menu.getMenuId()));
                menuPanel.add(idLabel, gbc);

                gbc.gridx = 1;
                JLabel nameLabel = new JLabel(menu.getNameplate());
                nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                nameLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addToCommandPanel(menu);
                    }
                });
                menuPanel.add(nameLabel, gbc);

                gbc.gridx = 2;
                JLabel priceLabel = new JLabel(String.format("%.2f €", menu.getPrix()));
                menuPanel.add(priceLabel, gbc);

                row++;
            }

            // Ajouter un espacement
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 4;
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        }

        return menuPanel;
    }

    // Ajouter un plat au commandPanel
    private void addToCommandPanel(Menu menu) {
        if (commandPanel == null) {
            return; // Si erreur
        }

        SwingUtilities.invokeLater(() -> {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST; // Left
            gbc.gridx = 0;
            gbc.gridy = commandPanel.getComponentCount(); // Ajouter la commande dans la dernière ligne

            JLabel commandLabel = new JLabel(menu.getNameplate() + " - " + String.format("%.2f €", menu.getPrix()));
            commandPanel.add(commandLabel, gbc);

            // Mise à jour total
            updateTotalLine();
        });
    }

    // Mettre à jour la ligne de total
    private void updateTotalLine() {
        float total = 0;
        
        // Calculer le montant total
        for (Component component : commandPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String text = label.getText();

                try {
                    // Extraire la partie prix
                    int priceStartIndex = text.lastIndexOf(" - ") + 3;
                    int priceEndIndex = text.lastIndexOf(" €");
                    if (priceStartIndex > 0 && priceEndIndex > priceStartIndex) {
                        String priceStr = text.substring(priceStartIndex, priceEndIndex).trim();
                        Number number = numberFormat.parse(priceStr);
                        float price = number.floatValue();
                        total += price;
                    }
                } catch (ParseException e) {
                    System.err.println("Error parsing price: " + e.getMessage());
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
        gbc.insets = new Insets(5, 5, 5, 5);
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
