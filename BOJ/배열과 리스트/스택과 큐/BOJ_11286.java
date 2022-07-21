import java.util.PriorityQueue;
import java.io.*;

public class BOJ_11286 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        int[] operators = new int[size];

        for (int i = 0; i < size; i += 1) {
            operators[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> heapQueue = new PriorityQueue<>((elem1, elem2) -> {
            int first_elem = Math.abs(elem1);
            int second_elem = Math.abs(elem2);

            if (first_elem == second_elem) {
                return elem1 > elem2 ? 1 : -1;
            } else {
                return first_elem - second_elem;
            }
        });

        for (int i = 0; i < size; i += 1) {
            if (operators[i] != 0) {
                heapQueue.add(operators[i]);
            } else {
                if (heapQueue.isEmpty()) {
                    bw.write("0\n");
                } else {
                    bw.write(heapQueue.poll() + "\n");
                }
            }
        }
        bw.flush();
    }
}