package Topology;

public class Node {
    private String id;
    private int connectedComponents=0;
    public Node(String id){
        this.id=id;
    }
    public int getConnectedComponents() {
        return connectedComponents;
    }

        public int addComponent(int num){
        assert num>0;
        connectedComponents+=num;
        return connectedComponents;
    }

    public int addComponent(){
        this.connectedComponents+=1;
        return connectedComponents;
    }

    public int removeComponent(int num){
        assert num>0;
        connectedComponents-=num;
        return connectedComponents;
    }

    public int removeComponent(){
        connectedComponents-=1;
        return connectedComponents;
    }

    public String getId(){
        return id;
    }
}
