class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int m = rooms.length;
        int n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int distance = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int position = queue.poll();
                int i = position / n;
                int j = position % n;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        if (rooms[x][y] == 2147483647) {
                            rooms[x][y] = distance;
                            queue.offer(x * n + y);
                        }
                    }
                }
            }
            distance++;
        }
    }
}

class Solution {
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> gates = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    gates.offer(new int[]{i, j});
                }
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int distance = 0;
        while (!gates.isEmpty()) {
            int size = gates.size();
            distance++;
            for (int k = 0; k < size; k++) {
                int[] cur = gates.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = distance;
                        gates.offer(new int[]{x, y});
                    }
                }
            }
        }
        
    }
}
