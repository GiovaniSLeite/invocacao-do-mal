
import java.rmi.RemoteException;
import java.util.List;


public class Repository implements PartRepository {
    String name;
    List<Part> parts;

    @Override
    public List<Part> allParts() throws RemoteException {
        return parts;
    }

    @Override
    public Part getPart(Integer code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insertPart(Part newOne) throws RemoteException {
        parts.add(newOne);
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }
    
}
