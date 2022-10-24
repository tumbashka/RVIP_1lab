import java.util.Random;
import java.util.concurrent.ExecutionException;


public class program {
    static int sizeMatr = 12000;
    static int[][] matr = new int[sizeMatr][sizeMatr];

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random random = new Random();
        for (int i = 0; i < sizeMatr; i++) {
            for (int j = 0; j < sizeMatr; j++) {
                matr[i][j] = random.nextInt(5) + 1;
            }
        }

        TaskOneThread task1 = new TaskOneThread();
        System.out.println("TaskOneThread:");
        System.out.println(task1.calculate());

        System.out.println("TaskThreadPoolExecutor:");
        TaskThreadPoolExecutor task2 = new TaskThreadPoolExecutor();
        System.out.println(task2.calculate());

        System.out.println("TaskForkJoinPool:");
        ForkJoinPollMatrix task3 = new ForkJoinPollMatrix(matr);

        System.out.println(task3.calculate());

    }
}

