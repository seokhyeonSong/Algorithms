import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
    static int N, M;
    static int[][][] dp = new int[3001][101][101];
    // Mbag은 원래 내림차순 정렬하여 m인덱스가 큰 값부터 결과에 더함.
    static int[] Nbag, Mbag;

    // n : 현재 바라보고 있는 Nbag의 인덱스
    // m : 현재 바라보고 있는 Mbag의 인덱스
    // pass : 지금까지 버린 Mbag의 갯수
    public static int findSnack(int n, int m, int pass) {
        // 만약 n이 범위를 벗어났으면, 0을 리턴해서 해당 Nbag[n]값과 result를 비교해서 담을 수 있도록 한다.
        if (n >= N) {
            return 0;
        }
        // 만약 이미 다른 경우에서 거쳐간 경우 해당 메모이제이션 값 사용, 0을 사용하면 안되는 이유는 result 결과가 0인 경우도 존재하기
        // 때문
        if (dp[n][m][pass] != -1) {
            return dp[n][m][pass];
        }
        int result = 0;
        // 항상 다음 상태는 무언가 고를 수 있는 상태
        if (n < N) {
            // N을 고르고 N을 패스
            result = Math.max(findSnack(n + 2, m, pass) + Nbag[n], result);
        }
        if (n < N) {
            // 선택 안하고 N 패스
            result = Math.max(findSnack(n + 1, m, pass), result);
            if (m + pass + 1 <= M) {
                // N을 고르고 M 패스
                result = Math.max(findSnack(n + 1, m, pass + 1) + Nbag[n], result);
                // M을 고르고 N 패스
                result = Math.max(findSnack(n + 1, m + 1, pass) + Mbag[m], result);
            }
        }
        if (m + pass + 2 <= M) {
            // M을 고르고 M 패스
            result = Math.max(findSnack(n, m + 1, pass + 1) + Mbag[m], result);
        }
        if (m + pass + 1 <= M) {
            // 선택 안하고 M 패스
            result = Math.max(findSnack(n, m, pass + 1), result);
        }
        dp[n][m][pass] = result;
        return result;
    }

    static void arrayReverse(int[] arr, int size) {
        for (int i = 0; i < size / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[size - i - 1];
            arr[size - i - 1] = tmp;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC;
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {

            N = Integer.parseInt(br.readLine());
            Nbag = new int[N];
            for (int j = 0; j < N; j += 1) {
                Nbag[j] = Integer.parseInt(br.readLine());
            }
            M = Integer.parseInt(br.readLine());
            Mbag = new int[M];
            for (int j = 0; j < M; j += 1) {
                Mbag[j] = Integer.parseInt(br.readLine());
            }
            for (int[][] a : dp) {
                for (int[] b : a) {
                    Arrays.fill(b, -1);
                }
            }
            Arrays.sort(Mbag);
            arrayReverse(Mbag, M);
            bw.write("#" + i + " " + findSnack(0, 0, 0) + "\n");
            bw.flush();
        }
    }
}
