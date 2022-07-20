import java.util.Scanner;

public class BOJ_2018 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int start_index = 1;
        int end_index = 1;
        int count = 1;
        double sum = 1;
        while (start_index * 2 < n) {
            if (sum < n) {
                end_index++;
                sum += end_index;
            } else if (sum > n) {
                sum -= start_index;
                start_index++;
            } else {
                end_index++;
                sum += end_index;
                count++;
            }
        }
        System.out.println(count);
        sc.close();
    }
}