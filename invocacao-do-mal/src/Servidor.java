
import java.rmi.RemoteException;
import java.util.List;


public class Servidor implements PartRepository {
    PartRepository repo;

    @Override
    public List<Part> allParts() throws RemoteException {
        return repo.allParts();
    }

    @Override
    public Part getPart(Integer code) throws RemoteException {
        return repo.getPart(code);
    }

    @Override
    public void insertPart(Part newOne) throws RemoteException {
        repo.insertPart(newOne);
    }

    @Override
    public String getName() throws RemoteException {
        return repo.getName();
    }
}
