import javax.swing.SwingUtilities;

public class CRUDApplication {
    public static void main(String[] args) {
        // Ensuring the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of UserDAO to be used by the UserGUI
                UserDAO userDAO = new UserDAO();
                
                // Create an instance of the UserGUI and pass the UserDAO instance
                new UserGUI(userDAO);
            }
        });
    }
}
