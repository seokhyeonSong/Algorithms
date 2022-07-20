import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2751 {
    public static int[] numbers, mergedNumbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());

        numbers = new int[size];
        mergedNumbers = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i += 1) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        swap = 0;

        mergeSort(0, size - 1);
        for(int i : numbers){
					bw.write(i+"\n");
				}
        bw.flush();
    }

    private static void mergeSort(int start, int end) {
        if (end - start < 1)
            return;
        int middle = (end - start) / 2 + start;
        mergeSort(start, middle);
        mergeSort(middle + 1, end);
        for (int i = start; i <= end; i += 1) {
            mergedNumbers[i] = numbers[i];
        }
        merge(start, end);
    }

    private static void merge(int start, int end) {
        int middle = (end - start) / 2 + start;
        int firstIndex = start, secondIndex = middle + 1;
        int i = start;
        while (firstIndex <= middle && secondIndex <= end) {
            if (mergedNumbers[firstIndex] <= mergedNumbers[secondIndex]) {
                numbers[i] = mergedNumbers[firstIndex];
                firstIndex++;
            } else {
                numbers[i] = mergedNumbers[secondIndex];
                secondIndex++;
            }
            i++;
        }
        while (secondIndex <= end) {
            numbers[i] = mergedNumbers[secondIndex];
            secondIndex++;
            i++;
        }
        while (firstIndex <= middle) {
            numbers[i] = mergedNumbers[firstIndex];
            firstIndex++;
            i++;
        }
    }
}