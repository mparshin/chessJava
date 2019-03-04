import java.util.Arrays;

public class Horse extends  FigureClass {


    int[] position;
	  int[] positionForAllOption = new int[8];


    public Horse(String color, int[] position, int power) {
        this.position = position;
        this.color = color;
        this.power = power;
    }


    @Override
    public String getName() {
        return "Horse";
    }


    @Override
    public int doStep(Figure[][] board1, int step) {
      FigureClass[][] board = new FigureClass[8][8];
      for(int i = 0; i < board1.length;i++)
          for(int j = 0; j<board1[i].length;j++)
              board[i][j] = (FigureClass) board1[i][j];
      int x = position[1], y = position[0];
      int x1, y1;


          int numberX = (int)(Math.random()*5) - 2;
          int numberY = 0;
          if(numberX == 1 || numberX == -1)
              numberY = 2;
          if(numberX == 2 || numberX == -2)
              numberY = 1;
          if(numberX != 0) {
              if ((int) (Math.random() * 2) == 0)
                  x1 = x + numberX;
              else
                  x1 = x - numberX;

              if ((int)(Math.random() * 2) ==0)
                  y1 = y + numberY;
              else
                  y1 = y - numberY;

              if (x1 > 7 || y1 > 7 || x1 < 0 || y1 < 0){
                  x1 = x;
                  y1 = y;
              }


              if(board[y1][x1] == null || !this.getColor().equals(board[y1][x1].getColor())) {
                  if(board[y1][x1]!= null)
                      System.out.println("Horse " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                  board1[y][x] = null;
                  position = new int[]{y1, x1};
                  board1[y1][x1] = this;
                  System.out.println("Horse " + color + " goes: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                  step = 1;
              }
          }

    	return step;
	}





}
