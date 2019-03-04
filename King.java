public  class King extends  FigureClass {


    int[] position;


    public King(String color, int[] position, int power) {
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

        int x = position[1], y = position[0];
        int x1 =x;
        int y1 =y;
        boolean eat = false;
        x1 = x + (int)(Math.random()*3) - 1;
        y1 = y + (int)(Math.random()*3) - 1;


    		if(x1 >=0 && x1 < 8 && y1 < 8 && y1 >=0 && board[y1][x1]!= null && !this.getColor().equals(board[y1][x1].getColor())) {
                board1[y1][x1] = this;
                position = new int[]{y1, x1};
                board1[y][x] = null;
                System.out.println("King " + color + " eats: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                eat = true;
                step = 1;
            }
            else if(x1 >=0 && x1 < 8 && y1 < 8 && y1 >=0  && board[y1][x1] != null && this.getColor().equals(board[y1][x1].getColor()))
            {   y1 = y;
                x1 = x;

                step = 2;

            }


            if(y1 < 0)
                y1 += 1;
            if(y1 > 7)
                y1 -= 1;
            if(x1 < 0)
                x1 += 1;
            if(x1 > 7)
                x1 -= 1;
            if(!eat && x1 != x && y1 != y) {
                board1[y][x] = null;
                position = new int[]{y1, x1};
                board1[y1][x1] = this;
                System.out.println("King " + color + " goes: " +  log_x.get(x) + log_y.get(y) + " -> " + log_x.get(x1) + log_y.get(y1));
                step = 1;
            }
            return  step;
    }

    @Override
    public String getName() {
        return "King";
    }


}
