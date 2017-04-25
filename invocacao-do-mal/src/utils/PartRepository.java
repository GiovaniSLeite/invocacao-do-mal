package utils;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public interface PartRepository extends Remote {
    
    String getName() throws RemoteException;
    Integer getRepositorySize() throws RemoteException;
    Part getPart(String code) throws RemoteException;
    void insertPart(String name, String description, SubcomponentsList subs) throws RemoteException;
    void setName(String name) throws RemoteException;
    String listRepositoryParts() throws RemoteException;
    
    
    static String[] listCurrentRepos() throws RemoteException{
    	Registry registry;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            registry = LocateRegistry.getRegistry(1099);
        }
    	return registry.list();
    }
}
