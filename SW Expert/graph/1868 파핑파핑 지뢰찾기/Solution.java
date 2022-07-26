import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    static int[] dx = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    static int click;
    static int boom;
    static char[][] booms;

    public static void checkBoom(int x, int y) {
        int currentX, currentY;
        for (int i = 0; i < 9; i += 1) {
            currentX = x + dx[i];
            currentY = y + dy[i];
            if (currentX < 0 || currentY < 0) {
                continue;
            }
            if (currentX >= boom || currentY >= boom) {
                continue;
            }
            if (booms[currentX][currentY] == '*') {
                continue;
            }
            booms[currentX][currentY] = 'x';
        }
    }

    public static void checkNoBoom(int x, int y) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(x, y));
        click += 1;
        while (!queue.isEmpty()) {
            int currentX, currentY;
            Node currentNode = queue.poll();
            for (int i = 0; i < 9; i += 1) {
                currentX = currentNode.x + dx[i];
                currentY = currentNode.y + dy[i];
                if (currentX < 0 || currentY < 0) {
                    continue;
                }
                if (currentX >= boom || currentY >= boom) {
                    continue;
                }
                if (booms[currentX][currentY] == 'x') {
                    booms[currentX][currentY] = 'o';
                    continue;
                }
                if (booms[currentX][currentY] == '.') {
                    booms[currentX][currentY] = 'o';
                    queue.add(new Node(currentX, currentY));
                    continue;
                }
            }
        }
    }

    public static void checkEachClick(int x, int y) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(new Node(x, y));
        while (!queue.isEmpty()) {
            int currentX, currentY;
            Node currentNode = queue.poll();
            for (int i = 0; i < 9; i += 1) {
                currentX = currentNode.x + dx[i];
                currentY = currentNode.y + dy[i];
                if (currentX < 0 || currentY < 0) {
                    continue;
                }
                if (currentX >= boom || currentY >= boom) {
                    continue;
                }
                if (booms[currentX][currentY] == 'x') {
                    booms[currentX][currentY] = '-';
                    click += 1;
                    queue.add(new Node(currentX, currentY));
                    continue;
                }
            }
        }
    }

    public static void run() {
        click = 0;
        for (int i = 0; i < boom; i += 1) {
            for (int j = 0; j < boom; j += 1) {
                if (booms[i][j] == '*') {
                    checkBoom(i, j);
                }
            }
        }
        for (int i = 0; i < boom; i += 1) {
            for (int j = 0; j < boom; j += 1) {
                if (booms[i][j] == '.') {
                    checkNoBoom(i, j);
                }
            }
        }
        for (int i = 0; i < boom; i += 1) {
            for (int j = 0; j < boom; j += 1) {
                if (booms[i][j] == 'x') {
                    checkEachClick(i, j);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC;
        br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {
            boom = Integer.parseInt(br.readLine());
            booms = new char[boom][boom];
            for (int j = 0; j < boom; j += 1) {
                booms[j] = br.readLine().toCharArray();
            }
            run();
            System.out.println("#" + i + " " + click);
        }
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}