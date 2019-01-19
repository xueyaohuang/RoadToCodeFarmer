// O(nlg(max-min))
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        
        int len = matrix.length;
        int high = matrix[len - 1][len - 1];
        int low = matrix[0][0];
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = numNoMoreThan(matrix, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;       
    }
    
    private int numNoMoreThan(int[][] matrix, int val) {
        
        int len = matrix.length;
        int i = len - 1;
        int j = 0;
        int res = 0;
        
        while (i >= 0 && j < len) {
            if (matrix[i][j] > val) {
                i--;
            } else {
                res += i + 1;
                j++;
            }
        }        
        return res;
    }
}

// O(mnlgk)
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pq.size() < k) {
                    pq.offer(matrix[i][j]);
                } else {
                    if (matrix[i][j] < pq.peek()) {
                        pq.poll();
                        pq.offer(matrix[i][j]);
                    }
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
