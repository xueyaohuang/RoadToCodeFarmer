class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int start = getMax(weights);
        int end = getSum(weights);
        while (start < end) {
            int mid = (start + end) / 2;
            if (canShip(weights, days, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private int getSum(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res += n;
        }
        return res;
    }
    
    private int getMax(int[] nums) {
        int res = Integer.MIN_VALUE;
        for (int n : nums) {
            res = Math.max(res, n);
        }
        return res;
    }
    
    private boolean canShip(int[] weights, int days, int cap) {
        int count = 1;
        int cur = 0;
        for (int w : weights) {
            if (cur + w > cap) {
                cur = w;
                count++;
            } else {
                cur += w;
            }
            if (count > days) {
                return false;
            }
        }
        return count <= days;
    }
}

class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        for (int w : weights) {
            sum += w;
        }
        int start = sum / D;
        int end = sum;
        while (start < end) {
            int mid = (start + end) / 2;
            if (canFinish(weights, D, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean canFinish(int[] weights, int D, int cap) {
        int count = 0;
        int left = 0;
        for (int w : weights) {
            if (w > cap) {
                return false;
            }
            if (w > left) {
                left = cap;
                count++;
            }
            left -= w;
        }
        return count <= D;
    }
}
