import java.util.Random;


public class program {
    static int sizeMatr = 20000;
    static int[][] matr = new int[sizeMatr][sizeMatr];

    public static void main(String[] args) {
        Random random = new Random();
        double testS;
        int testC;
        double testTotalAvg = 0;
        for (int i = 0; i < sizeMatr; i++) {
//            testS = 0;
//            testC = 0;
            for (int j = 0; j < sizeMatr; j++) {
                matr[i][j] = random.nextInt(5) + 1;
//                System.out.print(matr[i][j] + " ");
            }
//            for (int j = i + 1; j < sizeMatr; j++) {
//                testS += matr[i][j];
//                testC++;
//            }
//            if (testC != 0) {
//                System.out.print("    i = " + i);
//                System.out.print("    avg = " + testS / testC);
//                System.out.print("    sum = " + testS);
//                System.out.print("    count = " + testC);
//                testTotalAvg += (testS / testC);
//            }
//            System.out.println();

        }
//        testTotalAvg /= sizeMatr-1;
//        System.out.println("    testTotalAvg = " + testTotalAvg);

        TaskOneThread task1 = new TaskOneThread();
        System.out.println("TaskOneThread:");
        System.out.println(task1.calculate());

        System.out.println("TaskThreadPoolExecutor:");
        TaskThreadPoolExecutor task2 = new TaskThreadPoolExecutor();
        System.out.println(task2.calculate());

        System.out.println("TaskForkJoinPool:");
        TaskForkJoinPool task3 = new TaskForkJoinPool();
        System.out.println(task3.task());

    }
}

