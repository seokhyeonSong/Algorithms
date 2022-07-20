import java.io.*;

public class BOJ_10989 {
    public static int[] numbers;
    public static int[] output;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());

        numbers = new int[size];
        output = new int[size];

        for (int i = 0; i < size; i += 1) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        radixSort(5);

        for (int i : output) {
            bw.write(Integer.toString(i) + "\n");
        }
        bw.flush();
    }

    private static void radixSort(int maxDigit) {
        int currentDigit = 1;
        int digitCount = 0;
        while (digitCount < maxDigit) {
            int[] bucket = new int[10];
            for (int i = 0; i < numbers.length; i += 1) {
                bucket[(numbers[i] / currentDigit) % 10]++;
            }
            for (int i = 1; i < 10; i += 1) {
                bucket[i] += bucket[i - 1];
            }
            for (int i = numbers.length - 1; i >= 0; i -= 1) {
                int currentJarisu = (numbers[i] / currentDigit) % 10;
                output[bucket[currentJarisu] - 1] = numbers[i];
                bucket[currentJarisu]--;
            }
            for (int i = 0; i < numbers.length; i += 1) {
                numbers[i] = output[i];
            }
            digitCount++;
            currentDigit *= 10;
        }
    }
}