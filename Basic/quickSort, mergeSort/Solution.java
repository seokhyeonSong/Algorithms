import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static int[] numbers;
    private static int[] tmpArr;

    private static void mergeSort(int start, int end) {
        if (end > start + 1) {
            int median = (start + end) / 2;
            mergeSort(start, median);
            mergeSort(median + 1, end);
        }
        for (int i = start; i <= end; i += 1) {
            tmpArr[i] = numbers[i];
        }
        merge(start, end);
    }

    private static void merge(int start, int end) {
        int median = (start + end) / 2;
        int first = start, second = median + 1;
        int i = start;
        while (first <= median && second <= end) {
            if (tmpArr[first] <= tmpArr[second]) {
                numbers[i] = tmpArr[first];
                first++;
            } else {
                numbers[i] = tmpArr[second];
                second++;
            }
            i++;
        }
        if (first > median) {
            while (second <= end) {
                numbers[i] = tmpArr[second];
                second++;
                i++;
            }
        } else {
            while (first <= median) {
                numbers[i] = tmpArr[first];
                first++;
                i++;
            }
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot - 1);
        quickSort(arr, pivot + 1, end);

    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = numbers[start];
        int i = start, j = end;

        while (i < j) {
            while (numbers[j] > pivot && i < j) {
                j--;
            }
            while (numbers[i] <= pivot && i < j) {
                i++;
            }
            swap(numbers, i, j);
        }
        swap(numbers, start, i);
        return i;
    }

    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        StringTokenizer st;
        int cnt;

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        cnt = Integer.parseInt(br.readLine());
        numbers = new int[cnt];
        tmpArr = new int[numbers.length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i += 1) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, cnt - 1);
        for (int i = 0; i < cnt; i += 1) {
            bw.write(numbers[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}