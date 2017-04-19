


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.UUID;

public interface PartRepository extends Remote {
    
    String getName() throws RemoteException;
    Integer getRepositorySize() throws RemoteException;
    Part getPart(UUID code) throws RemoteException;
    void insertPart(String name, String description, Collection<Part> subs) throws RemoteException;
    void setName(String name) throws RemoteException;
    String listRepositoryParts() throws RemoteException;
    void teste()  throws RemoteException;
}
