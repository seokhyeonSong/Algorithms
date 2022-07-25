import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;

public class Solution {

    static final String UP = "up";
    static final String DOWN = "down";
    static final String RIGHT = "right";
    static final String LEFT = "left";

    static int[][] cell;
    static int size;
    static Stack<Maxi> stack;
    static ArrayList<Node> cores;
    static long min;

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        int TC;
        br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {
            size = Integer.parseInt(br.readLine());
            cell = new int[size + 1][size + 1];
            for (int j = 1; j <= size; j += 1) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= size; k += 1) {
                    cell[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#" + i + " " + run());
        }
    }

    public static long run() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
        cores = new ArrayList<Node>();
        for (int i = 2; i < cell.length; i += 1) {
            for (int j = 2; j < cell.length; j += 1) {
                if (cell[i][j] == 1 && checkLine(i, j) != "") {
                    cores.add(new Node(i, j));
                }
            }
        }
        DFS(0);
        return min;
    }

    public static void DFS(int i) {
        if (i == cores.size()) {
            int cnt = 0;
            for (int j = 1; j <= size; j += 1) {
                for (int k = 1; k <= size; k += 1) {
                    if (cell[j][k] == 2) {
                        cnt += 1;
                    }
                }
            }
            if (min > cnt) {
                min = cnt;
            }
            return;
        }
        Node core = cores.get(i);
        String directString = checkLine(core.x, core.y).trim();
        if (directString.length() > 0) {
            String[] availDirec = directString.split(" ");
            for (int k = 0; k < availDirec.length; k += 1) {
                Maxi currentMaxi = new Maxi(core, availDirec[k]);
                fillLine(currentMaxi);
                DFS(i + 1);
                removeLine(currentMaxi);
            }
        } else {
            return;
        }
    }

    public static void fillLine(Maxi maxi) {
        cell[maxi.node.x][maxi.node.y] = 3;
        switch (maxi.direction) {
            case UP:
                for (int i = maxi.node.x - 1; i > 0; i -= 1) {
                    cell[i][maxi.node.y] = 2;
                }
                break;
            case DOWN:
                for (int i = maxi.node.x + 1; i <= size; i += 1) {
                    cell[i][maxi.node.y] = 2;
                }
                break;
            case RIGHT:
                for (int i = maxi.node.y + 1; i <= size; i += 1) {
                    cell[maxi.node.x][i] = 2;
                }
                break;
            case LEFT:
                for (int i = maxi.node.y - 1; i > 0; i -= 1) {
                    cell[maxi.node.x][i] = 2;
                }
                break;
        }
    }

    public static void removeLine(Maxi maxi) {
        cell[maxi.node.x][maxi.node.y] = 1;
        switch (maxi.direction) {
            case UP:
                for (int i = maxi.node.x - 1; i > 0; i -= 1) {
                    if (cell[i][maxi.node.y] == 2) {
                        cell[i][maxi.node.y] = 0;
                    }
                }
                return;
            case DOWN:
                for (int i = maxi.node.x + 1; i <= size; i += 1) {
                    if (cell[i][maxi.node.y] == 2) {
                        cell[i][maxi.node.y] = 0;
                    }
                }
                return;
            case RIGHT:
                for (int i = maxi.node.y + 1; i <= size; i += 1) {
                    if (cell[maxi.node.x][i] == 2) {
                        cell[maxi.node.x][i] = 0;
                    }
                }
                return;
            case LEFT:
                for (int i = maxi.node.y - 1; i > 0; i -= 1) {
                    if (cell[maxi.node.x][i] == 2) {
                        cell[maxi.node.x][i] = 0;
                    }
                }
        }
    }

    public static String checkLine(int x, int y) {
        String direc = "";
        boolean check = false;

        check = false;
        for (int i = x - 1; i > 0; i -= 1) {
            if (cell[i][y] != 0) {
                check = true;
                break;
            }
        }
        if (!check)
            direc = direc + UP + " ";
        check = false;
        for (int i = x + 1; i <= size; i += 1) {
            if (cell[i][y] != 0) {
                check = true;
                break;
            }
        }
        if (!check)
            direc = direc + DOWN + " ";
        check = false;
        for (int i = y + 1; i <= size; i += 1) {
            if (cell[x][i] != 0) {
                check = true;
                break;
            }
        }
        if (!check)
            direc = direc + RIGHT + " ";
        check = false;
        for (int i = y - 1; i > 0; i -= 1) {
            if (cell[x][i] != 0) {
                check = true;
                break;
            }
        }
        if (!check)
            direc = direc + LEFT;
        return direc;
    }
}

class Maxi {
    Node node;
    String direction;

    Maxi(Node node, String direction) {
        this.node = node;
        this.direction = direction;
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}