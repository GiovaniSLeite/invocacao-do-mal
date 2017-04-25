package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import utils.Part;
import utils.PartRepository;
import utils.SubcomponentsList;
import utils.SubcomponentsListItem;

public class Client {
    PartRepository currentRepo;
    Part currentPart;
    SubcomponentsList currentSubcomponents;
    
	public static void main(String[] args) throws RemoteException {
		Client client = new Client();
		Scanner sc = new Scanner(System.in);
		/*
		 * - estabeleça uma conexão com um (processo) servidor;
		 */
		int mainMenuChoice = 0;
		while (mainMenuChoice != 3) {
			System.out.println("Menu \n" + " 1 - Conectar a um servidor: \n"
		+ " 2 - Listar servidores disponíveis\n"
		+ " 3 - Sair");
			try {
				mainMenuChoice = Integer.parseInt(sc.next());
			} catch (NumberFormatException ex) {
				System.out.println("Entrada inválida");
				mainMenuChoice = 0;
			}

			if (mainMenuChoice == 1) {
				System.out.println("Digite o nome do servidor para se conectar: ");
				String serverName = sc.next();
				if (client.connectTo(serverName)) {
					/*
					 * --- se a conexão foi bem sucedida ---
					 * 
					 * - interaja com o repositório implementado pelo servidor:
					 * – examinando o nome do repositório e o numero de peças
					 * nele contidas, – listando as peças no repositório, –
					 * buscando uma peça (por código de peça) no repositório, –
					 * adicionando ao repositório novas peças (primitivas ou
					 * agregadas);
					 */

					int repoMenuChoice = 0;
					while (repoMenuChoice != 6) {
						System.out.println();
						System.out.println("Repositório: \n"
								+ "1 - Informações\n" 
								+ "Peças: " 
								+ "\t2 - Adicionar\n"
								+ "\t3 - Buscar por código\n" 
								+ "\t4 - Listar\n" 
								+ "5 - Adicionar à lista de subpeças\n"
								+ "6 - Voltar");
						
						repoMenuChoice = sc.nextInt();
						client.repositoryInteraction(repoMenuChoice, sc);
						/*
						 * Depois de selecionar uma peça
						 * 
						 * – examinando o nome e a descrição da peça, 
						 * – obtendo o (nome do) repositório que a contém, 
						 * – verificando se a peça é primitiva ou agregada, 
						 * – obtendo o número de subcomponentes diretos e primitivos da peça, 
						 * – listando suas subpeças.
						 */

						if ((repoMenuChoice == 3)&&(client.currentPart != null)) {
							
							int partMenuChoice = 0;
							while(partMenuChoice!=6){
								System.out.println("Peça: \n" 
										+ "1 - Informações \n" 
										+ "2 - Repositório \n"
										+ "3 - Primitiva\n" 
										+ "Subcomponentes\n" 
										+ "\t4 - Numero\n" 
										+ "\t5 - Listar\n"
										+ "6 - Voltar");
								partMenuChoice = sc.nextInt();
								client.partInteraction(partMenuChoice, sc);
							}
						}
					}
				}
			}else if(mainMenuChoice == 2){
				for(String repository : PartRepository.listCurrentRepos()){
					System.out.println(repository);
				}
					
			}
		}

		sc.close();
	}

    public Client() {
        currentSubcomponents = new SubcomponentsList();
    }

	public Boolean connectTo(String serverName) {
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			currentRepo = (PartRepository) registry.lookup(serverName);
			System.out.println("Conectado com sucesso ao repositorio " + serverName + ".");
			return true;
		} catch (Exception ex) {
			System.out.println("Erro ao conectar ao servidor - " + ex.getMessage());
			return false;
		}
	}

    /* INTERACAO COM O SERVIDOR */
	void repositoryInteraction(int action, Scanner sc) {
		switch (action) {
		case 1:
			getCurrentRepoName();
			getCurrentRepoSize();
			break;
		case 2:
			this.addPart(sc);
			break;
		case 3:
			getPartByCode(sc);
			break;
		case 4:
			listCurrentRepoParts();
			break;
		case 5:
			System.out.println("Quantidade: ");
			addPartToCurrentSubComponents(sc.nextInt());
		default:
			return;
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
            int size = currentRepo.getRepositorySize();
            String p = size == 1 ? " peça." : " peças.";
            System.out.println("O repositorio " + currentRepo.getName() + " tem: " + size + p);
        } catch (RemoteException ex) {
            System.out.println("Erro ao contar as parts do repositorio - " + ex.getMessage());
        }
    }

    public void listCurrentRepoParts() {
        try {
            System.out.println(currentRepo.listRepositoryParts());
        } catch (RemoteException ex) {
            System.out.println("Erro ao listar peças do repositório corrente - " + ex.getMessage());
        }
    }
	
    public void addPart(Scanner sc) {
		sc.nextLine();
    	System.out.println("Digite as informações da peça: \n Nome:");
		String nome = sc.nextLine();
		System.out.println("Descrição: ");
		String desc = sc.nextLine();
		addPartToRepository(nome, desc);
	}
    
	public void getPartByCode(Scanner sc) {
		System.out.println("Digite o código da peça que deseja selecionar: ");
		getPart(sc.next());
	}
	
    public void getPart(String code) {
        try {
            currentPart = null;
            currentPart = currentRepo.getPart(code);
            if(currentPart != null)
                System.out.println("A peça buscada agora é a peça corrente.");
            else
                System.out.println("A peça procurada não foi encontrada");
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

    /* INTERACAO COM a PECA */

	void partInteraction(int action, Scanner sc) {
		switch (action) {
		case 1:
			getCurrentPartName();
			getCurrentPartDescription();
			break;
		case 2:
			getCurrentPartOrigin();
			break;
		case 3:
			currentPartIsPrimitive();
			break;
		case 4:
			countCurrentPartDirectComponents();
		case 5:
			listCurrentPartSubComponents();
		default:
			break;
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

    public void getCurrentPartOrigin() {
        try {
            System.out.println("A peça corrente pertence ao repositório: " + currentPart.getOrigin());
        } catch (RemoteException ex) {
            System.out.println("Erro ao buscar a origem da peça corrente - " + ex.getMessage());
        }
    }

    public void currentPartIsPrimitive() {
        try {
            String verbo = currentPart.isPrimitive() ? "é" : "não é";
            System.out.println("A peça corrente " + verbo + " primitiva.");
        } catch (RemoteException ex) {
            System.out.println("Erro ao verificar se a peça corrente é primitiva - " + ex.getMessage());
        }
    }

    /* INTERACAO COM A LISTA DE SUBCOMPONENTES */
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
    
    

}