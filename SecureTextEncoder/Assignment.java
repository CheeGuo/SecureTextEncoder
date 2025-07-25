import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;

public class Assignment {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Window");
        frame.setLayout(new GridBagLayout()); // To center components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding between components

        // Declare and initialize newPanel
        JPanel newPanel = new JPanel(new GridBagLayout());

        JLabel text = new JLabel("Enter text to encode (Uppercase letters, digits and spaces only):");
        text.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        JTextField code = new JTextField(25);
        code.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JButton generate = new JButton("Encode");
        generate.setFont(new Font("Times New Roman", Font.BOLD, 14));

        // Add components to newPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        newPanel.add(text, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        newPanel.add(code, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        newPanel.add(generate, gbc);

        // Add newPanel to the frame
        frame.add(newPanel);

        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = code.getText().trim();

                // Create Encoded object
                Encoded encoder = new Encoded();

                // Validate input
                if (!encoder.validateInput(inputText)) {
                    JLabel errorMessage = new JLabel("<html>Invalid input!<br>UPPERCASE letters, digits, and spaces only.</html>");
                    errorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
                    // Pop up dialog and prompt user to try again
                    int option = JOptionPane.showOptionDialog(
                        frame,
                        errorMessage,
                        "Error",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null,
                        new Object[] { "Try again" }, 
                        "Try Again"
                    );
        
                    // Clear text field 
                    if (option == 0) {
                        code.setText("");
                        return;
                    }
                }

                // Generate shift value
                int shift = encoder.generateShift();

                // Apply cipher to encode the input
                String encodedAnswer = encoder.applyCipher(inputText, shift);

                // Create new window to show result
                JFrame newFrame = new JFrame("Encoded Result");
                newFrame.setLayout(new GridBagLayout());
                GridBagConstraints resultGbc = new GridBagConstraints();
                resultGbc.insets = new Insets(5, 5, 5, 5);
                
                JLabel answerLabel = new JLabel("Encoded Answer: " + encodedAnswer);
                answerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
                JLabel shiftLabel = new JLabel("Final shift value: " + shift);
                shiftLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

                // Add components to the result window
                resultGbc.gridx = 0;
                resultGbc.gridy = 0;
                resultGbc.anchor = GridBagConstraints.CENTER;
                newFrame.add(answerLabel, resultGbc);

               // Add "Copy" button 
                ImageIcon clipboardIcon;
               clipboardIcon = null;
            try {
            java.net.URL imageUrl = Assignment.class.getResource("/copy.jpg"); // Look in resources
            if (imageUrl != null) {
            clipboardIcon = new ImageIcon(
            new ImageIcon(imageUrl).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)
            );
            } else {
            System.out.println("Image not found!");
            }
            } catch (Exception ex) {
            System.out.println("Error loading image: " + ex.getMessage());  
            }
                JButton copyButton = new JButton(clipboardIcon);
                copyButton.setToolTipText("Copy to Clipboard");// Tooltip
                copyButton.setPreferredSize(new Dimension(25, 25));
                copyButton.setBorderPainted(false); // Remove button border
                copyButton.setContentAreaFilled(false); // Remove button background
                copyButton.setFocusPainted(false); // Remove focus border
                
                resultGbc.gridx = 1; // Place beside encoded result
                resultGbc.anchor = GridBagConstraints.EAST; // Align button to right
                newFrame.add(copyButton, resultGbc);

                // Add action listener to "Copy" button
                copyButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                            new StringSelection(encodedAnswer), null
                        );

                        // Change JOptionPane font style globally before displaying
                        UIManager.put("OptionPane.messageFont", new Font("Times New Roman", Font.PLAIN, 14));
                        UIManager.put("OptionPane.buttonFont", new Font("Times New Roman", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(newFrame, 
                            "Encoded result copied to clipboard!", 
                            "Copied", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });

               // Add the shift label below
               resultGbc.gridx = 0;
               resultGbc.gridy = 2;
               resultGbc.gridwidth = 2; // Span across two columns
               resultGbc.anchor = GridBagConstraints.CENTER;
               newFrame.add(shiftLabel, resultGbc);


                newFrame.setSize(400, 180);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setVisible(true);
            }
        });
    }
}