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

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;
    private Node tail;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = null;
        tail = null;
        node = new Node[MAX_NODE];
        nodeCnt = 0;
    }

    public int findNode(int data) {
        Node tmpNode = head;
        int count = 1;
        while (tmpNode.data != data) {
            if (tmpNode.next == null) {
                return -1;
            }
            tmpNode = tmpNode.next;
            count++;
        }
        return count;
    }

    public int getReversedList(int[] output) {
        Node ptr = tail;
        for (int i = 0; i < nodeCnt; i += 1) {
            output[i] = ptr.data;
            ptr = ptr.prev;
        }
        return nodeCnt;
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
            newNode.next = null;
            tail = head;
        } else {
            Node tmpNode = head;
            head = newNode;
            newNode.next = tmpNode;
            tmpNode.prev = newNode;
        }
        newNode.prev = null;
    }

    public void addNode2Tail(int data) {
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

    public void addNode2Num(int data, int num) {
        if (num == 1) {
            addNode2Head(data);
            return;
        } else if (num == nodeCnt + 1) {
            addNode2Tail(data);
            return;
        }
        Node newNode = getNode(data);
        if (head == null) {
            head = newNode;
            tail = head;
            newNode.next = null;
            newNode.prev = null;
        } else {
            Node tmpNode = head;
            while (num > 1) {
                tmpNode = tmpNode.next;
                num--;
            }
            newNode.next = tmpNode;
            tmpNode.prev.next = newNode;
            newNode.prev = tmpNode.prev;
            tmpNode.prev = newNode;
        }
    }

    public void removeNode(int data) {
        if (head.data == data) {
            head = head.next;
            head.prev = null;
        } else if (tail.data == data) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node ptr = head;
            while (ptr.data != data) {
                if (ptr.next == null) {
                    return;
                }
                ptr = ptr.next;
            }
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
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