import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ResourceSharing resourceSharing = (ResourceSharing) registry.lookup("ResourceSharing");

            // Register the client
            String clientName = "Client " + (int) (Math.random() * 1000);
            resourceSharing.registerClient(clientName);

            // Share a message
            String response = resourceSharing.shareMessage("Hello from " + clientName);
            System.out.println(response);

            // List available resources
            List<String> resources = resourceSharing.listResources();
            System.out.println("Available Resources: " + resources);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
