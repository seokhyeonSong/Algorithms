import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1167 {
    static ArrayList<Tree>[] trees;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        distance = new int[N + 1];
        trees = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i += 1) {
            trees[i] = new ArrayList<>();
        }
        for (int i = 1; i < N + 1; i += 1) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            while (node != -1) {
                int distance = Integer.parseInt(st.nextToken());
                trees[num].add(new Tree(node, distance));
                node = Integer.parseInt(st.nextToken());
            }
        }

        int tempMaxDistance = BFS(1);
        Arrays.fill(distance, 0);
        Arrays.fill(visited, false);
        bw.write(distance[BFS(tempMaxDistance)] + "\n");
        bw.flush();
    }

    static int BFS(int num) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(num);
        visited[num] = true;
        distance[num] = 0;
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (Tree tree : trees[currentNode]) {
                if (!visited[tree.node]) {
                    visited[tree.node] = true;
                    queue.add(tree.node);
                    distance[tree.node] = distance[currentNode] + tree.distance;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int maxNum = -1;
        for (int i = 1; i < distance.length; i += 1) {
            if (distance[i] > max) {
                max = distance[i];
                maxNum = i;
            }
        }
        return maxNum;
    }

    static class Tree {
        public int node;
        public int distance;

        Tree(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}