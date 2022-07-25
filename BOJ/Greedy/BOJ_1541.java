import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1541 {
    private static BufferedReader br;

    public static void main(String args[]) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        String[] sums = expression.split("-");

        int sum = 0;
        for (int i = 0; i < sums.length; i += 1) {
            String[] numbers = sums[i].split("[+]");
            int partSum = 0;
            for (int j = 0; j < numbers.length; j += 1) {
                int number = Integer.parseInt(numbers[j]);
                partSum += number;
            }
            if (i == 0) {
                sum += partSum;
            } else {
                sum -= partSum;
            }
        }
        System.out.println(sum);
    }
}