package utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class SubcomponentsList implements Serializable{
    Collection<SubcomponentsListItem> list;

    public SubcomponentsList() {
        list = new ArrayList();
    }

    @Override
    public String toString() {
        StringBuilder listing = new StringBuilder();
        for(SubcomponentsListItem obj : list)
            listing.append(obj).append("\n");
        return listing.toString();
    }

    public void add(SubcomponentsListItem p){
        list.add(p);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void addAll(SubcomponentsList p){
        list.addAll(p.list);
    }
}
