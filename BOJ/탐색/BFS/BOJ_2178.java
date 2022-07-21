import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

public class BOJ_2178 {
    static ArrayList<Integer>[] nodes;
    static boolean[][] visited;
    static boolean[][] maze;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        maze = new boolean[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int i = 1; i < N + 1; i += 1) {
            String line = br.readLine();
            for (int j = 1; j < M + 1; j += 1) {
                int num = Integer.parseInt(line.substring(j - 1, j));
                visited[i][j] = num == 1 ? false : true;
            }
        }
        bw.write(explore(N, M) + "\n");
        bw.flush();
    }

    static int explore(int maxY, int maxX) {
        Queue<Coord> queue = new LinkedList<Coord>();
        int x = 1, y = 1, level = 1;
        visited[y][x] = true;
        queue.add(new Coord(y, x, level));
        while (y != maxY || x != maxX) {
            Coord currentCoord = queue.poll();
            x = currentCoord.x;
            y = currentCoord.y;
            level = currentCoord.level;
            if (y != 1) {
                if (!visited[y - 1][x]) {
                    queue.add(new Coord(y - 1, x, level + 1));
                    visited[y - 1][x] = true;
                }
            }
            if (x != 1) {
                if (!visited[y][x - 1]) {
                    queue.add(new Coord(y, x - 1, level + 1));
                    visited[y][x - 1] = true;
                }
            }
            if (y != maxY) {
                if (!visited[y + 1][x]) {
                    queue.add(new Coord(y + 1, x, level + 1));
                    visited[y + 1][x] = true;
                }
            }
            if (x != maxX) {
                if (!visited[y][x + 1]) {
                    queue.add(new Coord(y, x + 1, level + 1));
                    visited[y][x + 1] = true;
                }
            }
        }
        return level;
    }

    static class Coord {
        public int y;
        public int x;
        public int level;

        Coord(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }
}