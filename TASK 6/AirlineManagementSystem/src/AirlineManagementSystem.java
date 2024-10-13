import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AirlineManagementSystem {
    private JFrame frame;
    private JPanel panel;
    private Connection connection;

    public AirlineManagementSystem() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        frame = new JFrame("Airline Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(Color.BLUE);

        JLabel titleLabel = new JLabel("AIRLINE MANAGEMENT SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        // Customer Section
        JLabel customerLabel = new JLabel("CUSTOMER DETAILS", JLabel.CENTER);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        customerLabel.setForeground(Color.WHITE);
        panel.add(customerLabel);

        JButton insertCustButton = new JButton("Insert Customer Details");
        insertCustButton.setBackground(Color.GRAY);
        insertCustButton.addActionListener(e -> insertCustomerDetails());
        panel.add(insertCustButton);

        JButton updateCustButton = new JButton("Update Customer Details");
        updateCustButton.setBackground(Color.GRAY);
        updateCustButton.addActionListener(e -> updateCustomerDetails());
        panel.add(updateCustButton);

        JButton retrieveCustButton = new JButton("Retrieve Customer Records");
        retrieveCustButton.setBackground(Color.GRAY);
        retrieveCustButton.addActionListener(e -> retrieveCustomerRecords());
        panel.add(retrieveCustButton);

        // Flight Section
        JLabel flightLabel = new JLabel("FLIGHT DETAILS", JLabel.CENTER);
        flightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        flightLabel.setForeground(Color.WHITE);
        panel.add(flightLabel);

        JButton insertFlightButton = new JButton("Insert Flight Details");
        insertFlightButton.setBackground(Color.GRAY);
        insertFlightButton.addActionListener(e -> insertFlightDetails());
        panel.add(insertFlightButton);

        JButton updateFlightButton = new JButton("Update Flight Details");
        updateFlightButton.setBackground(Color.GRAY);
        updateFlightButton.addActionListener(e -> updateFlightDetails());
        panel.add(updateFlightButton);

        JButton retrieveFlightButton = new JButton("Retrieve Flight Details");
        retrieveFlightButton.setBackground(Color.GRAY);
        retrieveFlightButton.addActionListener(e -> retrieveFlightDetails());
        panel.add(retrieveFlightButton);

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void connectToDatabase() {
        try {
            // Database connection details (replace with your own)
            String url = "jdbc:mysql://localhost:3306/airline_management";
            String user = "sareena"; // replace with your username
            String password = "Ilovecoding456$"; // replace with your password
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertCustomerDetails() {
        String[] customerDetails = new String[7];
        customerDetails[0] = JOptionPane.showInputDialog("Enter Customer ID:");
        customerDetails[1] = JOptionPane.showInputDialog("Enter First Name:");
        customerDetails[2] = JOptionPane.showInputDialog("Enter Last Name:");
        customerDetails[3] = JOptionPane.showInputDialog("Enter Age:");
        customerDetails[4] = JOptionPane.showInputDialog("Enter Gender:");
        customerDetails[5] = JOptionPane.showInputDialog("Enter Phone No:");
        customerDetails[6] = JOptionPane.showInputDialog("Enter Email:");

        try {
            String query = "INSERT INTO customers (cust_id, first_name, last_name, age, gender, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < customerDetails.length; i++) {
                preparedStatement.setString(i + 1, customerDetails[i]);
            }
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Customer details inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomerDetails() {
        String custId = JOptionPane.showInputDialog("Enter Customer ID to update:");
        String field = JOptionPane.showInputDialog("Enter field to update (first_name, last_name, age, gender, phone, email):");
        String newValue = JOptionPane.showInputDialog("Enter new value:");

        try {
            String query = "UPDATE customers SET " + field + " = ? WHERE cust_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, custId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Customer details updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void retrieveCustomerRecords() {
        String custId = JOptionPane.showInputDialog("Enter Customer ID to retrieve:");

        try {
            String query = "SELECT * FROM customers WHERE cust_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, custId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String customerInfo = "ID: " + resultSet.getString("cust_id") +
                        "\nFirst Name: " + resultSet.getString("first_name") +
                        "\nLast Name: " + resultSet.getString("last_name") +
                        "\nAge: " + resultSet.getString("age") +
                        "\nGender: " + resultSet.getString("gender") +
                        "\nPhone: " + resultSet.getString("phone") +
                        "\nEmail: " + resultSet.getString("email");
                JOptionPane.showMessageDialog(frame, customerInfo);
            } else {
                JOptionPane.showMessageDialog(frame, "Customer not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertFlightDetails() {
        String[] flightDetails = new String[5];
        flightDetails[0] = JOptionPane.showInputDialog("Enter Flight ID:");
        flightDetails[1] = JOptionPane.showInputDialog("Enter Airline Name:");
        flightDetails[2] = JOptionPane.showInputDialog("Enter Fare Price:");
        flightDetails[3] = JOptionPane.showInputDialog("Enter Flight Type:");
        flightDetails[4] = JOptionPane.showInputDialog("Enter Flight Duration:");

        try {
            String query = "INSERT INTO flights (id, airline_name, fare_price, flight_type, flight_duration) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < flightDetails.length; i++) {
                preparedStatement.setString(i + 1, flightDetails[i]);
            }
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Flight details inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateFlightDetails() {
        String flightId = JOptionPane.showInputDialog("Enter Flight ID to update:");
        String field = JOptionPane.showInputDialog("Enter field to update (airline_name, fare_price, flight_type, flight_duration):");
        String newValue = JOptionPane.showInputDialog("Enter new value:");

        try {
            String query = "UPDATE flights SET " + field + " = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, flightId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Flight details updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void retrieveFlightDetails() {
        String flightId = JOptionPane.showInputDialog("Enter Flight ID to retrieve:");

        try {
            String query = "SELECT * FROM flights WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, flightId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String flightInfo = "ID: " + resultSet.getString("id") +
                        "\nAirline Name: " + resultSet.getString("airline_name") +
                        "\nFare Price: " + resultSet.getString("fare_price") +
                        "\nFlight Type: " + resultSet.getString("flight_type") +
                        "\nFlight Duration: " + resultSet.getString("flight_duration");
                JOptionPane.showMessageDialog(frame, flightInfo);
            } else {
                JOptionPane.showMessageDialog(frame, "Flight not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirlineManagementSystem::new);
    }
}
