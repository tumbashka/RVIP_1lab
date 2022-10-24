import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskThreadPoolExecutor {
    public double calculate() {
        long start = System.nanoTime();
        double totalSum = 0;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5, 6, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
        );

        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < program.sizeMatr - 1; i++) {
            Sum sum = new Sum(i);
            Future future = poolExecutor.submit(sum);
            list.add(future);
        }
        poolExecutor.shutdown();
        for (Future fut : list) {
            try {
                totalSum += (double) fut.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        double countElem = (double) (program.sizeMatr * program.sizeMatr - program.sizeMatr) / 2;
        System.out.println("Time: " + (System.nanoTime() - start)+" Nanoseconds");
        return totalSum / countElem;
    }

}

class Sum implements Callable<Double> {
    private double sum = 0;
    private final int i;

    public Sum(int i) {
        this.i = i;
    }

    @Override
    public Double call() throws Exception {
        for (int j = i + 1; j < program.sizeMatr; j++) {
            sum += program.matr[i][j];
        }
        return sum;
    }
}