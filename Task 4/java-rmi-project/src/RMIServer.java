import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            ResourceSharingImpl resourceSharing = new ResourceSharingImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ResourceSharing", resourceSharing);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
