import java.util.StringTokenizer;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long cnt = 0;

        long sums[] = new long[n];
        long remainders[] = new long[m];

        st = new StringTokenizer(br.readLine());
        sums[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i += 1) {
            sums[i] = Integer.parseInt(st.nextToken()) + sums[i - 1];
        }

        for (int i = 0; i < n; i += 1) {
            int remainder = (int) (sums[i] % m);
            if (remainder == 0) {
                cnt += 1;
            }
            remainders[remainder] += 1;
        }

        for (int i = 0; i < m; i += 1) {
            if (remainders[i] > 1)
                cnt += remainders[i] * (remainders[i] - 1) / 2;
        }

        System.out.println(cnt);
    }
}