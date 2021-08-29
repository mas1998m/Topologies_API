package Topology;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
public class Topology {
    String id;
    ArrayList <Component> devices = new ArrayList<Component>();


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

    public JSONObject topToJSON(){
        JSONObject result = new JSONObject();
        result.put("id", id);
        JSONArray comps = new JSONArray();
        for(int i=0;i<devices.size();i++){
            JSONObject comp = new JSONObject();
            if(devices.get(i) instanceof Resistance){
                comp.put("type","resistor");
                comp.put("id",devices.get(i).getId());
                comp.put("resistance",devices.get(i).getMy_param().getAll());

            }
            else if(devices.get(i) instanceof Nmos){
                comp.put("type","nmos");
                comp.put("id",devices.get(i).getId());
                comp.put("nmos",devices.get(i).getMy_param().getAll());
            }

            JSONObject net = new JSONObject();
            Iterator netlistIterator = devices.get(i).getMyNetlist().entrySet().iterator();
            while(netlistIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) netlistIterator.next();
                Node temp = (Node)mapElement.getValue();
                net.put((String)mapElement.getKey(),temp.getId());
            }
            comp.put("netlist",net);
            comps.put(comp);
        }
        result.put("components",comps);
        return result;
    }
}
