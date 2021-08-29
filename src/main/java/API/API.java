package API;
import Topology.*;
import org.json.*;
import java.util.*;
import java.io.*;  // Import the File class
import java.nio.file.*;

/**
 A class used to deal with Topolgoies (reading & writing from,to  files & memory)
 */
public class API {
    static ArrayList<Topology> memory = new ArrayList<Topology>();

    /**
     adds a topology to the memory.
     @param top the handle of the Topology to be loaded
     */
    static public void addTop(Topology top){
        memory.add(top);
    }

    /**
     creates a Topology object from the json file provided and adds it to the memory
     @param fileName the filename of the json file to be loaded
     @return a topology created from the file
     */
    static public Topology readJSON(String fileName)throws Exception{
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        JSONObject loaded = new JSONObject(data);

        Topology currentTop =   new Topology((String)loaded.get("id"));
        JSONArray devicesData = (JSONArray)loaded.get("components");
        for(int i=0;i<devicesData.length();i++){
            JSONObject currentDeviceData = (JSONObject)devicesData.get(i);
            String type = (String)currentDeviceData.get("type");
            String id = (String)currentDeviceData.get("id");
            if(type.equals("resistor")){
                double def = (double)((Integer)((JSONObject)currentDeviceData.get("resistance")).get("default"));
                double min = (double)((Integer)((JSONObject)currentDeviceData.get("resistance")).get("min"));
                double max = (double)((Integer)((JSONObject)currentDeviceData.get("resistance")).get("max"));
                String t1Id = (String)((JSONObject)currentDeviceData.get("netlist")).get("t1");
                String t2Id = (String)((JSONObject)currentDeviceData.get("netlist")).get("t2");
                Node t1 = new Node(t1Id);
                Node t2 = new Node(t2Id);
                currentTop.addComponent(new Resistance(id,def,min,max,t1,t2));
            }
            else if(type.equals("nmos")){
                double def = (double)((Integer)((JSONObject)currentDeviceData.get("nmos")).get("default"));
                double min = (double)((Integer)((JSONObject)currentDeviceData.get("nmos")).get("min"));
                double max = (double)((Integer)((JSONObject)currentDeviceData.get("nmos")).get("max"));
                String drainId = (String)((JSONObject)currentDeviceData.get("netlist")).get("drain");
                String sourceId = (String)((JSONObject)currentDeviceData.get("netlist")).get("source");
                String gateId = (String)((JSONObject)currentDeviceData.get("netlist")).get("gate");
                Node drain = new Node(drainId);
                Node source = new Node(sourceId);
                Node gate = new Node(gateId);
                currentTop.addComponent(new Nmos(id,def,min,max,drain,gate,source));
            }
        }
        memory.add(currentTop);
        return currentTop;
    }


    /**
     converts a topolgy from the memory into a json file
     @param id the id of the topology to be converted
     */
    static public void writeJSON(String id){
        JSONObject json = null;
        for(int i=0;i<memory.size();i++){
            if(memory.get(i).getId().equals(id)){
                json = memory.get(i).topToJSON();
            }
        }
        if( json !=null){
            try {
                File myObj = new File(id+".json");
                myObj.createNewFile();
                FileWriter myWriter = new FileWriter(id+".json");
                json.write(myWriter);
                myWriter.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }


    /**
     to get topologes from memroy
     @return ArrayList of Topologies loaded in memory
     */
    static public ArrayList<Topology> queryTopologies(){
        return memory;
    }

    /**
     deletes the topology with the given id
     @param id the id of the topology to be deleted
     @return the deleted topology
     */
    static public Topology deleteTopology(String id){
        for(int i=0;i<memory.size();i++){
            if(memory.get(i).getId().equals(id)){
                return memory.remove(i);
            }
        }
        return null;
    }

    /**
     gets the list of devices used in a certain topology
     @param id the id of the desired topology
     @return ArrayList of  devices used in this topology
     */
    static public ArrayList<Component> queryDevices(String id){
        for (Topology topology : memory) {
            if (topology.getId().equals(id)) {
                return topology.getDevices();
            }
        }
        return null;
    }

    /**
     gets the list of devices connected to a certain node within given topology
     @param id the id of the topology
     @param nodeId the id of node
     @return ArrayList of devices connected to this node in this topology
     */
    static public ArrayList<Component> queryDevicesWithNetListNode(String id,String nodeId){
        ArrayList<Component> result = new ArrayList<Component>();
        for (Topology topology : memory) {
            if (topology.getId().equals(id)) {
                for(Component comp : topology.getDevices()){

                    Iterator netlistIterator = comp.getMyNetlist().entrySet().iterator();
                    while(netlistIterator.hasNext()) {
                        Map.Entry mapElement = (Map.Entry) netlistIterator.next();
                        Node temp = (Node)mapElement.getValue();
                        if(temp.getId().equals(nodeId)){
                            result.add(comp);
                            break;
                        }
                    }
                }
                return topology.getDevices();
            }
        }
        return null;
    }

}
