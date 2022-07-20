import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.*;

public class BOJ_11004 {
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
        Collections.sort(numbers);

        bw.write(numbers.get(K - 1) + "");
        bw.flush();
    }
}