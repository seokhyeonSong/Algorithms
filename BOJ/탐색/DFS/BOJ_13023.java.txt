import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 13023 {
    static boolean isFriend;
    static boolean[] visited;
    static ArrayList<Integer>[] node;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        node = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i += 1) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i += 1) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            node[first].add(second);
            node[second].add(first);
        }

        isFriend = false;

        for (int i = 0; i < N; i += 1) {
            if (isFriend)
                break;
            else
                DFS(i, 1);
        }
        if (isFriend)
            bw.write(1 + "\n");
        else
            bw.write(0 + "\n");
        bw.flush();
    }

    static void DFS(int num, int count) {
        if (count == 5 || isFriend) {
            isFriend = true;
            return;
        }
        visited[num] = true;
        for (int nodes : node[num]) {
            if (!visited[nodes]) {
                DFS(nodes, count + 1);
            }
        }
        visited[num] = false;
    }
}