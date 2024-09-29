import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ResourceSharing extends Remote {
    void registerClient(String clientName) throws RemoteException;
    String shareMessage(String message) throws RemoteException;
    List<String> listResources() throws RemoteException;
}
