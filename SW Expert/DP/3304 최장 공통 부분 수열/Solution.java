import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    public static int run(char[] charArray1, char[] charArray2) {
        int[][] LCS = new int[charArray1.length + 1][charArray2.length + 1];

        for (int i = 1; i <= charArray1.length; i += 1) {
            for (int j = 1; j <= charArray2.length; j += 1) {
                int nextNum = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    LCS[i][j] = Math.max(LCS[i - 1][j - 1] + 1, nextNum);
                } else {
                    LCS[i][j] = nextNum;
                }
            }
        }

        return LCS[charArray1.length][charArray2.length];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC;
        char[] charArray1, charArray2;
        String[] string;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        TC = Integer.parseInt(br.readLine());

        for (int i = 1; i <= TC; i += 1) {
            string = br.readLine().split(" ");
            charArray1 = string[0].toCharArray();
            charArray2 = string[1].toCharArray();
            bw.write("#" + i + " " + run(charArray1, charArray2) + "\n");
            bw.flush();
        }
    }
}