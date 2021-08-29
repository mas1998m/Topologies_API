package Topology;
import java.util.*;

public class Parameter {

    private float my_def;
    private float min;
    private float max;
    public Parameter(float def,float min,float max){
        my_def = def;
        this.min = min;
        this.max = max;
    }
    public Parameter(){
        this(50,1,100);
    }
    public float getMy_def(){
        return my_def;
    }
    public float getMin(){
        return min;
    }
    public float getMax(){
        return max;
    }
    public Map<String, Float> getAll(){
        HashMap <String,Float> all = new HashMap<String,Float>();
        all.put("default",my_def);
        all.put("min",min);
        all.put("max",max);
        return all;
    }

    public void setMax(float max) {
        this.max = max;
    }
    public void setMin(float min) {
        this.min = min;
    }
    public void setMy_def(float my_def) {
        this.my_def = my_def;
    }
    public void setAll(float def,float min,float max){
        this.max = max;
        this.min = min;
        this.my_def = def;
    }

}
