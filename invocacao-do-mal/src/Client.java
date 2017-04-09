
import java.rmi.RemoteException;
import java.util.List;

public class Client implements ClientInterface {
    PartRepository currentRepo;
    Part currentPart;
    List<SubComponentItem> currentSubcomponents;
    
    @Override
    public void connectTo(String serverName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCurrentRepoName() {
        try {
            return currentRepo.getName();
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar nome do repositorio - " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Integer getCurrentReposize() {
        try {
            return currentRepo.allParts().size();
        } catch (RemoteException ex) {
            System.out.println("Erro ao contar as parts do repositorio - " + ex.getMessage());
            return null;
        }
    }

    @Override
    public String listCurrentRepoParts() {
        try {
            return currentRepo.allParts().toString();
        } catch (RemoteException ex) {
            System.out.println("Erro ao listar as parts do repositorio - " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void getPart(Integer code) {
        try {
            currentPart = currentRepo.getPart(code);
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar part no repositorio - " + ex.getMessage());
        }
    }

    @Override
    public void addPartToRepository(String name, String description) {
        try {
            currentRepo.insertPart(new Part(name, description, currentSubcomponents));
        } catch (RemoteException ex) {
            System.out.println("Erro ao adicionar Part ao repositorio - " + ex.getMessage());
        }
    }

    @Override
    public String getCurrentPartName() {
        return currentPart.getName();
    }

    @Override
    public String getCurrentPartDescription() {
        return currentPart.getDescription();
    }

    @Override
    public Integer countCurrentPartDirectComponents() {
        return currentPart.getSubComponents().size();
    }

    @Override
    public String listCurrentPartSubComponents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addPartToCurrentSubComponents(Integer quantity) {
        currentSubcomponents.add(new SubComponentItem(currentPart, quantity));
    }

    @Override
    public void clearCurrentSubComponents() {
        currentSubcomponents.clear();
    }

    @Override
    public void quit() {
        System.exit(0);
    }
}
