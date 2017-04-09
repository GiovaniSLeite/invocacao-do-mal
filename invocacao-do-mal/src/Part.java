
import java.util.List;

public class Part {
    static Integer nextcode = 0;
    private final Integer code;
    private final String name, description;
    private final List<SubComponentItem> subComponents;

    public Part(String name, String description, List<SubComponentItem> subComponents) {
        this.code = nextcode++;
        this.name = name;
        this.description = description;
        this.subComponents = subComponents;
    }
    
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public List<SubComponentItem> getSubComponents() {
        return subComponents;
    }
    
    public boolean isPrimitive() {
        return subComponents.isEmpty();
    }
    
}
