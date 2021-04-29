// O(nlg(max-min))
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1];
        while (start < end) {
            int mid = (start + end) / 2;
            if (countNoMoreThan(matrix, mid) < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    
    private int countNoMoreThan(int[][] matrix, int val) {
        int n = matrix.length;
        int i = n - 1, j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) {
                i--;
            } else {
                count += i + 1;
                j++;
            }
        }
        return count;
    }
}

// O(mnlgk)
// 最大堆
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pq.size() < k) {
                    pq.offer(matrix[i][j]);
                } else if (matrix[i][j] < pq.peek()) {
                    pq.poll();
                    pq.offer(matrix[i][j]);
                }
            }
        }
        return pq.peek();
    }
}

// O(klgn)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{matrix[0][j], 0, j});
        }
        
        // poll k-1个最小的，最后peek一下就是第k小的
        for (int i = 0; i < k - 1; i++) {
            int[] cur = pq.poll();
            if (cur[1] != m - 1) {
                pq.offer(new int[]{matrix[cur[1] + 1][cur[2]], cur[1] + 1, cur[2]});
            }
        }
        return pq.peek()[0];
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        
        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{matrix[0][j], 0, j});
        }
        
        for (int i = 0; i < k - 1; i++) {
            int[] cur = pq.poll();
            if (cur[1] != m - 1) {
                pq.offer(new int[]{matrix[cur[1] + 1][cur[2]], cur[1] + 1, cur[2]});
            }
        }
        return pq.peek()[0];
    }
}
