public class UserSolution {
    private static King[] kings;

    public void dfs_init(int N, int[][] path) {
        kings = new King[101];
        for (int i = 1; i < kings.length; i += 1) {
            kings[i] = new King();
        }

        for (int i = 0; i < N - 1; i += 1) {
            int king = path[i][0];
            for (int j = 1; j < 2; j += 1) {
                kings[king].add(path[i][j]);
            }
        }
    }

    public int dfs(int N) {
        Stack stack = new Stack();
        stack.push(N);
        while (stack.currentIndex > 0) {
            int currentKing = stack.pop();
            if (currentKing > N)
                return currentKing;
            int[] currentKings = kings[currentKing].king;
            for (int i = currentKings.length - 1; i >= 0; i -= 1) {
                stack.push(currentKings[i]);
            }
        }
        return -1;
    }
}

class Stack {
    int currentIndex = 0;
    int[] numbers = new int[100];

    public void push(int num) {
        numbers[currentIndex] = num;
        currentIndex++;
    }

    public int pop() {
        currentIndex--;
        return numbers[currentIndex];
    }
}

class King {
    int[] king = new int[0];

    public void add(int num) {
        if (king == null) {
            king = new int[1];
            king[0] = num;
        } else {
            int kidsLength = king.length;
            int[] tmpKids = new int[kidsLength];
            for (int i = 0; i < kidsLength; i += 1) {
                tmpKids[i] = king[i];
            }
            king = new int[kidsLength + 1];
            for (int i = 0; i < kidsLength; i += 1) {
                king[i] = tmpKids[i];
            }
            king[kidsLength] = num;
        }
    }
}