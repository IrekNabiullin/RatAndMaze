public class RatMaze {
    final int N = 4;

    /* Утилита для печати матрицы решения sol [N][N]*/

    void printSolution(int sol[][]) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* Утилита для проверки правильности значений x, y индекс для N * N лабиринт */
    boolean isSafe(int maze[][], int x, int y) {

        // if (x, y вне лабиринта) возвращает false
        return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
    }

    /* Эта функция решает проблему лабиринта,используя Откат.Он в основном использует solveMazeUtil()
    решить проблему.Возвращает false если нет путь возможен, в противном случае верните true и печатает путь
    в виде 1с.пожалуйста, обратите внимание что может быть более одного решения, это Функция печатает
    одно из возможных решений.*/

    boolean solveMaze(int maze[][]) {

        int sol[][] = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };


        if (solveMazeUtil(maze, 0, 0, sol) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol);
        return true;
    }


    // * Рекурсивная функция полезности для решения Maze проблема */
    boolean solveMazeUtil(int maze[][], int x, int y,
                          int sol[][]) {

        // если (x, y - цель), верните true
        if (x == N - 1 && y == N - 1) {
            sol[x][y] = 1;
            return true;
        }


        // Проверяем, действительно ли maze [x] [y]
        if (isSafe(maze, x, y) == true) {

            // помечаем x, y как часть пути решения
            sol[x][y] = 1;

            // *Двигаться вперед в направлении х * /

            if (solveMazeUtil(maze, x + 1, y, sol))
                return true;

            // *Если движение в направлении х не дает решение затем двигаться вниз в направлении у * /

            if (solveMazeUtil(maze, x, y + 1, sol))
                return true;

            // *Если ни одно из вышеперечисленных движений не работает, то
            //ОБРАТНАЯ СВЯЗЬ:снять отметку с x, y как часть решения
            // путь */

            sol[x][y] = 0;
            return false;
        }
        return false;
    }


    public static void main(String args[]) {

        RatMaze rat = new RatMaze();
        int maze[][] = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};
        rat.solveMaze(maze);
    }
}

