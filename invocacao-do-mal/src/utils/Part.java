package utils;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface Part extends Remote {
    UUID getCode() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    String getOrigin() throws RemoteException;
    boolean isPrimitive() throws RemoteException;
    Integer getDirectSubcomponentsSize() throws RemoteException;
    String listSubcomponents() throws RemoteException;
    String toText() throws RemoteException;
}
