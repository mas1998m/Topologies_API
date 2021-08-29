package Topology;


import java.util.*;

public class Nmos extends Component {
    public Nmos(String id, Parameter my_param, HashMap<String,Node> netlist){
        this.id = id;
        this.my_param = my_param;
        this.netlist = netlist;
        updateNodes();
    }

    public Nmos(String id, double def, double min, double max,  HashMap<String,Node> netlist){
        this.id = id;
        this.my_param = new Parameter(def,min,max);
        this.netlist = netlist;
        updateNodes();
    }

    public Nmos(String id, double def, double min, double max, Node drain, Node gate,Node source){
        this.id = id;
        my_param = new Parameter(def,min,max);
        netlist = new HashMap<String,Node>();
        netlist.put("drain",drain);
        drain.addComponent();
        netlist.put("gate",gate);
        gate.addComponent();
        netlist.put("source",source);
        source.addComponent();
    }
    public String printInfo(){
        String info;
        String nodes = new String("");
        double def = my_param.getMy_def();
        double min = my_param.getMin();
        double max = my_param.getMax();
        Iterator netlistIterator = netlist.entrySet().iterator();
        while(netlistIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) netlistIterator.next();
            Node temp = (Node)mapElement.getValue();
            nodes+= new String(mapElement.getKey()+":"+temp.getId()+"\n");
        }
        info = new String("my id is:"+id+"\nmy type is: nmos\ndefault="+def+" min="+min+" max="+max);
        nodes = new String("\nnodes:\n")+nodes;
        info+=nodes;
        return info;
    }


}
