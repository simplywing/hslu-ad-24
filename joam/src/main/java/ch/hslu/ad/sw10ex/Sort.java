package ch.hslu.ad.sw10ex;

import java.util.Arrays;

public final class Sort {
    private static void swap(final int[] arr, final int first, final int second) {
        int tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }

    public static void quickSort(final int[] arr) {
        final int right = arr.length - 1;
        quickSort(arr, 0, right);
    }

    private static void quickSort(final int[] arr, final int left, final int right) {
        int up = left;
        int down = right - 1;
        int pivotIndex = ((right - left) / 2) + left;
        swap(arr, pivotIndex, right); //move the pivot out of the way
        int pivot = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < pivot) {
                up++;
            }
            while ((arr[down] >= pivot) && (down > up)) {
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

    public static void quickSort2(final int[] arr) {
        final int right = arr.length - 1;
        quickSort2(arr, 0, right);
    }

    private static void quickSort2(final int[] arr, final int left, final int right) {
        int up = left;
        int down = right - 1;
        int pivotIndex = ((right - left) / 2) + left;
        swap(arr, pivotIndex, right); //move the pivot out of the way
        int pivot = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < pivot) {
                up++;
            }
            // now we also swap elements of same size (optimization)
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
            quickSort2(arr, left, (up - 1));
        }
        if ((up + 1) < right) {
            quickSort2(arr, (up + 1), right);
        }
    }

    public static void heapSort(final int[] arr) {
        IntHeap heap = new Heap(arr.length);
        for (final int i : arr) {
            heap.insert(i);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.remove();
        }
    }

    public static void arraysSort(int[] arr) {
        Arrays.sort(arr);
    }
}
