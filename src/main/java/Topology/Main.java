package Topology;

public class Main {

    public static void main(String[] args) {
        Node t1 = new Node("point1");
        Node t2 = new Node("point2");
        Node t3 = new Node("point3");

        Nmos myres =new Nmos("res1",50,10,100,t1,t2,t3);
        System.out.println(myres.printInfo());
        System.out.println(t1.getConnectedComponents());


        // write your code here
    }
}
