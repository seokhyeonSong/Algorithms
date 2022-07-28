import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int money;
    static int[] moneyCnt = new int[8];
    static int[] moneyType = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };

    static void countMoney() {
        int count;
        for (int i = 0; i < moneyType.length; i += 1) {
            count = money / moneyType[i];
            moneyCnt[i] = count;
            if (money >= moneyType[i]) {
                money -= moneyType[i] * count;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC;
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {
            money = Integer.parseInt(br.readLine());
            countMoney();
            bw.write("#" + i + "\n");
            for (int j = 0; j < moneyCnt.length; j += 1) {
                bw.write(moneyCnt[j] + " ");
            }
            bw.write("\n");
            bw.flush();
        }
    }
}
