class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        
        int[] rows = new int[row.size()];
        int[] cols = new int[col.size()];
        
        for (int i = 0; i < rows.length; i++) {
            rows[i] = row.get(i);
        }
        for (int i = 0; i < cols.length; i++) {
            cols[i] = col.get(i);
        }

        
        int rowMedian = randomQuickSelect(rows, 0, rows.length - 1, rows.length / 2);
        int colMedian = randomQuickSelect(cols, 0, cols.length - 1, cols.length / 2);

        
        int res = 0;
        for (int i = 0; i < rows.length; i++) {
            res += Math.abs(rows[i] - rowMedian);
        }
        for (int i = 0; i < cols.length; i++) {
            res += Math.abs(cols[i] - colMedian);
        }
        
        return res;
    }
    
    private int randomQuickSelect(int[] nums, int start, int end, int k) {
        Random rand = new Random();
        int pivotIdx = rand.nextInt(end - start + 1) + start;
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        int left = start;
        
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums, left, i);
                left++;
            }
        }
        
        swap(nums, left, end);
        
        if (left == k) {
            return nums[k];
        } else if (left < k) {
            return randomQuickSelect(nums, left + 1, end, k);
        } else {
            return randomQuickSelect(nums, start, left - 1, k);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
