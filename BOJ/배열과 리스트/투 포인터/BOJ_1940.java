import java.util.StringTokenizer;
import java.io.*;
import java.util.Arrays;

public class BOJ_1940 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] stuff = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i += 1) {
            stuff[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(stuff);
        int first_index = 0;
        int second_index = stuff.length - 1;
        long sum = stuff[first_index] + stuff[second_index];
        int count = 0;
        while (first_index < second_index) {
            sum = stuff[first_index] + stuff[second_index];
            if (sum == m) {
                first_index++;
                second_index--;
                count++;
            } else {
                if (sum < m) {
                    first_index++;
                } else {
                    second_index--;
                }
            }
        }
        System.out.println(count);
    }
}