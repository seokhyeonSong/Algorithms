import java.util.Stack;
import java.util.Scanner;
import java.io.*;

public class BOJ_1874 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int[] numbers = new int[n];
        int index = 1;
        Stack<Integer> s = new Stack<>();
        Boolean isBroken = false;

        for (int i = 0; i < n; i += 1) {
            numbers[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i += 1) {
            if ((index <= numbers[i])) {
                while (index <= numbers[i]) {
                    s.push(index++);
                    sb.append("+\n");
                }
            }
            int poppedNumber = s.pop();
            if (poppedNumber != numbers[i]) {
                isBroken = true;
                System.out.println("NO");
                break;
            } else {
                sb.append("-\n");
            }
        }

        if (!isBroken) {
            System.out.print(sb.toString());
        }
    }
}