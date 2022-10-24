import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskSum extends RecursiveTask<Double> {
    private final int[][] matr;
    private int count;

    public RecursiveTaskSum(int[][] matr, int count){
        this.matr = matr;
        this.count = count;
    }

    private OptionalDouble sum(){
        if (matr.length > 250) {
            return OptionalDouble.of(ForkJoinTask.invokeAll(createSubtasks()).stream().mapToDouble(ForkJoinTask::join).sum());
        } else { return processing(matr); }
    }

    private Collection<RecursiveTaskSum> createSubtasks() {
        List<RecursiveTaskSum> subTasks = new ArrayList<>();
        subTasks.add(new RecursiveTaskSum(Arrays.copyOfRange(matr, 0, matr.length / 2),count));
        subTasks.add(new RecursiveTaskSum(Arrays.copyOfRange(matr, matr.length / 2, matr.length), matr.length / 2 + count));
        return subTasks;
    }

    private OptionalDouble processing(int[][] matrix) {
        double sum = 0;
        for(int[] arr : matrix){
            for (int i = count+1; i < program.sizeMatr; i++) {
                sum += arr[i];
            }
            count++;
        }
        return OptionalDouble.of(sum);
    }

    @Override
    protected Double compute() {
        return sum().getAsDouble();
    }
}
