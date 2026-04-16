import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Project: Quantum Millionaire
 * Purpose: UI for hosting a game. Captures Port and Name to start the Server.
 * Student: Tien Nguyen
 */
public class HostDialog extends JDialog {
    private JTextField nameField;
    private JComboBox<String> portBox;
    private JComboBox<String> maxPlayersBox;
    private MillionaireServer server;

    public HostDialog(JFrame parent) {
        super(parent, "Host Game", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Player Name [cite: 58]
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Player Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Port Selection [cite: 59, 60]
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Port:"), gbc);
        String[] ports = {"3000", "4000", "5000", "8080"};
        portBox = new JComboBox<>(ports);
        portBox.setEditable(true);
        gbc.gridx = 1;
        add(portBox, gbc);

        // Max Players [cite: 61]
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Max Players:"), gbc);
        maxPlayersBox = new JComboBox<>(new String[]{"2", "3", "4"});
        gbc.gridx = 1;
        add(maxPlayersBox, gbc);

        // Instruction Label [cite: 62]
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(new JLabel("Enter your name and port"), gbc);

        // Buttons [cite: 63, 64]
        JPanel btnPanel = new JPanel();
        JButton hostBtn = new JButton("Host");
        JButton cancelBtn = new JButton("Cancel");

        hostBtn.addActionListener(this::handleHost);
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(hostBtn);
        btnPanel.add(cancelBtn);
        gbc.gridy = 4;
        add(btnPanel, gbc);

        pack();
        setLocationRelativeTo(parent);
    }

    private void handleHost(ActionEvent e) {
        try {
            String name = nameField.getText().trim();
            int port = Integer.parseInt(portBox.getSelectedItem().toString());
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }

            // Start your existing MillionaireServer
            server = new MillionaireServer(port);
            server.start();
            
            JOptionPane.showMessageDialog(this, "Server started on port " + port);
            dispose(); // Close dialog after starting
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Port Number.");
        }
    }
}

