package Topology;

import java.util.*;

abstract public class Component {
    String id;
    Parameter my_param;
    HashMap<String,Node> netlist;

    protected void updateNodes(){
        Iterator netlistIterator = netlist.entrySet().iterator();
        while(netlistIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) netlistIterator.next();
            Node temp = (Node)mapElement.getValue();
            temp.addComponent();
        }
    }

    public String getId(){
        return id;
    }

    public Parameter getMy_param(){
        return my_param;
    }


    public  HashMap<String,Node> getMyNetlist(){
        return netlist;
    }

    abstract public String printInfo();


}
