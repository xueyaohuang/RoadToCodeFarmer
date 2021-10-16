/*
The median could minimize the total manhattan distance. 

1. Let's start from the 1-dimension case

Suppose we have n people living on a straight street and they want to find somewhere to meet. The total distance is

sum|xi-m|

where xi is the location of each house and m is the meeting point. To minimize this problem, take the derivative of m. Each term will give you
1, if m > xi
-1, if m < xi
To reach the minimum, the derivative must be 0. To make the derivative 0, the number of 1s and -1s must equal.
If n is even, then m must be located between the middle two locations (Any locations between them will give you the minimum, not necessarily the median).
If n is odd, then m must be located on the middle one house. That's the median.

2. Then we can discuss the 2-dimension case

Let's write down the equation directly.

sum(|xi-m| + |yi-n|)

So this time, we have two variables - m and n. In multiple variables calculus.
To find the minimum, we need to take the partial derivatives for the equation. And each partial derivative (or we can say, each dimension)
will give you the same result as the 1-D case.

3. So we can even solve the problem in any dimension

Because every dimension is independent to each other. Do every dimension as 1-D case.
*/
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                }
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    col.add(j);
                }
            }
        }
        int res = 0;
        // row 和 col的size应该是一样的
        int i = 0, j = row.size() - 1;
        // since the meeting point is the median, we want to calculate (for example we have 6 houses)
        // sum(|xi-m|)= |x1-m|+|x2-m|+|x3-m|+|x4-m|+|x5-m|+|x6-m| = m-x1+m-x2+m-x3+x4-m+x5-m+x6-m=x6-x1+x5-x2+x4-x3
        // so actually we don't need to get the median
        while (i < j) {
            res += row.get(j) - row.get(i);
            res += col.get(j) - col.get(i);
            j--;
            i++;
        }
        return res;
    }
}

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
