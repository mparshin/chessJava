public class Board {

    FigureClass [][] figures = createBoard();


    private  FigureClass[][] createBoard(){

        FigureClass[][] boardFigure = new FigureClass[8][8];

        int[][][] board = {
                            {{4, 0}, {3, 1}, {5, 0}, {6, 1}, {7, 0}, {5, 1}, {3, 0}, {4, 1}}, //0,0
                            {{2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}}, //1,0

                            {{0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}}, //2,0
                            {{0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}}, //3,0
                            {{0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}}, //4,0
                            {{0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}, {0, 1}, {0, 0}}, //5,0

                            {{2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}, {2, 0}, {2, 1}}, //6,0
                            {{4, 1}, {3, 0}, {5, 1}, {6, 0}, {7, 1}, {5, 0}, {3, 1}, {4, 0}}  //7,0
                          };

        for(int i = 0; i < boardFigure.length; i++)
            for(int j = 0; j < boardFigure[i].length; j++){

                String color;
                if (i < 2) color = "black";
                else color = "white";

                if (board[i][j][0] != 0) {
                    boardFigure[i][j] = createFigure1(board[i][j][0], color, new int[]{i, j});
                    boardFigure[i][j].setColor(color);
                }
                else
                    boardFigure[i][j] = null;
            }

        return boardFigure;
    }


    private FigureClass createFigure1(int i, String color, int[] position){
        if(i == 0)
            return  null;
        if(i == 2)
            return new Pawn(color, position, 2);
        if(i == 3)
            return new Horse(color,position,3);
        if(i == 4)
            return new Rook(color, position,4);
        if(i == 5)
            return new Elephant(color,position,5);
        if(i == 6)
            return new Queen(color,position,6);
        if(i == 7)
            return new King(color,position,7);
        return null;
    }


    //ладья - любое количество по вертикали и горизонтали
    //ферзь - любое количество куда угодно
    //слон - любое количество по диагонали
    //конь - ходит буквой Г. может перепрыгивать свои и чужие фигуры
    //король - одно поле в любом направлении, если еще ни разу не двигался. не может напасть на ферзя
    //пешка - только одна клетка вперед по вертикали, но берут по диагонали (чужая пешка стоит справа или слева от текущей)



}
