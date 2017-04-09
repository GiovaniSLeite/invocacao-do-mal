
/*
    Interface desnecessaria
    Fiz para pontuar os métodos que eu entendi que um cliente deve poder exercer
*/

public interface ClientInterface {
    void connectTo(String serverName); /*bind*/
    String getCurrentRepoName();
    Integer getCurrentReposize();
    String listCurrentRepoParts(); /*listp*/
    Part getPart(Integer code); /*getp*/
    void addPartToRepository(Part p); /*addp*/
    String getCurrentPartName(); /*showp*/
    String getCurrentPartDescription(); /*showp*/
    Integer countCurrentPartDirectComponents(); /*showp*/
    String listCurrentPartSubComponents(); /*showp*/
    void addPartToCurrentSubComponents(Part p); /*addsubpart*/
    void clearCurrentSubComponents(); /*clearlist*/
    void quit(); /*quit*/
}
