class UserSolution {
    private int[][] map;
    private int[][] distance;
    private boolean[][] visited;
    private int mapSize;
    private int[] dx = { 0, 1, 0, -1 };
    private int[] dy = { 1, 0, -1, 0 };

    int findMin(int x, int y) {
        if (x > y)
            return x;
        return y;
    }

    void bfs_init(int map_size, int map[][]) {
        mapSize = map_size;
        this.map = new int[map_size + 1][map_size + 1];
        distance = new int[map_size + 1][map_size + 1];
        visited = new boolean[map_size + 1][map_size + 1];

        for (int i = 1; i <= map_size; i += 1) {
            for (int j = 1; j <= map_size; j += 1) {
                this.map[i][j] = map[i - 1][j - 1];
                distance[i][j] = 0;
            }
        }
    }

    int bfs(int x1, int y1, int x2, int y2) {
        Queue queue = new Queue(mapSize);
        for (int i = 1; i <= mapSize; i += 1) {
            for (int j = 1; j <= mapSize; j += 1) {
                distance[i][j] = 0;
                visited[i][j] = false;
            }
        }
        queue.enqueue(x1, y1);
        while (!queue.isQueueEmpty()) {
            Coord currentCoord = queue.dequeue();
            if (currentCoord.x == x2 && currentCoord.y == y2) {
                return distance[y2][x2];
            }
            for (int i = 0; i < 4; i += 1) {
                int x = currentCoord.x + dx[i];
                int y = currentCoord.y + dy[i];
                boolean coordCondition = x < mapSize + 1 && x > 0 && y < mapSize + 1 && y > 0;
                if (coordCondition && !visited[y][x] && map[y][x] != 1) {
                    if (distance[y][x] == 0) {
                        distance[y][x] = distance[currentCoord.y][currentCoord.x] + 1;
                    } else {
                        distance[y][x] = findMin(distance[currentCoord.y][currentCoord.x] + 1, distance[y][x]);
                    }
                    visited[y][x] = true;
                    queue.enqueue(x, y);
                }
            }
        }
        return -1;
    }
}

class Queue {
    int front;
    int rear;
    Coord[] coords;

    Queue(int size) {
        coords = new Coord[size * size + 1];
        front = 0;
        rear = 0;
    }

    public void enqueue(int x, int y) {
        coords[rear] = new Coord(x, y);
        rear++;
    }

    public Coord dequeue() {
        return coords[front++];
    }

    public boolean isQueueEmpty() {
        return front == rear;
    }
}

class Coord {
    int x, y;

    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}