import java.util.ArrayList;
import java.util.List;

public class  Main {

    public static void main(String[] args) {

        List<Integer> countSteps = new ArrayList<>();
        Board board = new Board(); //создается доска
        boolean countFiguresWhite = false; //присутствие фигур на доске
        boolean countFiguresBlack = false;

        // для нормального логгирования комманд
        String [] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};


        //главный цикл программы

        for(int i = 0; i<100000; i++) { //100000 ходов

            for (int k = 0; k < board.figures.length; k++) { //есть ли фигуры обеих цветов на доске
                for (int j = 0; j < board.figures[k].length; j++) {
                    if (board.figures[k][j] != null && board.figures[k][j].getColor().equals("white"))
                        countFiguresWhite = true;
                        countFiguresBlack = true;
                }
            }

            if (countFiguresBlack && countFiguresWhite) {
                step(i, board, countSteps); //делаем шаг
                countFiguresBlack = false; //обнуляем, вдруг съедена последжняя фигура
                countFiguresWhite = false;
            } else {
                System.out.println("Game over");
                break;

            }
        }


        // Вывод доски

        for(int i =0; i< board.figures.length;i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j< board.figures[i].length; j++)

              if(board.figures[i][j] != null)
                  System.out.print(board.figures[i][j].getName() + " " +  board.figures[i][j].getColor() + " ");
              else
                  System.out.print(" 0 ");

            System.out.println();
          }
        System.out.print("   ");
        for(int i =0; i< board.figures.length;i++)
            System.out.print(letters[i] + "  ");

    }



    private static void step(int i, Board board, List<Integer> countSteps) { //доделать логику проверки на все возможные  варианты
        int[] point; //массив точек
        int step = 0;
        boolean countFiguresWhite = false;
        boolean countFiguresBlack = false;
        point = getPoint(); //наполнение числами

        if (board.figures[point[0]][point[1]] != null) // проверка фигуры в конкретной точке

            while (step == 0) {

                if (i == 0 && board.figures[point[0]][point[1]].getColor().equals("black")) //проверка первого хода белыми
                    break;


                if (board.figures[point[0]][point[1]] != null && i % 2 == 0 && board.figures[point[0]][point[1]].getColor().equals("white")) { //если четный ход и фигура белая, то заходим в цикл

                    if (countSteps.isEmpty() || countSteps.get(countSteps.size() - 1) == 1) {  // если массив пуст или последний элемент = 1
                        step = board.figures[point[0]][point[1]].doStep(board.figures, step);

                        if (step != 0 && step != 2)
                            countSteps.add(0);

                    } else step = 3;

                }


                else if (board.figures[point[0]][point[1]] != null && i % 2 == 1 && board.figures[point[0]][point[1]].getColor().equals("black")) {  //если нечетный ход и фигура черная, то заходим в цикл
                    if (!countSteps.isEmpty() && countSteps.get(countSteps.size() - 1) == 0) {
                        step = board.figures[point[0]][point[1]].doStep(board.figures, step);
                        if (step != 0 && step != 2)
                            countSteps.add(1);
                    } else step = 3;
                }


                else step = 3; // фигура не подходит по цвету
                int gameOver = 0;
            }
    }



    private static int[] getPoint(){ //получение координаты точки
      int max = 8;
      return new int[] { (int)(Math.random()*max), (int)(Math.random()* max) };
    }




}
