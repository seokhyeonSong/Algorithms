import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private final static char INSERT = 'I';
    private final static int DELETE = 'D';
    private final static int CHANGE = 'C';

    private static BufferedReader br;

    private static int nodeCnt = 0;
    private static Node head;
    private static Node tail;

    private static void init() {
        head = null;
        tail = null;
        nodeCnt = 0;
    }

    private static Node getNode(int data) {
        Node newNode = new Node(data);
        nodeCnt++;
        return newNode;
    }

    private static int findNode(int data) {
        Node tmpNode = head;
        if (data >= nodeCnt)
            return -1;
        for (int i = 0; i < data; i += 1) {
            tmpNode = tmpNode.next;
        }
        return tmpNode.data;
    }

    private static void addNodeToTail(int data) {
        Node newNode = getNode(data);
        if (tail == null) {
            tail = newNode;
            newNode.prev = null;
            head = tail;
        } else {
            Node tmpNode = tail;
            tail = newNode;
            tail.prev = tmpNode;
            tmpNode.next = newNode;
        }
        newNode.next = null;
    }

    private static void addNodeToNum(int pos, int num) {
        if (pos == 0) {
            Node tmpHead = head;
            head = getNode(num);
            head.next = tmpHead;
            tmpHead.prev = head;
            return;
        }
        if (pos == nodeCnt) {
            Node tmpTail = tail;
            tail = getNode(num);
            tail.prev = tmpTail;
            tmpTail.next = tail;
            return;
        }
        Node newNode = getNode(num);
        Node tmpNode = head;
        for (int i = 0; i < pos - 1; i += 1) {
            tmpNode = tmpNode.next;
        }
        newNode.next = tmpNode.next;
        newNode.prev = tmpNode;
        tmpNode.next.prev = newNode;
        tmpNode.next = newNode;
    }

    private static void removeNode(int pos) {
        Node tmpNode = head;
        for (int i = 0; i < pos; i += 1) {
            tmpNode = tmpNode.next;
        }
        if (pos == 0) {
            tmpNode.next.prev = null;
            head = tmpNode.next;
        } else if (pos == nodeCnt - 1) {
            tmpNode.prev.next = null;
            tail = tmpNode.prev;
        } else {
            tmpNode.next.prev = tmpNode.prev;
            tmpNode.prev.next = tmpNode.next;
        }
        nodeCnt--;
        return;
    }

    private static void changeNode(int pos, int num) {
        Node tmpNode = head;
        for (int i = 0; i < pos; i += 1) {
            tmpNode = tmpNode.next;
        }
        tmpNode.data = num;
        return;
    }

    private static int run() throws Exception {
        int cnt, cmdCnt, findingNum, pos, num;
        char cmd;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        cnt = Integer.parseInt(st.nextToken());
        cmdCnt = Integer.parseInt(st.nextToken());
        findingNum = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < cnt; i += 1) {
            int nodeNum = Integer.parseInt(st.nextToken());
            addNodeToTail(nodeNum);
        }

        for (int i = 0; i < cmdCnt; i += 1) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();
            cmd = tmp.charAt(0);

            switch (cmd) {
                case INSERT:
                    pos = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    addNodeToNum(pos, num);
                    break;
                case DELETE:
                    pos = Integer.parseInt(st.nextToken());
                    removeNode(pos);
                    break;
                case CHANGE:
                    pos = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    changeNode(pos, num);
                    break;
            }
        }
        return findNode(findingNum);
    }

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int i = 1; i <= TC; i += 1) {
            init();
            int num = run();
            System.out.println("#" + i + " " + num);
        }
    }
}

class Node {
    public int data;
    public Node next;
    public Node prev;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}