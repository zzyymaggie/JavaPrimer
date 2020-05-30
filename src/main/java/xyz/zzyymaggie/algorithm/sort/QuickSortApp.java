package xyz.zzyymaggie.algorithm.sort;

import java.util.Arrays;

public class QuickSortApp {
    public static void quickSort(int a[], int left, int right) {

        if (left >= right) return;

        int pivot = a[left];
        int i = left;
        int j = right;
        //如果左右指针碰头就代表这一轮迭代结束
        while (i != j) {
            //先从右边开始,找小于pivot点的数字
            //因此，循环的条件是如果大于pivot就继续查找
            //小于pivot就停止

            while (a[j] >= pivot && i < j) {
                j--;
            }
            //后从左边开始，找大于pivot的数字
            //因此，循环的条件是如果是小于pivot就继续查找
            //大于pivot就停止
            while (a[i] <= pivot && i < j) {
                i++;
            }

            if (i < j) {
                //交换两个数字
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }

        }

        //基准归位
        a[left] = a[i];
        a[i] = pivot;

        quickSort(a, left, i - 1);

        quickSort(a, i + 1, right);

    }

    public static void main(String[] args) {
        int[] a = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
