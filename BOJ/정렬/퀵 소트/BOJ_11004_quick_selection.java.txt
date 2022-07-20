import java.util.StringTokenizer;
import java.io.*;

public class BOJ_11004_quick_selection {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i += 1) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
				quickSort(numbers, 0, size - 1, K - 1);

        bw.write(numbers.get(K - 1) + "");
        bw.flush();
    }

    public static int partition(int[] arr, int start, int end) {
        int i = start, j = end;
        int m = (start + end) / 2;
        int pivot = arr[m];
        swap(arr, start, m);

        while (i < j) {
            while (pivot < arr[j]) {
                j--;
            }
            while (i < j && pivot >= arr[i]) {
                i++;
            }
            swap(arr, i, j);
        }
        arr[start] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void quickSort(int[] arr, int start, int end, int K) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            if (pivot == K)
                return;
            else if (K < pivot) {
                quickSort(arr, start, pivot - 1, K);
            } else {
                quickSort(arr, pivot + 1, end, K);
            }
        }
    }

    public static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}