import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskSum extends RecursiveTask<Double> {
    private int[][] matrix;
    private int count;

    public RecursiveTaskSum(int[][] matrix, int count){
        this.matrix = matrix;
        this.count = count;
    }

    private OptionalDouble sum(){
        if (matrix.length > 250) {
            return OptionalDouble.of(ForkJoinTask.invokeAll(createSubtasks()).stream().mapToDouble(ForkJoinTask::join).sum());
        } else { return processing(matrix); }
    }

    private Collection<RecursiveTaskSum> createSubtasks() {
        List<RecursiveTaskSum> subTasks = new ArrayList<>();
        subTasks.add(new RecursiveTaskSum(Arrays.copyOfRange(matrix, 0, matrix.length / 2),count));
        subTasks.add(new RecursiveTaskSum(Arrays.copyOfRange(matrix, matrix.length / 2, matrix.length), matrix.length / 2 + count));
        return subTasks;
    }

    private OptionalDouble processing(int[][] matrix) {
        double sum = 0;
        for(int[] arr : matrix){
            for (int i = 0; i < count; i++) {
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
