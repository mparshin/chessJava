public class Rook extends  FigureClass {


    int[] position;


    public Rook(String color, int[] position, int power) {
        this.position = position;
        this.color = color;
        this.power = power;
    }


    @Override
    public int  doStep(Figure[][] board1, int step) {
        FigureClass[][] board = new FigureClass[8][8];
        for(int i = 0; i < board1.length;i++)
            for(int j = 0; j<board1[i].length;j++)
                board[i][j] = (FigureClass) board1[i][j];

        int x = position[1], y = position[0];
        int x1 = x;
        int y1 = y;
        int x2 = x1;
        int y2 = y1;
        boolean signX = getSign();
        boolean signY = getSign();
        int number = (int)(Math.random() * 15) - 7;
        boolean eat = false;


		    for(int i =0; i < Math.abs(number);i++){
            if(!eat) {
                x2 = x1; // чтобы можно было восстановить предыдущие координаты (не начальные!)
                y2 = y1;
                if((int) (Math.random() * 2) == 0) { // по какой будем двигаться
                    if (signX)
                        x1 += 1;
                    else
                        x1 -= 1;
                }
                else {
                    if (signY)
                        y1 += 1;
                    else
                        y1 -= 1;
                }
                if (x1 < 8 && x1 >= 0 && y1 < 8 && y1 >= 0 && board[y1][x1] != null)
                    if(!this.getColor().equals(board[y1][x1].color)) {
                        board1[y][x] = null;
                        position = new int[]{y1, x1};
                        board1[y1][x1] = this;

                        System.out.println("Rook " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                        eat = true;
                        step = 1;
                    }
                    else if(this.color.equals(board[y1][x1].getColor())){
                        x1 = x2;
                        y1 = y2;
                        if(x1 != x && y1 != y)
                            step = 1;
						else step = 2;
                    }
            }
        }

        //проверка на выход за пределы поля
        if(x1 > 7)
            do{
                x1 -= 1;
            }while(x1 > 7);
        if(x1 < 0)
            do{
                x1 += 1;
            }while(x1 < 0);
        if(y1 > 7)
            do{
                y1 -= 1;
            }while(y1 > 7);
        if(y1 < 0)
            do{
                y1 += 1;
            }while(y1 < 0);
        if(!eat) {
            board1[y][x] = null;
            position = new int[]{y1, x1};
            board1[y1][x1] = this;
            //System.out.println("Rook " + color + " goes from " + y + ":" + x + " to " + y1 + ":" + x1);
            System.out.println("Rook " + color + " goes: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
            step = 1;
        }
        return step;
    }


    @Override
    public String getName() {
        return "Rook";
    }


    private boolean getSign(){
        //в какую сторону перемещать
        if ((int) (Math.random() * 2) == 0)
            return true;
        else
            return false;

    }

}
