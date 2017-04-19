
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Repository implements PartRepository {

    private String name;
    private HashMap<UUID, Part> partCollection;

    public static void main(String[] args) {
        
    	//if (System.getSecurityManager() == null) {
          //     System.setSecurityManager(new SecurityManager());
           //}

        try {
            PartRepository obj = new Repository();
            obj.setName("repo");
            PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = null;
            try {
            	registry = LocateRegistry.createRegistry(1099);
            } catch (Exception e) 
            {	
            	System.out.println("A porta já foi iniciada. Conectando...");
            	registry = LocateRegistry.getRegistry(1099);
            }
            System.out.println(Arrays.toString(registry.list()));
            registry.bind("repo", stub);
            System.out.println(Arrays.toString(registry.list()));
            System.err.println("Servidor pronto");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
    
    public Repository() throws RemoteException {
		partCollection = new HashMap<>();
	}

    public void teste() throws RemoteException {
    	System.out.println("TESTOU");
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
    public void insertPart(String name, String description, Collection<Part> subs) throws RemoteException {
        try {
            PartObject p = new PartObject(name, description, this.name, subs);
            Part stub = (Part) UnicastRemoteObject.exportObject(p, 0);

            Registry registry = LocateRegistry.getRegistry(1099);
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
