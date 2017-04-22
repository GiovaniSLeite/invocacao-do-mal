package utils;
import java.io.Serializable;
import java.rmi.RemoteException;

public class SubcomponentsListItem implements Serializable {
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
    public String toString(){
        try {
            return quantity + "x " + part.toText();
        } catch (RemoteException ex) {
            System.out.println("Erro ao imprimir peça");
            return null;
        }
    }
}
