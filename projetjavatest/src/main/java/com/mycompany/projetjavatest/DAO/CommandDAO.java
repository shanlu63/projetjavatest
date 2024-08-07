package com.mycompany.projetjavatest.dao;


import com.mycompany.projetjavatest.domain.Command;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;


public class CommandDAO {
    private static int currentId = (int) (System.currentTimeMillis() & 0xfffffff);//start ID from an initial value
    private JPanel commandPanel;
    private NumberFormat numberFormat;
    
     public CommandDAO() {
       
    }

    public CommandDAO(JPanel commandPanel) {
        this.commandPanel = commandPanel;
        this.numberFormat = NumberFormat.getInstance(Locale.FRANCE);
        this.numberFormat.setMinimumFractionDigits(2);
        this.numberFormat.setMaximumFractionDigits(2);
    }

   

    // sauvgarder dan le table
    // Save commands to file
    public void saveCommandsToFile(String fileName, int idTable) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
            for (Component component : commandPanel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel panel = (JPanel) component;
                    String nameplate = null;
                    String priceStr = null;
                    String quantity = null;
                    int idCommand = generateUniqueCommandId(); // A method to generate a unique command ID
                    Date date = new Date(); // Current date and time
                    float price = 0.0f;
                    float prixTVA = 0.0f;
                    boolean payment = false;

                    for (Component innerComponent : panel.getComponents()) {
                        if (innerComponent instanceof JLabel) {
                            JLabel label = (JLabel) innerComponent;
                            String text = label.getText();
                            // Extract nameplate and price
                            if (text.contains(" - ")) {
                                nameplate = text.split(" - ")[0];
                                priceStr = text.split(" - ")[1].split(" ")[0];
                                try {
                                    price = numberFormat.parse(priceStr).floatValue();
                                } catch (ParseException e) {
                                    System.err.println("Error parsing price: " + priceStr);
                                    continue; // Skip this entry if parsing fails
                                }
                                prixTVA = price * 0.1f;
                            }
                        }
                        if (innerComponent instanceof JTextField) {
                            quantity = ((JTextField) innerComponent).getText();
                        }
                    }

                    // Verify all information is saved
                    if (nameplate != null && priceStr != null && quantity != null) {
                        
                        String formattedPrixTVA = numberFormat.format(prixTVA);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
                        String formattedDate = dateFormat.format(date);
                        String entry = String.join(";", 
                            String.valueOf(idCommand), 
                            String.valueOf(idTable), 
                            nameplate, 
                            quantity, 
                            priceStr, 
                            formattedPrixTVA, 
                            formattedDate, 
                            String.valueOf(payment)
                        );
                        writer.write(entry);
                        writer.newLine();
                        System.out.println("Saved entry: " + entry); // Debugging info
                    } else {
                        System.err.println("Missing information for entry, not saved.");
                    }
                }
            }
            // System.out.println("Commands saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Dummy method to generate unique command ID
    private int generateUniqueCommandId() {
        // Implement your logic to generate a unique command ID
        return currentId++;
    }
    
    
     // Read commands from file
    public List<Command> readCommandsFromFile(String fileName) {
        List<Command> commands = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 8) {
                    int idCommand = Integer.parseInt(parts[0]);
                    int idTable = Integer.parseInt(parts[1]);
                    String nameplate = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    float prixTotalHT = Float.parseFloat(parts[4].replace(",", "."));
                    float prixTVA = Float.parseFloat(parts[5].replace(",", "."));
                    Date date = dateFormat.parse(parts[6]);
                    boolean payment = Boolean.parseBoolean(parts[7]);
                    commands.add(new Command(idCommand, idTable, nameplate, quantity, date, prixTotalHT, prixTVA, payment));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return commands;
    }
     // Create a command panel displaying commands from the file
    public JPanel createCommandPanel(String fileName) {
      
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically

        // Read commands from file
        List<Command> commands = readCommandsFromFile(fileName);

        // Add a label for headers
        JPanel headerPanel = new JPanel(new GridLayout(1, 8));
        headerPanel.add(new JLabel("ID"));
        headerPanel.add(new JLabel("Table ID"));
        headerPanel.add(new JLabel("Nameplate"));
        headerPanel.add(new JLabel("Quantity"));
        headerPanel.add(new JLabel("Price"));
        headerPanel.add(new JLabel("Prix TVA"));
        headerPanel.add(new JLabel("Date"));
        headerPanel.add(new JLabel("Payment"));
        panel.add(headerPanel);

        // Format for dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

        // Add a panel for each command
        for (Command command : commands) {
            JPanel commandPanel = new JPanel(new GridLayout(1, 8));
            commandPanel.add(new JLabel(String.valueOf(command.getIdCommand())));
            commandPanel.add(new JLabel(String.valueOf(command.getIdTable())));
            commandPanel.add(new JLabel(command.getPlate()));
            commandPanel.add(new JLabel(String.valueOf(command.getQuantity())));
            commandPanel.add(new JLabel(String.format("%.2f", command.getPrixTotalHT())));
            commandPanel.add(new JLabel(String.format("%.2f", command.getPrixTVA())));
            commandPanel.add(new JLabel(dateFormat.format(command.getDate())));
            commandPanel.add(new JLabel(String.valueOf(command.isPayement())));
            panel.add(commandPanel);
        }

        

        return panel; // Return the JScrollPane containing the panel
    }

    
    
}
