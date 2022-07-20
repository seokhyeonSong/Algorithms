import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    private final static char INSERT = 'I';
    private final static int DELETE = 'D';
    private final static int ADD = 'A';

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

    private static void addNodesToNum(int pos, int num, Node groupHead, Node groupTail) {
        if (pos == 0) {
            Node tmpHead = head;
            head = groupHead;
            groupTail.next = tmpHead;
            tmpHead.prev = groupTail;
            nodeCnt += num;
            return;
        }

        if (pos == nodeCnt) {
            tail.next = groupHead;
            groupHead.prev = tail;
            tail = groupTail;
            nodeCnt += num;
            return;
        }
        Node tmpNode = head;
        for (int i = 0; i < pos; i += 1) {
            tmpNode = tmpNode.next;
        }
        groupTail.next = tmpNode;
        groupHead.prev = tmpNode.prev;
        tmpNode.prev.next = groupHead;
        tmpNode.prev = groupTail;
        nodeCnt += num;
    }

    private static void removeNode(int pos, int num) {
        Node tmpHead = head;
        for (int i = 1; i < pos; i += 1) {
            tmpHead = tmpHead.next;
        }
        Node tmpNode = tmpHead;
        for (int i = 0; i < num; i += 1) {
            tmpHead = tmpHead.next;
        }
        tmpNode.next = tmpHead.next;
        tmpHead.next.prev = tmpNode;
        nodeCnt -= num;
    }

    private static void addNodesToTail(int num, Node groupHead, Node groupTail) {
        addNodesToNum(nodeCnt, num, groupHead, groupTail);
    }

    private static void run() throws Exception {
        int pos, num;
        char cmd;
        String str;
        StringTokenizer st;

        str = br.readLine();
        int passwordLength = Integer.parseInt(str);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < passwordLength; i += 1) {
            int password = Integer.parseInt(st.nextToken());
            addNodeToTail(password);
        }

        str = br.readLine();
        int commandLength = Integer.parseInt(str);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < commandLength; i += 1) {
            Node groupHead = null;
            Node groupTail = null;
            String tmp = st.nextToken();
            cmd = tmp.charAt(0);

            switch (cmd) {
                case INSERT:
                    pos = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < num; j += 1) {
                        if (j == 0) {
                            groupHead = new Node(Integer.parseInt(st.nextToken()));
                            groupHead.next = null;
                            groupHead.prev = null;
                            groupTail = groupHead;
                        } else {
                            Node tmpNode = groupTail;
                            Node newNode = new Node(Integer.parseInt(st.nextToken()));
                            groupTail = newNode;
                            groupTail.prev = tmpNode;
                            tmpNode.next = newNode;
                        }
                    }
                    addNodesToNum(pos, num, groupHead, groupTail);
                    break;
                case DELETE:
                    pos = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    removeNode(pos, num);
                    break;
                case ADD:
                    num = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < num; j += 1) {
                        if (j == 0) {
                            groupHead = new Node(Integer.parseInt(st.nextToken()));
                            groupHead.next = null;
                            groupHead.prev = null;
                            groupTail = groupHead;
                        } else {
                            Node tmpNode = groupTail;
                            Node newNode = new Node(Integer.parseInt(st.nextToken()));
                            groupTail = newNode;
                            groupTail.prev = tmpNode;
                            tmpNode.next = newNode;
                        }
                    }
                    addNodesToTail(num, groupHead, groupTail);
                    break;
            }

        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream("res/input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i < 11; i += 1) {
            init();
            run();
            System.out.print("#" + i + " ");
            for (int j = 0; j < 10; j += 1) {
                System.out.print(head.data + " ");
                head = head.next;
            }
            System.out.println();
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