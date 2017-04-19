
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.UUID;

public class PartObject implements Part {
    UUID code;
    String name, description, origin;
    Collection subcomponents;

    public PartObject(String name, String description, String origin, Collection<Part> subs) {
        this.code = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.subcomponents = subs;
    }
    
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public String getRepositoryName() throws RemoteException {
        return origin;
    }

    @Override
    public boolean isPrimitive() throws RemoteException {
        return subcomponents.isEmpty();
    }

    @Override
    public Integer getDirectSubcomponentsSize() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getTotalSubcomponentsSize() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String listSubcomponents() throws RemoteException {
        return subcomponents.toString();
    }

    @Override
    public UUID getCode() throws RemoteException {
        return code;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}
