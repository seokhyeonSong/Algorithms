import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.*;

public class BOJ_2164
 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());

        Queue<Integer> cardQueue = new LinkedList<>();
        Queue<Integer> newCardQueue = new LinkedList<>();

        for (int i = 0; i < size; i += 1) {
            cardQueue.add(i + 1);
        }

        while (cardQueue.size() > 1) {
            cardQueue.poll();
            cardQueue.add(cardQueue.poll());
        }
        System.out.println(cardQueue.peek());
    }
}