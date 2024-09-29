import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ResourceSharingImpl extends UnicastRemoteObject implements ResourceSharing {
    private List<String> resources;
    private List<String> clients;

    protected ResourceSharingImpl() throws RemoteException {
        resources = new ArrayList<>();
        clients = new ArrayList<>();
        // Initialize some resources
        resources.add("Resource 1");
        resources.add("Resource 2");
        resources.add("Resource 3");
    }

    @Override
    public void registerClient(String clientName) throws RemoteException {
        clients.add(clientName);
        System.out.println("Client " + clientName + " has connected.");
    }

    @Override
    public String shareMessage(String message) throws RemoteException {
        return "Received: " + message;
    }

    @Override
    public List<String> listResources() throws RemoteException {
        return resources;
    }
}
