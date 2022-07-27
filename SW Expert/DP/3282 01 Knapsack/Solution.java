import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static int cnt, capacity;
    static int[][] bag;
    static Thing[] thing;

    public static int run() {
        for (int i = 1; i <= cnt; i += 1) {
            for (int j = 1; j <= capacity; j += 1) {
                if (thing[i - 1].weight <= j) {
                    bag[i][j] = Math.max(bag[i - 1][j], bag[i - 1][j - thing[i - 1].weight] + thing[i - 1].value);
                } else {
                    bag[i][j] = bag[i - 1][j];
                }
            }
        }
        for (int i = 0; i <= cnt; i += 1) {
            for (int j = 0; j <= capacity; j += 1) {
                System.out.print(bag[i][j] + " ");
            }
            System.out.println();
        }
        return bag[cnt][capacity];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC, weight, value;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        TC = Integer.parseInt(br.readLine());

        for (int i = 1; i <= TC; i += 1) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            capacity = Integer.parseInt(st.nextToken());
            bag = new int[cnt + 1][capacity + 1];
            thing = new Thing[cnt];
            for (int j = 0; j < cnt; j += 1) {
                st = new StringTokenizer(br.readLine());
                weight = Integer.parseInt(st.nextToken());
                value = Integer.parseInt(st.nextToken());
                thing[j] = new Thing(weight, value);
            }
            bw.write("#" + i + " " + run() + "\n");
            bw.flush();
        }
    }
}

class Thing {
    int weight, value;

    Thing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}