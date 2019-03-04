
import java.util.HashMap;
import java.util.Map;


public abstract class FigureClass implements  Figure{

    String name;
    int power;
    String color;


    Map<Integer,String> log_x = new HashMap<Integer,String>();
    Map<Integer,Integer> log_y = new HashMap<Integer,Integer>();
    String [] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};



    FigureClass() {
      for (int i = 7; i > -1; i--)
        log_x.put(i, letters[i]);

      for (int i = 7; i > -1; i--)
        log_y.put(i, 8-i);
   }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor(){return color;}

    public int getPower(){
        return this.power;
    }

}
