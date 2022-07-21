import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class BOJ_1260 {
    static boolean[] visited;
    static ArrayList<Integer>[] nodes;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i += 1) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i < M + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            nodes[first].add(second);
            nodes[second].add(first);
        }

        for (int i = 1; i < N + 1; i += 1) {
            Collections.sort(nodes[i]);
        }

        DFS(start);
        bw.write("\n");
        Arrays.fill(visited, false);
        BFS(start);

        bw.flush();
    }

    static void DFS(int num) throws IOException {
        visited[num] = true;
        bw.write(num + " ");
        for (int node : nodes[num]) {
            if (visited[node]) {
                continue;
            } else {
                DFS(node);
            }
        }
    }

    static void BFS(int num) throws IOException {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(num);
        visited[num] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            bw.write(currentNode + " ");
            for (int node : nodes[currentNode]) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
        bw.write("\n");
    }
}