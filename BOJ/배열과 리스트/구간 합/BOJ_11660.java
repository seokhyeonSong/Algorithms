import java.util.StringTokenizer;
import java.io.*;

public class BOJ_11660 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int sums[][] = new int[n + 1][n + 1];

        for (int i = 1; i < n + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j += 1) {
                sums[i][j] = sums[i - 1][j] + sums[i][j - 1] + Integer.parseInt(st.nextToken()) - sums[i - 1][j - 1];
            }
        }

        for (int i = 0; i < m; i += 1) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            System.out.println(sums[x2][y2] + sums[x1 - 1][y1 - 1] - sums[x2][y1 - 1] - sums[x1 - 1][y2]);
        }
    }
}