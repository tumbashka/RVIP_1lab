public class TaskOneThread {
    public double calculate() {
        long start = System.nanoTime();
        double sum = 0;
        double count = 0;
        for (int i = 0; i < program.sizeMatr-1; i++) {// счётчик строк
            for (int j = i+1; j < program.sizeMatr; j++) {// счётчик элементов строки
                sum += program.matr[i][j];
                count++;
            }
        }
        System.out.println("Time: " + (System.nanoTime() - start));

        return sum/count;
    }
}
