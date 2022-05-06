import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final int ARR_CAPACITY = 45000000;
    public static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        Integer[] array = newArray();
        long start = System.currentTimeMillis();
        System.out.println("Однопоточный подсчет суммы элементов массива.");
        System.out.println("Результат вычислений: " + getSumValuewSingleThread(array));
        System.out.println();
        start = System.currentTimeMillis();
        System.out.println("Подсчет суммы элементов массива при помощи ForkJoinPool.");
        System.out.println("Результат вычислений: " + getSumValuewForkJoinPool(array));
    }

    public static Integer[] newArray() {
        Random random = new Random();
        Integer[] array = new Integer[ARR_CAPACITY];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(MAX_VALUE);
        }
        return array;
    }

    public static Integer getSumValuewSingleThread(Integer[] array) {
        long start = System.currentTimeMillis();
        Integer sum = 0;
        for (Integer arrayElement : array) {
            sum += arrayElement;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("Время выполнения: " + time);
        return sum;
    }

    public static Integer getSumValuewForkJoinPool(Integer[] array) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(new ArraySumValues(array));
        long time = System.currentTimeMillis() - start;
        System.out.println("Время выполнения: " + time);
        return result;
    }
}
