import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    static int cnt;
    static int[][] parent;
    static Node[] nodes;
    static Queue<Integer> BFSqueue;

    public static long run(int maxDepth) {
        initDepth(maxDepth);
        return getDepth();
    }

    public static void initDepth(int maxDepth) {
        int k = 0, temp = 1;
        while (temp <= maxDepth) {
            temp <<= 1;
            k += 1;
        }
        parent = new int[k + 1][cnt + 1];
        for (int i = 1; i <= cnt; i += 1) {
            parent[0][i] = nodes[i].parent;
        }

        for (int i = 1; i < parent.length; i += 1) {
            for (int j = 1; j <= cnt; j += 1) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];
            }
        }
    }

    public static long getDepth() {
        long depthSum = 0;
        int currentNode = -1, previousNode = -1;

        BFSqueue = new LinkedList<>();
        BFSqueue.add(1);

        while (!BFSqueue.isEmpty()) {
            currentNode = BFSqueue.poll();
            if (previousNode != -1) {
                depthSum += getCommonDepth(currentNode, previousNode);
            }
            previousNode = currentNode;
            for (int i = 0; i < nodes[currentNode].child.size(); i += 1) {
                BFSqueue.add(nodes[currentNode].child.get(i));
            }
        }

        return depthSum;
    }

    public static long getCommonDepth(int x, int y) {
        long commonDepth = 0;

        if (nodes[x].depth < nodes[y].depth) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for (int i = parent.length - 1; i >= 0; i -= 1) {
            if (Math.pow(2, i) <= nodes[x].depth - nodes[y].depth) {
                x = parent[i][x];
                commonDepth += Math.pow(2, i);
            }
        }

        if (x == y)
            return commonDepth;

        for (int i = parent.length - 1; i >= 0; i -= 1) {
            if (parent[i][x] != parent[i][y]) {
                commonDepth += Math.pow(2, i + 1);
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        commonDepth += 2;
        return commonDepth;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC, maxDepth;
        br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {
            maxDepth = 0;

            cnt = Integer.parseInt(br.readLine());

            nodes = new Node[cnt + 1];
            for (int j = 1; j <= cnt; j += 1) {
                nodes[j] = new Node();
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 2; j <= cnt; j += 1) {
                int parent = Integer.parseInt(st.nextToken());
                nodes[j].parent = parent;
                nodes[j].depth = nodes[parent].depth + 1;
                if (maxDepth < nodes[j].depth) {
                    maxDepth = nodes[j].depth;
                }
                nodes[parent].child.add(j);
            }

            System.out.println("#" + i + " " + run(maxDepth));
        }
    }
}

class Node {
    int parent;
    ArrayList<Integer> child = new ArrayList<Integer>();
    int depth;
}