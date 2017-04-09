
public class SubComponentItem {
    private Part subpart;
    private Integer quantity;

    public SubComponentItem(Part subpart, Integer quantity) {
        this.subpart = subpart;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Part getSubpart() {
        return subpart;
    }
}
