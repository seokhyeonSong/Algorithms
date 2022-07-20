class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = null;
        node = new Node[MAX_NODE];
        nodeCnt = 0;
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
            newNode.next = null;
        } else {
            Node tmpNode = head;
            head = newNode;
            if (tmpNode == null) {
                newNode.next = null;
            } else {
                newNode.next = tmpNode;
            }
        }
    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
        } else {
            Node ptr = head;
            while (ptr.next != null) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
        }
        newNode.next = null;
    }

    public void addNode2Num(int data, int num) {
        if (num == 1) {
            addNode2Head(data);
            return;
        }
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
            newNode.next = null;
        } else {
            Node prev = head;
            while (num > 2) {
                prev = prev.next;
                num--;
            }
            Node tmpNode = prev.next;
            prev.next = newNode;
            newNode.next = tmpNode;
        }
    }

    public void removeNode(int data) {
        if (head.data == data) {
            head = head.next;
        } else {
            Node ptr = head;
            while (ptr.next.data != data) {
                ptr = ptr.next;
                if (ptr.next == null) {
                    return;
                }
            }
            ptr.next = ptr.next.next;
        }
        nodeCnt--;
    }

    public int getList(int[] output) {
        Node ptr = head;
        for (int i = 0; i < nodeCnt; i += 1) {
            output[i] = ptr.data;
            ptr = ptr.next;
        }
        return nodeCnt;
    }
}