package taejoong.chapter8.item53;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[5];
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            arr[i] = random.nextInt() % 10;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("sum(arr) = " + sum(arr));
        System.out.println("minV1(arr) = " + minV1(arr));
        System.out.println("minV2(arr) = " + minV2(arr[0], Arrays.copyOfRange(arr, 1, arr.length)));

        // 번외 - subArray
        int[] subArrayV1 = Arrays.copyOfRange(arr, 1, arr.length);
        int[] subArrayV2 = IntStream.range(1, arr.length)
                .map(idx -> arr[idx])
                .toArray();

        System.out.println("subArrayV1 = " + Arrays.toString(subArrayV1));
        System.out.println("subArrayV2 = " + Arrays.toString(subArrayV2));
    }

    static int sum(int... args) {
        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    // 잘못 구현한 예
    static int minV1(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("needs to one");
        }

        int min = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }

        return min;
    }

    static int minV2(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }

        return min;
    }
}
