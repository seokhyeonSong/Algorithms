import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class Solution {
    private static BufferedWriter bw;

    private static int nodeCnt;
    private static int edgeCnt;
    private static ArrayList<Integer>[] nodes;
    private static Queue<Integer> BFSNode;
    private static int[] DFSNode;
    private static boolean[] visited;

    private static void BFS(int start) throws Exception {
        BFSNode.add(start);
        visited[start] = true;
        while (!BFSNode.isEmpty()) {
            int currentNode = BFSNode.poll();
            bw.write(currentNode + " ");
            for (int node : nodes[currentNode]) {
                if (!visited[node]) {
                    visited[node] = true;
                    BFSNode.add(node);
                }
            }
        }
        bw.write("\n");
    }

    private static void DFS(int start) throws Exception {
        visited[start] = true;
        bw.write(start + " ");
        for (int node : nodes[start]) {
            if (!visited[node]) {
                DFS(node);
            }
        }
    }

    public static void main(String args[]) throws Exception {
        int start, firstNode, secondNode;

        System.setIn(new java.io.FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        nodeCnt = Integer.parseInt(st.nextToken());
        edgeCnt = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        BFSNode = new LinkedList<Integer>();
        DFSNode = new int[nodeCnt + 1];
        visited = new boolean[nodeCnt + 1];
        nodes = new ArrayList[nodeCnt + 1];

        for (int i = 1; i < nodeCnt + 1; i += 1) {
            nodes[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < edgeCnt + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            firstNode = Integer.parseInt(st.nextToken());
            secondNode = Integer.parseInt(st.nextToken());
            nodes[firstNode].add(secondNode);
            nodes[secondNode].add(firstNode);
        }
        DFS(start);
        bw.write("\n");
        visited = new boolean[nodeCnt + 1];
        BFS(start);
        bw.flush();

    }
}