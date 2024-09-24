import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int SERVER_PORT = 12346; // Port number
    private static Socket socket;
    private static PrintWriter out;
    
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a panel with the background color and add it to the frame
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.YELLOW); // Set background color to yellow
        contentPanel.setLayout(new BorderLayout());
        frame.add(contentPanel, BorderLayout.CENTER);

        // Create the text area for displaying messages
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.YELLOW); // Ensure the text area background is yellow
        textArea.setForeground(Color.BLACK); // Ensure text is visible
        contentPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create a panel for user input and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.YELLOW); // Set background color to yellow

        // Create the text field for user input
        JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);

        // Create the Send button
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(Color.GRAY); // Gray button
        sendButton.setForeground(Color.WHITE); // Ensure text is visible on the gray button
        panel.add(sendButton, BorderLayout.EAST);

        // Add the input panel to the content panel
        contentPanel.add(panel, BorderLayout.SOUTH);

        // Add action listener for the Send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(textField.getText());
                textField.setText("");
            }
        });

        // Add action listener for the text field
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(textField.getText());
                textField.setText("");
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
        
        try {
            // Connect to the server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Prompt for the user's name
            String name = JOptionPane.showInputDialog(frame, "Enter your name:");
            out.println(name);
            
            // Continuously read and display messages from the server
            String message;
            while ((message = in.readLine()) != null) {
                textArea.append(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void sendMessage(String message) {
        if (message != null && !message.trim().isEmpty()) {
            out.println(message);
        }
    }
}
