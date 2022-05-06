import java.util.concurrent.RecursiveTask;

public class ArraySumValues extends RecursiveTask<Integer> {

    private final Integer[] ARRAY;

    private int startIndex;
    private int endIndex;

    public ArraySumValues(Integer[] ARRAY) {
        this.ARRAY = ARRAY;
        this.startIndex = 0;
        this.endIndex = ARRAY.length;
    }

    public ArraySumValues(Integer[] ARRAY, int startIndex, int endIndex) {
        this.ARRAY = ARRAY;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Integer compute() {
        switch (this.endIndex - this.startIndex) {
            case 0:
                return 0;
            case 1:
                return ARRAY[startIndex];
            case 2:
                return ARRAY[startIndex] + ARRAY[startIndex + 1];
            default:
                final int middle = (this.endIndex - this.startIndex) / 2 + this.startIndex;
                ArraySumValues task1 = new ArraySumValues(this.ARRAY, this.startIndex, middle);
                ArraySumValues task2 = new ArraySumValues(this.ARRAY, middle, this.endIndex);
                invokeAll(task1, task2);
                return task1.join() + task2.join();
        }
    }
}
