
/*
    Interface desnecessaria
    Fiz para pontuar os métodos que eu entendi que um cliente deve poder exercer
*/

public interface ClientInterface {
    void connectTo(String serverName); /*bind*/
    String getCurrentRepoName();
    Integer getCurrentReposize();
    String listCurrentRepoParts(); /*listp*/
    void getPart(Integer code); /*getp*/
    void addPartToRepository(String name, String description); /*addp*/
    String getCurrentPartName(); /*showp*/
    String getCurrentPartDescription(); /*showp*/
    Integer countCurrentPartDirectComponents(); /*showp*/
    String listCurrentPartSubComponents(); /*showp*/
    void addPartToCurrentSubComponents(Integer quantity); /*addsubpart*/
    void clearCurrentSubComponents(); /*clearlist*/
    void quit(); /*quit*/
}
