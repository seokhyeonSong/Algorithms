import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2343 {
    static int maxSize = Integer.MIN_VALUE;
    static int[] data;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        data = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            data[i] = Integer.parseInt(st.nextToken());
            if (data[i] > max)
                max = data[i];
            sum += data[i];
        }
        bw.write(blueLowerBound(max, sum, M) + "");
        bw.flush();
    }

    static int blueLowerBound(int start, int end, int num) {
        while (start < end) {
            int median = (start + end) / 2;
            int blueCnt = 0, blueSum = 0;
            for (int i = 0; i < data.length; i += 1) {
                if (blueSum + data[i] > median) {
                    blueCnt++;
                    blueSum = 0;
                }
                blueSum += data[i];
            }
            if (blueSum != 0)
                blueCnt++;
            if (blueCnt <= num) {
                end = median;
            } else {
                start = median + 1;
            }
        }
        return start;
    }
}