import java.util.Stack;
import java.util.StringTokenizer;
import java.io.*;

public class BOJ_17298 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size];
        Stack<Integer> indexes = new Stack<>();
        int[] NGEs = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i += 1) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        indexes.push(0);
        NGEs[0] = -1;

        for (int i = 1; i < size; i += 1) {
            NGEs[i] = -1;
            int currentNumber = numbers[i];
            if (currentNumber > numbers[indexes.peek()]) {
                while (!indexes.isEmpty() && numbers[indexes.peek()] < currentNumber) {
                    NGEs[indexes.pop()] = currentNumber;
                }
            }
            indexes.push(i);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < size; i += 1) {
            bw.write(NGEs[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}