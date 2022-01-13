// BFS
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == n * n) {
                    return steps;
                }
                for (int j = cur + 1; j <= Math.min(cur + 6, n * n); j++) {
                    int next = getBoardValue(board, j);
                    int dest = next != -1 ? next : j;
                    if (visited[dest]) {
                        continue;
                    }
                    visited[dest] = true;
                    queue.offer(dest);
                }
            }
            steps++;
        }
        return -1;
    }
    
    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int numOfRowsFromBottom = (num - 1) / n;
        int x = n - 1 - numOfRowsFromBottom;
        int y = numOfRowsFromBottom % 2 == 0 ? num - 1 - numOfRowsFromBottom * n : (numOfRowsFromBottom + 1) * n - num;
        return board[x][y];
    }
}
