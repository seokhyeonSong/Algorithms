import java.io.*;

public class BOJ_2023 {
    static int N;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        DFSPrime(2, 1);
        DFSPrime(3, 1);
        DFSPrime(5, 1);
        DFSPrime(7, 1);
        bw.flush();
    }

    static boolean isPrime(int num) {
        if (num % 2 == 0)
            return false;
        for (int i = 3; i <= num / 2; i += 2) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    static void DFSPrime(int num, int digit) throws IOException {
        if (digit == N) {
            bw.write(num + "\n");
            return;
        }
        for (int i = 1; i < 10; i += 1) {
            int currentNumber = num * 10 + i;
            if (!isPrime(currentNumber)) {
                continue;
            }
            DFSPrime(currentNumber, digit + 1);
        }
    }
}