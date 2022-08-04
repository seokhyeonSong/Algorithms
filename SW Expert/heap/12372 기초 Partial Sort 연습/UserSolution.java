public class UserSolution {
    final private int MAX_SIZE = 100000;
    int heapCnt;
    User[] heap;

    public void init() {
        heap = new User[MAX_SIZE + 1];
        heapCnt = 1;
    }

    public boolean compare(User user1, User user2) {
        if (user1.income > user2.income) {
            return true;
        } else if (user1.income < user2.income) {
            return false;
        } else {
            if (user1.uID > user2.uID) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void addUser(int uID, int income) {
        heap[heapCnt] = new User(uID, income);
        int parent = heapCnt / 2;
        int current = heapCnt;
        while (parent >= 1) {
            if (!compare(heap[parent], heap[current])) {
                swap(parent, current, heap);
                current = parent;
                parent = current / 2;
            } else {
                break;
            }
        }
        heapCnt += 1;
    }

    public User pop() {
        User popUser = heap[1];
        heap[1] = heap[heapCnt - 1];

        int current = 1;
        while (current * 2 + 1 < heapCnt) {
            if (current * 2 + 1 >= heapCnt) {
                if (!compare(heap[current], heap[current * 2])) {
                    swap(current, current * 2, heap);
                    current = current * 2;
                } else {
                    break;
                }
            } else {
                if (compare(heap[current * 2], heap[current * 2 + 1])) {
                    if (!compare(heap[current], heap[current * 2])) {
                        swap(current, current * 2, heap);
                        current = current * 2;
                    } else {
                        break;
                    }
                } else {
                    if (!compare(heap[current], heap[current * 2 + 1])) {
                        swap(current, current * 2 + 1, heap);
                        current = current * 2 + 1;
                    } else {
                        break;
                    }
                }
            }
        }
        heapCnt -= 1;
        return popUser;
    }

    public void swap(int user1, int user2, User[] user) {
        User tmp = user[user1];
        user[user1] = user[user2];
        user[user2] = tmp;
    }

    int getTop10(int[] result) {
        User[] user = new User[10];
        int i;
        for (i = 0; i < 10; i += 1) {
            user[i] = pop();
            result[i] = user[i].uID;
            if (heapCnt <= 1) {
                i += 1;
                break;
            }
        }
        for (int j = 0; j < i; j += 1) {
            addUser(user[j].uID, user[j].income);
        }
        return i;
    }

    class User {
        int uID, income;

        User(int uID, int income) {
            this.uID = uID;
            this.income = income;
        }
    }
}
