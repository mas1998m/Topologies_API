package Topology;
import java.util.*;

public class Parameter {

    private double my_def;
    private double min;
    private double max;
    public Parameter(double def,double min,double max){
        my_def = def;
        this.min = min;
        this.max = max;
    }
    public Parameter(){
        this(50,1,100);
    }
    public double getMy_def(){
        return my_def;
    }
    public double getMin(){
        return min;
    }
    public double getMax(){
        return max;
    }
    public HashMap<String, Double> getAll(){
        HashMap <String,Double> all = new HashMap<String,Double>();
        all.put("default",my_def);
        all.put("min",min);
        all.put("max",max);
        return all;
    }

    public void setMax(double max) {
        this.max = max;
    }
    public void setMin(double min) {
        this.min = min;
    }
    public void setMy_def(double my_def) {
        this.my_def = my_def;
    }
    public void setAll(double def,double min,double max){
        this.max = max;
        this.min = min;
        this.my_def = def;
    }

}
