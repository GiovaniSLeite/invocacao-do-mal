
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getCurrentReposize() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String listCurrentRepoParts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Part getPart(Integer code) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addPartToRepository(Part p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCurrentPartName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCurrentPartDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer countCurrentPartDirectComponents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String listCurrentPartSubComponents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addPartToCurrentSubComponents(Part p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    @Override
    public void clearCurrentSubComponents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
