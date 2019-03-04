public class Pawn extends  FigureClass {


    int[] position;


    public Pawn(String color, int[] position, int power) {
        this.position = position;
        this.color = color;
        this.power = power;
    }


    @Override
    public int doStep(Figure[][] board1, int step) {
        FigureClass[][] board = new FigureClass[8][8];
        for(int i = 0; i < board1.length;i++)
            for(int j = 0; j<board1[i].length;j++)
                board[i][j] = (FigureClass) board1[i][j];

        int x = position[1];
        int y = position[0];
        int x1 = x;
        int y1 = y;
        boolean eat = false;

        if (board[y][x]!= null && getColor().equals("white") && y>0 && board[y-1][x]!=null && this.getColor().equals(board[y-1][x].getColor()))
          return 2;

        if (board[y][x]!=null && getColor().equals("black") && y<7 && board[y+1][x]!=null && this.getColor().equals(board[y+1][x].getColor()))
          return 2;


          //Определить новые координаты

          if(board[y][x]!= null && getColor().equals("white") && y > 0) {
              if(board[y - 1][x]== null)
                  y1 = y - 1;
          }

          else if(board[y][x]!= null && getColor().equals("black") && y < 7) {
              if (board[y + 1][x]==null)
                  y1 = y + 1;
          }


          //Съесть кого-нибудь,если возможно

          if(x1 + 1 < 8 && board[y][x1 + 1]!= null && !this.getColor().equals(board[y][x1 + 1].getColor())) {
              board1[y1][x1 + 1] = this;
              position = new int[]{y1, x1 + 1};
              board1[y][x] = null;
              System.out.println("Pawn " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));

              eat = true;
              step = 1;
          } else if (x1 - 1 >= 0 &&  board[y][x1 - 1]!= null && !this.getColor().equals(board[y][x1 - 1].getColor())) {
              board1[y1][x1 - 1] = this;
              position = new int[]{y1, x1 - 1};
              board1[y][x] = null;
              System.out.println("Pawn " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
              eat = true;
              step = 1;

          }
          else if(board[y1][x1] != null && this.getColor().equals(board[y1][x1].getColor()))
          {   y1 = y;
              x1 = x;
              step = 2;
          }



        //Если нельзя съестъ, то пойти по определенным координатам

          if(!eat){
            board1[y][x] = null;
            position = new int[] {y1,x1};
            board1[y1][x1] = this;
            System.out.println("Pawn " + color + " goes: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
            step = 1;
           }

          return step;
    }


    @Override
    public String getName() {
        return "Pawn";
    }


}
