package xyz.zzyymaggie.algorithm.sort;

import java.util.Arrays;

public class InsertSortApp {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 8, 1, 3, 5, 4, 7, 10};
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println(Arrays.toString(arr));
    }
}
