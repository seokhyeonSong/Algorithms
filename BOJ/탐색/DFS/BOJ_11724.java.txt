import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class BOJ_11724 {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        int linkedListCount = 0;
        visited = new boolean[node + 1];
        graph = new ArrayList[node + 1];

        for (int i = 1; i < node + 1; i += 1) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i < edge + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            graph[firstNode].add(secondNode);
            graph[secondNode].add(firstNode);
        }

        for (int i = 1; i < node + 1; i += 1) {
            if (!visited[i]) {
                linkedListCount++;
                DFS(i);
            }
        }
        bw.write(linkedListCount + "\n");
        bw.flush();
    }

    static void DFS(int node) {
        visited[node] = true;
        for (int thisNode : graph[node]) {
            if (!visited[thisNode]) {
                DFS(thisNode);
            }
        }
    }
}