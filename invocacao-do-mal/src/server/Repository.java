package server;
import utils.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Repository implements PartRepository {
    private String name;
    private HashMap<String, Part> partCollection;

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "remote";
        try {
            PartRepository obj = new Repository();
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(1099);
            }
            obj.setName(name);
            PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);
            try {
                registry.bind(name, stub);
            } catch (AlreadyBoundException e) {
                System.out.println("Já existe um repositório com o mesmo nome");
                System.exit(0);
            }
            System.err.println("Servidor pronto");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }

    public Repository() {
        partCollection = new HashMap<>();
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public Integer getRepositorySize() throws RemoteException {
        return partCollection.size();
    }

    @Override
    public Part getPart(String code) throws RemoteException {
        return partCollection.get(code);
    }

    @Override
    public void insertPart(String name, String description, SubcomponentsList subs) throws RemoteException {
        try {
            PartObject p = new PartObject(name, description, this.name, subs);
            Part stub = (Part) UnicastRemoteObject.exportObject(p, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.bind(p.getCode().toString(), stub);
            partCollection.put(p.getCode().toString(), stub);
        } catch (Exception ex) {
            System.out.println("Erro durante a inserção - " + ex.getMessage());
        }
    }

    @Override
    public void setName(String name) throws RemoteException {
    	this.name = name;
    }

    @Override
    public String listRepositoryParts() throws RemoteException {
        StringBuilder list = new StringBuilder();
        list.append("Peças do repositorio: ").append(this.getName()).append("\n");
        
        for(Part p : partCollection.values())
            list.append(p.toText()).append("\n");
        
        list.append("--- fim da lista ---");
        
        return list.toString();
    }
}
