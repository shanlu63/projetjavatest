package com.mycompany.projetjavatest.dao;
import com.mycompany.projetjavatest.domain.Command;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommandDAO {

    private static int currentId = (int) (System.currentTimeMillis() & 0xfffffff); // Start ID from an initial value
    private JPanel commandPanel;
    private NumberFormat numberFormat;
    public float totalToPay;
    public float totalHT;
    public float totalTVA;
 

    public CommandDAO() {
    }

    public CommandDAO(JPanel commandPanel) {
        this.commandPanel = commandPanel;
        this.numberFormat = NumberFormat.getInstance(Locale.FRANCE);
        this.numberFormat.setMinimumFractionDigits(2);
        this.numberFormat.setMaximumFractionDigits(2);
    }

    public float getTotalToPay() {
        return totalToPay;
    }
    
    public void  setTotalToPay(float totalToPay) {
        // Round to two decimal places
        this.totalToPay = Math.round(totalToPay * 100.0f) / 100.0f;
    }

    // Save commands to file
    public void saveCommandsToFile(String fileName, int idTable) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Component component : commandPanel.getComponents()) {
                if (component instanceof JPanel) {
                    JPanel panel = (JPanel) component;
                    String nameplate = null;
                    String priceStr = null;
                    String quantity = null;
                    int idCommand = generateUniqueCommandId(); // Generate a unique command ID
                    Date date = new Date(); // Current date and time
                    float price = 0.0f;
                    float prixTVA = 0.0f;
                    boolean payment = false;

                    for (Component innerComponent : panel.getComponents()) {
                        if (innerComponent instanceof JLabel) {
                            JLabel label = (JLabel) innerComponent;
                            String text = label.getText();
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

                    if (nameplate != null && priceStr != null && quantity != null) {
                        String formattedPrixTVA = numberFormat.format(prixTVA);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int generateUniqueCommandId() {
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

    public JPanel createCommandPanel(String fileName, int tableactual) {
        JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically

    // Read commands from file
    List<Command> commands = readCommandsFromFile(fileName);

    // Add a label for headers with tighter spacing
    JPanel headerPanel = new JPanel(new GridLayout(1, 9, 1, 0)); // hgap = 1, vgap = 0 for tighter spacing
    headerPanel.add(createCompactLabel("ID"));
    headerPanel.add(createCompactLabel("Table ID"));
    headerPanel.add(createCompactLabel("Plate"));
    headerPanel.add(createCompactLabel("Quantity"));
    headerPanel.add(createCompactLabel("Prix HT"));
    headerPanel.add(createCompactLabel("Prix TVA"));
    headerPanel.add(createCompactLabel("Date"));
    headerPanel.add(createCompactLabel("Payment"));
    headerPanel.add(createCompactLabel("Action")); // New column for delete button
    panel.add(headerPanel);

    // Format for dates
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

    // Variables pour calculer les totaux
    totalHT = 0;
    totalTVA = 0;

    // Add a panel for each command
    for (Command command : commands) {
        if ((command.getIdTable() == tableactual) && (!command.isPayement())) {

            JPanel commandPanel = new JPanel(new GridLayout(1, 9, 1, 0)); // hgap = 1, vgap = 0 for tighter spacing
            commandPanel.add(createCompactLabel(String.valueOf(command.getIdCommand())));
            commandPanel.add(createCompactLabel(String.valueOf(command.getIdTable())));
            commandPanel.add(createCompactLabel(command.getPlate()));
            commandPanel.add(createCompactLabel(String.valueOf(command.getQuantity())));
            commandPanel.add(createCompactLabel(String.format("%.2f", command.getPrixTotalHT())));
            commandPanel.add(createCompactLabel(String.format("%.2f", command.getPrixTVA())));
            commandPanel.add(createCompactLabel(dateFormat.format(command.getDate())));
            commandPanel.add(createCompactLabel(String.valueOf(command.isPayement())));

            // Add delete button
            JButton deleteButton = new JButton("Effacer");
            deleteButton.setPreferredSize(new Dimension(60, 20)); // Set button size for compact look
            deleteButton.addActionListener(e -> {
               // Remove command panel from the UI
                panel.remove(commandPanel);

                // Remove command from the file
                deleteCommandFromFile("command.txt", command.getIdCommand());

                // Update totals
                totalHT -= command.getPrixTotalHT();
                totalTVA -= command.getPrixTVA();
                totalToPay = totalHT + totalTVA;
                setTotalToPay(totalToPay);

                // Refresh total panel
                updateTotalPanel(panel, totalToPay);
                

                // Refresh the panel
                panel.revalidate();
                panel.repaint();
            });
            
            commandPanel.add(deleteButton);
            panel.add(commandPanel);

            // calculer le prix HT et les TVA 
            totalHT += command.getPrixTotalHT();
            totalTVA += command.getPrixTVA();
        }
    }

    // Calculer le montant total à payer
    totalToPay = totalHT + totalTVA;
     setTotalToPay(totalToPay);

    // Ajouter une ligne pour le montant total à payer
    updateTotalPanel(panel, totalToPay);

    return panel;
    }
    
    
    public void deleteCommandFromFile(String fileName, int idCommandToDelete) {
        List<Command> remainingCommands = new ArrayList<>();

        // Read the file and keep only commands that should not be deleted
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int idCommand = Integer.parseInt(parts[0]);

                if (idCommand != idCommandToDelete) {
                    Command command = parseCommand(line);
                    if (command != null) {
                        remainingCommands.add(command);
                    }
                } else {
                    System.out.println("Deleting command with ID: " + idCommandToDelete);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Recalculate totals
       totalToPay = calculateTotal(remainingCommands);
        setTotalToPay(totalToPay);
        // Write remaining commands back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (Command command : remainingCommands) {
                writer.write(commandToString(command));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the file was updated
        List<Command> updatedCommands = readCommandsFromFile(fileName);
        if (updatedCommands.stream().noneMatch(cmd -> cmd.getIdCommand() == idCommandToDelete)) {
            System.out.println("Command with ID " + idCommandToDelete + " successfully deleted.");
        } else {
            System.err.println("Failed to delete command with ID " + idCommandToDelete + ".");
        }
    }
    
    
    private float calculateTotal(List<Command> commands) {
        float total = 0;
        for (Command command : commands) {
            total += command.getPrixTotalHT()*1.2; //
        }
        return total;
    }
    



    private Command parseCommand(String line) {
        if (line == null || line.trim().isEmpty()) {
        System.err.println("Line is empty or null, skipping.");
        return null;
    }

        String[] parts = line.split(";");
        if (parts.length != 8) {
            System.err.println("Incorrect format in the file: " + line);
            return null;
        }

        try {
            int idCommand = Integer.parseInt(parts[0]);
            int idTable = Integer.parseInt(parts[1]);
            String plate = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            float prixTotalHT = Float.parseFloat(parts[4].replace(",", "."));
            float prixTVA = Float.parseFloat(parts[5].replace(",", "."));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
            Date date = dateFormat.parse(parts[6]);
            boolean payment = Boolean.parseBoolean(parts[7]);

            return new Command(idCommand, idTable, plate, quantity, date, prixTotalHT, prixTVA, payment);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
            System.err.println("Error parsing line: " + line);
            return null;
        }
    }

    private String commandToString(Command command) {
        // Ensure the use of correct number formatting for the locale
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRANCE);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);

        // Ensure the use of the correct date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

        return String.join(";",
            String.valueOf(command.getIdCommand()),                // Command ID
            String.valueOf(command.getIdTable()),                  // Table ID
            command.getPlate(),                                    // Plate name
            String.valueOf(command.getQuantity()),                 // Quantity
            numberFormat.format(command.getPrixTotalHT()),         // Prix HT formatted correctly
            numberFormat.format(command.getPrixTVA()),             // Prix TVA formatted correctly
            dateFormat.format(command.getDate()),                  // Date formatted correctly
            String.valueOf(command.isPayement())                   // Payment status
        );
    }

    private JLabel createCompactLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(60, 20)); // Adjust size for readability
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        return label;
    }

    private void updateTotalPanel(JPanel panel, float totalToPay) {
         // Remove existing total panel if present
    Component[] components = panel.getComponents();
    for (Component component : components) {
        if (component instanceof JPanel) {
            JPanel existingPanel = (JPanel) component;
            if (existingPanel.getComponentCount() > 0 && 
                existingPanel.getComponent(0) instanceof JLabel &&
                ((JLabel) existingPanel.getComponent(0)).getText().contains("Montant")) {
                panel.remove(existingPanel);
                break;
            }
        }
    }

    // Create a new total panel
    JPanel finalAmountPanel = new JPanel(new GridLayout(1, 9, 1, 0)); // hgap = 1, vgap = 0 for tighter spacing
    finalAmountPanel.add(createCompactLabel("Montant"));
    finalAmountPanel.add(createCompactLabel("à payer"));
    finalAmountPanel.add(createCompactLabel(""));
    finalAmountPanel.add(createCompactLabel("(Prix TTC)"));
    finalAmountPanel.add(createCompactLabel(String.format("%.2f €", totalToPay)));
    finalAmountPanel.add(createCompactLabel(" "));
    finalAmountPanel.add(createCompactLabel(" "));
    finalAmountPanel.add(createCompactLabel(" "));
    finalAmountPanel.add(createCompactLabel(" "));
    panel.add(finalAmountPanel);
    }

    public void updatePaymentStatus(String fileName, int idTable) {
        List<Command> commands = readCommandsFromFile(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Command command : commands) {
                if (command.getIdTable() == idTable) {
                    command.setPayement(true); // Update payment status to true
                }
                writer.write(String.join(";",
                    String.valueOf(command.getIdCommand()),
                    String.valueOf(command.getIdTable()),
                    command.getPlate(),
                    String.valueOf(command.getQuantity()),
                    String.format("%.2f", command.getPrixTotalHT()),
                    String.format("%.2f", command.getPrixTVA()),
                    new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(command.getDate()),
                    String.valueOf(command.isPayement())
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Command getCommandByTable(String commandtxt, int tableactual) {
        Command foundCommand = null;

        try (BufferedReader br = new BufferedReader(new FileReader(commandtxt))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each line in the command.txt represents a Command in a specific format
                String[] parts = line.split(";");
                if (parts.length < 8) { // Ensure there are enough parts to avoid ArrayIndexOutOfBoundsException
                    System.err.println("Format incorrect dans le fichier : " + line);
                    continue;
                }

                int idTable = Integer.parseInt(parts[1]);
                if (idTable == tableactual) {
                    int idCommand = Integer.parseInt(parts[0]);
                    String plate = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    Date date = new SimpleDateFormat("dd/MM/yyyy-HH:mm").parse(parts[4]);
                    float prixTotalHT = Float.parseFloat(parts[5].replace(",", "."));
                    float prixTVA = Float.parseFloat(parts[6].replace(",", "."));
                    boolean paymentStatus = Boolean.parseBoolean(parts[7]);

                    foundCommand = new Command(idCommand, idTable, plate, quantity, date, prixTotalHT, prixTVA, paymentStatus);
                    break; // Exit loop once the command for the specified table is found
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from " + commandtxt + ": " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing command data: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        return foundCommand;
    
    
    }
}
