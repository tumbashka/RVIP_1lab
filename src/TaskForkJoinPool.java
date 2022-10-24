import java.util.concurrent.*;

public class TaskForkJoinPool {
    public Double calculate() throws ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        Future<Double> future = forkJoinPool.submit(new RecursiveTaskSum(program.matr, 0));
        Double sum = future.get();

        forkJoinPool.shutdown();
        System.out.println("Time: " + (System.nanoTime() - startTime)+" Nanoseconds");
        double countElem = (double) (program.sizeMatr * program.sizeMatr - program.sizeMatr) / 2;
        return sum/countElem;
    }
}