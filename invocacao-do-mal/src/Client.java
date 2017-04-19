
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class Client {
    PartRepository currentRepo;
    Part currentPart;
    SubcomponentsList currentSubcomponents = new SubcomponentsList();
    
     public static void main(String[] args) {
        //TODO - Menu
    }

    public void connectTo(String serverName) {
        try {
            Registry registry = LocateRegistry.getRegistry(1200);
            currentRepo = (PartRepository) registry.lookup(serverName);
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao servidor - " + ex.getMessage());
        }
    }

    public String getCurrentRepoName() {
        try {
            return currentRepo.getName();
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar nome do repositorio - " + ex.getMessage());
            return null;
        }
    }

    public Integer getCurrentRepoSize() {
        try {
            return currentRepo.getRepositorySize();
        } catch (RemoteException ex) {
            System.out.println("Erro ao contar as parts do repositorio - " + ex.getMessage());
            return null;
        }
    }

    public void getPart(UUID code) {
        try {
            currentPart = currentRepo.getPart(code);
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar part no repositorio - " + ex.getMessage());
        }
    }

    public void addPartToRepository(String name, String description) {
        try {
            currentRepo.insertPart(name, description, currentSubcomponents);
        } catch (RemoteException ex) {
            System.out.println("Erro ao adicionar Part ao repositorio - " + ex.getMessage());
        }
    }

    public String getCurrentPartName() {
        try {
            return currentPart.getName();
        } catch (RemoteException ex) {
            return "Erro ao buscar o nome";
        }
    }

    public String getCurrentPartDescription() {
        try {
            return currentPart.getDescription();
        } catch (RemoteException ex) {
            return "Erro ao buscar o nome";
        }
    }

    public Integer countCurrentPartDirectComponents() {
        try {
            return currentPart.getDirectSubcomponentsSize();
        } catch (RemoteException ex) {
            return -1;
        }
    }

    public String listCurrentPartSubComponents() {
        try {
            return currentPart.listSubcomponents();
        } catch (RemoteException ex) {
            return "Erro ao listar subcomponents";
        }
    }

    public void addPartToCurrentSubComponents(Integer quantity) {
        currentSubcomponents.add(new SubcomponentsListItem(currentPart, quantity));
    }

    public void clearCurrentSubComponents() {
        currentSubcomponents.clear();
    }

    public void quit() {
        System.exit(0);
    }

    public String listCurrentRepoParts() {
        try {
            return currentRepo.listRepositoryParts();
        } catch (RemoteException ex) {
            System.out.println("Erro ao listar peças do repositório corrente");
            return null;
        }
    }
}
