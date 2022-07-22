import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class Solution {
    private static BufferedReader br;

    private static Node[] node;

    private static ArrayList<Integer> findDesc(int num) {
        ArrayList<Integer> parentList = new ArrayList<>();
        while (node[num].data != 1) {
            parentList.add(node[num].parent);
            num = node[num].parent;
        }
        return parentList;
    }

    private static int search(int num) {
        if (node[num].left == -1) {
            return 1;
        } else if (node[num].right == -1) {
            return search(node[num].left) + 1;
        } else {
            return search(node[num].left) + search(node[num].right) + 1;
        }

    }

    private static int[] run() throws Exception {
        int nodeCnt, dgrCnt, num1, num2, node1, node2;
        ArrayList<Integer> num1Parents = new ArrayList<>();
        ArrayList<Integer> num2Parents = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCnt = Integer.parseInt(st.nextToken());
        dgrCnt = Integer.parseInt(st.nextToken());
        num1 = Integer.parseInt(st.nextToken());
        num2 = Integer.parseInt(st.nextToken());
        node = new Node[nodeCnt + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= dgrCnt; i += 1) {
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            if (node[node1] == null) {
                node[node1] = new Node(node1);
            }
            if (node[node2] == null) {
                node[node2] = new Node(node2);
            }
            node[node2].parent = node1;
            if (node[node1].left != -1) {
                node[node1].right = node2;
            } else {
                node[node1].left = node2;
            }
        }

        num1Parents = findDesc(num1);
        num2Parents = findDesc(num2);

        for (int i = 0; i < num1Parents.size(); i += 1) {
            for (int j = 0; j < num2Parents.size(); j += 1) {
                if (num1Parents.get(i).equals(num2Parents.get(j))) {
                    return new int[] { num2Parents.get(j).intValue(), search(num2Parents.get(j).intValue()) };
                }
            }
        }

        return new int[] { 0, 0 };
    }

    public static void main(String args[]) throws Exception {
        int TC;
        int[] result = new int[2];
        System.setIn(new FileInputStream("res/input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());

        for (int i = 1; i <= TC; i += 1) {
            result = run();
            System.out.println("#" + i + " " + result[0] + " " + result[1]);
        }
    }
}

class Node {
    int data;
    int parent;
    int left;
    int right;

    Node(int data) {
        this.data = data;
        this.left = -1;
        this.right = -1;
        this.parent = -1;
    }
}