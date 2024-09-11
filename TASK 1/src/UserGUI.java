import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserGUI extends JFrame {
    private JTextField idField, nameField, emailField, phoneField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea textArea;
    private UserDAO userDAO;

    public UserGUI(UserDAO userDAO) {
        this.userDAO = userDAO;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("CRUD Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        // Add form fields
        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        // Create buttons
        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        viewButton = new JButton("View Users");

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        // Create a text area for output
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Add components to the frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });

        // Set the background colors
        getContentPane().setBackground(Color.GREEN);
        formPanel.setBackground(Color.GRAY);
        buttonPanel.setBackground(Color.GRAY);
        textArea.setBackground(Color.GRAY);

        // Make the frame visible
        setVisible(true);
    }

    private void addUser() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        User user = new User(name, email, phone);
        userDAO.addUser(user);
        textArea.setText("User added: " + user.toString());
        clearFields();
    }

    private void updateUser() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            User user = new User(id, name, email, phone);
            userDAO.updateUser(user);
            textArea.setText("User updated: " + user.toString());
            clearFields();
        } catch (NumberFormatException e) {
            textArea.setText("Invalid ID format.");
        }
    }

    private void deleteUser() {
        try {
            int id = Integer.parseInt(idField.getText());
            userDAO.deleteUser(id);
            textArea.setText("User deleted with ID: " + id);
            clearFields();
        } catch (NumberFormatException e) {
            textArea.setText("Invalid ID format.");
        }
    }

    private void viewUsers() {
        List<User> users = userDAO.getAllUsers();
        textArea.setText("");
        if (users.isEmpty()) {
            textArea.append("No users found.");
        } else {
            for (User user : users) {
                textArea.append(user.toString() + "\n");
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }
}
