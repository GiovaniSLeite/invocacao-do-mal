
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class Repository implements PartRepository {

    private String name;
    private HashMap<UUID, Part> partCollection;

    public static void main(String[] args) {
        
    	if (System.getSecurityManager() == null) {
               System.setSecurityManager(new SecurityManager());
           }

        try {
            PartRepository obj = new Repository();
            obj.setName("repo");
            PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.createRegistry(1200);
            registry.rebind("repo", stub);

            System.err.println("Servidor pronto");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
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
    public Part getPart(UUID code) throws RemoteException {
        return partCollection.get(code);
    }

    @Override
    public void insertPart(String name, String description, SubcomponentsList subs) throws RemoteException {
        try {
            PartObject p = new PartObject(name, description, this.name, subs);
            Part stub = (Part) UnicastRemoteObject.exportObject(p, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind(p.getCode().toString(), stub);

            partCollection.put(p.getCode(), stub);
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
        list.append("Peças do repositorio ").append(this.getName());
        
        for(Part p : partCollection.values())
            list.append(p).append("\n");
        
        list.append("--- fim da lista ---");
        
        return list.toString();
    }
}
