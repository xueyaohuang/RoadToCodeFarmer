class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        // 因为每次最多吃一个pile，所以更多也没用
        int end = getMax(piles);
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (canFinish(piles, h, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for (int p : piles) {
            if (p % k == 0) {
                hours += p / k;
            } else {
                hours += p / k + 1;
            }
            if (hours > h) {
                return false;
            }
        }
        return hours <= h;
    }
    
    private int getMax(int[] piles) {
        int res = 0;
        for (int p : piles) {
            res = Math.max(res, p);
        }
        return res;
    }
}
