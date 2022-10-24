import java.util.concurrent.*;

public class ForkJoinPollMatrix {
    private int[][] matrix;
    public ForkJoinPollMatrix(int[][] matrix){
        this.matrix = matrix;
    }


    public Double calculate() throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int count = 0;

        Future<Double> future = forkJoinPool.submit(new RecursiveTaskSum(matrix, count));
        Double average = future.get();

        forkJoinPool.shutdown();
        System.out.println("Время ForkJoinPool: " + (System.nanoTime() - startTime));
        return average/(matrix.length*(matrix.length-1)/2);
    }
}