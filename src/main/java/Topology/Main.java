package Topology;
import org.json.*;
import API.*;

public class Main {

    public static void main(String[] args) {
        Node t1 = new Node("point1");
        Node t2 = new Node("point2");
        Node t3 = new Node("point3");

        Nmos myMos =new Nmos("mos1",50,10,100,t1,t2,t3);
        Resistance myRes = new Resistance("res1",1.1f, 0.5f,3,t2,t3);
        Topology mytop = new Topology("top1");
        mytop.addComponent(myMos);
        mytop.addComponent(myRes);


//        API.addTop(mytop);
//        API.writeJSON("top1");

        JSONObject jo = mytop.topToJSON();
        try{
            API.readJSON("top1.json");

        }
        catch (Exception e){

            System.out.println(e);
        }
        API.writeJSON("Readtop");

//        System.out.        System.out.println(myres.printInfo());
//        println(t1.getConnectedComponents());
//        System.out.println(jo.toString());




        // write your code here
    }
}
