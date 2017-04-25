package server;
import utils.*;
import java.rmi.RemoteException;
import java.util.UUID;

public class PartObject implements Part {
    UUID code;
    String name, description, origin;
    SubcomponentsList subcomponents;

    public PartObject(String name, String description, String origin, SubcomponentsList subs) {
        this.code = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.subcomponents = new SubcomponentsList();
        this.subcomponents.addAll(subs);
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
    public boolean isPrimitive() throws RemoteException {
        return subcomponents.isEmpty();
    }

    @Override
    public Integer getDirectSubcomponentsSize() throws RemoteException {
        return subcomponents.getSize();
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

    @Override
    public String toText() throws RemoteException {
        return this.toString();
    }

    @Override
    public String getOrigin() throws RemoteException {
        return this.origin;
    }
}
