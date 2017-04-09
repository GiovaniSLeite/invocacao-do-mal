
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {
    List<Part> allParts() throws RemoteException;
    Part getPart(Integer code) throws RemoteException;
    void insertPart(Part newOne) throws RemoteException;
}
