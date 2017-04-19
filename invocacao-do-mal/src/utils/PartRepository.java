package utils;




import utils.SubcomponentsList;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface PartRepository extends Remote {
    
    String getName() throws RemoteException;
    Integer getRepositorySize() throws RemoteException;
    Part getPart(String code) throws RemoteException;
    void insertPart(String name, String description, SubcomponentsList subs) throws RemoteException;
    void setName(String name) throws RemoteException;
    String listRepositoryParts() throws RemoteException;
}
