import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class Solution {
    private final static String PLUS = "+";
    private final static String MINUS = "-";
    private final static String MULTI = "*";
    private final static String DIVIDE = "/";

    private static BufferedReader br;
    private static Node node[];

    private static double calc(int num) {
        switch (node[num].data) {
            case PLUS:
                return calc(node[num].left) + calc(node[num].right);
            case MINUS:
                return calc(node[num].left) - calc(node[num].right);
            case MULTI:
                return calc(node[num].left) * calc(node[num].right);
            case DIVIDE:
                return calc(node[num].left) / calc(node[num].right);
            default:
                return (double) (Double.parseDouble(node[num].data));
        }
    }

    private static int run() throws Exception {
        int cnt, left = 0, right = 0;
        String data;
        StringTokenizer st;

        cnt = Integer.parseInt(br.readLine());
        node = new Node[cnt + 1];
        for (int i = 1; i <= cnt; i += 1) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            data = st.nextToken();
            if (st.hasMoreTokens()) {
                left = Integer.parseInt(st.nextToken());
                right = Integer.parseInt(st.nextToken());
                node[i] = new Node(data, left, right);
            } else {
                node[i] = new Node(data);
            }
        }

        return (int) calc(1);
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i < 11; i += 1) {
            System.out.println("#" + i + " " + run());
        }
    }
}

class Node {
    String data;
    int left;
    int right;

    Node(String data) {
        this.data = data;
        this.left = 0;
        this.right = 0;
    }

    Node(String data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}