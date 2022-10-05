import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TaskForkJoinPool {
    public double task() {
        long start = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        double result = forkJoinPool.invoke(new MultiTask(program.matr));
        forkJoinPool.shutdown();
        System.out.println("Time: " + (System.nanoTime() - start));
        return result;
    }
}

class MultiTask extends RecursiveTask<Double> {
    private int[][] matr;
    public MultiTask(int[][] matr) {
        this.matr = matr;
    }
    @Override
    protected Double compute() {
        double sum = 1;
        if (matr.length <= 1) {
            for(int j = 0; j < program.sizeMatr; j++) {
                sum += matr[0][j];
            }
            return sum;
        } else {
            int mid = matr.length / 2;
            MultiTask MultiTask1 = new MultiTask(Arrays.copyOfRange(matr, 0, mid));
            MultiTask MultiTask2 = new MultiTask(Arrays.copyOfRange(matr, mid, matr.length));
            MultiTask1.fork();
            return MultiTask2.compute() + MultiTask1.join();
        }
    }
}