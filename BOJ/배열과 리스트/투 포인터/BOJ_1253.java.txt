import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class BOJ_1253 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long[] like = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i += 1) {
            like[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(like);
        int current_index = 0;
        int start_index = 0;
        int end_index = like.length - 1;
        long sum;
        long count = 0;
        while (current_index != like.length) {
            if (current_index == start_index) {
                start_index++;
                continue;
            }
            if (current_index == end_index) {
                end_index--;
                continue;
            }
            if (start_index == end_index) {
                current_index++;
                start_index = 0;
                end_index = like.length - 1;
                continue;
            }
            sum = like[start_index] + like[end_index];
            if (sum < like[current_index]) {
                start_index++;
            } else if (sum > like[current_index]) {
                end_index--;
            } else {
                count++;
                current_index++;
                start_index = 0;
                end_index = like.length - 1;
            }
        }
        System.out.println(count);
    }
}