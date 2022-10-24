import java.util.Random;
import java.util.concurrent.ExecutionException;


public class program {
    static int sizeMatr = 100;
    static int[][] matr = new int[sizeMatr][sizeMatr];

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        for (int i = 0; i < sizeMatr; i++) {
            for (int j = 0; j < sizeMatr; j++) {
                matr[i][j] = random.nextInt(5) + 1;
            }
        }

        System.out.println("TaskOneThread:");
        TaskOneThread task1 = new TaskOneThread();
        System.out.println("Result = " + task1.calculate());
        System.out.println();

        System.out.println("TaskThreadPoolExecutor:");
        TaskThreadPoolExecutor task2 = new TaskThreadPoolExecutor();
        System.out.println("Result = " + task2.calculate());
        System.out.println();

        System.out.println("TaskForkJoinPool:");
        TaskForkJoinPool task3 = new TaskForkJoinPool();
        System.out.println("Result = " + task3.calculate());
    }
}

