package com.mycompany.projetjavatest.dao;

import com.mycompany.projetjavatest.domain.Menu;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDAO {

     public List<Menu> readMenusFromFile(String fileName) {
        List<Menu> menus = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // 假设文件中的每一行用逗号分隔
                if (parts.length == 4) {
                    int menuId = Integer.parseInt(parts[0].trim());
                    String nameplate = parts[1].trim();
                    String type = parts[2].trim();
                    float prix = Float.parseFloat(parts[3].trim());
                    menus.add(new Menu(menuId, nameplate, type, prix));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menus;
    }


    public Map<String, List<Menu>> categorizeMenus(List<Menu> menus) {
        Map<String, List<Menu>> categorizedMenus = new HashMap<>();
        for (Menu menu : menus) {
            String type = menu.getType();
            if (!categorizedMenus.containsKey(type)) {
                categorizedMenus.put(type, new ArrayList<>());
            }
            categorizedMenus.get(type).add(menu);
        }
        return categorizedMenus;
    }

    public JPanel createMenuPanel(String fileName) {
        //creer a panel
        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 间距

        // lire
        List<Menu> menus = readMenusFromFile(fileName);

        // 
        Map<String, List<Menu>> categorizedMenus = categorizeMenus(menus);

        int row = 0;
        // afficier les plates
        for (Map.Entry<String, List<Menu>> entry : categorizedMenus.entrySet()) {
            String type = entry.getKey();
            List<Menu> menuList = entry.getValue();
            
            JLabel typeLabel = new JLabel("<html><h2>" + type + "</h2></html>");
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 2; // 
            menuPanel.add(typeLabel, gbc);

            for (Menu menu : menuList) {
                gbc.gridx = 0;
                gbc.gridy = row;
                gbc.gridwidth = 1; // chaque clomne
                JLabel idLabel = new JLabel(String.valueOf(menu.getMenuId()));
                menuPanel.add(idLabel, gbc);

                gbc.gridx = 1;
                JLabel nameLabel = new JLabel(menu.getNameplate());
                menuPanel.add(nameLabel, gbc);

                gbc.gridx = 2;
                JLabel typeLabelMenu = new JLabel(menu.getType());
                menuPanel.add(typeLabelMenu, gbc);

                gbc.gridx = 3;
                JLabel priceLabel = new JLabel(String.format("%.2f €", menu.getPrix()));
                menuPanel.add(priceLabel, gbc);

                row++;
            }

            // ajouter un distance
            gbc.gridx = 0;
            gbc.gridy = row++;
            gbc.gridwidth = 4;
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);
        }

        return menuPanel;
    }
}
