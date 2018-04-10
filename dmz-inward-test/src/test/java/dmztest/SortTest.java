package dmztest;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * @author dmz
 * @date 2018/3/23
 */
public class SortTest {

    private int[] mergeData;
    private int[] selectData;
    private int[] bubbleData;
    private int dataRange = 100000;
    private Sort sort = new Sort();

    @Before
    public void setData() {
        Random dataRange = new Random();
        mergeData = new int[dataRange.nextInt(this.dataRange)];
        int length = mergeData.length;
        selectData = new int[length];
        bubbleData = new int[length];
        for (int i = 0; i < mergeData.length; i++) {
            mergeData[i] = dataRange.nextInt(this.dataRange);
        }
        System.arraycopy(mergeData, 0, selectData, 0, length);
        System.arraycopy(mergeData, 0, bubbleData, 0, length);

    }

    @Test
    public void mergeSortByRecursiveTest() {
        //sort.mergeSortByRecursive(data);
        sort.mergeSortIterative(mergeData);
    }

    @Test
    public void mergeSortByIterativeTest() {

    }


    class Sort {

        public void mergeSortByRecursive(int[] data) {
            long startTime = System.nanoTime();
            mergeSortRecursive(0, data.length - 1, data, new int[data.length]);
            System.out.println("MergeSortRecursive Time Cost : " + (System.nanoTime() - startTime) * 0.000001 +" ms");
            checkSortedData(data,"MergeSortRecursive");
        }

        // 递归法
        private void mergeSortRecursive(int lowIndex, int highIndex, int[] data, int[] temp) {
            if (lowIndex >= highIndex) {
                return;
            }
            int mid = ((highIndex - lowIndex) >> 1) + lowIndex;
            int startOne = lowIndex, endOne = mid, startTwo = mid + 1, endTwo = highIndex;
            mergeSortRecursive(startOne, endOne, data, temp);
            mergeSortRecursive(startTwo, endTwo, data, temp);

            // 归并数据
            mergeData(lowIndex, mid, highIndex, data, temp);
        }

        private void mergeSortIterative(int[] data) {
            long startTime = System.nanoTime();
            for (int block = 1; block <= data.length - 1 / 2; block *= 2) {
                for (int start = 0; start < data.length - 1; start += 2 * block) {
                    int mid = start + block - 1 < data.length ? start + block - 1 : start - 1;
                    int end = (start + 2 * block - 1) < data.length ? start + 2 * block - 1 : data.length - 1;

                    // 归并数据
                    mergeData(start, mid, end, data, new int[data.length]);

                }
            }
            System.out.println("MergeSourIterative Time Cost : " + (System.nanoTime() - startTime) * 0.000001 +" ms");
            checkSortedData(data,"MergeSortIterative");
        }

        private void mergeData(int lowIndex, int middle, int highIndex, int[] data, int[] temp) {

            for (int i = lowIndex; i <= highIndex; i++) {
                temp[i] = data[i];
            }

            int leftStart = lowIndex, rightStart = middle + 1;
            int tempIndex = leftStart;
            while (leftStart <= middle && rightStart <= highIndex) {
                data[tempIndex++] = temp[leftStart] < temp[rightStart] ? temp[leftStart++] : temp[rightStart++];
            }
            while (leftStart <= middle) {
                data[tempIndex++] = temp[leftStart++];
            }

        }

        public void mergeSortByIterative(int[] data) {

        }

        public void bubbleSort(int[] data) {
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data.length - 1 - i; j++) {
                    if (data[j] > data[j + 1]) {
                        data[j] ^= data[j + 1];
                        data[j + 1] ^= data[j];
                        data[j] ^= data[j + 1];
                    }
                }
            }
            //return data;
            //比较次数:n*(n-1) ≈ n^2
            //交换次数:0 ~ n^2
            //已是有序（最好情况)，交换：0次
            //逆序（最坏情况) , 交换：n*(n-1) 次
            //最坏时间复杂度:	n*(n-1) = n^2-1 ≈ O(n^2)
            //平均时间复杂度:  O(n^2)
            //空间复杂度: O(n) , 优化后:O(1) (in-place)
        }

        public void selectSort(int[] data) {
            //int minIndex;
            //for (int i = 0; i < data.length - 1; i++) {
            //    minIndex = i;
            //    for (int j = i + 1; j < data.length; j++) {
            //        if (a[j] < data[minIndex]) {
            //            minIndex = j;
            //        }
            //    }
            //
            //    if (minIndex == i) {
            //        continue;
            //    }
            //
            //    // swap
            //    a[i] ^= a[minIndex];
            //    a[minIndex] ^= a[i];
            //    a[i] ^= a[minIndex];
            //
            //}
            //return a;
            //    比较次数: (n-1)+(n-2)+(n-3)...+1 = n*(n-1)/2 ≈ O(n^2)
            //    交换次数: 0 ~ (n-1) ≈ O(n)
            //    已经是有序（最好情况)，交换：0 次
            //    逆序（最坏情况)，交换：n-1 次
        }

        public void quickSort(int[] data) {

        }

        public void checkSortedData(int[] data, String sortType) {
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i] > data[i + 1]) {
                    System.out.println(sortType + ":Not Sorted Data");
                }
            }
        }


    }

    public static void main(String[] args) {
        int[] a = {2, 0, 0, 8, 9, 8, 1, 3};
        showArrays(a);
        recursiveMergeSort(a, new int[a.length], 0, a.length - 1);
        //showArrays(a);
        //showArrays(bubbleSort(a));
        //showArrays(selectSort(a));
        //showArrays(mergeSort(a));
        showArrays(a);

    }

    static void showArrays(int[] sortArr) {
        for (int i = 0; i < sortArr.length; i++) {
            System.out.print(sortArr[i]);
        }
        System.out.println();
    }

    static int[] mergeSort(int[] origin) {
        int[] result = new int[origin.length];
        mergeSortRecursive(origin, result, 0, origin.length - 1);
        return result;
    }

    // Merge Sort By Recursive
    static void mergeSortRecursive(int[] origin, int[] result, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = ((end - start) >> 1) + start;
        int startOne = start, endOne = mid;
        int startTwo = mid + 1, endTwo = end;

        mergeSortRecursive(origin, result, startOne, endOne);
        mergeSortRecursive(origin, result, startTwo, endTwo);

        // 比较归并Merge
        int k = start;
        while (startOne <= endOne && startTwo <= endTwo) {
            result[k++] = origin[startOne] < origin[startTwo] ? origin[startOne++] : origin[startTwo++];
        }
        while (startOne <= endOne) {
            result[k++] = origin[startOne++];
        }
        while (startTwo <= endTwo) {
            result[k++] = origin[startTwo++];
        }
        for (k = start; k <= end; k++)
            origin[k] = result[k];

    }


    // mergeSortRecursive
    static void recursiveMergeSort(int[] origin, int[] result, int start, int end) {

        if (start >= end) {
            return;
        }

        int mid = ((end - start) >> 1) + start;
        int start1 = start, end1 = mid, start2 = mid + 1, end2 = end;

        recursiveMergeSort(origin, result, start1, end1);
        recursiveMergeSort(origin, result, start2, end2);

        int t = start;
        while (start1 <= end1 && start2 <= end2) {
            result[t++] = origin[start1] < origin[start2] ? origin[start1++] : origin[start2++];
        }

        while (start1 <= end1) {
            result[t++] = origin[start1++];
        }
        while (start2 <= end2) {
            result[t++] = origin[start2++];
        }

        // 有序小序列的合并
        for (t = start; t <= end; t++) {
            origin[t] = result[t];
        }


    }

    static int[] selectSort(int... a) {
        int minIndex;
        for (int i = 0; i < a.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex == i) {
                continue;
            }

            // swap
            a[i] ^= a[minIndex];
            a[minIndex] ^= a[i];
            a[i] ^= a[minIndex];

        }
        return a;
        //    比较次数: (n-1)+(n-2)+(n-3)...+1 = n*(n-1)/2 ≈ O(n^2)
        //    交换次数: 0 ~ (n-1) ≈ O(n)
        //    已经是有序（最好情况)，交换：0 次
        //    逆序（最坏情况)，交换：n-1 次

    }


    public static String strReverseWithRecursive(String string) {
        if (string == null || string.length() == 0 || string.length() == 1) return string;
        return strReverseWithRecursive(string.substring(1)) + string.charAt(0);
    }
}
