import java.io.*;

public class BOJ_1300 {
    static int maxSize = Integer.MIN_VALUE;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        bw.write(cubeLowerBound(k) + "\n");
        bw.flush();
    }

    static int cubeLowerBound(int num) {
        int start = 0;
        int end = num;
        while (start < end) {
            int median = (start + end) / 2;
            int count = 0;
            for (int i = 1; i <= N; i += 1) {
                count += Math.min(N, median / i);
            }
            if (count < num) {
                start = median + 1;
            } else {
                end = median;
            }
        }
        return start;
    }
}