package utils;



public class SubcomponentsListItem {
    private Part part;
    private Integer quantity;

    public SubcomponentsListItem(Part part, Integer quantity) {
        this.part = part;
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return quantity + "x " + part;
    }
}
