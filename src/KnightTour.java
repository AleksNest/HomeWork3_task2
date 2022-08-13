// Задача обойти конем доску..
class KnightTour {
    static int N = 8;

    static boolean isSafe(int x, int y, int sol[][]) {                          // метод проверки i,j- это действительные индексы для шахматной доски N * N

        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    static void printSolution(int sol[][]) {                                        // печать результата решения
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++)
                System.out.print(sol[x][y] + " ");
            System.out.println();
        }
    }

    static boolean solveKT() {                                                     // Метод обратного отслеживания. false - если полный обход невозможен и true - ели возможен
        int sol[][] = new int[8][8];

        for (int x = 0; x < N; x++)                                                 // инициализациия матрицы решения
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

        int moves[][] = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}}; // move[x][y] определяют следующий ход коня по кординатам x и y.

        sol[0][0] = 0;                                                              //конь изначально находится на первом блоке

        if (!solveKTUtil(0, 0, 1, sol, moves)) {                        // Начиная с  0,0 и изучиние  всех ходов, используя solveKTUtil
            System.out.println("Решения не существует");
            return false;
        } else
            printSolution(sol);

        return true;
    }


    static boolean solveKTUtil(int x, int y, int movei, int sol[][], int moves[][]) { // Рекурсивный метод для решения задачи Knight

        int k, next_x, next_y;
        if (movei == N * N)
            return true;

        for (k = 0; k < 8; k++) {                                                       //Попробовать все следующие шаги от текущей координаты x и  y
            next_x = x + moves[k][0];
            next_y = y + moves[k][1];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1,
                        sol, moves))
                    return true;
                else
                    sol[next_x][next_y] = -1;                                           // backtracking
            }
        }

        return false;
    }

    public static void main(String args[]) {
        solveKT();
    }
}

