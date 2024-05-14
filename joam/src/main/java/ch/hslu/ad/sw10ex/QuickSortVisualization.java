package ch.hslu.ad.sw10ex;

import java.util.HashMap;
import java.util.Map;

public class QuickSortVisualization {
    //private static final String alphabet = "▁▂▃▄▅▆▇█";
    private static final Map<Integer, String> alphabet0 = Map.of(
            0, "▁",
            1, "▂",
            2, "▃",
            3, "▄",
            4, "▅",
            5, "▆",
            6, "▇",
            7, "█"
    );
    private static final Map<Integer, String> alphabet = Map.of(
            0, "🔴",
            1, "🟠",
            2, "🟡",
            3, "🟢",
            4, "🔵",
            5, "🟣"
    );
    private static final int size = 12;
    private static final int sleepMs = 0;

    public static void main(String[] args) {
        int[] data = getArray();
        System.out.println("=============== Start shuffle ===============\n");
        shuffle(data);
        System.out.println("=============== Start sorting ===============\n");
        print(data);
        quickSort(data);
        print(data);
    }

    private static void sleep() {
        try {
            Thread.sleep(QuickSortVisualization.sleepMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[] getArray() {
        int[] arr = new int[QuickSortVisualization.size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i % alphabet.size();
        }
        return arr;
    }

    private static void shuffle(final int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            final int target = (int) (arr.length * Math.random());
            if (target == i) continue;
            swap(arr, i, target);
        }
    }

    private static void print(final int[] arr) {
        for (int i : arr) {
            System.out.print(" " + alphabet.get(i) + " ");
        }
        System.out.println();
    }

    private static void printIndicators(final int a, final int b, final int pivot) {
        for (int i = 0; i < size; i++) {
            if (i == a || i == b) {
                System.out.print(" ⬆️ ");
            } else if (i == pivot) {
                System.out.print(" ❌ ");
            } else {
                System.out.print(" ➖ ");
            }
        }
        System.out.println();
    }

    private static void swap(final int[] arr, final int first, final int second) {
        print(arr);
        printIndicators(first, second, -1);
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;

        sleep();
    }

    public static void quickSort(final int[] arr) {
        final int right = arr.length - 1;
        quickSort(arr, 0, right);
    }

    private static void quickSort(final int[] arr, final int left, final int right) {
        int up = left;
        int down = right - 1;
        int pivotIndex = ((right - left) / 2) + left;
        printIndicators(-1, -1, pivotIndex);
        swap(arr, pivotIndex, right); //move the pivot out of the way
        int pivot = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < pivot) {
                up++;
            }
            while ((arr[down] > pivot) && (down > up)) {
                down--;
            }
            if (down > up) {
                swap(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        swap(arr, up, right); //put pivot at correct position
        if (left < (up - 1)) {
            quickSort(arr, left, (up - 1));
        }
        if ((up + 1) < right) {
            quickSort(arr, (up + 1), right);
        }
    }

}
