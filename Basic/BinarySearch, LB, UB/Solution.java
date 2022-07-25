import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class Solution {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] numbers;

    private static int binarySearch(int num) {
        int median, start, end;
        start = 0;
        end = numbers.length - 1;
        while (start <= end) {
            median = (start + end) / 2;
            if (numbers[median] == num) {
                return median;
            } else if (numbers[median] > num) {
                end = median - 1;
            } else {
                start = median + 1;
            }
        }
        return -1;
    }

    private static int lowerBound(int num) {
        int median, start, end;
        start = 0;
        end = numbers.length;
        while (start < end) {
            median = (start + end) / 2;
            if (numbers[median] >= num) {
                end = median;
            } else {
                start = median + 1;
            }
        }
        return start;
    }

    private static int upperBound(int num) {
        int median, start, end;
        start = 0;
        end = numbers.length;
        while (start < end) {
            median = (start + end) / 2;
            if (numbers[median] <= num) {
                start = median + 1;
            } else {
                end = median;
            }
        }
        return start;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int cnt;

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        cnt = Integer.parseInt(br.readLine());
        numbers = new int[cnt];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i += 1) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int findingNum = Integer.parseInt(st.nextToken());
            bw.write(binarySearch(findingNum) + " / ");
            bw.write(lowerBound(findingNum) + " / ");
            bw.write(upperBound(findingNum) + "\n");
        }
        bw.flush();
    }
}