package Topology;
import java.util.*;
public class Topology {
    String id;
    ArrayList <Component> devices;

    public Component addComponent(Component newComp){
        devices.add(newComp);
        return newComp;
    }

    public Topology(String id){
        this.id = id;

    }

    public String getId() {
        return id;
    }
}
