import java.util.StringTokenizer;
import java.io.*;

public class BOJ_11659 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int sums[] = new int[n + 1];
        sums[0] = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i += 1) {
            sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
        }
        for (int j = 0; j < m; j += 1) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(sums[end] - sums[start - 1]);
        }
    }
}
