package Topology;


import java.util.*;

public class Resistance extends Component {
    public Resistance(String id, Parameter my_param, HashMap<String,Node> netlist){
        this.id = id;
        this.my_param = my_param;
        this.netlist = netlist;
        updateNodes();
    }

    public Resistance(String id, float def, float min, float max,  HashMap<String,Node> netlist) {
        this.id = id;
        this.my_param = new Parameter(def, min, max);
        this.netlist = netlist;
        updateNodes();
    }

    public Resistance(String id, float def, float min, float max, Node t1, Node t2){
        this.id = id;
        my_param = new Parameter(def,min,max);
        netlist = new HashMap<String,Node>();
        netlist.put("t1",t1);
        t1.addComponent();
        netlist.put("t2",t2);
        t2.addComponent();
    }

    public String printInfo(){
        String info;
        String nodes = new String("");
        float def = my_param.getMy_def();
        float min = my_param.getMin();
        float max = my_param.getMax();
        Iterator netlistIterator = netlist.entrySet().iterator();
        while(netlistIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) netlistIterator.next();
            Node temp = (Node)mapElement.getValue();
            nodes+= new String(mapElement.getKey()+":"+temp.getId()+"\n");
        }
        info = new String("my id is:"+id+"\nmy type is: resistance\ndefault="+def+" min="+min+" max="+max);
        nodes = new String("\nnodes:\n")+nodes;
        info+=nodes;
        return info;
    }





}
