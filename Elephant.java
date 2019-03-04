public class Elephant extends  FigureClass{


    int[] position;


    public Elephant(String color, int [] position, int power){
      this.position = position;
      this.color = color;
      this.power = power;
    }


    @Override
    public String getName() {
        return "Elephant";
    }


    @Override
    public int doStep(Figure[][] board1, int step) {
        FigureClass[][] board = getFigureClass(board1);

        int x = position[1];
        int y = position[0];
        int x1 =x;
        int y1 =y;
        int x2;
        int y2;
        boolean signX = getSign();
        boolean signY = getSign();
        boolean eat = false;
        int number = (int)((Math.random() * 15) - 7); //на сколько переместить
        for ( int i = 0;i < Math.abs(number);i++) {
            if (!eat) {
                x2 = x1;
                y2 = y1;
                x1 = doStep(signX,x1);
                y1 = doStep(signY,y1);
                if (x1 < 8 && x1 >= 0 && y1 < 8 && y1 >= 0 && board[y1][x1] != null)
                    if(!this.getColor().equals(board[y1][x1].getColor())) { //проеверка на другой свет фигуры для съедения
                        System.out.println("Elephant " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                        board1[y][x] = null;
                        position = new int[]{y1, x1};
                        board1[y1][x1] = this;
                        eat = true;
                        step = 1;
                    }

                    else if(this.getColor().equals(board[y1][x1].getColor())){
                        x1 = x2;
                        y1 = y2;

                        if(x1 != x && y1 != y)
                            step = 1;
                    }

            }
        }

        int [] newPosition = defineBoundaries(y1,y,x1,x);
        y1=newPosition[0];
        x1 = newPosition[1];


        if(!eat) {
            board1[y][x] = null;
            position = new int[]{y1, x1};
            board1[y1][x1] = this;
            step = 1;
            System.out.println("Elephant " + color + " goes: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
        }
        return step;

    }


    // расчитывается траектория хода

    private FigureClass[][] getFigureClass (Figure[][] board1){
        FigureClass[][] newBoard = new FigureClass[8][8];
        for(int i = 0; i < board1.length;i++)
            for(int j = 0; j<board1[i].length;j++)
                newBoard[i][j] = (FigureClass) board1[i][j];
        return newBoard;
    }


    private boolean getSign(){
      //выбор направления хода (в какую сторону перемещать)
      if ((int) (Math.random()*2) ==0)
        return true;
        else
        return false;
    }


    private int doStep(boolean sign, int number){
  if (sign)
      number +=1;
    else
      number -=1;
    return number;
}


    private int[] defineBoundaries(int y1, int y, int x1, int x) {
        int[] newProblem = {y1,x1};
        if (y1 > 7 && x1 < 0) {
            do {
                y1 -= 1;
                x1 += 1;
            } while (y1 > 7 || x1 < 0);
        }
        if (y1 > 7 && x1 > 7) {
            do {
                y1 -= 1;
                x1 -= 1;
            } while (y1 > 7 || x1 > 7);

        }
        if (y1 < 0 && x1 > 7) {
            do {
                y1 += 1;
                x1 -= 1;
            } while (y1 < 0 || x1 > 7);
        }
        if (y1 < 0 && x1 < 0) {
            do {
                y1 += 1;
                x1 += 1;
            } while (y1 < 0 || x1 < 0);
        }

        if (x1 >= 0 && x1 < 8 && y1 < 0) {
            newProblem = lessZero(x1, y1, x);
            y1 = newProblem[1];
            x1 = newProblem[0];
        }
        if (x1 > 7 && y1 >= 0 && y1 < 8) {
            newProblem = moreSeven(y1, x1, y); //возвращаемое - 1-нормальное, 2-проблемное
            y1 = newProblem[0];
            x1 = newProblem[1];
        }
        if (x1 < 8 && x1 >= 0 && y1 > 7) {
            newProblem = moreSeven(x1, y1, x); //возвращаемое - 1-нормальное, 2-проблемное
            y1 = newProblem[1];
            x1 = newProblem[0];
        }
        if (y1 >= 0 && y1 < 8 && x1 < 0) {
            newProblem = lessZero(y1, x1, y);
            y1 = newProblem[0];
            x1 = newProblem[1];
        }

        return new int[]{y1,x1};
    }


    private int[] lessZero(int norm, int problemate, int number){
        do {
            if (norm < number)
                norm += 1;
            else
                norm -= 1;
            problemate += 1;
        }
        while (problemate < 0);
        return new int[]{norm,problemate};
    }


    private int[] moreSeven(int norm, int problemate, int number){
        do {
            if (norm < number)
                norm += 1;
            else
                norm -= 1;
            problemate -= 1;
        } while (problemate > 7);
        return new int[]{norm,problemate};
    }
}
