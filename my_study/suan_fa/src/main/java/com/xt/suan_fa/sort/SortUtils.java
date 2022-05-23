package com.xt.suan_fa.sort;

public class SortUtils {

    public static void main(String[] args) {
        int arr[] = {11, 5, 7, 1, 9, 6, 4, 8, 3, 2};
//        quickSort(arr, 0, arr.length-1);
        HeadSort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    /**
     * 数组元素交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    /**
     * 分治 分割数组
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int spite(int arr[], int low, int high) {
        int i = low;
        int x = arr[low];//基准元素
        for (int j = low + 1; j <= high; j++)
            if (arr[j] < x)
                if (++i != j) swap(arr, i, j);
        swap(arr, i, low);
        return i;
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int arr[], int low, int high) {
        if (low < high) {
            int i = spite(arr, low, high);
            quickSort(arr, low, i - 1);
            quickSort(arr, i + 1, high);
        }
    }

    /**
     * 简单选择排序
     *
     * @param arr
     */
    public static void selectSort(int arr[]) {
        int i, j, min;
        for (i = 0; i < arr.length - 1; i++) {
            min = i;
            for (j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[min])
                    min = j;
            if (min != i)
                swap(arr, i, min);

        }
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int i, j;
        for (i = 0; i < arr.length; i++)
            for (j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[i])
                    swap(arr, i, j);
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static void shellSort(int[] arr) {
        int swap;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++)
                for (int j = i; j > gap - 1; j -= gap)
                    if (arr[j] < arr[j - gap])
                        swap(arr, j, j - gap);
                    else break;
        }
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public static void HeadSort(int[] arr) {
        //如果数组为空，或数组长度为1 直接退出 不需要在排序了
        if (arr == null || arr.length <= 1) {
            return;
        }
        CreateHead(arr);
        //遍历选取最后一个值跟根节点交换，并排除最后一个叶子节点，重复堆的数值交换
        for (int i = arr.length - 1; i >= 1; i--) {
            swap(arr, 0, i);
            MinHead(arr, i, 0);
        }
    }

    public static void MinHead(int[] arr, int length, int index) {
        int left = 2 * index + 1;//左孩子
        int right = 2 * index + 2;//右孩子
        int minIndex = index;//最大数

        if (left < length && arr[left] > arr[minIndex]) {
            minIndex = left;
        }
        if (right < length && arr[right] > arr[minIndex]) {
            minIndex = right;
        }
        if (minIndex != index) {
            swap(arr, index, minIndex);
            MinHead(arr, length, minIndex);
        }

    }

    //创建堆
    public static void CreateHead(int[] arr) {
        //找到最后一个有孩子的节点
        int parent = arr.length / 2 - 1;
        for (int i = parent; i >= 0; i--)
            //调用交换节点数据的方法，将最大的数移到顶端节点
            MinHead(arr, arr.length, i);
    }
}
