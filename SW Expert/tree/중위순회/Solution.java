import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int cnt;
    private static int start;
    private static char[] chars;

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int traverse(int num) {
        if (num == start - 1) // if finished (start : most biggest degree's most left number)
            return -1;

        if (num * 2 + 1 > cnt) { // if don't have right node to go
            if (num % 2 == 0) { // go to parent node from left node
                num = num / 2;
                return num;
            } else { // go to parent node from right node, we should go to most lowest level parent
                     // node which not visited
                while (num % 2 != 0) { // if it's parent node is odd number, then it means it was visited
                    num = num / 2; // caculate most low level of even number, which is most highest level of we
                                   // visited
                }
                num = num / 2; // go to most highest level of we visited's upper level
                return num;
            }
        } else {
            num = num * 2 + 1; // go to right node
            while (num * 2 <= cnt) { // if can go to left node, go left node
                num = num * 2;
            }
            return num;
        }
    }

    private static String run() throws Exception {
        cnt = Integer.parseInt(br.readLine());
        chars = new char[cnt + 1];
        for (int i = 1; i < cnt + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            chars[i] = st.nextToken().charAt(0);
        }
        int temp = cnt;
        start = 1;
        while (temp > 1) {
            temp = temp >> 1;
            start = start << 1;
        }
        String answer = "";
        int returnValue = start;
        while (true) {
            answer += chars[returnValue];
            returnValue = traverse(returnValue);
            if (returnValue == -1)
                break;
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i += 1) {
            System.out.println("#" + i + " " + run());
        }
    }
}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}