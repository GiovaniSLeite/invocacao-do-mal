package client;


import utils.Part;
import utils.PartRepository;
import utils.SubcomponentsList;
import utils.SubcomponentsListItem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    PartRepository currentRepo;
    Part currentPart;
    SubcomponentsList currentSubcomponents;
    
    public static void main(String[] args) throws RemoteException {
        Client x = new Client();
    }

    void teste() throws RemoteException {
        System.out.println("escreve o nome do repositorio");
        Scanner sc = new Scanner(System.in);
        this.connectTo(sc.next());
        System.out.println(this.currentRepo.getRepositorySize());
        this.addPartToRepository("Amanda", "besta");
        System.out.println(this.currentRepo.getRepositorySize());
        this.listCurrentRepoParts();
        this.getCurrentRepoName();
        this.getCurrentRepoSize();
        System.out.println("escreve o cod");
        this.getPart(sc.next());
        System.out.println(currentPart.getDescription());
    }

    public Client() {
        currentSubcomponents = new SubcomponentsList();
    }

    public void connectTo(String serverName) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            currentRepo = (PartRepository) registry.lookup(serverName);            
            System.out.println("Conectado com sucesso ao repositorio " + serverName + ".");
        } catch (Exception ex) {
            System.out.println("Erro ao conectar ao servidor - " + ex.getMessage());
        }
    }

    public void getCurrentRepoName() {
        try {
            System.out.println("Nome do repositorio: " + currentRepo.getName()+ ".");
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar nome do repositorio - " + ex.getMessage());
        }
    }

    public void getCurrentRepoSize() {
        try {
            System.out.println("O repositorio " + currentRepo.getName() + " tem: " + currentRepo.getRepositorySize() + "peças.");
        } catch (RemoteException ex) {
            System.out.println("Erro ao contar as parts do repositorio - " + ex.getMessage());
        }
    }

    public void getPart(String code) {
        try {
            currentPart = currentRepo.getPart(code);
            System.out.println("A peça buscada agora é a peça corrente.");
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar part no repositorio - " + ex.getMessage());
        }
    }

    public void addPartToRepository(String name, String description) {
        try {
            currentRepo.insertPart(name, description, currentSubcomponents);
            System.out.println("Peça inserida com sucesso.");
        } catch (RemoteException ex) {
            System.out.println("Erro ao adicionar Part ao repositorio - " + ex.getMessage());
        }
    }

    public void getCurrentPartName() {
        try {
            System.out.println("Nome da peça corrente: " + currentPart.getName() + ".");
        } catch (RemoteException ex) {
            System.out.println("Erro ao exibir o nomeda peça corrente - " + ex.getMessage());
        }
    }

    public void getCurrentPartDescription() {
        try {
            System.out.println("Descrição da peça corrente: " + currentPart.getDescription());
        } catch (RemoteException ex) {
            System.out.println("Erro ao exibir a descrição da peça corrente - " + ex.getMessage());
        }
    }

    public void countCurrentPartDirectComponents() {
        try {
            System.out.println("A peça corrente é composta por: " + currentPart.getDirectSubcomponentsSize() + "subcomponentes diretos.");
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar o número de subcomponentes da peça corrente - " + ex.getMessage());
        }
    }

    public void listCurrentPartSubComponents() {
        try {
            System.out.println(currentPart.listSubcomponents());
        } catch (RemoteException ex) {
            System.out.println("Erro ao listar subcomponents - " + ex.getMessage());
        }
    }

    public void addPartToCurrentSubComponents(Integer quantity) {
        currentSubcomponents.add(new SubcomponentsListItem(currentPart, quantity));
        System.out.println("A parte corrente foi adicionada a lista de subcomponentes corrente com sucesso.");
    }

    public void clearCurrentSubComponents() {
        currentSubcomponents.clear();
        System.out.println("A lista de subcomponentes corrente foi esvaziada com sucesso.");
    }

    public void quit() {
        System.out.println("Tchau!");
        System.exit(0);
    }

    public void listCurrentRepoParts() {
        try {
            System.out.println(currentRepo.listRepositoryParts());
        } catch (RemoteException ex) {
            System.out.println("Erro ao listar peças do repositório corrente - " + ex.getMessage());
        }
    }
}
